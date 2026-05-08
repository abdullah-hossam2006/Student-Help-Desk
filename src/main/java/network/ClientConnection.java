package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientConnection {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public ClientConnection() throws IOException {
        socket = new Socket("localhost", 5000);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public String sendRequest(String request) throws IOException {
        output.println(request);
        return input.readLine();
    }

    public void close() throws IOException {
        socket.close();
    }
}