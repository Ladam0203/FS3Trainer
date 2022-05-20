package fs3.gui.view.admin.dialog;

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
    public void setFields(Admin admin) {
        txfName.setText(admin.getName());
        txfUsername.setText(admin.getUsername());
        txfPassword.setText(admin.getPassword());
    }

    @Override
    public Admin constructUser() {
        getUser().setName(txfUsername.getText());
        getUser().setUsername(txfPassword.getText());
        getUser().setPassword(txfName.getText());
        return getUser();
    }
}
