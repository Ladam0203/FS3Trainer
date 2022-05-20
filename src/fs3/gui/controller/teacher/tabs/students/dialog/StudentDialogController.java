package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.Student;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class StudentDialogController extends UserDialogController<Student> {
    @FXML
    protected TextField txfName, txfUsername, txfPassword;


    private Student student = new Student("", "");

    private String getUsername() {
        return txfUsername.getText();
    }

    private String getPassword() {
        return txfPassword.getText();
    }

    private String getName() {
        return txfName.getText();
    }

    @Override
    public void setUser(Student student) {
        this.student = student;
        setFields(this.student);
    }

    @Override
    public void setFields(Student student) {
        txfName.setText(student.getName());
        txfUsername.setText(student.getUsername());
        txfPassword.setText(student.getPassword());
    }

    @Override
    public Student constructUser() {
        student.setName(getName());
        student.setUsername(getUsername());
        student.setPassword(getPassword());
        return student;
    }
}
