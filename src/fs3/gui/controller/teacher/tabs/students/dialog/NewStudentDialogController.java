package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewStudentDialogController {

    @FXML
    private TextField txfStudentName, txfUsername, txfPassword;

    public void setStudent(Student selected) {
        setFields(selected);
    }

    private void setFields(Student student){
        txfStudentName.setText(student.getName());
        txfUsername.setText(student.getUsername());
        txfPassword.setText(student.getPassword());
    }
}
