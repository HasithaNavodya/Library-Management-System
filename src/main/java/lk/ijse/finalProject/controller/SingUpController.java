package lk.ijse.finalProject.controller;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.dto.User;
import lk.ijse.finalProject.model.UserModel;

public class SingUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane loginAncPane;

    @FXML
    private TextField userNameTxt;

    @FXML
    private TextField PassWordText;

    @FXML
    private TextField ComPassWordText;

    @FXML
    private TextField EmailText;

    @FXML
    private JFXButton btn2;

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void SignUpClick(ActionEvent event) throws IOException {



        User user = new User();

        user.setUserName(userNameTxt.getText());
        user.setPassword(PassWordText.getText());
       user.setEmail(EmailText.getText());

        try {
            boolean isSingUp = UserModel.SingUp(user);

            if (isSingUp) {
                new Alert(Alert.AlertType.CONFIRMATION, "saved").show();



                Parent root = FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.centerOnScreen();
                stage.show();
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Error").show();
        }

    }

    @FXML
    void initialize() {
        assert loginAncPane != null : "fx:id=\"loginAncPane\" was not injected: check your FXML file 'SingUp.fxml'.";
        assert userNameTxt != null : "fx:id=\"userNameTxt\" was not injected: check your FXML file 'SingUp.fxml'.";
        assert PassWordText != null : "fx:id=\"PassWordText\" was not injected: check your FXML file 'SingUp.fxml'.";
        assert ComPassWordText != null : "fx:id=\"ComPassWordText\" was not injected: check your FXML file 'SingUp.fxml'.";
        assert EmailText != null : "fx:id=\"EmailText\" was not injected: check your FXML file 'SingUp.fxml'.";
        assert btn2 != null : "fx:id=\"btn2\" was not injected: check your FXML file 'SingUp.fxml'.";

    }

    public void buttonOnAction(ActionEvent actionEvent) throws IOException {
        /*Parent parent =  FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();

        stage.show();*/

        Stage stage = new Stage();
        Parent root = null;
        stage.setTitle("Login Page");
        root = FXMLLoader.load(getClass().getResource("/view/Login_form.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        loginAncPane.getScene().getWindow().hide();
    }
}
