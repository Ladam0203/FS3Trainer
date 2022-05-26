package fs3.gui.controller.admin.dialog;

import fs3.be.Admin;
import fs3.gui.controller.dialog.UserDialogController;
import fs3.util.PopUp;
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
        if (txfName.getText().isEmpty()     ||
            txfPassword.getText().isEmpty() ||
            txfName.getText().isEmpty()     ){
                PopUp.showError("Please fill in all mandatory fields! (*)");
                return false;
        }
        return true;
    }
}
