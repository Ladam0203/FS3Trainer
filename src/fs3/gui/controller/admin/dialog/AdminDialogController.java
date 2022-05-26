package fs3.gui.controller.admin.dialog;

import fs3.be.Admin;
import fs3.gui.controller.dialog.UserDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class AdminDialogController extends UserDialogController<Admin> {
    @FXML
    protected TextField txfName, txfUsername, txfPassword;

    @Override
    public Admin constructEmptyUser() {
        return new Admin("", "");
    }

    @Override
    protected void setFields(Admin user) {
        txfName.setText(user.getName());
        txfPassword.setText(user.getPassword());
        txfUsername.setText(user.getUsername());

    }

    @Override
    public Admin constructUser() {
        getUser().setName(txfName.getText());
        getUser().setPassword(txfPassword.getText());
        getUser().setUsername(txfUsername.getText());
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
