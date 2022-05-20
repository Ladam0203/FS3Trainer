package fs3.gui.controller.dialog;

import fs3.be.User;

public abstract class UserDialogController<T extends User> {
    protected T user;

    public UserDialogController() {
        setUser(constructEmptyUser());
    }

    protected T getUser() {
        return user;
    }

    protected void setUser(T user) {
        this.user = user;
    }

    public void passUser(T user) {
        setUser(user);
        setFields(user);
    }

    public abstract T constructEmptyUser();

    protected abstract void setFields(T user);

    public abstract T constructUser();
}
