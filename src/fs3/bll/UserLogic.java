package fs3.bll;

import fs3.be.Student;
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

    public void updateStudent(Student student) throws Exception {
        daoFacade.updateUser(student);
    }

    public User tryLogin(String username, String password) throws Exception {
        return daoFacade.readUser(username, password);
    }

    public User createUser(User user) throws Exception {
        return daoFacade.createUser(user);
    }
}
