package main.java.server.controller;

import client.dto.StudentRequestDTO;
import client.dto.StudentResponseDTO;
import client.model.Response;
import client.model.Student;
import client.model.User;
import server.service.StudentService;

import java.util.List;

public class ClientController {
    private final StudentService service;

    public ClientController(StudentService service) {
        this.service = service;
    }

    public StudentResponseDTO create(StudentRequestDTO request) {
        Student student;
        StudentResponseDTO response = new StudentResponseDTO();
        if (request.getBody() instanceof Student body) {
            student = body;
        } else {
            response.setResponse(Response.ERROR);
            return response;
        }

        if (this.service.create(student)) {
            response.setResponse(Response.OK);
        } else {
            response.setResponse(Response.ERROR);
        }

        return response;
    }

    public StudentResponseDTO edit(StudentRequestDTO request) {
        Student student;
        StudentResponseDTO response = new StudentResponseDTO();
        if (request.getBody() instanceof Student body) {
            student = body;
        } else {
            response.setResponse(Response.ERROR);
            return response;
        }

        if (this.service.edit(student)) {
            response.setResponse(Response.OK);
        } else {
            response.setResponse(Response.ERROR);
        }

        return response;
    }

    public StudentResponseDTO getAll() {
        List<Student> students = this.service.getAll();
        StudentResponseDTO response = new StudentResponseDTO();
        if (students == null) {
            response.setResponse(Response.ERROR);
            response.setBody(null);
        }
        else {
            response.setResponse(Response.OK);
            response.setBody(students);
        }

        return response;
    }

    public StudentResponseDTO get(StudentRequestDTO request) {
        int id;
        StudentResponseDTO response = new StudentResponseDTO();
        if (request.getBody() instanceof Integer) {
            id = (int) request.getBody();
        } else {
            response.setResponse(Response.ERROR);
            return response;
        }

        Student studentToSend = this.service.get(id);
        if (studentToSend != null) {
            response.setResponse(Response.OK);
            response.setBody(studentToSend);
        } else {
            response.setResponse(Response.ERROR);
        }

        return response;
    }

    public StudentResponseDTO register(StudentRequestDTO request) {
        User user;
        StudentResponseDTO response = new StudentResponseDTO();
        if (request.getBody() instanceof User body) {
            user = body;
        } else {
            response.setResponse(Response.ERROR);
            return response;
        }

        User userResult = this.service.register(user);
        if (userResult != null) {
            response.setResponse(Response.OK);
            response.setBody(userResult);
        } else {
            response.setResponse(Response.ERROR);
        }

        return response;
    }

    public StudentResponseDTO login(StudentRequestDTO request) {
        User user;
        StudentResponseDTO response = new StudentResponseDTO();
        if (request.getBody() instanceof User body) {
            user = body;
        } else {
            response.setResponse(Response.ERROR);
            return response;
        }

        User userResult = this.service.login(user);
        if (userResult != null) {
            response.setResponse(Response.OK);
            response.setBody(userResult);
        } else {
            response.setResponse(Response.ERROR);
        }

        return response;
    }

    public StudentResponseDTO notFound() {
        StudentResponseDTO response = new StudentResponseDTO();
        response.setResponse(Response.NOTFOUND);
        return response;
    }
}
