package model;

public class Ticket {
    private int id;
    private String studentUsername;
    private String title;
    private String description;
    private String reply;
    private TicketStatus status;

    public Ticket(int id, String studentUsername, String title, String description) {
        this.id = id;
        this.studentUsername = studentUsername;
        this.title = title;
        this.description = description;
        this.reply = "";
        this.status = TicketStatus.PENDING;
    }

    public int getId() {
        return id;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getReply() {
        return reply;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public void reply(String reply) {
        this.reply = reply;
        this.status = TicketStatus.ANSWERED;
    }
}