package fs3.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Citizen {
    private IntegerProperty id;
    private ObjectProperty<PersonalInformation> personalInformation;

    public Citizen() {
        id = new SimpleIntegerProperty();
        personalInformation = new SimpleObjectProperty<>();
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

    public PersonalInformation getPersonalInformationProperty() {
        return personalInformation.get();
    }

    @Override
    public String toString(){
        return this.personalInformation.get().getNameProperty() + " (" + this.getIdProperty() + ")";
    }
}
