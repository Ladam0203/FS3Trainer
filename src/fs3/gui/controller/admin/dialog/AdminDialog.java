package fs3.gui.controller.admin.dialog;

import fs3.be.Admin;
import fs3.gui.controller.dialog.CUDialog;

public class AdminDialog extends CUDialog<Admin> {
    public AdminDialog() {
        super(new AdminDialogController(), "/fs3/gui/view/dialog/UserDialogView.fxml", Admin.class.getSimpleName());
    }
}
