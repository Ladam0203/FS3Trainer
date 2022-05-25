package fs3.dal.user;

import fs3.be.School;
import fs3.be.Student;
import fs3.be.Teacher;
import fs3.be.User;
import fs3.dal.school.SchoolDAO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Disabled
    @Test
    void readAllTeachers() throws Exception {
        UserDAO userDAO = new UserDAO();
        Teacher teacher = userDAO.readAllTeachers().get(0);

        assertEquals("Jeppe", teacher.getName());
    }

    @Disabled
    @Test
    void readAllStudents() throws Exception {
        UserDAO userDAO = new UserDAO();
        Student student = userDAO.readAllStudents().get(0);

        assertEquals("Milo", student.getName());
    }

    @Test
    @Disabled
    void createUser() throws Exception {
        UserDAO userDAO = new UserDAO();
        Student student = new Student("Adi", "Adi");
        student.setName("Adi");

        userDAO.create(student);

        assertEquals("Adi", ((Student)userDAO.read("Adi", "Adi")).getName());
        assertEquals(18, ((Student)userDAO.read("Adi", "Adi")).getId());
    }

    @Disabled
    @Test
    void createTeacher() throws Exception {
        UserDAO userDAO = new UserDAO();
        Teacher teacher = new Teacher("Test", "Test");
        teacher.setName("Test");

        userDAO.create(teacher);
    }

    @Test
    void updateTeacher() throws Exception {
        UserDAO userDAO = new UserDAO();
        List<Teacher> teachers = userDAO.readAllTeachers();
        Teacher teacher = teachers.get(teachers.size() - 1);

        SchoolDAO schoolDAO = new SchoolDAO();
        List<School> schools = schoolDAO.readAll();

        teacher.setSchool(schools.get(0));

        userDAO.update(teacher);
    }

}