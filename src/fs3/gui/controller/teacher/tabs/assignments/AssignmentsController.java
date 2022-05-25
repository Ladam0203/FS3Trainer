package fs3.gui.controller.teacher.tabs.assignments;

import fs3.be.CitizenInstance;
import fs3.be.Student;
import fs3.be.Teacher;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.LoginModel;
import fs3.gui.model.StudentModel;
import fs3.util.PopUp;
import javafx.collections.transformation.FilteredList;
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
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class AssignmentsController implements Initializable {
    @FXML
    private TextField txfFilterAssignments;
    @FXML
    private ListView<CitizenInstance> ltvAssignments;

    private LoginModel loginModel;
    private CitizenInstanceModel citizenModel;
    private StudentModel studentModel;

    private FilteredList<CitizenInstance> citizenInstanceFilteredList;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = CitizenInstanceModel.getInstance();
            loginModel = LoginModel.getInstance();
            studentModel = StudentModel.getInstance(((Teacher)loginModel.getLoggedUser()).getSchool());
        } catch (Exception e) {
            e.printStackTrace();
            PopUp.showError("Failed to initalize Citizen Instance Model");
        }
        citizenInstanceFilteredList = new FilteredList<>(citizenModel.getObservableCitizens());
        ltvAssignments.setItems(citizenInstanceFilteredList);
    }

    @FXML
    private void handleSelectCitizen(MouseEvent mouseEvent) {
        CitizenInstance citizen = ltvAssignments.getSelectionModel().getSelectedItem();
        if (citizen != null) {
            citizenModel.setSelectedCitizenInstance(citizen);
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                ContextMenu contextMenuAssignments = new ContextMenu();
                MenuItem deleteItem = new MenuItem("Delete");
                contextMenuAssignments.getItems().add(deleteItem);
                ltvAssignments.setContextMenu(contextMenuAssignments);
                contextMenuAssignments.show(ltvAssignments.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());
                deleteItem.setOnAction(event -> {
                    try {
                        citizenModel.deleteCitizenInstance(citizenModel.getSelectedCitizenInstance());
                        studentModel.getObservableStudents().forEach(s -> s.getAssignedCitizens().remove(citizenModel.getSelectedCitizenInstance()));
                    } catch (Exception e) {
                        PopUp.showError("Cannot delete citizen!");
                    }
                });
            }
        }
    }

    @FXML
    private void handleFilterAssignments(KeyEvent keyEvent) {
        String query = txfFilterAssignments.getText();
        citizenInstanceFilteredList.setPredicate(new Predicate<CitizenInstance>() {
            @Override
            public boolean test(CitizenInstance citizenInstance) {
                return citizenInstance.getPersonalInformation().getName().toLowerCase().contains(query.toLowerCase());
            }
        });

    }
}
