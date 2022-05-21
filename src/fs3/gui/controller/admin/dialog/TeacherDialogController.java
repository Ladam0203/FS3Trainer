package fs3.gui.controller.admin.dialog;

import fs3.be.Teacher;
import fs3.gui.controller.dialog.UserDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class TeacherDialogController extends UserDialogController<Teacher> {
    @FXML
    protected TextField txfName, txfUsername, txfPassword;

    @Override
    public Teacher constructEmptyUser() {
        return new Teacher("", "");
    }

    @Override
    public void setFields(Teacher teacher) {
        txfName.setText(teacher.getName());
        txfUsername.setText(teacher.getUsername());
        txfPassword.setText(teacher.getPassword());
    }

    @Override
    public Teacher constructUser() {
        getUser().setName(txfName.getText());
        getUser().setUsername(txfUsername.getText());
        getUser().setPassword(txfPassword.getText());
        return getUser();
    }

    @Override
    public boolean isValid() {
        if (txfName.getText().isEmpty()) {
            return false;
        }
        if (txfUsername.getText().isEmpty()) {
            return false;
        }
        if (txfPassword.getText().isEmpty()) {
            return false;
        }
        return true;
    }
}
