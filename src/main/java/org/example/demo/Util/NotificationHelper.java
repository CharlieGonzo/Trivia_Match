package org.example.demo.Util;

import javafx.scene.control.Alert;

public class NotificationHelper {
    private static Alert alert;

    public static void alertError(String message){
        alert = new Alert(Alert.AlertType.ERROR, message);
        alert.showAndWait();
    }

    private static void alertInfo(String message){
        alert = new Alert(Alert.AlertType.INFORMATION, message);
    }

    public static void alertWarning(String message){
        alert = new Alert(Alert.AlertType.WARNING, message);
    }

    public static void alertByCode(int code){
        switch (code){
            case 200 -> {
                alert =  new Alert(Alert.AlertType.INFORMATION, "Successful login!");
                alert.showAndWait();
            }
            case 401 -> {
                alert = new Alert(Alert.AlertType.WARNING, "Invalid Credentials");
                alert.showAndWait();
            }
            case 409 -> {
                alert = new Alert(Alert.AlertType.INFORMATION, "Username is taken");
                alert.showAndWait();
            }
            case 500 -> {
                alert = new  Alert(Alert.AlertType.INFORMATION, "Server error");
                alert.showAndWait();
            }
            case 201 -> {
                alert =  new Alert(Alert.AlertType.INFORMATION, "Registration successful!");
                alert.showAndWait();
            }

        }
    }
}
