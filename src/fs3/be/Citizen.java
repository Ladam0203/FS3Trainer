package fs3.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Citizen {
    private IntegerProperty id;
    private ObjectProperty<PersonalInformation> personalInformation;
    private ObjectProperty<GeneralInformation> generalInformation;

    public Citizen() {
        id = new SimpleIntegerProperty();
        personalInformation = new SimpleObjectProperty<>();
        generalInformation = new SimpleObjectProperty<>();
    }

    public void setIdProperty(int id) {
        this.id.set(id);
    }

    public int getIdProperty() {
        return id.get();
    }

    public void setPersonalInformationProperty(PersonalInformation personalInformation) {
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

    @Override
    public String toString(){
        return this.getPersonalInformation().getName() + " (" + this.id.get() + ")";
    }
}
