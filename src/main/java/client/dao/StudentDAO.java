package main.java.client.dao;

import client.dto.StudentResponseDTO;
import client.model.Request;
import client.model.Response;
import client.model.Student;
import client.model.User;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private final SocketManager socketManager = new SocketManager("localhost", 5556);

    public boolean edit(Student newValue) {
        StudentResponseDTO response = socketManager
                .sendRequest(newValue, Request.EDIT);
        return (response != null)
                && (response.getResponse() == Response.OK);
    }

    public List<Student> getAll() {
        StudentResponseDTO response = socketManager
                .sendRequest(null, Request.GET_ALL);

        if ((response != null)
                && (response.getResponse() == Response.OK)
                && (response.getBody() instanceof ArrayList<?>)) {
            try {
                return (ArrayList<Student>) response.getBody();
            } catch (ClassCastException e) {
                return new ArrayList<>();
            }
        }

        return new ArrayList<>();
    }

    public Student get(int id) {
        StudentResponseDTO response = socketManager
                .sendRequest(id, Request.GET);

        if ((response != null)
                && (response.getResponse() == Response.OK)
                && (response.getBody() instanceof Student student)) {
            return student;
        }

        return null;
    }

    public boolean create(Student item) {
        StudentResponseDTO response = socketManager
                .sendRequest(item, Request.CREATE);
        return (response != null)
                && (response.getResponse() == Response.OK);
    }

    public User register(User user) {
        StudentResponseDTO response = socketManager
                .sendRequest(user, Request.REGISTER);
        if (response.getBody() instanceof User body) {
            return body;
        }

        return null;
    }

    public User login(User user) {
        StudentResponseDTO response = socketManager
                .sendRequest(user, Request.LOGIN);
        if (response.getBody() instanceof User body) {
            return body;
        }

        return null;
    }
}
