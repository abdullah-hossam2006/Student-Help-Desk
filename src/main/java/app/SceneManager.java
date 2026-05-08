package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

// Handles switching between JavaFX scenes
public class SceneManager {

    public static void switchScene(ActionEvent event, String fxmlPath) throws Exception {

        // Load the target FXML file
        FXMLLoader loader = new FXMLLoader(
                SceneManager.class.getResource(fxmlPath)
        );

        // Create a new scene with fixed size
        Scene scene = new Scene(loader.load(), 800, 600);

        // Get the current stage from the clicked button or component
        Stage stage = (Stage) ((Node) event.getSource())
                .getScene()
                .getWindow();

        // Set the new scene on the current stage
        stage.setScene(scene);

        // Show the updated stage
        stage.show();
    }
}