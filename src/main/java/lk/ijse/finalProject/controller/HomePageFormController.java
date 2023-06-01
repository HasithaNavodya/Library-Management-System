package lk.ijse.finalProject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.ijse.finalProject.db.DBConnection;
import lk.ijse.finalProject.util.TimeAndDateController;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;

public class HomePageFormController {

    public Label lblTime;
    public Label lblDate;
    @FXML
    private Button btnDonator;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnMember;
    @FXML
    private AnchorPane TitlePane;
    @FXML
    private AnchorPane root;

    public void LMSButtonOnAction(ActionEvent actionEvent) {

    }

    public void BookButtonOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Book_Management_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Book Management");
        stage.centerOnScreen();
    }

    @FXML
    public void btnDashboardOnAction(ActionEvent actionEvent) {

    }

    @FXML
    public void EmployeeButtonOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Employee_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Employee Management");
        stage.centerOnScreen();

    }

    @FXML
    public void MemberButtonOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Member_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Member Management");
        stage.centerOnScreen();
    }

    public void FineButtonOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Fine_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Fine Management");
        stage.centerOnScreen();
    }

    public void DonatorButtonOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Donator_form.fxml"));

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Donator Management");
        stage.centerOnScreen();
    }

    public void LogoutButtonOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout?");
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {

                System.out.println("Logging out...");

                Stage stage = (Stage) btnLogout.getScene().getWindow();
                stage.close();
            }
        });
    }

    public void InventoryButtonOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Inventory_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Inventory Management");
        stage.centerOnScreen();
    }

    public void SalaryButtonOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Salary_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Salary Management");
        stage.centerOnScreen();
    }

    public void btnDonateBooksOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Donate_books_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Donate Books ");
        stage.centerOnScreen();
    }

    public void btnBorrowBooksOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/Borrow_books_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Borrow Books ");
        stage.centerOnScreen();
    }

    public void btnBookDetailsOnAction(ActionEvent actionEvent) {
        InputStream resource = this.getClass().getResourceAsStream("/reports/books_report.jrxml");
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(resource);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BackOnAction(ActionEvent actionEvent) {
        
    }

    @FXML
    void initialize() {
        
        TimeAndDateController timeobject = new TimeAndDateController();
        timeobject.timenow(lblTime,lblDate);
    }
}