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
        functionalAbilityData.setCurrentLimitationLevel(LimitationLevel.SEVERE_LIMITATION);
        functionalAbilityData.setExpectedLimitationLevel(LimitationLevel.SLIGHT_LIMITATION);
        functionalAbilityData.setProfessionalNote("professional note");
        functionalAbilityData.setPerformance(Performance.DOES_NOT_PERFORM_BY_THEMSELF);
        functionalAbilityData.setPerceivedLimitationLevel(PerceivedLimitationLevel.EXPERIENCES_LIMITATION);
        functionalAbilityData.setCitizenRequest("request");
        functionalAbilityData.setFollowUpDate(LocalDate.now());
        functionalAbilityData.setObservationNote("observation notesss");

        citizen.getFunctionalAbilities().put(FunctionalAbility.DRINKING, functionalAbilityData);

        citizenDAO.update(citizen);

        citizen = citizenDAO.readAll().get(0);

        Assertions.assertEquals(LimitationLevel.SEVERE_LIMITATION, citizen.getFunctionalAbilities().get(FunctionalAbility.DRINKING).getCurrentLimitationLevel());
    }
}
