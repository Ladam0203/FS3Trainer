package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.Student;
import fs3.gui.controller.dialog.CUDialog;

public class StudentDialog extends CUDialog<Student> {

    public StudentDialog() {
        super(new StudentDialogController(), "/fs3/gui/view/dialog/UserDialogView.fxml", Student.class.getSimpleName());
    }
}
