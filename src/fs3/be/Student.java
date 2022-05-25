package fs3.be;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    StringProperty name;
    List<CitizenInstance> assignedCitizens;
    ObjectProperty<School> school;

    public Student(String username, String password) {
        super(username, password);
        this.name = new SimpleStringProperty();
        assignedCitizens = new ArrayList<>();
        school = new SimpleObjectProperty<>();
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

    @Override
    public String toString() {
        return getName() + " (" + getUsername() + ")";
    }

    public List<CitizenInstance> getAssignedCitizens() {
        return assignedCitizens;
    }

    public void assignCitizen(CitizenInstance citizenInstance) {
        assignedCitizens.add(citizenInstance);
    }

    public void removeCitizen(CitizenInstance citizenInstance) {
        assignedCitizens.remove(citizenInstance);
    }

    public School getSchool() {
        return school.get();
    }

    public void setSchool(School school) {
        this.school.set(school);
    }
}
