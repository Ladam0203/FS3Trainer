package fs3.gui.controller.dialog;

import fs3.be.User;

/* The generalized UserDialogController class for the UserDialog */
public abstract class UserDialogController<T extends User> {
    /* Some FXML component might also come here if they are needed in each UserDialog, however we have left those to be included in the implementation */

    /* The user which we are working on, empty object by default, gotten from constructEmptyUser(), has value if call the passUser() method */
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

    /* Sets the user and the fields if we are editing a user instead of creating a new */
    public void passUser(T user) {
        setUser(user);
        setFields(user);
    }

    /* We have to supply an empty user here to the controller, this is loaded as default in the constructor */
    public abstract T constructEmptyUser();

    /* Sets the fields in the controller, based on the user. */
    protected abstract void setFields(T user);

    /* Should construct a new user, preferably from the information the user provided in the controls */
    public abstract T constructUser();

    /* Should check if the fields have the necessary data for the Dialog to be submitted  */
    public abstract boolean isValid();
}
