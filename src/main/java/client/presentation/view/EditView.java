package main.java.client.presentation.view;

import client.model.Student;
import client.model.User;
import client.presentation.view.input.SetInputStudent;
import client.presentation.view_model.EditModelView;
import client.service.StudentService;
import org.javatuples.Pair;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EditView extends PresentationView{
    private final List<Pair<String, SetInputStudent>> inputs = Arrays.asList(
            new Pair<>("Print name:", (student, input) -> {
                student.setName(input);
                return true;
            }),
            new Pair<>("Birthday(dd/MM/yyyy):", (student, input) -> {
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                try {
                    student.setBirthday(LocalDate.parse(input, dateTimeFormatter));
                    return true;
                } catch (DateTimeParseException ex) {
                    System.out.println("Invalid format");
                }

                return false;
            }),
            new Pair<>("Characteristic:", (student, input) -> {
                student.setCharacteristic(input);
                return true;
            })
    );

    public EditView(StudentService studentService, User user, int id) {
        super(studentService, user);
        this.model = new EditModelView(studentService, id);
    }

    @Override
    public void show() {
        Student student;
        Scanner scanner = new Scanner(System.in);
        List<Student> items = this.model.getItems();
        if (items.isEmpty()) {
            System.out.println("Not found.");
            return;
        } else {
            student = items.get(0);
            System.out.println(student);
        }

        student.setLastModification(LocalDateTime.now());

        int i = 0;
        String input;
        System.out.println("Enter 'quit' to exit.");
        while (i < inputs.size()) {
            System.out.println(inputs.get(i).getValue0());
            input = scanner.nextLine();
            if (input.equals("quit")) {
                return;
            }

            if (inputs.get(i).getValue1().setInput(student, input)) {
                i++;
            } else {
                System.out.println("Invalid input!");
            }
        }

        if (!this.studentService.edit(student)) {
            System.out.println("Error writing: Probably student changed by other client.");
        }
    }

    @Override
    public PresentationView getInput(String input) {
        return new EditSelectView(this.studentService, this.currentUser);
    }
}
