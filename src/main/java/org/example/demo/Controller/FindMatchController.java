package org.example.demo.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.example.demo.ApplicationManagement.ApplicationManager;
import org.example.demo.Client.ClientNetworkHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class FindMatchController implements Initializable {

    @FXML
    private Label WelcomeMessage;

    @FXML
    private Label numOfWins;

    @FXML
    private Button queueButton;

    ClientNetworkHandler client = ClientNetworkHandler.getClientNetworkHandler();

    ApplicationManager appManager;

    @FXML
    void queue(ActionEvent event) {
        client.joinQueue();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        appManager = ApplicationManager.getInstance();
        numOfWins.setText(numOfWins.getText() + appManager.getCurrentuser().getNum_of_wins());
        WelcomeMessage.setText(WelcomeMessage.getText() + " " +  appManager.getCurrentuser().getUsername());
    }
}
