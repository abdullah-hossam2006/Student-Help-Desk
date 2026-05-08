module com.example.student_help_desk {
    requires javafx.controls;
    requires javafx.fxml;

    opens app to javafx.graphics, javafx.fxml;
    opens controller to javafx.fxml;
    opens model to javafx.base;

    exports app;
    exports controller;
    exports model;
    exports network;
    exports service;
    exports exception;
}