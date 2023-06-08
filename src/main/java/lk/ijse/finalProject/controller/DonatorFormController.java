package lk.ijse.finalProject.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.bo.BoFactory;
import lk.ijse.finalProject.bo.custom.impl.DonatorBOImpl;
import lk.ijse.finalProject.dto.DonatorDTO;
import lk.ijse.finalProject.view.tdm.DonatorTM;
import lk.ijse.finalProject.model.DonatorModel;
import lk.ijse.finalProject.util.AlertController;
import lk.ijse.finalProject.util.DataValidateController;

public class DonatorFormController {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtDonatorName;

    @FXML
    private TextField txtContact;

    @FXML
    private TableView<DonatorTM> tblDonator;

    @FXML
    private TableColumn<?, ?> colDonId;

    @FXML
    private TableColumn<?, ?> colDonName;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnBack;

    DonatorBOImpl donatorBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.DONATOR_BO);

    @FXML
    void initialize() throws SQLException {
        setCellValueFactory();
        getAll();
        generateNextDonatorId();


        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert txtId != null : "fx:id=\"txtId\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert txtDate != null : "fx:id=\"txtDate\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert txtDonatorName != null : "fx:id=\"txtDonatorName\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert txtContact != null : "fx:id=\"txtContact\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert tblDonator != null : "fx:id=\"tblDonator\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert colDonId != null : "fx:id=\"colDonId\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert colDonName != null : "fx:id=\"colDonName\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert colContact != null : "fx:id=\"colBookName\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert colDate != null : "fx:id=\"colDate\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert btnDelete != null : "fx:id=\"btnDelete\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert btnUpdate != null : "fx:id=\"btnUpdate\" was not injected: check your FXML file 'Donator_form.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'Donator_form.fxml'.";

    }



    private void getAll()  {
        tblDonator.getItems().clear();
        try {
            ArrayList<DonatorDTO> allDonator = donatorBO.getAllDonator();
            for (DonatorDTO d : allDonator) {
                tblDonator.getItems().add(new DonatorTM (d.getDonator_id(), d.getName(), d.getContact(), d.getDate(),d.getUsername()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void generateNextDonatorId(){
        try {
            String id = donatorBO.getNextDonatorId();
            txtId.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    private void setCellValueFactory() {

        colDonId.setCellValueFactory(new PropertyValueFactory<>("donator_id"));
        colDonName.setCellValueFactory(new PropertyValueFactory<>("donator_name"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    }

    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        DonatorDTO donator = new DonatorDTO();

        donator.setDonator_id(txtId.getText());
        donator.setName(txtDonatorName.getText());
        donator.setContact(txtContact.getText());
        donator.setDate(txtDate.getText());

        try {
            boolean isSaved = donatorBO.save(donator);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION,"Saved").show();
                setCellValueFactory();
                getAll();
                txtId.setStyle("-fx-border-color : transparent");
                txtDonatorName.setStyle("-fx-border-color : transparent");
                txtContact.setStyle("-fx-border-color : transparent");
                txtDate.setStyle("-fx-border-color : transparent");

                btnAdd.setDisable(true);
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);

            }

        } catch (SQLIntegrityConstraintViolationException throwables) {

            new Alert(Alert.AlertType.ERROR,"Duplicate ID").show();


        } catch (Exception throwables) {

            new Alert(Alert.AlertType.ERROR,"error").show();
            System.out.println(throwables);
        }

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {

        String donatorId=txtId.getText();
        boolean result = AlertController.okconfirmmessage("Are you sure you want to Delete this Donator?");

        if(result==true){


            try {
                boolean isDeleted = DonatorModel.delete(donatorId);
                if (isDeleted) {
                    AlertController.confirmmessage("Delete Successful");
                    setCellValueFactory();
                    getAll();
                    btnAdd.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);

                } else {
                  //  AlertController.errormessage("Somethink went wrong");
                    new Alert(Alert.AlertType.ERROR,"Duplicate ID").show();

                }
            }catch (SQLException throwables){
                throwables.printStackTrace();
              //  AlertController.errormessage("Somethink went wrong");
                new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
            }

        }

        getAll();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {


        DonatorDTO donator=new DonatorDTO();

        donator.setDonator_id(txtId.getText());
        donator.setName(txtDonatorName.getText());
        donator.setContact(txtContact.getText());
        donator.setDate(txtDate.getText());

        boolean result = AlertController.okconfirmmessage("Are you sure you want to Update this Buyer?");
        if (result == true) {

            try {
                boolean isUpdates = DonatorModel.update(donator);
                if (isUpdates) {
                   // AlertController.confirmmessage("Update Ok");
                    new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();

                    txtId.setStyle("-fx-border-color : transparent");
                    txtDonatorName.setStyle("-fx-border-color : transparent");
                    txtContact.setStyle("-fx-border-color : transparent");
                    txtDate.setStyle("-fx-border-color : transparent");

                    btnAdd.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);

                } else {

                   // AlertController.errormessage("Error!!");
                    new Alert(Alert.AlertType.ERROR,"Something went wrong").show();

                }
            } catch (SQLException e) {
                System.out.println(e);
              //  AlertController.errormessage("Error");

                new Alert(Alert.AlertType.ERROR,"Something went wrong").show();

            }
        }
        getAll();
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

    public void BuyerOnMouseClick(MouseEvent mouseEvent) {

        TablePosition pos=tblDonator.getSelectionModel().getSelectedCells().get(0);
        int row=pos.getRow();

        ObservableList<TableColumn<DonatorTM,?>> columns=tblDonator.getColumns();

        txtId.setText(columns.get(0).getCellData(row).toString());
        txtDonatorName.setText(columns.get(1).getCellData(row).toString());
        txtContact.setText(columns.get(2).getCellData(row).toString());
        txtDate.setText(columns.get(3).getCellData(row).toString());

        btnDelete.setDisable(false);
    }

    public void txtIdOnKeyTyped(KeyEvent keyEvent) {
        try {
            String id = txtId.getText();
            boolean isValidate = DataValidateController.donatorIdValidate(id);
            if (isValidate) {
                txtId.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty());

            } else {
                txtId.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty());

            }
        }catch (Exception e){}
    }

    public void txtDateOnKeyTyped(KeyEvent keyEvent) {
        try {
            String date = txtDate.getText();
            boolean isValidate = DataValidateController.dateCheck(date);
            if (isValidate) {
                txtDate.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty());

            } else {
                txtDate.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtContact.getText().isEmpty());

            }
        }catch (Exception e){}
    }

    public void txtDonatorNameOnKeyTyped(KeyEvent keyEvent) {
            try {
                String name = txtDonatorName.getText();
                boolean isValidate = DataValidateController.customerNameValidate(name);
                if (isValidate) {
                    txtDonatorName.setStyle("-fx-border-color : green; -fx-border-width: 5");
                    btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty() );
                    btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty());
                    btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty());

                } else {
                    txtDonatorName.setStyle("-fx-border-color : red; -fx-border-width: 5");
                    btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty());
                    btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty());
                    btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtContact.getText().isEmpty() | txtDate.getText().isEmpty());

                }
            }catch (Exception e){}
    }

    public void txtBooknameOnKeyTyped(KeyEvent keyEvent) {
        try {
            String contact = txtContact.getText();
            boolean isValidate = DataValidateController.contactCheck(contact);
            if (isValidate) {
                txtContact.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtDate.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtDate.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtDate.getText().isEmpty());

            } else {
                txtContact.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtDate.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtDate.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtDonatorName.getText().isEmpty() | txtDate.getText().isEmpty());

            }
        }catch (Exception e){}
    }
}
