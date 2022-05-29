package fs3.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin extends User {
    private final StringProperty name;

    public Admin(String username, String password) {
        super(username, password);
        this.name = new SimpleStringProperty();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Override
    public String toString() {
        return getName() + " (" + getUsername() + ")";
    }
}
