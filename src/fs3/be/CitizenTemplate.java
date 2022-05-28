package fs3.be;

import fs3.enums.FunctionalAbility;
import fs3.enums.HealthCondition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CitizenTemplate extends Citizen {
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
        this.setMedicineListProperty(new SimpleStringProperty(other.getMedicineList()));
        this.setSchool(other.getSchool() == null ? null : new School(other.getSchool()));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CitizenTemplate other = (CitizenTemplate) obj;
        if (!Objects.equals(super.getId(), other.getId())) {
            return false;
        }
        return true;
    }
}
