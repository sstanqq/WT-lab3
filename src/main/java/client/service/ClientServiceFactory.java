package main.java.client.service;

import client.dao.StudentDAO;

public class ClientServiceFactory {
    private static final ClientServiceFactory instance = new ClientServiceFactory();

    private ClientServiceFactory() {}

    public StudentService getStudentService(StudentDAO studentDAO) {

        return new StudentService(studentDAO);
    }

    public static ClientServiceFactory getInstance() {
        return instance;
    }
}
