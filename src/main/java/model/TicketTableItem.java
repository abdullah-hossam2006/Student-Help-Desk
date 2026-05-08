package model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TicketTableItem {
    private SimpleIntegerProperty id;
    private SimpleStringProperty studentUsername;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleStringProperty status;
    private SimpleStringProperty reply;

    public TicketTableItem(int id, String studentUsername, String title,
                           String description, String status, String reply) {
        this.id = new SimpleIntegerProperty(id);
        this.studentUsername = new SimpleStringProperty(studentUsername);
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.status = new SimpleStringProperty(status);
        this.reply = new SimpleStringProperty(reply);
    }

    public int getId() {
        return id.get();
    }

    public String getStudentUsername() {
        return studentUsername.get();
    }

    public String getTitle() {
        return title.get();
    }

    public String getDescription() {
        return description.get();
    }

    public String getStatus() {
        return status.get();
    }

    public String getReply() {
        return reply.get();
    }
}