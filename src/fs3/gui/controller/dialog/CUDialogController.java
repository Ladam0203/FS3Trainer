package fs3.gui.controller.dialog;


/* The generalized CUDialogController class for the CUDialog */
public abstract class CUDialogController<T> {
    /* Some FXML component might also come here if they are needed in each CUDialog, however we have left those to be included in the implementation */

    /* The user which we are working on, empty object by default, gotten from constructEmptyObject(), has value if call the passObject() method */
    protected T object;

    /* By default, the view loads with the user being a new empty user */
    public CUDialogController() {
        setObject(constructEmptyObject());
    }

    protected T getObject() {
        return object;
    }

    protected void setObject(T object) {
        this.object = object;
    }

    /* Sets the user and the fields if we are editing a user instead of creating a new */
    public void passObject(T object) {
        setObject(object);
        setFields(object);
    }

    /* We have to supply an empty user here to the controller, this is loaded as default in the constructor */
    public abstract T constructEmptyObject();

    /* Sets the fields in the controller, based on the user. */
    protected abstract void setFields(T user);

    /* Should construct a new user, preferably from the information the user provided in the controls */
    public abstract T constructObject();

    /* Should check if the fields have the necessary data for the Dialog to be submitted  */
    public abstract boolean isValid();
}
