package fs3.be;

import javafx.beans.property.StringProperty;

public class PersonalInformation {
    private StringProperty firstName;
    private StringProperty lastName;

    public String getFirstName() {
        return firstName.get();
    }

    public String getLastName() {
        return lastName.get();
    }
}
