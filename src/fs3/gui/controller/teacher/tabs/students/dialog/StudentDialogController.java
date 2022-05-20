package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.Student;
import fs3.gui.controller.dialog.UserDialogController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StudentDialogController extends UserDialogController<Student> {
    @FXML
    protected TextField txfName, txfUsername, txfPassword;

    @Override
    public Student constructEmptyUser() {
        return new Student("", "");
    }

    @Override
    public void setFields(Student student) {
        txfName.setText(student.getName());
        txfUsername.setText(student.getUsername());
        txfPassword.setText(student.getPassword());
    }

    @Override
    public Student constructUser() {
        getUser().setName(txfName.getText());
        getUser().setUsername(txfUsername.getText());
        getUser().setPassword(txfPassword.getText());
        return getUser();
    }
}
