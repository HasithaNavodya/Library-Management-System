package lk.ijse.finalProject.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.bo.BoFactory;
import lk.ijse.finalProject.bo.custom.BorrowBooksBO;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.dto.BorrowBooksDTO;
import lk.ijse.finalProject.view.tdm.BorrowBookCartTM;
import lk.ijse.finalProject.util.AlertController;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

public class BorrowBooksController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnAddToCart;

    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> cmbBookID;

    @FXML
    private ComboBox<String> cmbMemberID;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colDueDate;

    @FXML
    private TableColumn<?, ?> colIssueId;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private DatePicker datePickerDueDate;

    @FXML
    private Label lblDonationId;

    @FXML
    private Label lblIssueId;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BorrowBookCartTM> tblBookBorrow;

    BorrowBooksBO borrowBooksBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.BORROW_BOOKS_BO);

    @FXML
    public void initialize() {
        assert btnAdd != null : "fx:id=\"btnAdd\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert btnAddToCart != null : "fx:id=\"btnAddToCart\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert btnBack != null : "fx:id=\"btnBack\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert cmbBookID != null : "fx:id=\"cmbBookID\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert cmbMemberID != null : "fx:id=\"cmbMemberID\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert colAction != null : "fx:id=\"colAction\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert colBookId != null : "fx:id=\"colBookId\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert colDueDate != null : "fx:id=\"colDueDate\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert colIssueId != null : "fx:id=\"colIssueId\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert colMemberId != null : "fx:id=\"colMemberId\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert datePickerDueDate != null : "fx:id=\"datePickerDueDate\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert lblDonationId != null : "fx:id=\"lblDonationId\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert lblIssueId != null : "fx:id=\"lblIssueId\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";
        assert root != null : "fx:id=\"root\" was not injected: check your FXML file 'Borrow_books_form.fxml'.";

        generateNextIssueId();
        loadMemberIDs();
        loadBookIDs();
        setCellValueFactory();
    }

    private void setCellValueFactory() {
        colIssueId.setCellValueFactory(new PropertyValueFactory<>("issue_id"));
        colMemberId.setCellValueFactory(new PropertyValueFactory<>("member_id"));
        colBookId.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("due_date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));
    }

    public void generateNextIssueId(){
        try {
            String id = borrowBooksBO.getNextIssueId();
            lblIssueId.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }


    @FXML
    void btnAddOnAction(ActionEvent event) {
        String issue_id = lblIssueId.getText();
        String mem_id = cmbMemberID.getValue();
        String due_date = String.valueOf(datePickerDueDate.getValue());

        if (obList.isEmpty()) {
            AlertController.errormessage("Please make sure to add related details to the cart first");
        } else {
            List<BorrowBooksDTO> borrowBooksList = new ArrayList<>();

            for (int i = 0; i < tblBookBorrow.getItems().size(); i++) {
                BorrowBookCartTM borrowBookCartTM = obList.get(i);

                BorrowBooksDTO placeIssue = new BorrowBooksDTO(
                        borrowBookCartTM.getIssue_id(),
                        borrowBookCartTM.getMember_id(),
                        borrowBookCartTM.getBook_id(),
                        borrowBookCartTM.getDue_date()
                );
                borrowBooksList.add(placeIssue);
            }

            boolean isPlaced = false;
            try {
                BorrowBooksDTO borrowBooksDTO = new BorrowBooksDTO(issue_id,mem_id,due_date, borrowBooksList);
                isPlaced = borrowBooksBO.placeIssue(borrowBooksDTO);
                if (isPlaced) {
                    AlertController.confirmmessage("Book/Books Issued Successfully");

                    lblIssueId.setText("");
                    cmbMemberID.setValue(null);
                    cmbBookID.setValue(null);
                    datePickerDueDate.setValue(null);
                    generateNextIssueId();

                    boolean result = AlertController.okconfirmmessage("Do you want the bill?");

                    if (result) {
                        InputStream resource = this.getClass().getResourceAsStream("/assets/reports/borrow_books.jrxml");
                        try {
                            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
                            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
                            JasperViewer.viewReport(jasperPrint, false);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                } else {
                    AlertController.errormessage("Error Issuing Books");
                }
            } catch (Exception e) {
                System.out.println(e);
                AlertController.errormessage("SQL Error");
            }
        }

    }

    private ObservableList<BorrowBookCartTM> obList = FXCollections.observableArrayList();

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String issue_id = lblIssueId.getText();
        String mem_id = cmbMemberID.getValue();
        String book_id = cmbBookID.getValue();
        String due_date = String.valueOf(datePickerDueDate.getValue());
        Button btnremove = new Button("Remove");
        btnremove.setCursor(Cursor.HAND);

        System.out.println(mem_id);

        if (mem_id==null || book_id==null || datePickerDueDate.getValue()==null) {
            AlertController.errormessage("Please make sure to fill out all the required fields before you proceed");
        } else {
            setRemoveBtnOnAction(btnremove); /* set action to the btnRemove */

            if (!obList.isEmpty()) {

                for (int i = 0; i < tblBookBorrow.getItems().size(); i++) {
                    if (colBookId.getCellData(i).equals(book_id)) {

                        AlertController.errormessage(book_id+" Already Added to the Cart");
                        break;
                    }
                }
                BorrowBookCartTM tm = new BorrowBookCartTM(issue_id,mem_id,book_id,due_date,btnremove);

                obList.add(tm);
                tblBookBorrow.setItems(obList);

                cmbBookID.setValue(null);
            } else {
                BorrowBookCartTM tm = new BorrowBookCartTM(issue_id,mem_id,book_id,due_date,btnremove);

                obList.add(tm);
                tblBookBorrow.setItems(obList);

                cmbBookID.setValue(null);
            }
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

    public void loadMemberIDs(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = borrowBooksBO.loadMemberIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbMemberID.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    public void loadBookIDs(){
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = borrowBooksBO.loadBookIDs();

            for (String id : ids) {
                obList.add(id);
            }
            cmbBookID.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }




    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {
                int index = tblBookBorrow.getSelectionModel().getSelectedIndex();

                obList.remove(index);
                tblBookBorrow.refresh();

            }
        });
    }
}
