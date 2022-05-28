package fs3.dal.citizen;

import fs3.be.*;
import fs3.dal.citizen.CitizenDAO;
import fs3.enums.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class CitizenDAOTest {

    public CitizenDAOTest() throws Exception {
    }

    @Disabled
    @Test
    public void TestUpdateHealthConditions() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();

        Citizen citizen = citizenDAO.readAllCitizenInstances().get(0);

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

        citizen = citizenDAO.readAllCitizenInstances().get(0);

        Assertions.assertEquals(LimitationLevel.SEVERE_LIMITATION, citizen.getFunctionalAbilities().get(FunctionalAbility.DRINKING).getCurrentLimitationLevel());
    }

    @Disabled
    @Test
    //test create citizen
    public void TestCreateCitizen() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        CitizenInstance citizen = new CitizenInstance();

        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setName("Created");

        citizen.setPersonalInformation(personalInformation);

        Citizen newCitizen = citizenDAO.create(citizen);
        Assertions.assertEquals(11, newCitizen.getId());
        Assertions.assertEquals(CitizenInstance.class, newCitizen.getClass());
    }

    @Disabled
    @Test
    void testDeleteCitizen() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        CitizenInstance citizen = citizenDAO.readAllCitizenInstances().get(0);
        citizenDAO.delete(citizen);

        List<CitizenInstance> citizens = citizenDAO.readAllCitizenInstances();
        Assertions.assertEquals(1, citizens.size());
    }

    @Disabled
    @Test
    void copy() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        CitizenTemplate citizenTemplate = citizenDAO.readAllCitizenTemplates().get(0);
        CitizenInstance citizenInstance = new CitizenInstance(citizenTemplate);
        CitizenInstance createdInstance = (CitizenInstance) citizenDAO.create(citizenInstance);
    }

    @Disabled
    @Test
    void delete() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        List<CitizenTemplate> citizenTemplates = citizenDAO.readAllCitizenTemplates();
        for (int i = 7; i < citizenTemplates.size(); i++) {
            citizenDAO.delete(citizenTemplates.get(i));
        }
    }

    @Test
    @Disabled
    void update() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        CitizenTemplate citizen = citizenDAO.readAllCitizenTemplates().get(5);;
        citizen.setGeneralInformation(new GeneralInformation());
        citizen.getGeneralInformation().setCoping("He won't");

        citizenDAO.update(citizen);

        citizen = citizenDAO.readAllCitizenTemplates().get(5);
        Assertions.assertEquals("He won't", citizen.getGeneralInformation().getCoping());
    }

    @Test
    @Disabled
    void medicineList() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        CitizenInstance citizen = citizenDAO.readAllCitizenInstances().get(0);
        citizen.setMedicineList("test");

        citizenDAO.create(citizen);
    }

    @Test
    void observations() throws Exception {
        CitizenDAO citizenDAO = new CitizenDAO();
        CitizenInstance citizen = citizenDAO.readAllCitizenInstances().get(0);

        Assertions.assertEquals(120, citizen.getObservations().getBloodPressure().get(0).getMeasurement().getSystolic());
    }
}
