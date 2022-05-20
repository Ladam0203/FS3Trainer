package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.Student;

public class StudentDialog extends UserDialog<Student> {

    public StudentDialog() {
        super(new StudentDialogController(), "/fs3/gui/view/teacher/tabs/students/dialog/UserDialogView.fxml", "Student");
    }
}
