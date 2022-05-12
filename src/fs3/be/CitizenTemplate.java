package fs3.be;

import fs3.enums.FunctionalAbility;
import fs3.enums.HealthCondition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class CitizenTemplate extends Citizen{
    public CitizenTemplate() {
        super();
    }

    //copy constructor
    public CitizenTemplate(CitizenTemplate other) {
        super();
        this.setPersonalInformation(other.getPersonalInformation() == null ? null : new PersonalInformation(other.getPersonalInformation()));
        this.setGeneralInformation(other.getGeneralInformation() == null ? null : new GeneralInformation(other.getGeneralInformation()));
        for (Map.Entry<HealthCondition, HealthConditionData> entry : other.getHealthConditions().entrySet()) {
            this.getHealthConditions().put(entry.getKey(), new HealthConditionData(entry.getValue()));
        }
        for (Map.Entry<FunctionalAbility, FunctionalAbilityData> entry : other.getFunctionalAbilities().entrySet()) {
            this.getFunctionalAbilities().put(entry.getKey(), new FunctionalAbilityData(entry.getValue()));
        }
    }
}
