package fs3.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonalInformation {
    private final StringProperty name;
    private final IntegerProperty age;

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

    public void setName(String name) {
        this.name.set(name);
    }

    public int getAge() {
        return age.get();
    }

    public void setAge(int age) {
        this.age.set(age);
    }
}
