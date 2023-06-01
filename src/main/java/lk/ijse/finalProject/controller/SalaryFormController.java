package lk.ijse.finalProject.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.dto.Salary;
import lk.ijse.finalProject.dto.tm.BookTM;
import lk.ijse.finalProject.dto.tm.SalaryTM;
import lk.ijse.finalProject.model.SalaryModel;
import lk.ijse.finalProject.util.AlertController;
import lk.ijse.finalProject.util.DataValidateController;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class SalaryFormController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colBonus;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SalaryTM> tblSalary;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtBonus;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtId;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            String sal_id = txtId.getText();
            Double bonus = Double.valueOf(txtBonus.getText());
            String date = txtDate.getText();
            Double amount = Double.valueOf(txtAmount.getText());

            if (sal_id.isEmpty() || txtBonus.getText().isEmpty() || date.isEmpty() || txtAmount.getText().isEmpty()) {
                AlertController.errormessage("Salary details not saved.\nPlease make sure to fill all the required fields ");
            } else {
                Salary salary = new Salary(sal_id, bonus, date, amount);

                boolean isAdded = SalaryModel.save(salary);
                if (isAdded) {
                    AlertController.confirmmessage("Salary Details Added Successfully");
                    txtId.setText("");
                    txtBonus.setText("");
                    txtDate.setText("");
                    txtAmount.setText("");

                    getAll();

                    txtId.setStyle("-fx-border-color : transparent");
                    txtBonus.setStyle("-fx-border-color : transparent");
                    txtDate.setStyle("-fx-border-color : transparent");
                    txtAmount.setStyle("-fx-border-color : transparent");

                    btnAdd.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);

                } else {
                    AlertController.errormessage("Something went wrong");
                }
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            AlertController.errormessage("Duplicate Salary ID");
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something went wrong");
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/HomePage_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String salary_id = txtId.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this salary details from the system?");
        if(result==true) {

            try {
                boolean isDeleted = SalaryModel.delete(salary_id);
                if (isDeleted) {
                    AlertController.confirmmessage("Salary Details Removed Successfully");
                    txtId.setText("");
                    txtBonus.setText("");
                    txtDate.setText("");
                    txtAmount.setText("");

                    btnAdd.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);

                    getAll();
                } else{
                    AlertController.errormessage("No Book ID Selected");
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this salary details?");
        if(result==true) {

            try {
                String sal_id = txtId.getText();
                Double bonus = Double.valueOf(txtBonus.getText());
                String date = txtDate.getText();
                Double amount = Double.valueOf(txtAmount.getText());

                if(sal_id.isEmpty() || txtBonus.getText().isEmpty() || date.isEmpty() || txtAmount.getText().isEmpty()) {
                    AlertController.errormessage("Salary details not saved.\nPlease make sure to fill all the required fields ");
                }else{
                    Salary salary = new Salary(sal_id, bonus, date, amount);

                    boolean isUpdated = SalaryModel.update(salary);
                    if (isUpdated) {
                        AlertController.confirmmessage("Salary Details Updated");
                        txtId.setText("");
                        txtBonus.setText("");
                        txtDate.setText("");
                        txtAmount.setText("");

                        getAll();

                        txtId.setStyle("-fx-border-color : transparent");
                        txtBonus.setStyle("-fx-border-color : transparent");
                        txtDate.setStyle("-fx-border-color : transparent");
                        txtAmount.setStyle("-fx-border-color : transparent");

                        btnAdd.setDisable(true);
                        btnUpdate.setDisable(true);
                        btnDelete.setDisable(true);
                    }
                }
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println(e);
                AlertController.errormessage(e.getMessage());
            }catch (Exception e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    private void getAll(){
        ObservableList<SalaryTM> obList = null;
        try {
            obList = SalaryModel.getAll();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        tblSalary.setItems(obList);
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("salary_id"));
        colBonus.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        setCellValueFactory();
    }

    public void tblSalaryOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblSalary.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<SalaryTM, ?>> columns = tblSalary.getColumns();

        txtId.setText(columns.get(0).getCellData(row).toString());
        txtBonus.setText(columns.get(1).getCellData(row).toString());
        txtDate.setText(columns.get(2).getCellData(row).toString());
        txtAmount.setText(columns.get(3).getCellData(row).toString());

        btnDelete.setDisable(false);
    }

    public void txtValiid(KeyEvent keyEvent) {
        try {
            String id = txtId.getText();
            boolean isValidate = DataValidateController.salaryIdValidate(id);
            if (isValidate) {
                txtId.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );

            } else {
                txtId.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );

            }
        }catch (Exception e){}
    }

    public void txtValiBonus(KeyEvent keyEvent) {
        try {
            String id = txtBonus.getText();
            boolean isValidate = DataValidateController.priceValidate(id);
            if (isValidate) {
                txtBonus.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );

            } else {
                txtBonus.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtDate.getText().isEmpty() | txtAmount.getText().isEmpty() );

            }
        }catch (Exception e){}
    }

    public void txtValiDate(KeyEvent keyEvent) {
        try {
            String id = txtDate.getText();
            boolean isValidate = DataValidateController.dateCheck(id);
            if (isValidate) {
                txtDate.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtAmount.getText().isEmpty() );

            } else {
                txtDate.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtAmount.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtAmount.getText().isEmpty() );

            }
        }catch (Exception e){}
    }

    public void txtValiAmount(KeyEvent keyEvent) {
        try {
            String id = txtAmount.getText();
            boolean isValidate = DataValidateController.priceValidate(id);
            if (isValidate) {
                txtAmount.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() );

            } else {
                txtAmount.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtBonus.getText().isEmpty() | txtDate.getText().isEmpty() );

            }
        }catch (Exception e){}
    }
}

