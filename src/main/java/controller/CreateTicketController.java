package controller;

import app.SceneManager;
import app.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateTicketController {
    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private Label messageLabel;

    @FXML
    public void submitTicket() {
        try {
            String response = Session.connection.sendRequest(
                    "CREATE_TICKET|" +
                            Session.currentUsername + "|" +
                            titleField.getText() + "|" +
                            descriptionArea.getText()
            );

            messageLabel.setText(response.split("\\|")[1]);

        } catch (Exception e) {
            messageLabel.setText("Server error");
        }
    }

    @FXML
    public void goBack(ActionEvent event) {
        try {
            SceneManager.switchScene(event, "/fxml/student_dashboard.fxml");
        } catch (Exception e) {
            messageLabel.setText("Cannot go back");
        }
    }
}