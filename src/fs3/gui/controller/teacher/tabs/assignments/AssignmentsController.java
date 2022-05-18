package fs3.gui.controller.teacher.tabs.assignments;

import fs3.be.CitizenInstance;
import fs3.gui.model.CitizenInstanceModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AssignmentsController implements Initializable {
     @FXML
    private ListView<CitizenInstance> ltvAssignments;

     private CitizenInstanceModel citizenModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = CitizenInstanceModel.getInstance();
        }
        catch (Exception e) {
            e.printStackTrace();
            PopUp.showError("Failed to initalize Citizen Instance Model");
        }
        ltvAssignments.setItems(citizenModel.getObservableCitizens());
    }

    @FXML
    private void handleSelectCitizen() {
        CitizenInstance citizen = ltvAssignments.getSelectionModel().getSelectedItem();
        if (citizen != null) {
            citizenModel.setSelectedCitizenInstance(citizen);
        }
    }
}
