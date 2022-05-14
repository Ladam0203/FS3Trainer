package fs3.dal.user;

import fs3.be.CitizenInstance;
import fs3.be.Student;
import fs3.dal.citizen.CitizenDAO;
import org.junit.jupiter.api.Test;

public class StudentDAOTest {
    @Test
    void assignCitizen() throws Exception {
        UserDAO userDAO = new UserDAO();
        CitizenDAO citizenDAO = new CitizenDAO();

        Student student = userDAO.readAllStudents().get(0);
        CitizenInstance citizenInstance = citizenDAO.readAllCitizenInstances().get(0);

        student.assignCitizen(citizenInstance);

        userDAO.update(student);
    }
}
