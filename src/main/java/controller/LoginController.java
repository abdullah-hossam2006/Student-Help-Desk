package controller;

import app.SceneManager;
import app.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import network.ClientConnection;

public class LoginController {
    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private Label messageLabel;

    @FXML
    public void handleLogin(ActionEvent event) {
        try {
            if (Session.connection == null) {
                Session.connection = new ClientConnection();
            }

            String username = usernameField.getText();
            String password = passwordField.getText();

            String response = Session.connection.sendRequest(
                    "LOGIN|" + username + "|" + password
            );

            if (response.startsWith("SUCCESS")) {
                String role = response.split("\\|")[1];

                Session.currentUsername = username;
                Session.currentRole = role;

                if (role.equals("ADMIN")) {
                    SceneManager.switchScene(event, "/fxml/admin_dashboard.fxml");
                } else {
                    SceneManager.switchScene(event, "/fxml/student_dashboard.fxml");
                }
            } else {
                messageLabel.setText(response.split("\\|")[1]);
            }

        } catch (Exception e) {
            messageLabel.setText("Cannot connect to server");
        }
    }

    @FXML
    public void goToRegister(ActionEvent event) {
        try {
            SceneManager.switchScene(event, "/fxml/register.fxml");
        } catch (Exception e) {
            messageLabel.setText("Cannot open register screen");
        }
    }
}