package fs3.gui.controller.teacher.tabs.assignments;

import fs3.be.CitizenInstance;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.StudentModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AssignmentsController implements Initializable {
    @FXML
    private ListView<CitizenInstance> ltvAssignments;

    private CitizenInstanceModel citizenModel;
    private StudentModel studentModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = CitizenInstanceModel.getInstance();
            studentModel = StudentModel.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            PopUp.showError("Failed to initalize Citizen Instance Model");
        }
        ltvAssignments.setItems(citizenModel.getObservableCitizens());
    }

    public void handleSelectCitizen(MouseEvent mouseEvent) {
        CitizenInstance citizen = ltvAssignments.getSelectionModel().getSelectedItem();
        if (citizen != null) {
            citizenModel.setSelectedCitizenInstance(citizen);
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
                ContextMenu contextMenuAssignments = new ContextMenu();
                MenuItem deleteItem = new MenuItem("Delete!");
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

}
