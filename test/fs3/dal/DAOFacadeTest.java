package fs3.dal;

import fs3.be.Student;
import fs3.be.User;
import fs3.dal.user.StudentDAO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAOFacadeTest {
    @Test
    void readUser() throws Exception {
        IDAOFacade daoFacade = new DAOFacade();
        User user = daoFacade.readUser("s", "s");
        Student student = (Student) user;

        assertEquals("s", student.getUsername());
        assertEquals("s", student.getPassword());
        assertEquals("Milo", student.getName());
    }
}