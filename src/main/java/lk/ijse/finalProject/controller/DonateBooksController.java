package lk.ijse.finalProject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.bo.BoFactory;
import lk.ijse.finalProject.bo.custom.DonateBooksBO;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.model.DonateBooksDTO;
import lk.ijse.finalProject.view.tdm.DonateBookCartTM;
import lk.ijse.finalProject.util.AlertController;
import lk.ijse.finalProject.util.ValidateField;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

public class DonateBooksController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> cmbDonatorId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colBookAuthor;

    @FXML
    private TableColumn<?, ?> colBookCategory;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colCupNo;

    @FXML
    private Label lblDonationId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<DonateBookCartTM> tblBookDonation;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtBookID;

    @FXML
    private TextField txtBookName;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtCupboardNo;

    DonateBooksBO donateBooksBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.DONATE_BOOKS_BO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();

        generateNextDonationId();
        loadDonatorIDs();
        generateNextBookId();
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBookAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colBookCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCupNo.setCellValueFactory(new PropertyValueFactory<>("cup_no"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));
    }

    public void generateNextDonationId(){
        try {
            String id = donateBooksBO.getNextDonationId();
            lblDonationId.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    public void generateNextBookId(){
        try {
            String id = donateBooksBO.getNextBookId();
            txtBookID.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }



    public void btnAddOnAction(ActionEvent actionEvent) {
        String donation_Id = lblDonationId.getText();
        String donator_Id = cmbDonatorId.getValue();

        if (obList.isEmpty()) {
            AlertController.errormessage("Please make sure to add related details to the cart first");
        } else {
            List<DonateBooksDTO> donateBooksList = new ArrayList<>();

            for (int i = 0; i < tblBookDonation.getItems().size(); i++) {
                DonateBookCartTM donateBookCartTM = obList.get(i);

                DonateBooksDTO placeOrder = new DonateBooksDTO(
                        donateBookCartTM.getId(),
                        donateBookCartTM.getName(),
                        donateBookCartTM.getAuthor(),
                        donateBookCartTM.getCategory(),
                        donateBookCartTM.getCup_no()
                );
                donateBooksList.add(placeOrder);
            }

            boolean isPlaced = false;
            try {
                DonateBooksDTO donateBooksDTO = new DonateBooksDTO(donation_Id,donator_Id, donateBooksList);
                isPlaced = donateBooksBO.placeDonation(donateBooksDTO);
                if (isPlaced) {
                    AlertController.confirmmessage("Donation Placed");

                    generateNextDonationId();
                    generateNextBookId();
                    cmbDonatorId.setValue(null);
                    txtBookID.setText("");
                    txtBookName.setText("");
                    txtAuthor.setText("");
                    txtCategory.setText("");
                    txtCupboardNo.setText("");

                    boolean result = AlertController.okconfirmmessage("Do you want the bill?");

                    if (result) {
                        InputStream resource = this.getClass().getResourceAsStream("/assets/reports/donate_books.jrxml");
                        try {
                            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
                            JasperViewer.viewReport(jasperPrint, false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    AlertController.errormessage("Donation Not Placed");
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("SQL Error");
            }
        }

    }

    private ObservableList<DonateBookCartTM> obList = FXCollections.observableArrayList();

    public void btnAddToCartOnAction(ActionEvent actionEvent) {

        if (cmbDonatorId.getValue()==null || txtBookID.getText().isEmpty() || txtBookName.getText().isEmpty() || txtAuthor.getText().isEmpty() || txtCategory.getText().isEmpty() || String.valueOf(txtCupboardNo.getText()).isEmpty()) {
            AlertController.errormessage("Please make sure to fill out all the required fields before you proceed");
        } else {
            if (ValidateField.numberCheck(txtCupboardNo.getText())) {
                String book_Id = txtBookID.getText();
                String book_Name = txtBookName.getText();
                String author = txtAuthor.getText();
                String category = txtCategory.getText();
                int cupboard_No = Integer.parseInt(txtCupboardNo.getText());
                Button btnremove = new Button("Remove");
                btnremove.setCursor(Cursor.HAND);

                setRemoveBtnOnAction(btnremove); /* set action to the btnRemove */

                DonateBookCartTM tm = new DonateBookCartTM(book_Id,book_Name,author,category,cupboard_No,btnremove);

                obList.add(tm);
                tblBookDonation.setItems(obList);

                txtBookID.setText("");
                txtBookName.setText("");
                txtAuthor.setText("");
                txtCategory.setText("");
                txtCupboardNo.setText("");
            }else{
                AlertController.errormessage("Wrong input format for Cupboard No field");
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

    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                int index = tblBookDonation.getSelectionModel().getSelectedIndex();

                obList.remove(index);
                tblBookDonation.refresh();

            }

        });
    }

    public void loadDonatorIDs(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = donateBooksBO.loadDonatorIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbDonatorId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }


}
