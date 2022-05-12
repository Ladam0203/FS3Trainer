package fs3.be;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CitizenInstanceTest {
    @Test
    void createBasedOnTemplate() {
        CitizenTemplate ct = new CitizenTemplate();
        PersonalInformation pi = new PersonalInformation();
        pi.setName("John");
        ct.setPersonalInformation(pi);

        CitizenInstance ci = new CitizenInstance(ct);

        ct.getPersonalInformation().setName("Peter");

        assertEquals("John", ci.getPersonalInformation().getName());
    }
}