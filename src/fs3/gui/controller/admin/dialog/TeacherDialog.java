package fs3.gui.controller.admin.dialog;

import fs3.be.Teacher;
import fs3.gui.controller.dialog.UserDialog;

public class TeacherDialog extends UserDialog<Teacher> {
    public TeacherDialog() {
        super(new TeacherDialogController(), "/fs3/gui/view/dialog/UserDialogView.fxml", Teacher.class.getSimpleName());
    }
}
