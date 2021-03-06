package fs3.bll;

import fs3.be.*;
import fs3.dal.DAOFacade;
import fs3.dal.IDAOFacade;

import java.util.List;

public class UserLogic {
    private final IDAOFacade daoFacade;

    public UserLogic() {
        daoFacade = new DAOFacade();
    }

    public List<Student> readAllStudents() throws Exception {
        return daoFacade.readAllStudents();
    }

    public List<Teacher> readAllTeachers() throws Exception {
        return daoFacade.readAllTeachers();
    }

    public void updateUser(User user) throws Exception {
        daoFacade.updateUser(user);
    }

    public User tryLogin(String username, String password) throws Exception {
        return daoFacade.readUser(username, password);
    }

    public User createUser(User user) throws Exception {
        return daoFacade.createUser(user);
    }

    public void deleteUser(User user) throws Exception {
        daoFacade.deleteUser(user);
    }

    public List<Student> readAllStudentsFrom(School school) throws Exception {
        return daoFacade.readAllStudentsFrom(school);
    }

    public List<Admin> readAllAdmins() throws Exception {
        return daoFacade.readAllAdmins();
    }

}
