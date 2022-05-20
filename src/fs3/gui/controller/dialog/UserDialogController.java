package fs3.gui.controller.dialog;

import fs3.be.User;

/* The generalized UserDialogController class for the UserDialog */
public abstract class UserDialogController<T extends User> {
    /* What would also come here is the list of FXML components that might or might not be specific t each controller */

    protected T user;

    /* By default, the view loads with the user being a new empty user */
    public UserDialogController() {
        setUser(constructEmptyUser());
    }

    protected T getUser() {
        return user;
    }

    protected void setUser(T user) {
        this.user = user;
    }

    /* Sets the user and the fields if we are editing a user */
    public void passUser(T user) {
        setUser(user);
        setFields(user);
    }

    /* We have to supply an empty user here to the controller, this is loaded as default in the constructor */
    public abstract T constructEmptyUser();

    /* Sets the fields in the controller */
    protected abstract void setFields(T user);

    /* Should construct a new user from the fields */
    public abstract T constructUser();
}
