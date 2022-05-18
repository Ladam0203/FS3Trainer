package fs3.gui.controller.teacher.tabs.students;

import fs3.be.CitizenInstance;
import fs3.be.Student;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.StudentModel;
import fs3.util.PopUp;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentsController implements Initializable {
    @FXML
    private ListView<CitizenInstance> ltvAvailableAssignements;
    @FXML
    private ListView<CitizenInstance> ltvAssignedCases;
    @FXML
    private ListView<Student> ltvStudents;

    private StudentModel studentModel;
    private CitizenInstanceModel instanceModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            studentModel = StudentModel.getInstance();
            instanceModel = CitizenInstanceModel.getInstance();

            ltvStudents.setItems(studentModel.readAllStudents());
        } catch (Exception e) {
            PopUp.showError("Cannot load students!");
        }
    }

    public void handleAdd(ActionEvent event) {
        CitizenInstance available = ltvAvailableAssignements.getSelectionModel().getSelectedItem();
        if(available != null){

        }
    }

    public void handleRemove(ActionEvent event) {
        CitizenInstance assigned = ltvAssignedCases.getSelectionModel().getSelectedItem();
        if(assigned != null){

        }
    }

    public void handleSelectStudent(MouseEvent mouseEvent) {
        Student selected = ltvStudents.getSelectionModel().getSelectedItem();
        if(selected != null){
            ltvAssignedCases.setItems(FXCollections.observableList(selected.getAssignedCitizens()));
        }
    }
}
