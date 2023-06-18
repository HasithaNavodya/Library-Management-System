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
import lk.ijse.finalProject.bo.custom.InventoryBO;
import lk.ijse.finalProject.model.InventoryDTO;
import lk.ijse.finalProject.view.tdm.InventoryTM;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InventoryFormController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<InventoryTM> tblInventory;

    @FXML
    private TextField txtCatagory;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQuantity;

    InventoryBO inventoryBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.INVENTORY_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        getAll();
        setCellValueFactory();
        generateNextItemId();
    }



    private void getAll()  {
        tblInventory.getItems().clear();
        try {
            ArrayList<InventoryDTO> allItem = inventoryBO.getAllItem();
            for (InventoryDTO i : allItem) {
                tblInventory.getItems().add(new InventoryTM(i.getItem_id(), i.getItem_name(), i.getCategory(), i.getQuantity()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void generateNextItemId(){
        try {
            String id = inventoryBO.getNextItemId();
            txtId.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    private void setCellValueFactory() {

        colId.setCellValueFactory(new PropertyValueFactory<>("item_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("item_name"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));

    }


    @FXML
    void btnAddOnAction(ActionEvent event) throws SQLException {

        InventoryDTO inventoryDTO = new InventoryDTO();

        inventoryDTO.setItem_id(txtId.getText());
        inventoryDTO.setItem_name(txtName.getText());
        inventoryDTO.setCategory(txtCatagory.getText());
        inventoryDTO.setQuantity(Integer.parseInt(txtQuantity.getText()));


        try {
            boolean isSaved = inventoryBO.saveItem(inventoryDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved").show();
                getAll();

                txtId.setStyle("-fx-border-color : transparent");
                txtName.setStyle("-fx-border-color : transparent");
                txtCatagory.setStyle("-fx-border-color : transparent");
                txtQuantity.setStyle("-fx-border-color : transparent");

                btnAdd.setDisable(true);
                btnUpdate.setDisable(true);
                btnDelete.setDisable(true);
            }
        } catch (SQLIntegrityConstraintViolationException throwables) {

            new Alert(Alert.AlertType.ERROR, "Dupplicate ID").show();


        } catch (Exception throwables) {

            new Alert(Alert.AlertType.ERROR, "error").show();
            System.out.println(throwables);
        }
        getAll();

    }



    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {

        String inventory_Id = txtId.getText();
        boolean result = AlertController.okconfirmmessage("Are you sure you want to Delete this Item?");

        if (result == true) {

            try {
                boolean isDeleted = inventoryBO.deleteItem(inventory_Id);
                if (isDeleted) {
                    AlertController.confirmmessage("Delete Successful");

                    btnAdd.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);

                } else {
                    //  AlertController.errormessage("Somethink went wrong");
                    new Alert(Alert.AlertType.ERROR, "Dupplicate ID").show();

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                //  AlertController.errormessage("Somethink went wrong");
                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();


            }

        }


        getAll();

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {

        InventoryDTO inventoryDTO = new InventoryDTO();

        inventoryDTO.setItem_id(txtId.getText());
        inventoryDTO.setItem_name(txtName.getText());
        inventoryDTO.setCategory(txtCatagory.getText());
        inventoryDTO.setQuantity(Integer.parseInt(txtQuantity.getText()));

        boolean result = AlertController.okconfirmmessage("Are you sure you want to Update this Item?");
        if (result == true) {

            try {
                boolean isUpdates = inventoryBO.updateItem(inventoryDTO);
                if (isUpdates) {
                    AlertController.confirmmessage("Updated");
                    getAll();

                    txtId.setStyle("-fx-border-color : transparent");
                    txtName.setStyle("-fx-border-color : transparent");
                    txtCatagory.setStyle("-fx-border-color : transparent");
                    txtQuantity.setStyle("-fx-border-color : transparent");

                    btnAdd.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);

                } else {

                    // AlertController.errormessage("Error!!");
                    new Alert(Alert.AlertType.ERROR, "Something went wrong").show();

                }
            } catch (SQLException e) {
                System.out.println(e);
                //  AlertController.errormessage("Error");

                new Alert(Alert.AlertType.ERROR, "Something went wrong").show();

            }
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/HomePage_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }

    public void ItemOnMouseClick(MouseEvent mouseEvent) {

        TablePosition pos = tblInventory.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();

        ObservableList<TableColumn<InventoryTM, ?>> columns = tblInventory.getColumns();

        txtId.setText(columns.get(0).getCellData(row).toString());
        txtName.setText(columns.get(1).getCellData(row).toString());
        txtCatagory.setText(columns.get(2).getCellData(row).toString());
        txtQuantity.setText(columns.get(3).getCellData(row).toString());

        btnDelete.setDisable(false);

    }

    public void txtValiId(KeyEvent keyEvent) {
        try {
            String id = txtId.getText();
            boolean isValidate = DataValidateController.itemIdValidate(id);
            if (isValidate) {
                txtId.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtName.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtName.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnDelete.setDisable(txtName.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());

            } else {
                txtId.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtName.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtName.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnDelete.setDisable(txtName.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }

    public void txtValiQty(KeyEvent keyEvent) {
        try {
            String id = txtQuantity.getText();
            boolean isValidate = DataValidateController.quantityValidate(id);
            if (isValidate) {
                txtQuantity.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtCatagory.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtCatagory.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtCatagory.getText().isEmpty());

            } else {
                txtQuantity.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtCatagory.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtCatagory.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtCatagory.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }

    public void txtValiName(KeyEvent keyEvent) {
        try {
            String id = txtName.getText();
            boolean isValidate = DataValidateController.customerNameValidate(id);
            if (isValidate) {
                txtName.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());

            } else {
                txtName.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtCatagory.getText().isEmpty() | txtQuantity.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }

    public void txtValiCate(KeyEvent keyEvent) {
        try {
            String id = txtCatagory.getText();
            boolean isValidate = DataValidateController.customerNameValidate(id);
            if (isValidate) {
                txtCatagory.setStyle("-fx-border-color : green;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtQuantity.getText().isEmpty());

            } else {
                txtCatagory.setStyle("-fx-border-color : red;-fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnUpdate.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtQuantity.getText().isEmpty());
                btnDelete.setDisable(!isValidate | txtId.getText().isEmpty() | txtName.getText().isEmpty() | txtQuantity.getText().isEmpty());

            }
        } catch (Exception e) {
        }
    }
}

