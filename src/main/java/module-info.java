module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;
// This is the magic line:
    opens org.example.demo.Client to com.fasterxml.jackson.databind, javafx.fxml;
    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
    exports org.example.demo.Util;
    opens org.example.demo.Util to javafx.fxml;
    opens org.example.demo.Model to com.fasterxml.jackson.databind, javafx.fxml;
    exports org.example.demo.Controller;
    opens org.example.demo.Controller to javafx.fxml;
}