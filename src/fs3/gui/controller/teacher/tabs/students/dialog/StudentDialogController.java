package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.Student;
import fs3.gui.controller.dialog.CUDialogController;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StudentDialogController extends CUDialogController<Student> {
    @FXML
    protected TextField txfName, txfUsername, txfPassword;

    @Override
    public Student constructEmptyObject() {
        return new Student("", "");
    }

    @Override
    public void setFields(Student student) {
        txfName.setText(student.getName());
        txfUsername.setText(student.getUsername());
        txfPassword.setText(student.getPassword());
    }

    @Override
    public Student constructObject() {
        getObject().setName(txfName.getText());
        getObject().setUsername(txfUsername.getText());
        getObject().setPassword(txfPassword.getText());
        return getObject();
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
