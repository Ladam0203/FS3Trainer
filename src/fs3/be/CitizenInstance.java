package fs3.be;

import javafx.beans.property.SimpleIntegerProperty;

public class CitizenInstance extends Citizen {
    public CitizenInstance() {
        super();
    }
    public CitizenInstance(CitizenTemplate citizenTemplate) {
        id = new SimpleIntegerProperty();
        personalInformation = citizenTemplate.personalInformation;
        generalInformation = citizenTemplate.generalInformation;
        healthConditions = citizenTemplate.healthConditions;
        functionalAbilities = citizenTemplate.functionalAbilities;
    }
}
