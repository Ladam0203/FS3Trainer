package fs3.dal.user;

import fs3.be.Student;
import fs3.be.Teacher;
import fs3.be.User;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest {
    @Disabled
    @Test
    //test reading user
    void readUser() throws Exception {
        UserDAO userDAO = new UserDAO();
        User user = userDAO.read("t", "t");

        assertEquals(Teacher.class, user.getClass());
        assertEquals("Jeppe", ((Teacher)user).getName());
    }

    @Test
    @Disabled
    void readStudents() throws Exception {
        UserDAO userDAO = new UserDAO();
        Student student = userDAO.readAllStudents().get(0);

        assertEquals("Milo", student.getName());
    }

    @Test
    void readAllTeachers() throws Exception {
        UserDAO userDAO = new UserDAO();
        Teacher teacher = userDAO.readAllTeachers().get(0);

        assertEquals("Jeppe", teacher.getName());
    }

    @Test
    void readAllStudents() throws Exception {
        UserDAO userDAO = new UserDAO();
        Student student = userDAO.readAllStudents().get(0);

        assertEquals("Milo", student.getName());
    }

}