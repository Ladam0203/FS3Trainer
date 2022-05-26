package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.Student;
import fs3.gui.controller.dialog.UserDialogController;
import fs3.util.PopUp;
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
