package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;//content inside window
import javafx.stage.Stage;//Window

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Load the login screen from the FXML file
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/login.fxml"));
        // Create the main scene with fixed width and height
        Scene scene = new Scene(loader.load(), 800, 600);
        // Set the window title
        stage.setTitle("Student Help Desk System");
        // Put the scene inside the stage (window)
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}