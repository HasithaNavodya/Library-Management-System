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
import lk.ijse.finalProject.bo.BoFactory;
import lk.ijse.finalProject.bo.custom.impl.EmployeeBOImpl;
import lk.ijse.finalProject.dto.EmployeeDTO;
import lk.ijse.finalProject.view.tdm.EmployeeTM;
import lk.ijse.finalProject.model.EmployeeModel;
import lk.ijse.finalProject.util.AlertController;
import lk.ijse.finalProject.util.DataValidateController;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EmployeeFormController implements Initializable {


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmployeeTM> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNo;

    @FXML
    private TextField txtState;

    EmployeeBOImpl employeeBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.EMPLOYEE_BO);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        setCellValueFactory();
        generateNextEmployeeId();
    }

    private void getAll() {
        tblEmployee.getItems().clear();
        try {
            ArrayList<EmployeeDTO> allEmployee = employeeBO.getAllEmployee();
            for (EmployeeDTO e : allEmployee) {
                tblEmployee.getItems().add(new EmployeeTM(e.getEmployee_id(), e.getEmployee_name(), e.getStatus(), e.getAddress(),e.getContact_no()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void generateNextEmployeeId(){
        try {
            String id = employeeBO.getNextEmployeeId();
            txtId.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }


    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("employee_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("employee_name"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact_no"));
    }



    public void btnAddOnAction(ActionEvent actionEvent) {
        try {
            String emp_id = txtId.getText();
            String emp_name = txtName.getText();
            String status = txtState.getText();
            String address = txtAddress.getText();
            String contact = txtNo.getText();

            if (emp_id.isEmpty() || emp_name.isEmpty() || status.isEmpty() || address.isEmpty() || contact.isEmpty()) {
                AlertController.errormessage("Employee details not saved.\nPlease make sure to fill all the required fields ");
            } else {
                EmployeeDTO employeeDTO = new EmployeeDTO(emp_id, emp_name, status, address, contact);

                boolean isAdded = employeeBO.saveEmployee(employeeDTO);
                if (isAdded) {
                    AlertController.confirmmessage("Employee Added Successfully");
                    txtId.setText("");
                    txtName.setText("");
                    txtState.setText("");
                    txtAddress.setText("");
                    txtNo.setText("");

                    getAll();
                    txtId.setStyle("-fx-border-color : transparent");
                    txtName.setStyle("-fx-border-color : transparent");
                    txtState.setStyle("-fx-border-color : transparent");
                    txtAddress.setStyle("-fx-border-color : transparent");
                    txtNo.setStyle("-fx-border-color : transparent");

                    btnAdd.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);

                } else {
                    AlertController.errormessage("Something went wrong");
                }
            }
        }catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(e);
            AlertController.errormessage("Duplicate Employee ID");
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something went wrong");
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String employee_id = txtId.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this employee from the system?");
        if(result==true) {

            try {
                boolean isDeleted = employeeBO.deleteEmployee(employee_id);
                if (isDeleted) {
                    AlertController.confirmmessage("Employee Removed Successfully");
                    txtId.setText("");
                    txtName.setText("");
                    txtState.setText("");
                    txtAddress.setText("");
                    txtNo.setText("");

                    btnAdd.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);

                    getAll();
                } else{
                    AlertController.errormessage("No Employee ID Selected");
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this employee's details?");
        if(result==true) {

            try {
                String emp_id = txtId.getText();
                String emp_name = txtName.getText();
                String status = txtState.getText();
                String address = txtAddress.getText();
                String contact = txtNo.getText();

                if(emp_id.isEmpty() || emp_name.isEmpty() || status.isEmpty() || address.isEmpty() || contact.isEmpty()) {
                    AlertController.errormessage("Employee details not saved.\nPlease make sure to fill all the required fields ");
                }else{
                    EmployeeDTO employeeDTO = new EmployeeDTO(emp_id, emp_name, status, address, contact);

                    boolean isUpdated = employeeBO.updateEmployee(employeeDTO);
                    if (isUpdated) {
                        AlertController.confirmmessage("Employee Details Updated");
                        txtId.setText("");
                        txtName.setText("");
                        txtState.setText("");
                        txtAddress.setText("");
                        txtNo.setText("");

                        getAll();
                        txtId.setStyle("-fx-border-color : transparent");
                        txtName.setStyle("-fx-border-color : transparent");
                        txtState.setStyle("-fx-border-color : transparent");
                        txtAddress.setStyle("-fx-border-color : transparent");
                        txtNo.setStyle("-fx-border-color : transparent");

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

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/HomePage_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }



    public void tblEmployeeOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblEmployee.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<EmployeeTM, ?>> columns = tblEmployee.getColumns();

        txtId.setText(columns.get(0).getCellData(row).toString());
        txtName.setText(columns.get(1).getCellData(row).toString());
        txtState.setText(columns.get(2).getCellData(row).toString());
        txtAddress.setText(columns.get(3).getCellData(row).toString());
        txtNo.setText(columns.get(4).getCellData(row).toString());

        btnDelete.setDisable(false);
    }

    public void txtValiId(KeyEvent keyEvent) {
        try {
            String id = txtId.getText();
            boolean isValidate = DataValidateController.empIdValidate(id);
            if (isValidate) {
                txtId.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtName.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtName.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnDelete.setDisable(txtName.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());

            } else {
                txtId.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtName.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtName.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtName.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());

            }
        }catch (Exception e){}
    }

    public void txtValiName(KeyEvent keyEvent) {
        try {
            String name = txtName.getText();
            boolean isValidate = DataValidateController.customerNameValidate(name);
            if (isValidate) {
                txtName.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());

            } else {
                txtName.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtName.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtName.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnDelete.setDisable(txtName.getText().isEmpty() | txtState.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());

            }
        }catch (Exception e){}

    }

    public void txtValiState(KeyEvent keyEvent) {
        try {
            String name = txtState.getText();
            boolean isValidate = DataValidateController.addressValidate(name);
            if (isValidate) {
                txtState.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());

            } else {
                txtState.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtNo.getText().isEmpty());

            }
        }catch (Exception e){}
    }

    public void txtValiCnt(KeyEvent keyEvent) {
        try {
            String cnt = txtNo.getText();
            boolean isValidate = DataValidateController.contactCheck(cnt);
            if (isValidate) {
                txtNo.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtState.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtState.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtState.getText().isEmpty());

            } else {
                txtNo.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtState.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtState.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtState.getText().isEmpty());

            }
        }catch (Exception e){}

    }

    public void txtValiAddress(KeyEvent keyEvent) {
        try {
            String address = txtAddress.getText();
            boolean isValidate = DataValidateController.addressValidate(address);
            if (isValidate) {
                txtAddress.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtNo.getText().isEmpty() | txtState.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtNo.getText().isEmpty() | txtState.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtNo.getText().isEmpty() | txtState.getText().isEmpty());

            } else {
                txtAddress.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtName.getText().isEmpty() | txtName.getText().isEmpty() | txtNo.getText().isEmpty() | txtState.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtName.getText().isEmpty() | txtName.getText().isEmpty() | txtNo.getText().isEmpty() | txtState.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtName.getText().isEmpty() | txtName.getText().isEmpty() | txtNo.getText().isEmpty() | txtState.getText().isEmpty());

            }
        }catch (Exception e){}
    }
}
