package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewUserDialogController {

    @FXML
    private TextField txfStudentName, txfUsername, txfPassword;

    private Student student = new Student("", "");

    public void setStudent(Student selected) {
        student = selected;
        setFields(selected);
    }

    private void setFields(Student student){
        txfStudentName.setText(student.getName());
        txfUsername.setText(student.getUsername());
        txfPassword.setText(student.getPassword());
    }

    private String getUsername() {
        return txfUsername.getText();
    }

    private String getPassword() {
        return txfPassword.getText();
    }

    private String getName() {
        return txfStudentName.getText();
    }

    public Student constructStudent(){
        student.setName(getName());
        student.setUsername(getUsername());
        student.setPassword(getPassword());
        return student;
    }
}
