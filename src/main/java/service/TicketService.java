package service;

import model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketService {
    private final List<Ticket> tickets = new ArrayList<>();
    private int nextId = 1;

    public synchronized String createTicket(String username, String title, String description) {
        if (title == null || description == null || title.isBlank() || description.isBlank()) {
            return "ERROR|Title and description are required";
        }

        Ticket ticket = new Ticket(nextId++, username, title, description);
        tickets.add(ticket);

        return "SUCCESS|Ticket created successfully";
    }

    public synchronized String getMyTickets(String username) {
        StringBuilder result = new StringBuilder("TICKETS");

        for (Ticket ticket : tickets) {
            if (ticket.getStudentUsername().equals(username)) {
                result.append("|")
                        .append(ticket.getId()).append(",")
                        .append(ticket.getStudentUsername()).append(",")
                        .append(ticket.getTitle()).append(",")
                        .append(ticket.getDescription()).append(",")
                        .append(ticket.getStatus()).append(",")
                        .append(ticket.getReply());
            }
        }

        return result.toString();
    }

    public synchronized String getAllTickets() {
        StringBuilder result = new StringBuilder("TICKETS");

        for (Ticket ticket : tickets) {
            result.append("|")
                    .append(ticket.getId()).append(",")
                    .append(ticket.getStudentUsername()).append(",")
                    .append(ticket.getTitle()).append(",")
                    .append(ticket.getDescription()).append(",")
                    .append(ticket.getStatus()).append(",")
                    .append(ticket.getReply());
        }

        return result.toString();
    }

    public synchronized String replyTicket(int id, String reply) {
        if (reply == null || reply.isBlank()) {
            return "ERROR|Reply cannot be empty";
        }

        for (Ticket ticket : tickets) {
            if (ticket.getId() == id) {
                ticket.reply(reply);
                return "SUCCESS|Reply sent successfully";
            }
        }

        return "ERROR|Ticket not found";
    }
}