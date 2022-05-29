package fs3.dal.school;

import fs3.be.School;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class SchoolDAOTest {
    @Disabled
    @Test
    void testReadAll() throws Exception {
        SchoolDAO dao = new SchoolDAO();
        Assertions.assertEquals(3, dao.readAll().size());
        for (School school : dao.readAll()) {
            System.out.println(school.getId());
            System.out.println(school.getName());
        }
    }
}
