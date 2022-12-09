package main.java.client.dao;

import client.dto.StudentRequestDTO;
import client.dto.StudentResponseDTO;
import client.model.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SocketManager {
    private final String ip;
    private final int port;

    public SocketManager(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public StudentResponseDTO sendRequest(Object body, Request type) {
        Socket client = null;
        try {
            try {
                client = new Socket(this.ip, this.port);

                ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
                ObjectInputStream is = new ObjectInputStream(client.getInputStream());

                StudentRequestDTO req = new StudentRequestDTO();
                req.setBody(body);
                req.setRequest(type);

                os.writeObject(req);
                os.flush();

                return (StudentResponseDTO) is.readObject();

            } catch (IOException | ClassNotFoundException e) {
                System.out.printf("Error client: %s%n", e.getMessage());
            } finally {
                if ((client != null) && !client.isClosed()) {
                    client.close();
                }
            }

        } catch (IOException e) {
            System.out.printf("Error client: %s%n", e.getMessage());
        }

        return null;
    }
}
