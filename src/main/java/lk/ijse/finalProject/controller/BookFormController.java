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
import lk.ijse.finalProject.bo.custom.BookBO;
import lk.ijse.finalProject.model.BookDTO;
import lk.ijse.finalProject.view.tdm.BookTM;
import lk.ijse.finalProject.controller.AlertController;
import lk.ijse.finalProject.controller.DataValidateController;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookFormController implements Initializable {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnNextValid;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAuthor;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colCupNo;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private Label lblid;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<BookTM> tblBook;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtNo;

    BookBO bookBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.BOOK_BO);

    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        getAll();
        setCellValueFactory();
        generateNextBookId();
    }

    private void getAll() {
        tblBook.getItems().clear();
        try {
            ArrayList<BookDTO> allBooks = bookBO.getAllBooks();
            for (BookDTO i : allBooks) {
                tblBook.getItems().add(new BookTM(i.getBook_id(), i.getName(), i.getAuthor(), i.getCategory(),i.getCupboard_no()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void generateNextBookId(){
        try {
            String id = bookBO.getNextBookId();
            lblid.setText(id);
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Exception!").show();
        }
    }

    private void setCellValueFactory() {
        colid.setCellValueFactory(new PropertyValueFactory<>("book_id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colCupNo.setCellValueFactory(new PropertyValueFactory<>("cupboard_no"));
    }







    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException {
        try {
            String book_id = lblid.getText();
            String name = txtName.getText();
            String author = txtAuthor.getText();
            String category = txtCategory.getText();
            int cupboard_no = Integer.parseInt(txtNo.getText());

            if (book_id.isEmpty() || name.isEmpty() || author.isEmpty() || category.isEmpty() || String.valueOf(cupboard_no).isEmpty()) {
                AlertController.errormessage("Book details not saved.\nPlease make sure to fill all the required fields ");
            } else {



                boolean isAdded =  bookBO.saveBook(new BookDTO(book_id, name, author, category, cupboard_no));
                if (isAdded) {
                    AlertController.confirmmessage("Book Added Successfully");
                    lblid.setText("");
                    txtName.setText("");
                    txtAuthor.setText("");
                    txtCategory.setText("");
                    txtNo.setText("");

                    generateNextBookId();
                    getAll();

                    txtName.setStyle("-fx-border-color : transparent");
                    txtAuthor.setStyle("-fx-border-color : transparent");
                    txtCategory.setStyle("-fx-border-color : transparent");
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
            AlertController.errormessage("Duplicate Book ID");
        } catch (Exception e) {
            System.out.println(e);
            AlertController.errormessage("Something went wrong");
        }
    }



    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String book_id = lblid.getText();

        boolean result = AlertController.okconfirmmessage("Are you sure you want to remove this book from the system?");
        if(result==true) {

            try {
                boolean isDeleted = bookBO.deleteBook(book_id);
                if (isDeleted) {
                    AlertController.confirmmessage("Book Removed Successfully");
                    lblid.setText("");
                    txtName.setText("");
                    txtAuthor.setText("");
                    txtCategory.setText("");
                    txtNo.setText("");

                    generateNextBookId();

                    getAll();

                    btnAdd.setDisable(true);
                    btnUpdate.setDisable(true);
                    btnDelete.setDisable(true);
                } else{
                    AlertController.errormessage("No Book ID Selected");
                }
            } catch (SQLException e) {
                System.out.println(e);
                AlertController.errormessage("something went wrong!");
            }
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        boolean result = AlertController.okconfirmmessage("Are you sure you want to update this items' details?");
        if(result==true) {

            try {
                String bookId = lblid.getText();
                String name = txtName.getText();
                String author = txtAuthor.getText();
                String category = txtCategory.getText();
                int cupboardNo = Integer.parseInt(txtNo.getText());

                if(bookId.isEmpty() || name.isEmpty() || author.isEmpty() || category.isEmpty() || String.valueOf(cupboardNo).isEmpty()) {
                    AlertController.errormessage("Book details not saved.\nPlease make sure to fill all the required fields ");
                }else{


                    boolean isUpdated = bookBO.updateBook(new BookDTO(bookId, name, author, category, cupboardNo));
                    if (isUpdated) {
                        AlertController.confirmmessage("Book Details Updated");
                        lblid.setText("");
                        txtName.setText("");
                        txtAuthor.setText("");
                        txtCategory.setText("");
                        txtNo.setText("");

                        generateNextBookId();
                        getAll();

                        txtName.setStyle("-fx-border-color : transparent");
                        txtAuthor.setStyle("-fx-border-color : transparent");
                        txtCategory.setStyle("-fx-border-color : transparent");
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
//        Parent parent = FXMLLoader.load(getClass().getResource("/view/HomePage_form.fxml"));
//
//        Stage stage = (Stage) BookPane.getScene().getWindow();
//        stage.setTitle("Home Page");
//        stage.setScene(new Scene(parent));
//        stage.centerOnScreen();
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/HomePage_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home");
        stage.centerOnScreen();
    }


    public void tblBookOnMouseClicked(MouseEvent mouseEvent) {
        TablePosition pos = tblBook.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();
        // Get the data from the selected row
        ObservableList<TableColumn<BookTM, ?>> columns = tblBook.getColumns();

        lblid.setText(columns.get(0).getCellData(row).toString());
        txtName.setText(columns.get(1).getCellData(row).toString());
        txtAuthor.setText(columns.get(2).getCellData(row).toString());
        txtCategory.setText(columns.get(3).getCellData(row).toString());
        txtNo.setText(columns.get(4).getCellData(row).toString());

        btnDelete.setDisable(false);

    }



    public void btnNextValidOnAction(ActionEvent actionEvent) {
        generateNextBookId();
    }

    public void txtNoOnKeyTyped(KeyEvent keyEvent) {
        try {
            String no = txtNo.getText();
            boolean isValidate = DataValidateController.quantityValidate(no);
            if (isValidate) {
                txtNo.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtName.getText().isEmpty() | txtCategory.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtName.getText().isEmpty() | txtCategory.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtName.getText().isEmpty() | txtCategory.getText().isEmpty() );

            } else {
                txtNo.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtCategory.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtCategory.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtCategory.getText().isEmpty() );

            }
        }catch (Exception e){}
    }

    public void txtCatagoryOnKeyTyped(KeyEvent keyEvent) {
        try {
            String cate = txtCategory.getText();
            boolean isValidate = DataValidateController.customerNameValidate(cate);
            if (isValidate) {
                txtCategory.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtName.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtName.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtName.getText().isEmpty() | txtNo.getText().isEmpty() );

            } else {
                txtCategory.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );

            }
        }catch (Exception e){}
    }

    public void txtNameOnKeyTyped(KeyEvent keyEvent) {
        try {
            String id = txtName.getText();
            boolean isValidate = DataValidateController.customerNameValidate(id);
            if (isValidate) {
                txtName.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );

            } else {
                txtName.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtAuthor.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );

            }
        }catch (Exception e){}
    }

    public void txtAuthorOnKeyTyped(KeyEvent keyEvent) {
        try {
            String auth = txtAuthor.getText();
            boolean isValidate = DataValidateController.customerNameValidate(auth);
            if (isValidate) {
                txtAuthor.setStyle("-fx-border-color : green; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtName.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtName.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtName.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );

            } else {
                txtAuthor.setStyle("-fx-border-color : red; -fx-border-width: 5");
                btnAdd.setDisable(!isValidate | txtName.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnUpdate.setDisable(!isValidate | txtName.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );
                btnDelete.setDisable(!isValidate | txtName.getText().isEmpty() | txtCategory.getText().isEmpty() | txtNo.getText().isEmpty() );

            }
        }catch (Exception e){}
    }
}








