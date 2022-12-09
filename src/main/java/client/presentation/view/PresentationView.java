package main.java.client.presentation.view;

import client.model.User;
import client.presentation.view_model.PresentationModel;
import client.service.StudentService;

public abstract class PresentationView {
    protected PresentationModel model;
    protected StudentService studentService;
    protected User currentUser;

    protected PresentationView(StudentService studentService, User user) {
        this.studentService = studentService;
        this.currentUser = user;
    }

    public abstract void show();
    public abstract PresentationView getInput(String input);
}
