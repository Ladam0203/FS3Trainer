package fs3.be;

import fs3.enums.FunctionalAbility;
import fs3.enums.HealthCondition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.EnumMap;
import java.util.HashMap;

public abstract class Citizen {
    protected IntegerProperty id;
    protected ObjectProperty<PersonalInformation> personalInformation;
    protected ObjectProperty<GeneralInformation> generalInformation;
    protected HashMap<HealthCondition, HealthConditionData> healthConditions;
    protected HashMap<FunctionalAbility, FunctionalAbilityData> functionalAbilities;

    protected Citizen() {
        id = new SimpleIntegerProperty();
        personalInformation = new SimpleObjectProperty<>();
        generalInformation = new SimpleObjectProperty<>();
        healthConditions = new HashMap<>();
        functionalAbilities = new HashMap<>();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public int getId() {
        return id.get();
    }

    public void setPersonalInformation(PersonalInformation personalInformation) {
        this.personalInformation.set(personalInformation);
    }

    public PersonalInformation getPersonalInformation() {
        return personalInformation.get();
    }

    public GeneralInformation getGeneralInformation() {
        return generalInformation.get();
    }

    public void setGeneralInformation(GeneralInformation generalInformation) {
        this.generalInformation.set(generalInformation);
    }

    public HashMap<HealthCondition, HealthConditionData> getHealthConditions() {
        return healthConditions;
    }

    public void setHealthConditions(HashMap<HealthCondition, HealthConditionData> healthConditions) {
        this.healthConditions = healthConditions;
    }

    public HashMap<FunctionalAbility, FunctionalAbilityData> getFunctionalAbilities() {
        return functionalAbilities;
    }

    public void setFunctionalAbilities(HashMap<FunctionalAbility, FunctionalAbilityData> functionalAbilities) {
        this.functionalAbilities = functionalAbilities;
    }

    @Override
    public String toString(){
        return this.getPersonalInformation().getName() + " (" + this.id.get() + ")";
    }
}
