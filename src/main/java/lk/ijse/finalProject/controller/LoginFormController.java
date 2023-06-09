package lk.ijse.finalProject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.finalProject.bo.BoFactory;
import lk.ijse.finalProject.bo.custom.LoginBO;
import lk.ijse.finalProject.bo.custom.impl.LoginBOImpl;
import lk.ijse.finalProject.dto.UserDTO;
import lk.ijse.finalProject.model.UserModel;
import lk.ijse.finalProject.util.AlertController;


import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {

    public static final String username ="a";
    public static final String password ="a";

    @FXML
    private AnchorPane loginPane;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblError;

    LoginBO loginBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.LOGIN_BO);

    @FXML
    private Stage stage;
    private Scene scene;
    private Parent root;

    UserDTO userDTO = new UserDTO();
    int count;

    public void initialize() {
        txtPassword.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                login();
            }
        });

        txtUsername.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.DOWN) {
                txtPassword.requestFocus();
            }
        });

        btnLogin.setOnMouseEntered(event -> {
            btnLogin.setStyle("-fx-background-color: #00ccff; -fx-text-fill: white; -fx-background-radius: 20;");
        });

        btnLogin.setOnMouseExited(event -> {
            btnLogin.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-background-radius: 10;");

        });

        // Set initial button style
        btnLogin.setStyle("-fx-background-color: #336699; -fx-text-fill: white; -fx-background-radius: 10;");
    }

    @FXML
    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {


        /*userDTO.setUserName(txtUsername.getText());
        userDTO.setPassword(txtPassword.getText());*/

        try {

            if( txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty()){
                new Alert(Alert.AlertType.ERROR,"invalid  login details").show();


            }
            else{

                    if (loginBO.LoginAction(new UserDTO(txtUsername.getText(),txtPassword.getText()))){
                       // new Alert(Alert.AlertType.CONFIRMATION,"Loging Sucssesful").show();
                        boolean result = AlertController.notificationBar("LIBRARY MANAGEMENT ","Login Successful");

                        if (result) {
                            Parent root = FXMLLoader.load(getClass().getResource("/view/HomePage_form.fxml"));
                            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.centerOnScreen();
                            stage.show();
                        } else {
                            Parent root = FXMLLoader.load(getClass().getResource("/view/HomePage_form.fxml"));
                            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                            scene = new Scene(root);
                            stage.setScene(scene);
                            stage.centerOnScreen();
                            stage.show();
                        }
                    }

                if (!loginBO.LoginAction(new UserDTO(txtUsername.getText(),txtPassword.getText()))){
                    new Alert(Alert.AlertType.ERROR,"invalid  login details").show();


                }
            }

        } catch (SQLException | AWTException throwables) {
            throwables.printStackTrace();
        }



    }

    private void login() {
        count++;
        if(count>3){
            System.exit(0);
        }

        try{
            if(username.equals(txtUsername.getText()) && password.equals(txtPassword.getText())){
                Parent parent = FXMLLoader.load(getClass().getResource("/view/HomePage_form.fxml"));

                Stage stage = (Stage) loginPane.getScene().getWindow();
                stage.setTitle("Home Page");
                stage.setScene(new Scene(parent));
                stage.centerOnScreen();
            } else {
                txtPassword.setStyle("-fx-border-color: red");
                txtUsername.setStyle("-fx-border-color: red");

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Invalid Username or Password");
                alert.setContentText("Please enter valid credentials.");

                alert.showAndWait();
            }

            } catch(Exception e) {
            System.out.println(e);
        }
    }

    public void EnterOnPress(KeyEvent keyEvent) {
    }

    public void SignUpClick(ActionEvent event) throws IOException {


        Parent root = FXMLLoader.load(getClass().getResource("/view/SingUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}