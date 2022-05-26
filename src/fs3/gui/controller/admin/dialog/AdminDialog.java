package fs3.gui.controller.admin.dialog;

import fs3.be.Admin;
import fs3.be.Teacher;
import fs3.gui.controller.dialog.UserDialog;

public class AdminDialog extends UserDialog<Admin> {
   public AdminDialog(){
       super(new AdminDialogController(), "/fs3/gui/view/dialog/UserDialogView.fxml", Admin.class.getSimpleName());
   }
}
