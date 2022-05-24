package fs3.be;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CitizenTest {
    //test toString
    @Test
    void testToString() {
        Citizen citizen = new CitizenInstance();
        citizen.setId(1);
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setName("name");
        personalInformation.setAge(1);
        citizen.setPersonalInformation(personalInformation);

        Assertions.assertEquals("[1]: name (1)", citizen.toString());
    }
}
