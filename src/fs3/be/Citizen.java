package fs3.be;

import fs3.enums.FunctionalAbility;
import fs3.enums.HealthCondition;
import javafx.beans.property.*;

import java.util.HashMap;
import java.util.Objects;

public abstract class Citizen {
    private final IntegerProperty id;
    private final ObjectProperty<PersonalInformation> personalInformation;
    private final ObjectProperty<GeneralInformation> generalInformation;
    private HashMap<HealthCondition, HealthConditionData> healthConditions;
    private HashMap<FunctionalAbility, FunctionalAbilityData> functionalAbilities;
    private StringProperty medicineList;
    private final ObjectProperty<School> school;

    protected Citizen() {
        id = new SimpleIntegerProperty();
        personalInformation = new SimpleObjectProperty<>();
        generalInformation = new SimpleObjectProperty<>();
        healthConditions = new HashMap<>();
        functionalAbilities = new HashMap<>();
        medicineList = new SimpleStringProperty();
        school = new SimpleObjectProperty<>();
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

    public String getMedicineList() {
        return medicineList.get();
    }

    public void setMedicineList(String medicineList) {
        this.medicineList.set(medicineList);
    }

    public void setMedicineListProperty(SimpleStringProperty medicineListProperty) {
        this.medicineList = medicineListProperty;
    }

    public School getSchool() {
        return school.get();
    }

    public void setSchool(School school) {
        this.school.set(school);
    }

    @Override
    public String toString() {
        return String.format("[%d]: %s (%d)", getId(), getPersonalInformation().getName(), getPersonalInformation().getAge());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Citizen other = (Citizen) obj;
        return Objects.equals(this.id, other.id);
    }
}
