package fs3.gui.controller.dialog;

import fs3.be.User;

public abstract class UserDialogController<T extends User> {
    public abstract void setUser(T user);
    public abstract void setFields(T user);
    public abstract T constructUser();
}
