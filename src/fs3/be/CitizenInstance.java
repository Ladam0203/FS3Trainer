package fs3.be;

import fs3.enums.FunctionalAbility;
import fs3.enums.HealthCondition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

public class CitizenInstance extends Citizen {
    public CitizenInstance() {
        super();
    }

    //sort of similar to a copy constructor but taking a template
    public CitizenInstance(CitizenTemplate citizenTemplate) {
        super();
        //personal information
        this.setPersonalInformation(citizenTemplate.getPersonalInformation() == null ? null : new PersonalInformation(citizenTemplate.getPersonalInformation()));
        //general
        this.setGeneralInformation(citizenTemplate.getGeneralInformation() == null ? null : new GeneralInformation(citizenTemplate.getGeneralInformation()));
        //health conditions
        for (Map.Entry<HealthCondition, HealthConditionData> entry : citizenTemplate.getHealthConditions().entrySet()) {
            this.getHealthConditions().put(entry.getKey(), new HealthConditionData(entry.getValue()));
        }
        //func ab
        for (Map.Entry<FunctionalAbility, FunctionalAbilityData> entry : citizenTemplate.getFunctionalAbilities().entrySet()) {
            this.getFunctionalAbilities().put(entry.getKey(), new FunctionalAbilityData(entry.getValue()));
        }
        //med list
        this.setMedicineListProperty(new SimpleStringProperty(citizenTemplate.getMedicineList()));
        //observations
        this.setObservations(citizenTemplate.getObservations() == null ? null : new Observations(citizenTemplate.getObservations()));
        //school
        this.setSchool(citizenTemplate.getSchool() == null ? null : new School(citizenTemplate.getSchool()));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CitizenInstance other = (CitizenInstance) obj;
        if (!Objects.equals(super.getId(), other.getId())) {
            return false;
        }
        return true;
    }
}
