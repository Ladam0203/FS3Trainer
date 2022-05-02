package fs3.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonalInformation {
    private StringProperty firstName;
    private StringProperty lastName;
    
    public PersonalInformation() {
        firstName = new SimpleStringProperty();
        lastName = new SimpleStringProperty();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getLastName() {
        return lastName.get();
    }
}
