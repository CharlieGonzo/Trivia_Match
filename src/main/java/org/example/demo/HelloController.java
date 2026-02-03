package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button continue_Button;

    @FXML
    private TextField usernameBox;

    @FXML
    void checkUsername(ActionEvent event) throws IOException {
        System.out.println("checkUsername");
        FXMLLoader loader = new FXMLLoader(
                HelloApplication.class.getResource("second-screen.fxml")
        );
        Scene scene = new Scene(loader.load(),continue_Button.getScene().getWidth(),continue_Button.getScene().getHeight());

        Stage stage = (Stage) continue_Button.getScene().getWindow();
        stage.setScene(scene);
    }

}
