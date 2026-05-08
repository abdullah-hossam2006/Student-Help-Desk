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

public class AdminDashboardController {
    @FXML private TableView<TicketTableItem> ticketsTable;
    @FXML private TableColumn<TicketTableItem, Integer> idColumn;
    @FXML private TableColumn<TicketTableItem, String> studentColumn;
    @FXML private TableColumn<TicketTableItem, String> titleColumn;
    @FXML private TableColumn<TicketTableItem, String> descriptionColumn;
    @FXML private TableColumn<TicketTableItem, String> statusColumn;
    @FXML private TableColumn<TicketTableItem, String> replyColumn;
    @FXML private TextArea replyArea;
    @FXML private Label messageLabel;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        studentColumn.setCellValueFactory(new PropertyValueFactory<>("studentUsername"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        replyColumn.setCellValueFactory(new PropertyValueFactory<>("reply"));

        loadTickets();
    }

    @FXML
    public void loadTickets() {
        try {
            String response = Session.connection.sendRequest("GET_ALL_TICKETS");

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
    public void replyToTicket() {
        try {
            TicketTableItem selected = ticketsTable.getSelectionModel().getSelectedItem();

            if (selected == null) {
                messageLabel.setText("Please select a ticket");
                return;
            }

            String response = Session.connection.sendRequest(
                    "REPLY_TICKET|" + selected.getId() + "|" + replyArea.getText()
            );

            messageLabel.setText(response.split("\\|")[1]);
            loadTickets();

        } catch (Exception e) {
            messageLabel.setText("Cannot send reply");
        }
    }

    @FXML
    public void logout(ActionEvent event) {
        try {
            SceneManager.switchScene(event, "/fxml/login.fxml");
        } catch (Exception e) {
            messageLabel.setText("Cannot logout");
        }
    }
}