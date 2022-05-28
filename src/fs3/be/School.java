package fs3.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class School {
    private final IntegerProperty id;
    private final StringProperty name;

    public School() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
    }

    //copy constructor
    public School(School other) {
        this.id = new SimpleIntegerProperty(other.getId());
        this.name = new SimpleStringProperty(other.getName());
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final School other = (School) obj;

        return Objects.equals(getId(), other.getId());
    }
}
