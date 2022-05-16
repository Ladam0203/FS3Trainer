package fs3.dal;

import fs3.be.*;
import fs3.dal.citizen.CitizenDAO;
import fs3.dal.user.UserDAO;

import java.util.List;

public class DAOFacade implements IDAOFacade {
    CitizenDAO citizenDAO = new CitizenDAO();
    UserDAO userDAO = new UserDAO();

    @Override
    public void updateCitizen(Citizen citizen) throws Exception {
        citizenDAO.update(citizen);
    }

    /*
     * Call the readUser with the username and password, if the value returned is not null,
     * it will be either an Admin, Teacher or a Student. Depending on the type of user that you wanted to log in,
     * you can safely downcast the user to the correct type.
     *
     * Eg.
     * User user = daoFacade.readUser("username", "password");
     * if (user != null) {
     *    Student student = (Student) user;
     * }
     */
    @Override
    public User readUser(String username, String password) throws Exception {
        return userDAO.readUser(username, password);
    }

    @Override
    public List<CitizenInstance> readAllCitizenInstances() throws Exception {
        return citizenDAO.readAllCitizenInstances();
    }

    @Override
    public List<CitizenTemplate> readAllCitizenTemplates() throws Exception {
        return citizenDAO.readAllCitizenTemplates();
    }

    @Override
    public Citizen createCitizen(Citizen citizen) throws Exception {
        return citizenDAO.create(citizen);
    }

    @Override
    public void deleteCitizen(Citizen citizen) throws Exception {
        citizenDAO.delete(citizen);
    }

    @Override
    public List<Student> readAllStudents() throws Exception {
        return userDAO.readAllStudents();
    }

    @Override
    public void updateUser(User user) throws Exception {
        userDAO.update(user);
    }

    @Override
    public void deleteUser(User user) throws Exception {
        userDAO.delete(user);
    }
}
