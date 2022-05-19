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
import java.util.ArrayList;
import java.util.List;
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
            ltvAvailableAssignements.setItems(instanceModel.getObservableCitizens());
        } catch (Exception e) {
            PopUp.showError("Cannot load students!");
        }
    }

    public void handleAdd(ActionEvent event) {
        CitizenInstance available = ltvAvailableAssignements.getSelectionModel().getSelectedItem();

        Student student = ltvStudents.getSelectionModel().getSelectedItem();
        if (available != null && student != null) {
            student.getAssignedCitizens().add(available);
            try {
                studentModel.updateStudent(student);
            } catch (Exception e) {
                PopUp.showError("Cannot add citizen to student!");
            }
            ltvAssignedCases.setItems(FXCollections.observableList(student.getAssignedCitizens()));
        }
    }

    public void handleRemove(ActionEvent event) {
        CitizenInstance assigned = ltvAssignedCases.getSelectionModel().getSelectedItem();
        Student student = ltvStudents.getSelectionModel().getSelectedItem();
        if (assigned != null && student != null) {
            student.getAssignedCitizens().remove(assigned);
            try {
                studentModel.updateStudent(student);
            } catch (Exception e) {
                PopUp.showError("Cannot remove citizen from student!");
            }
            ltvAssignedCases.setItems(FXCollections.observableList(student.getAssignedCitizens()));
        }
    }

    public void handleSelectStudent(MouseEvent mouseEvent) {
        Student selected = ltvStudents.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ltvAssignedCases.setItems(FXCollections.observableList(selected.getAssignedCitizens()));
            //ltvAvailableAssignements.setItems(FXCollections.observableList(instanceModel.getObservableCitizens()).filtered(ci -> !selected.getAssignedCitizens().contains(ci)));
            List<CitizenInstance> notThere = new ArrayList<>();
            for(CitizenInstance citizenInstance : selected.getAssignedCitizens()){
                if(!selected.getAssignedCitizens().contains(citizenInstance)){
                    notThere.add(citizenInstance);
                }
            }
            ltvAvailableAssignements.setItems(FXCollections.observableList(notThere));
        }

    }
}
