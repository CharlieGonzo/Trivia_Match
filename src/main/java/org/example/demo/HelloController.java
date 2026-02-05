package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.example.demo.Client.ClientNetworkHandler;
import org.example.demo.Client.Form;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button loginButton;

    @FXML
    private TextField passwordBox;

    @FXML
    private Button registerButton;

    @FXML
    private TextField usernameBox;

    ClientNetworkHandler client;


    @FXML
    void login(ActionEvent event) throws IOException {
       String username =  usernameBox.getText();
       String password = passwordBox.getText();
       Form form = new Form(username,password);
       client.login(form);
    }

    @FXML
    void register(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        client = ClientNetworkHandler.getClientNetworkHandler();

    }
}
