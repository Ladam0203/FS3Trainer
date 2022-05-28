package fs3.gui.controller.admin.dialog;

import fs3.be.Teacher;
import fs3.gui.controller.dialog.CUDialog;

public class TeacherDialog extends CUDialog<Teacher> {
    public TeacherDialog() {
        super(new TeacherDialogController(), "/fs3/gui/view/admin/dialog/TeacherDialog.fxml", Teacher.class.getSimpleName());
    }
}
