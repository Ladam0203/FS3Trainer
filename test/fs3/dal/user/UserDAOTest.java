package fs3.dal.user;

import fs3.be.Student;
import fs3.be.Teacher;
import fs3.be.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    @Test
    //test reading user
    void readUser() throws Exception {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.readUser("t", "t");

        assertEquals(Teacher.class, user.getClass());
        assertEquals("Jeppe", ((Teacher)user).getName());
    }
}