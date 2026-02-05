package org.example.demo.Controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.example.demo.Client.ClientNetworkHandler;
import org.example.demo.HelloApplication;
import org.example.demo.Model.Form;
import org.example.demo.Util.NotificationHelper;
import org.example.demo.Util.SceneHelper;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {

    @FXML
    private Button continue_Button;

    @FXML
    private TextField usernameBox;

    @FXML
    private TextField passwordBox;

    @FXML
    private TextField confirmPasswordBox;

    ClientNetworkHandler client;

    @FXML
    void register(ActionEvent event) throws IOException {
        if(!passwordBox.getText().equals(confirmPasswordBox.getText())) {
            NotificationHelper.alertError("Passwords don't match");
            return;
        }
        int statusCode = client.Register(new Form(usernameBox.getText(),passwordBox.getText()));
        NotificationHelper.alertByCode(statusCode);
        if (statusCode == 201) {
            FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("choose-username.fxml"));
            SceneHelper.changeScenes(passwordBox,loader);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = ClientNetworkHandler.getClientNetworkHandler();
        Platform.runLater(() -> {
            usernameBox.getScene().setOnKeyPressed((e) -> {
                if (e.getCode() == KeyCode.ENTER) {
                    try {
                        register(null);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        });
    }
}
