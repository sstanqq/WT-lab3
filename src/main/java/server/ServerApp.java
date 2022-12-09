package main.java.server;

import server.controller.Server;

public class ServerApp {
    public static void main(String[] args) {
        Server server = new Server(5556);
        server.start();
        try {
            server.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
