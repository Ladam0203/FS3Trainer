package fs3.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class School {
    private IntegerProperty id;
    private StringProperty name;

    public School() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
    }

    //copy constructror
    public School(School other) {
        this.id = new SimpleIntegerProperty(other.getId());
        this.name = new SimpleStringProperty(other.getName());
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
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
        return getName();
    }
}
