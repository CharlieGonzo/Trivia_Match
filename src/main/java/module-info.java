module org.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires okhttp;
    requires okhttp3;
    requires com.fasterxml.jackson.databind;


    opens org.example.demo to javafx.fxml;
    exports org.example.demo;
}