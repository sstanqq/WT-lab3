package main.java.server.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server extends Thread {
    private final int port;
    private static LinkedList<ClientHandler> clients = new LinkedList<>();

    public Server(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        ServerSocket server = null;
        try {
            try {
                server = new ServerSocket(this.port);
                while (true) {
                    Socket socket = server.accept();
                    try {
                        clients.add(new ClientHandler(socket));
                    } catch (IOException e) {
                        socket.close();
                    }
                }
            } catch (IOException ignored) {
            } finally {
                if ((server != null) && !server.isClosed()) {
                    server.close();
                }
            }

        } catch (IOException ignored) {
        }
    }
}
