package fs3.dal.school;

import fs3.be.School;
import org.junit.jupiter.api.Test;

public class SchoolDAOTest {
    @Test
    void testReadAll() throws Exception {
        SchoolDAO dao = new SchoolDAO();
        for (School school : dao.readAll()) {
            System.out.println(school.getId());
            System.out.println(school.getName());
        }
    }
}
