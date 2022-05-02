package fs3.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonalInformation {
    private StringProperty name;

    public PersonalInformation() {
        name = new SimpleStringProperty();
    }

    public void setNameProperty(String name) {this.name.set(name);}

    public String getNameProperty() {
        return name.get();
    }
}
