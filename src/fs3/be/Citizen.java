package fs3.be;

import fs3.enums.HealthConditions;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.HashMap;

public class Citizen {
    private IntegerProperty id;
    private ObjectProperty<PersonalInformation> personalInformation;
    private ObjectProperty<GeneralInformation> generalInformation;
    private HashMap<HealthConditions, HealthCondition> healthConditions;

    public Citizen() {
        id = new SimpleIntegerProperty();
        personalInformation = new SimpleObjectProperty<>();
        generalInformation = new SimpleObjectProperty<>();
        healthConditions = new HashMap<>();
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

    public HashMap<HealthConditions, HealthCondition> getHealthConditions() {
        return healthConditions;
    }

    public void setHealthConditions(HashMap<HealthConditions, HealthCondition> healthConditions) {
        this.healthConditions = healthConditions;
    }

    @Override
    public String toString(){
        return this.getPersonalInformation().getName() + " (" + this.id.get() + ")";
    }
}
