package fs3.gui.controller.teacher.tabs.students;

import fs3.be.CitizenInstance;
import fs3.be.Student;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.StudentModel;
import fs3.util.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class StudentsController implements Initializable {
    @FXML
    private ListView<CitizenInstance> ltvAvailableAssignements;
    @FXML
    private ListView<CitizenInstance> ltvAssignedCases;
    @FXML
    private ListView<Student> ltvStudents;

    private StudentModel studentModel;
    private CitizenInstanceModel instanceModel;

    FilteredList<CitizenInstance> availableCitizens;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            studentModel = StudentModel.getInstance();
            instanceModel = CitizenInstanceModel.getInstance();

            ltvStudents.setItems(studentModel.readAllStudents());
            availableCitizens = new FilteredList<>(instanceModel.getObservableCitizens());
            availableCitizens.setPredicate(null);
            ltvAvailableAssignements.setItems(availableCitizens);
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
            filterAvailableCitizens(student);
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
            filterAvailableCitizens(student);
        }
    }

    public void handleSelectStudent(MouseEvent mouseEvent) {
        Student selected = ltvStudents.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ltvAssignedCases.setItems(FXCollections.observableList(selected.getAssignedCitizens()));
            filterAvailableCitizens(selected);
        }
    }

    public void filterAvailableCitizens(Student student) {
        availableCitizens.setPredicate(new Predicate<CitizenInstance>() {
            @Override
            public boolean test(CitizenInstance citizenInstance) {
                System.out.println(!student.getAssignedCitizens().contains(citizenInstance));
                return !student.getAssignedCitizens().contains(citizenInstance);
            }
        });
    }
}
