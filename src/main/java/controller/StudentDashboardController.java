package controller;

import app.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class StudentDashboardController {

    @FXML
    public void goToCreateTicket(ActionEvent event) throws Exception {
        SceneManager.switchScene(event, "/fxml/create_ticket.fxml");
    }

    @FXML
    public void goToMyTickets(ActionEvent event) throws Exception {
        SceneManager.switchScene(event, "/fxml/my_tickets.fxml");
    }

    @FXML
    public void logout(ActionEvent event) throws Exception {
        SceneManager.switchScene(event, "/fxml/login.fxml");
    }
}