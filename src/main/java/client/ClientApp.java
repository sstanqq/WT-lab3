package main.java.client;

import client.dao.ClientDAOFactory;
import client.presentation.Presentation;
import client.service.ClientServiceFactory;

public class ClientApp {
    public static void main(String[] args) {
        ClientServiceFactory factory = ClientServiceFactory.getInstance();
        ClientDAOFactory daoFactory = ClientDAOFactory.getInstance();
        Presentation presentation = new Presentation(factory.getStudentService(daoFactory.getStudentDAO()));
        presentation.show();
    }
}
