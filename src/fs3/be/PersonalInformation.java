package fs3.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonalInformation {
    private StringProperty name;
    private IntegerProperty age;

    public PersonalInformation() {
        name = new SimpleStringProperty();
        age = new SimpleIntegerProperty();
    }

    //copy constructor
    public PersonalInformation(PersonalInformation other) {
        name = new SimpleStringProperty(other.getName());
        age = new SimpleIntegerProperty(other.getAge());
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

    public int getAge() {
        return age.get();
    }

    public IntegerProperty ageProperty() {
        return age;
    }

    public void setAge(int age) {
        this.age.set(age);
    }
}
