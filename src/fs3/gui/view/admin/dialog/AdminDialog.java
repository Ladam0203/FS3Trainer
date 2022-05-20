package fs3.gui.view.admin.dialog;

import fs3.be.Admin;
import fs3.gui.controller.dialog.UserDialog;
import fs3.gui.controller.dialog.UserDialogController;

public class AdminDialog extends UserDialog<Admin> {
    public AdminDialog() {
        super(new AdminDialogController(), "/fs3/gui/view/teacher/tabs/students/dialog/UserDialogView.fxml", Admin.class.getSimpleName());
    }
}
