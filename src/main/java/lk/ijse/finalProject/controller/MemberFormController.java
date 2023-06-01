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
import lk.ijse.finalProject.dto.Member;
import lk.ijse.finalProject.dto.tm.MemberTM;
import lk.ijse.finalProject.model.MemberModel;
import lk.ijse.finalProject.util.AlertController;
import lk.ijse.finalProject.util.DataValidateController;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MemberFormController implements Initializable {

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
    private TableColumn<?, ?> colFee;

    @FXML
    private TableColumn<?, ?> colGrade;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<MemberTM> tblMember;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtGrade;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/HomePage_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();

    }

    @FXML
    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String  id = txtId.getText();

        try {
            boolean isDelete = MemberModel.delete(id);
            if(isDelete){
                AlertController.confirmmessage("Member Is DELETED!");
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
    public void btnAddOnAction(ActionEvent actionEvent) {
   String id = txtId.getText();
   String name = txtName.getText();
   String address = txtAddress.getText();
   String grade = txtGrade.getText();
        double fee = Double.parseDouble(txtFee.getText());
   String contact = txtContact.getText();

        Member member = new Member(id,name,address,grade,fee,contact);
        try {
            boolean isSaved = MemberModel.add(member);
            if(isSaved){
                AlertController.confirmmessage("Member Is Saved!");
                getAll();
                ClearAll();

                txtId.setStyle("-fx-border-color : transparent");
                txtName.setStyle("-fx-border-color : transparent");
                txtAddress.setStyle("-fx-border-color : transparent");
                txtGrade.setStyle("-fx-border-color : transparent");
                txtFee.setStyle("-fx-border-color : transparent");
                txtContact.setStyle("-fx-border-color : transparent");

                btnAdd.setDisable(true);
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        } catch (SQLException throwables) {
            AlertController.errormessage("Something Wemt Wrong");
        }
    }


    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        String grade = txtGrade.getText();
        double fee = Double.parseDouble(txtFee.getText());
        String contact = txtContact.getText();

        Member member = new Member(id,name,address,grade,fee,contact);
        try {
            boolean isUpdate = MemberModel.update(member);
            if(isUpdate){
                AlertController.confirmmessage("MemberDetails Is Updates!");
                getAll();
                ClearAll();

                txtId.setStyle("-fx-border-color : transparent");
                txtName.setStyle("-fx-border-color : transparent");
                txtAddress.setStyle("-fx-border-color : transparent");
                txtGrade.setStyle("-fx-border-color : transparent");
                txtFee.setStyle("-fx-border-color : transparent");
                txtContact.setStyle("-fx-border-color : transparent");

                btnAdd.setDisable(true);
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        } catch (SQLException throwables) {
            AlertController.errormessage("Something Wemt Wrong");
        }
    }


    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        ClearAll();
        setCellValueFactory();
        getAll();
    }

    private void getAll() {
        try {
            ObservableList<MemberTM> obList = MemberModel.getAll();
            tblMember.setItems(obList);
        } catch (SQLException e) {

        }
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));

        colGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
    }


    public void tblClick(MouseEvent mouseEvent) {
        MemberTM tm = (MemberTM) tblMember.getSelectionModel().getSelectedItem();
        txtId.setText(tm.getMember_id());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtContact.setText(tm.getContact());
        txtGrade.setText(tm.getGrade());
        txtFee.setText(String.valueOf(tm.getFee()));

        btnDelete.setDisable(false);
    }


    public  void ClearAll(){
        txtId.setText(null);
        txtName.setText(null);
        txtAddress.setText(null);
        txtGrade.setText(null);
        txtFee.setText(null);
        txtContact.setText(null);
    }

    public void txtValiId(KeyEvent keyEvent) {
        try {
            String id = txtId.getText();
            boolean isValidate = DataValidateController.memberId(id);
            if (isValidate) {
                txtId.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());

            } else {
                txtId.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());

            }
        }catch (Exception e){}
    }

    public void txtValiName(KeyEvent keyEvent) {
        try {
            String name = txtName.getText();
            boolean isValidate = DataValidateController.customerNameValidate(name);
            if (isValidate) {
                txtName.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());

            } else {
                txtName.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtAddress.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());

            }
        }catch (Exception e){}
    }

    public void txtValiAddress(KeyEvent keyEvent) {
        try {
            String address = txtAddress.getText();
            boolean isValidate = DataValidateController.customerNameValidate(address);
            if (isValidate) {
                txtAddress.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());

            } else {
                txtAddress.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());

            }
        }catch (Exception e){}
    }

    public void txtValiGrade(KeyEvent keyEvent) {
        try {
            String address = txtGrade.getText();
            boolean isValidate = DataValidateController.customerNameValidate(address);
            if (isValidate) {
                txtGrade.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());

            } else {
                txtGrade.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtAddress.getText().isEmpty() | txtFee.getText().isEmpty() | txtContact.getText().isEmpty());

            }
        }catch (Exception e){}
    }

    public void txtValiFee(KeyEvent keyEvent) {
        try {
            String fee = txtFee.getText();
            boolean isValidate = DataValidateController.priceValidate(fee);
            if (isValidate) {
                txtFee.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());

            } else {
                txtFee.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtAddress.getText().isEmpty() | txtContact.getText().isEmpty());

            }
        }catch (Exception e){}

    }

    public void txtCnt(KeyEvent keyEvent) {
        try {
            String address = txtContact.getText();
            boolean isValidate = DataValidateController.contactCheck(address);
            if (isValidate) {
                txtContact.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtAddress.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtAddress.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtAddress.getText().isEmpty());

            } else {
                txtContact.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtAddress.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtAddress.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtGrade.getText().isEmpty() | txtFee.getText().isEmpty() | txtAddress.getText().isEmpty());

            }
        }catch (Exception e){}
    }
}

