package network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true)
        ) {
            String request;

            while ((request = input.readLine()) != null) {
                String response = handleRequest(request);
                output.println(response);
            }

        } catch (Exception e) {
            System.out.println("Client disconnected");
        }
    }

    private String handleRequest(String request) {
        try {
            String[] parts = request.split("\\|", -1);

            switch (parts[0]) {
                case "REGISTER":
                    return Server.authService.register(parts[1], parts[2], parts[3]);

                case "LOGIN":
                    return Server.authService.login(parts[1], parts[2]);

                case "CREATE_TICKET":
                    return Server.ticketService.createTicket(parts[1], parts[2], parts[3]);

                case "GET_MY_TICKETS":
                    return Server.ticketService.getMyTickets(parts[1]);

                case "GET_ALL_TICKETS":
                    return Server.ticketService.getAllTickets();

                case "REPLY_TICKET":
                    return Server.ticketService.replyTicket(
                            Integer.parseInt(parts[1]),
                            parts[2]
                    );

                default:
                    return "ERROR|Unknown command";
            }

        } catch (Exception e) {
            return "ERROR|Invalid command format";
        }
    }
}