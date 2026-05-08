package network;

import service.AuthService;
import service.TicketService;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 5000;
//this object responsibel about login,register,validation;
    public static final AuthService authService = new AuthService();
//    this object responsibel about creat tickets,store tickets,mange tickets;
    public static final TicketService ticketService = new TicketService();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server started on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(socket);
                new Thread(handler).start();
            }

        } catch (Exception e) {
            System.out.println("Server error: " + e.getMessage());
        }
    }
}