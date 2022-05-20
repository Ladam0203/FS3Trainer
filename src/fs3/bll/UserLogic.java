package fs3.bll;

import fs3.be.Student;
import fs3.be.Teacher;
import fs3.be.User;
import fs3.dal.DAOFacade;
import fs3.dal.IDAOFacade;

import java.util.ArrayList;
import java.util.List;

public class UserLogic {
    private IDAOFacade daoFacade;

    public UserLogic(){
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
}
