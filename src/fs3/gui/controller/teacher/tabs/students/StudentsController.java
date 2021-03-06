package fs3.gui.controller.teacher.tabs.students;

import fs3.be.CitizenInstance;
import fs3.be.Student;
import fs3.be.Teacher;
import fs3.gui.controller.dialog.CUDialog;
import fs3.gui.controller.teacher.tabs.students.dialog.StudentDialog;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.LoginModel;
import fs3.gui.model.StudentModel;
import fs3.util.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class StudentsController implements Initializable {
    public TextField txfFilterStudents;
    @FXML
    private ListView<CitizenInstance> ltvAvailableAssignments;
    @FXML
    private ListView<CitizenInstance> ltvAssignedCases;
    @FXML
    private ListView<Student> ltvStudents;

    private LoginModel loginModel;
    private StudentModel studentModel;
    private CitizenInstanceModel instanceModel;

    private FilteredList<CitizenInstance> availableCitizens;
    private FilteredList<Student> studentFilteredList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loginModel = LoginModel.getInstance();
            studentModel = StudentModel.getInstance();
            instanceModel = CitizenInstanceModel.getInstance();

            studentFilteredList = new FilteredList<>(studentModel.getObservableStudents());
            studentFilteredList.setPredicate(null);
            ltvStudents.setItems(studentFilteredList);

            availableCitizens = new FilteredList<>(instanceModel.getObservableCitizens());
            availableCitizens.setPredicate(null);
            ltvAvailableAssignments.setItems(availableCitizens);
        } catch (Exception e) {
            PopUp.showError("Cannot load students!", e);
        }

    }

    @FXML
    private void handleAdd(ActionEvent event) {
        CitizenInstance available = ltvAvailableAssignments.getSelectionModel().getSelectedItem();

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

    @FXML
    private void handleRemove(ActionEvent event) {
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

    @FXML
    private void handleSelectStudent(MouseEvent mouseEvent) {
        Student selected = ltvStudents.getSelectionModel().getSelectedItem();
        if (selected != null) {
            ltvAssignedCases.setItems(FXCollections.observableList(selected.getAssignedCitizens()));
            filterAvailableCitizens(selected);
        }

        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {

            ContextMenu contextMenu = new ContextMenu();
            MenuItem newItem = new MenuItem("New student");
            MenuItem editItem = new MenuItem("Edit student");
            MenuItem deleteItem = new MenuItem("Delete student");

            contextMenu.getItems().add(newItem);
            contextMenu.getItems().add(editItem);
            contextMenu.getItems().add(deleteItem);

            ltvStudents.setContextMenu(contextMenu);

            contextMenu.show(ltvStudents.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());

            newItem.setOnAction(event -> {
                CUDialog<Student> dialog = new StudentDialog();
                Optional<Student> result = dialog.showAndWait();
                result.ifPresent(response -> {
                    try {
                        response.setSchool(((Teacher) loginModel.getLoggedUser()).getSchool());
                        studentModel.createStudent(response);
                    } catch (Exception e) {
                        PopUp.showError("Cannot create new student!");
                        e.printStackTrace();
                    }
                });
            });

            editItem.setOnAction(event -> {
                CUDialog<Student> dialog = new StudentDialog();
                dialog.passObject(selected);
                Optional<Student> result = dialog.showAndWait();
                result.ifPresent(response -> {
                    try {
                        //update student info
                        studentModel.updateStudent(response);
                    } catch (Exception e) {
                        PopUp.showError("Cannot edit student!");
                        e.printStackTrace();
                    }
                });
            });

            deleteItem.setOnAction(event -> {
                try {
                    studentModel.deleteStudent(selected);
                } catch (Exception e) {
                    PopUp.showError("Cannot delete student!");
                    e.printStackTrace();
                }
            });
        }
    }

    @FXML
    private void filterAvailableCitizens(Student student) {
        availableCitizens.setPredicate(new Predicate<CitizenInstance>() {
            @Override
            public boolean test(CitizenInstance citizenInstance) {
                return !student.getAssignedCitizens().contains(citizenInstance);
            }
        });
    }

    @FXML
    private void handleFilterStudents(KeyEvent keyEvent) {
        String query = txfFilterStudents.getText();

        studentFilteredList.setPredicate(new Predicate<Student>() {
            @Override
            public boolean test(Student student) {
                return student.getName().toLowerCase().contains(query.toLowerCase());
            }
        });
    }
}
