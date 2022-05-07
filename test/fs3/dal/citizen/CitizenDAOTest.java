package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.FunctionalAbilityData;
import fs3.be.HealthConditionData;
import fs3.dal.citizen.CitizenDAO;
import fs3.enums.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class CitizenDAOTest {

    //test update
    @Test
    public void TestUpdateHealthConditions() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();

        Citizen citizen = citizenDAO.readAll().get(0);

        FunctionalAbilityData functionalAbilityData = new FunctionalAbilityData();
        functionalAbilityData.setCurrentLimitationLevel(LimitationLevel.NOT_RELEVANT);

        citizen.getFunctionalAbilities().put(FunctionalAbility.COOKING, functionalAbilityData);

        citizenDAO.update(citizen);

        citizen = citizenDAO.readAll().get(0);

        Assertions.assertEquals(LimitationLevel.NOT_RELEVANT, citizen.getFunctionalAbilities().get(FunctionalAbility.COOKING).getCurrentLimitationLevel());
    }
}
