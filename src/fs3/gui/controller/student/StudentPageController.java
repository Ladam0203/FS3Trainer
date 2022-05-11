package fs3.gui.controller.student;

import fs3.be.Citizen;
import fs3.be.CitizenInstance;
import fs3.gui.model.CitizenInstanceModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {
    @FXML
    private ComboBox<CitizenInstance> cmbAssignedCitizens;

    private CitizenInstanceModel citizenInstanceModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
            cmbAssignedCitizens.setItems(citizenInstanceModel.getObservableCitizens());
        } catch (Exception e) {
            //TODO: handle exception gracefully
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSelectCitizen(ActionEvent actionEvent) {
        CitizenInstance citizenInstance = cmbAssignedCitizens.getSelectionModel().getSelectedItem();
        if (citizenInstance != null) {
            citizenInstanceModel.setSelectedCitizenInstance(citizenInstance);
        }
    }


}
