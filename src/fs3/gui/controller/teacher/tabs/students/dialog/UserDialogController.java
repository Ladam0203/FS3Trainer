package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.User;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public abstract class UserDialogController<T extends User> {
    public abstract void setUser(T user);
    public abstract void setFields(T user);
    public abstract T constructUser();
}
