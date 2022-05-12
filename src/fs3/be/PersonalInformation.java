package fs3.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.Serializable;

public class PersonalInformation {
    private StringProperty name;

    public PersonalInformation() {
        name = new SimpleStringProperty();
    }

    //copy constructor
    public PersonalInformation(PersonalInformation other) {
        name = new SimpleStringProperty(other.getName());
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
}
