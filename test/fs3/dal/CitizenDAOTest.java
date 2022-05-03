package fs3.dal;

import fs3.be.Citizen;
import fs3.dal.citizen.CitizenDAO;
import fs3.enums.HealthCondition;
import fs3.enums.HealthConditionState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CitizenDAOTest {
    @Disabled
    @Test
    public void testGetAllFirstNotNull() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        Citizen citizen = citizenDAO.readAll().get(0);

        Assertions.assertNotNull(citizen);
    }

    @Disabled
    @Test
    public void testGetAllAllData() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        Citizen citizen = citizenDAO.readAll().get(0);

        Assertions.assertEquals(1, citizen.getId());
        Assertions.assertEquals("Romi", citizen.getPersonalInformation().getName());
        Assertions.assertEquals("a", citizen.getGeneralInformation().getCoping());
        Assertions.assertNotNull(citizen.getHealthConditions());
        Assertions.assertNotNull(citizen.getHealthConditions().get(HealthCondition.PROBLEMS_WITH_PERSONAL_CARE));
        Assertions.assertEquals(HealthConditionState.ACTIVE, citizen.getHealthConditions().get(HealthCondition.PROBLEMS_WITH_PERSONAL_CARE).getHealthConditionState());
        Assertions.assertNull(citizen.getHealthConditions().get(HealthCondition.ACUTE_PAIN));

    }

    @Test
    //test update
    public void testUpdate() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();

        Citizen citizen = citizenDAO.readAll().get(0);

        citizen.getPersonalInformation().setName("Bruh123");

        citizen.getGeneralInformation().setCoping("b");

        citizen.getHealthConditions().get(HealthCondition.PROBLEMS_WITH_PERSONAL_CARE).setHealthConditionState(HealthConditionState.INACTIVE);

        citizenDAO.update(citizen);

        citizen = citizenDAO.readAll().get(0);

        Assertions.assertEquals("Bruh123", citizen.getPersonalInformation().getName());
        Assertions.assertEquals("b", citizen.getGeneralInformation().getCoping());
        Assertions.assertEquals(HealthConditionState.INACTIVE, citizen.getHealthConditions().get(HealthCondition.PROBLEMS_WITH_PERSONAL_CARE).getHealthConditionState());
    }
}
