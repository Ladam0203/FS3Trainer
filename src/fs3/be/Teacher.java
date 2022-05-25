package fs3.be;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher extends User{
    StringProperty name;
    ObjectProperty<School> school;

    public Teacher(String username, String password) {
        super(username, password);
        this.name = new SimpleStringProperty();
        this.school = new SimpleObjectProperty<>();
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

    public School getSchool() {
        return school.get();
    }

    public ObjectProperty<School> schoolProperty() {
        return school;
    }

    public void setSchool(School school) {
        this.school.set(school);
    }

    @Override
    public String toString() {
        return getName() + " (" + getUsername() + ")";
    }
}
