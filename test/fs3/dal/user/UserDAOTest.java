package fs3.dal.user;

import fs3.be.Student;
import fs3.be.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    @Test
    //test reading user
    void readUser() throws Exception {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.readUser("", "");

        assertEquals(Student.class, user.getClass());
        assertEquals("Milo", ((Student) user).getName());
    }
}