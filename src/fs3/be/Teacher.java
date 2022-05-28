package fs3.be;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Teacher extends User {
    private StringProperty name;
    private ObjectProperty<School> school;

    public Teacher(String username, String password) {
        super(username, password);
        this.name = new SimpleStringProperty();
        this.school = new SimpleObjectProperty<>();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public School getSchool() {
        return school.get();
    }

    public void setSchool(School school) {
        this.school.set(school);
    }

    @Override
    public String toString() {
        return getName() + " (" + getUsername() + ")";
    }
}
