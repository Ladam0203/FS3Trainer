package fs3.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;

public class Citizen {
    private IntegerProperty id;
    private ObjectProperty<PersonalInformation> personalInformation;

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
}
