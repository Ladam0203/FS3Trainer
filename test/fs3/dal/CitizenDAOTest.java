package fs3.dal;

import fs3.be.Citizen;
import fs3.dal.citizen.CitizenDAO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CitizenDAOTest {
    @Test
    public void testGetAllFirstNotNull() throws Exception {
        DAO<Citizen> citizenDAO = new CitizenDAO();
        Citizen citizen = citizenDAO.readAll().get(0);

        Assertions.assertNotNull(citizen);
    }

    @Test
    public void testGetAllAllData() throws Exception {
        DAO<Citizen> citizenDAO = new CitizenDAO();
        Citizen citizen = citizenDAO.readAll().get(0);

        Assertions.assertEquals(1, citizen.getIdProperty());
        Assertions.assertEquals("Romi", citizen.getPersonalInformationProperty().getNameProperty());
    }
}
