package main.java.server.controller;

import client.dto.StudentRequestDTO;
import client.dto.StudentResponseDTO;
import server.dao.ServerDAOFactory;
import server.service.StudentService;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static client.model.Request.*;

public class ClientHandler extends Thread {
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    private final ClientController controller;

    public ClientHandler(Socket socket) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        StudentService service = new StudentService(ServerDAOFactory.getInstance().getStudentDAO());
        this.controller = new ClientController(service);
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                StudentRequestDTO request = (StudentRequestDTO) this.in.readObject();
                StudentResponseDTO response = switch (request.getRequest()) {
                    case CREATE -> this.controller.create(request);
                    case GET -> this.controller.get(request);
                    case GET_ALL -> this.controller.getAll();
                    case EDIT -> this.controller.edit(request);
                    case REGISTER -> this.controller.register(request);
                    case LOGIN -> this.controller.login(request);
                    default -> this.controller.notFound();
                };

                this.out.writeObject(response);
                this.out.flush();
            }

        } catch (EOFException ignored) {
            // End of socket stream.
        } catch (IOException | ClassNotFoundException e) {
            System.out.printf("Failed read: %s%n", e.getMessage());
        }
    }
}
