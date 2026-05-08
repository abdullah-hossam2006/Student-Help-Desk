package controller;

import app.SceneManager;
import app.Session;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.TicketTableItem;

public class MyTicketsController {
    @FXML private TableView<TicketTableItem> ticketsTable;
    @FXML private TableColumn<TicketTableItem, Integer> idColumn;
    @FXML private TableColumn<TicketTableItem, String> titleColumn;
    @FXML private TableColumn<TicketTableItem, String> statusColumn;
    @FXML private TableColumn<TicketTableItem, String> replyColumn;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        replyColumn.setCellValueFactory(new PropertyValueFactory<>("reply"));

        loadTickets();
    }

    @FXML
    public void loadTickets() {
        try {
            String response = Session.connection.sendRequest(
                    "GET_MY_TICKETS|" + Session.currentUsername
            );

            ObservableList<TicketTableItem> items = FXCollections.observableArrayList();

            String[] tickets = response.split("\\|");

            for (int i = 1; i < tickets.length; i++) {
                String[] data = tickets[i].split(",", -1);

                items.add(new TicketTableItem(
                        Integer.parseInt(data[0]),
                        data[1],
                        data[2],
                        data[3],
                        data[4],
                        data[5]
                ));
            }

            ticketsTable.setItems(items);

        } catch (Exception e) {
            messageLabel.setText("Cannot load tickets");
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