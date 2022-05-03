package fs3.dal;

import fs3.be.Citizen;
import fs3.dal.citizen.CitizenDAO;
import fs3.enums.HealthCondition;
import fs3.enums.HealthConditionState;
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

        //Assertions.assertEquals(1, citizen.getId());
        //Assertions.assertEquals("Romi", citizen.getPersonalInformation().getName());
        //Assertions.assertEquals("a", citizen.getGeneralInformation().getCoping());
        //Assertions.assertNotNull(citizen.getHealthConditions());
        //Assertions.assertNotNull(citizen.getHealthConditions().get(HealthCondition.PROBLEMS_WITH_PERSONAL_CARE));
        Assertions.assertEquals(HealthConditionState.ACTIVE, citizen.getHealthConditions().get(HealthCondition.PROBLEMS_WITH_PERSONAL_CARE).getHealthConditionState());

    }
}
