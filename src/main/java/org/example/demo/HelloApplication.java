package org.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("choose-username.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage = stage;
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
