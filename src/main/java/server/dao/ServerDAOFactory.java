package main.java.server.dao;

public class ServerDAOFactory  {
    private static final ServerDAOFactory instance = new ServerDAOFactory();

    private ServerDAOFactory() {}

    public StudentDAO getStudentDAO() {
        return new StudentDAO();
    }

    public static ServerDAOFactory getInstance() {
        return instance;
    }
}
