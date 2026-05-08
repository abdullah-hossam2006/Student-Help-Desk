package controller;

import app.SceneManager;
import app.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import network.ClientConnection;

public class RegisterController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private ComboBox<String> roleComboBox;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        roleComboBox.getItems().addAll("STUDENT", "ADMIN");
        roleComboBox.setValue("STUDENT");
    }

    @FXML
    public void handleRegister() {
        try {
            if (Session.connection == null) {
                Session.connection = new ClientConnection();
            }

            String response = Session.connection.sendRequest(
                    "REGISTER|" +
                            roleComboBox.getValue() + "|" +
                            usernameField.getText() + "|" +
                            passwordField.getText()
            );

            messageLabel.setText(response.split("\\|")[1]);

        } catch (Exception e) {
            messageLabel.setText("Cannot connect to server");
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            SceneManager.switchScene(event, "/fxml/login.fxml");
        } catch (Exception e) {
            messageLabel.setText("Cannot go back");
        }
    }
}