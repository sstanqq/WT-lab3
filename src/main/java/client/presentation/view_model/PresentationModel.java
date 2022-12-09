package main.java.client.presentation.view_model;

import client.model.Student;
import client.service.StudentService;

import java.util.List;

public abstract class PresentationModel {
    protected StudentService studentService;
    public PresentationModel(StudentService studentService) {
        this.studentService = studentService;
    }

    public abstract List<Student> getItems();
}
