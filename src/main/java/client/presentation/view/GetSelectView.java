package main.java.client.presentation.view;

import client.model.Student;
import client.model.User;
import client.service.StudentService;

import java.util.List;

public class GetSelectView extends PresentationView{
    public GetSelectView(StudentService studentService, User user) {
        super(studentService, user);
    }

    @Override
    public void show() {
        List<Student> studentList = this.studentService.getAll();
        for (Student student : studentList) {
            System.out.println(student.getId() + ": " + student.getName());
        }

        System.out.println("Print 'quit' to exit");
        System.out.println("Select student id: ");
    }

    @Override
    public PresentationView getInput(String input) {
        if ("quit".equals(input)) {
            return switch (this.currentUser.getRole()) {
                case USER -> new IndexView(this.studentService, this.currentUser);
                case ADMIN -> new AdminView(this.studentService, this.currentUser);
                default -> null;
            };
        }

        int id;
        try {
            id = Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException();
        }

        return new GetView(this.studentService, this.currentUser, id);
    }
}
