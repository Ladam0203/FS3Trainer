package fs3.be;

import fs3.enums.HealthCondition;
import fs3.enums.HealthConditionState;
import org.junit.jupiter.api.Test;

import java.io.Serial;

import static org.junit.jupiter.api.Assertions.*;

class CitizenTemplateTest {
    @Test
    //test clone
    void testClone() {
        CitizenTemplate ct = new CitizenTemplate();
        PersonalInformation pi = new PersonalInformation();
        pi.setName("John");

        HealthConditionData hcd = new HealthConditionData();
        hcd.setHealthConditionState(HealthConditionState.ACTIVE);
        ct.getHealthConditions().put(HealthCondition.CIRCADIAN_RHYTHM_PROBLEMS, hcd);

        ct.setPersonalInformation(pi);

        CitizenTemplate ct2 = new CitizenTemplate(ct);

        ct.getPersonalInformation().setName("Jane");

        assertEquals("John", ct2.getPersonalInformation().getName());
        assertEquals(HealthConditionState.ACTIVE, ct2.getHealthConditions().get(HealthCondition.CIRCADIAN_RHYTHM_PROBLEMS).getHealthConditionState());
    }
}