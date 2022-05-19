package fs3.be;

import fs3.enums.FunctionalAbility;
import fs3.enums.HealthCondition;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Map;

public class CitizenInstance extends Citizen {
    public CitizenInstance() {
        super();
    }

    //sort of similiar to a copy constructor but taking a template
    public CitizenInstance(CitizenTemplate citizenTemplate) {
        super();
        this.setPersonalInformation(citizenTemplate.getPersonalInformation() == null ? null : new PersonalInformation(citizenTemplate.getPersonalInformation()));
        this.setGeneralInformation(citizenTemplate.getGeneralInformation() == null ? null : new GeneralInformation(citizenTemplate.getGeneralInformation()));
        for (Map.Entry<HealthCondition, HealthConditionData> entry : citizenTemplate.getHealthConditions().entrySet()) {
            this.getHealthConditions().put(entry.getKey(), new HealthConditionData(entry.getValue()));
        }
        for (Map.Entry<FunctionalAbility, FunctionalAbilityData> entry : citizenTemplate.getFunctionalAbilities().entrySet()) {
            this.getFunctionalAbilities().put(entry.getKey(), new FunctionalAbilityData(entry.getValue()));
        }
    }


}
