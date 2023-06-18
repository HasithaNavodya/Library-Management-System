package lk.ijse.finalProject.controller;

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
import lk.ijse.finalProject.bo.custom.FineBO;
import lk.ijse.finalProject.model.FineDTO;
import lk.ijse.finalProject.view.tdm.FineTM;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FineFormController implements Initializable {


    @FXML
    private ComboBox cmbCustIds;

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
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colFineId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<FineTM> tblFine;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtDesc;

    @FXML
    private TextField txtId;

    FineBO fineBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.FINE_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setCellValueFactory();
        getAll();
        generateNextFineId();

    }

    public void generateNextFineId(){
        try {
            String id = fineBO.getNextFineId();
            txtId.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    private void getAll()  {
        tblFine.getItems().clear();
        try {
            ArrayList<FineDTO> allFine = fineBO.getAllFine();
            for (FineDTO f : allFine) {
                tblFine.getItems().add(new FineTM(f.getFine_id(), f.getAmount(), f.getDate(), f.getDescription(),f.getMember_id()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colFineId.setCellValueFactory(new PropertyValueFactory<>("fine_id"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));

    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
       String id = txtId.getText();
       double amount = Double.parseDouble(txtAmount.getText());
       String date =  txtDate.getText();
       String desc = txtDesc.getText();
       String cId = String.valueOf(cmbCustIds.getValue());

        FineDTO fineDTO = new FineDTO(id,amount,date,desc,cId);

        try {
            boolean isSaved = fineBO.saveFine(fineDTO);
            if(isSaved){
                AlertController.confirmmessage("Fine Details Is Saved!");
                //getAll();
                ClearAll();

                txtId.setStyle("-fx-border-color : transparent");
                txtAmount.setStyle("-fx-border-color : transparent");
                txtDesc.setStyle("-fx-border-color : transparent");
                txtDate.setStyle("-fx-border-color : transparent");

                btnAdd.setDisable(true);
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);

            }
        } catch (SQLException throwables) {
            AlertController.errormessage("Something Wemt Wrong");
            System.out.println(throwables);
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
    String id = txtId.getText();

        try {
            boolean isDelete = fineBO.deleteFineId(id);
            if(isDelete){
                AlertController.confirmmessage("Fine Details Deleted Successfully !");
                getAll();
                ClearAll();

                btnAdd.setDisable(true);
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        } catch (SQLException throwables) {
            AlertController.errormessage("Something Wemt Wrong");

        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        double amount = Double.parseDouble(txtAmount.getText());
        String date =  txtDate.getText();
        String desc = txtDesc.getText();
        String cId = String.valueOf(cmbCustIds.getValue());

        FineDTO fineDTO = new FineDTO(id,amount,date,desc,cId);
        try {
            boolean isUpdate = fineBO.updateFine(fineDTO);
            if(isUpdate){
                AlertController.confirmmessage("Fine Details Is Update!");
                getAll();
                ClearAll();

                txtId.setStyle("-fx-border-color : transparent");
                txtAmount.setStyle("-fx-border-color : transparent");
                txtDesc.setStyle("-fx-border-color : transparent");
                txtDate.setStyle("-fx-border-color : transparent");

                btnAdd.setDisable(true);
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        } catch (SQLException throwables) {
            AlertController.errormessage("Something Wemt Wrong");
        }
    }


    public  void ClearAll(){
        txtId.setText(null);
        txtAmount.setText(null);
        txtDate.setText(null);
        txtDesc.setText(null);
       cmbCustIds.setValue(null);




    }

    public void tblClick(MouseEvent mouseEvent) {

            FineTM tm = (FineTM) tblFine.getSelectionModel().getSelectedItem();
            txtId.setText(tm.getFine_id());
            txtAmount.setText(String.valueOf(tm.getAmount()));
            txtDate.setText(tm.getDate());
            txtDesc.setText(tm.getDescription());
            cmbCustIds.setValue(tm.getMem_id());

        btnDelete.setDisable(false);
    }

    public void txtValiDate(KeyEvent keyEvent) {
        try {
            String id = txtDate.getText();
            boolean isValidate = DataValidateController.dateCheck(id);
            if (isValidate) {
                txtDate.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtId.getText().isEmpty() | txtDesc.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtId.getText().isEmpty() | txtDesc.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtId.getText().isEmpty() | txtDesc.getText().isEmpty() );

            } else {
                txtDate.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtId.getText().isEmpty() | txtDesc.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtId.getText().isEmpty() | txtDesc.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtId.getText().isEmpty() | txtDesc.getText().isEmpty() );

            }
        }catch (Exception e){}
    }

    public void txtValiDesc(KeyEvent keyEvent) {
        try {
            String id = txtDesc.getText();
            boolean isValidate = DataValidateController.customerNameValidate(id);
            if (isValidate) {
                txtDesc.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );

            } else {
                txtDesc.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );

            }
        }catch (Exception e){}
    }

    public void txtValiAmo(KeyEvent keyEvent) {
        try {
            String id = txtAmount.getText();
            boolean isValidate = DataValidateController.priceValidate(id);
            if (isValidate) {
                txtAmount.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtDesc.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtDesc.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtDesc.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );

            } else {
                txtAmount.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtDesc.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtDesc.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtDesc.getText().isEmpty() | txtDate.getText().isEmpty() | txtId.getText().isEmpty() );

            }
        }catch (Exception e){}
    }

    public void txtValiId(KeyEvent keyEvent) {
        try {
            String id = txtId.getText();
            boolean isValidate = DataValidateController.fineId(id);
            if (isValidate) {
                txtId.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtDesc.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtDesc.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtDesc.getText().isEmpty() );

            } else {
                txtId.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtDesc.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtDesc.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAmount.getText().isEmpty() | txtDate.getText().isEmpty() | txtDesc.getText().isEmpty() );

            }
        }catch (Exception e){}
    }
}