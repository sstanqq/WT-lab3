package main.java.client.dao;

public class ClientDAOFactory {
    private static final ClientDAOFactory instance = new ClientDAOFactory();

    private ClientDAOFactory() {}

    public StudentDAO getStudentDAO() {
        return new StudentDAO();
    }

    public static ClientDAOFactory getInstance() {
        return instance;
    }
}
