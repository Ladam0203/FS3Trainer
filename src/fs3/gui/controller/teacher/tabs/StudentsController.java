package fs3.gui.controller.teacher.tabs;

import fs3.gui.model.StudentModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {
    @FXML
    private ListView ltvStudents;

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

}
