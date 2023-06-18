package lk.ijse.finalProject.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Optional;

public class AlertController {
    public static void errormessage(String msg){
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
//            alert.getDialogPane().setMinSize(300, 150); // Set the minimum size of the alert dialog pane
//            alert.getDialogPane().setMaxSize(300, 150); // Set the maximum size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: #6d8ac6;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("assets/img/wrongicon.png"));
        ButtonType cancelButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }

    public static void confirmmessage(String msg){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
//            alert.getDialogPane().setMinSize(300, 150); // Set the minimum size of the alert dialog pane
//            alert.getDialogPane().setMaxSize(300, 150); // Set the maximum size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: #6d8ac6;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("assets/img/tick.png"));
        ButtonType cancelButton = new ButtonType("Ok", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }

    public static boolean okconfirmmessage(String msg){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
//            alert.getDialogPane().setMinSize(300, 150); // Set the minimum size of the alert dialog pane
//            alert.getDialogPane().setMaxSize(300, 150); // Set the maximum size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: #6d8ac6;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("assets/img/tick.png"));
        ButtonType okButton = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(okButton,cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.orElse(cancelButton) == okButton){
            return true;
        }
        return false;
    }

    public static boolean onlyokconfirmmessage(String msg){
        Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
//            alert.getDialogPane().setMinSize(300, 150); // Set the minimum size of the alert dialog pane
//            alert.getDialogPane().setMaxSize(300, 150); // Set the maximum size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: #6d8ac6;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("assets/img/tick.png"));
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        ButtonType okButton = new ButtonType("Continue", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        Optional<ButtonType> result = alert.showAndWait();
        if(result.orElse(cancelButton) == okButton){
            return true;
        }
        return false;
    }

    public static void warningmessage(String msg){
        Alert alert= new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(null);
        alert.setContentText(msg);

        alert.getDialogPane().setPrefSize(300, 150); // Set the size of the alert dialog pane
//            alert.getDialogPane().setMinSize(300, 150); // Set the minimum size of the alert dialog pane
//            alert.getDialogPane().setMaxSize(300, 150); // Set the maximum size of the alert dialog pane
        alert.getDialogPane().setStyle("-fx-background-color: #6d8ac6;"); // Set the background color of the alert dialog pane
        alert.getDialogPane().setHeaderText(null); // Remove the header text from the alert dialog pane

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image("assets/img/wrongicon.png"));
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(cancelButton);

        alert.showAndWait();
    }

    public static boolean notificationBar(String title, String massage) throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        java.awt.Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Notification Example");
        trayIcon.setImageAutoSize(true);
        trayIcon.setToolTip("Click me to see the message");
        tray.add(trayIcon);
        trayIcon.displayMessage(title, massage, TrayIcon.MessageType.INFO);
        return false;
    }
}
