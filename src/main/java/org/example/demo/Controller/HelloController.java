package org.example.demo.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.example.demo.HelloApplication;
import org.example.demo.Util.NotificationHelper;
import org.example.demo.Client.ClientNetworkHandler;
import org.example.demo.Model.Form;
import org.example.demo.Util.HashHelper;
import org.example.demo.Util.SceneHelper;

import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordBox;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameBox;

    ClientNetworkHandler client;


    @FXML
    void login(ActionEvent event) throws IOException, NoSuchAlgorithmException {
        String username =  usernameBox.getText();
        String password = passwordBox.getText();
        Form form = new Form(username, HashHelper.hashPassword(password));
        int statusCode = client.login(form);
        NotificationHelper.alertByCode(statusCode);
        if(statusCode == 200) {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("find-match-screen.fxml"));
            SceneHelper.changeScenes(passwordBox, loader);
        }
    }


    @FXML
    void goToRegisterPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("register-screen.fxml"));
        SceneHelper.changeScenes(passwordBox,loader);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = ClientNetworkHandler.getClientNetworkHandler();
        // adds Enter key listener so that if you hit the ENTER key, it will login.
        Platform.runLater(()->{
            passwordBox.getScene().setOnKeyPressed((e) -> {
                if(e.getCode() == KeyCode.ENTER){
                    try {
                        login(null);
                    } catch (IOException | NoSuchAlgorithmException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        });


    }
}
