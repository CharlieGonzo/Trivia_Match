package org.example.demo.Util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Control;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneHelper {

    public static void changeScenes(Control node, FXMLLoader loader) throws IOException {
        Stage currStage = (Stage)node.getScene().getWindow();
        Scene scene = new Scene(loader.load(),currStage.getHeight(),currStage.getWidth());
        currStage.setScene(scene);
        currStage.show();
    }
}
