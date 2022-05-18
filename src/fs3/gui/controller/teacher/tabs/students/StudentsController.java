package fs3.gui.controller.teacher.tabs.students;

import fs3.be.Student;
import fs3.gui.model.StudentModel;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {
    @FXML
    private ListView ltvAvailableAssignements;
    @FXML
    private ListView ltvAssignedCases;
    @FXML
    private ListView<Student> ltvStudents;

    private StudentModel studentModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentModel = StudentModel.getInstance();
        try {
            ltvStudents.setItems(studentModel.readAllStudents());
        } catch (Exception e) {
            PopUp.showError("Cannot load students!");
        }
    }

    public void handleAdd(ActionEvent event) {
    }

    public void handleRemove(ActionEvent event) {
    }
}
