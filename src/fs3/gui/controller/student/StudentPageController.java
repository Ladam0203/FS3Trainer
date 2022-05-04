package fs3.gui.controller.student;

import fs3.be.Citizen;
import fs3.gui.model.CitizenModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {
    @FXML
    private ComboBox<Citizen> cmbAssignedCitizens;

    private CitizenModel citizenModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = CitizenModel.getInstance();
            cmbAssignedCitizens.setItems(citizenModel.getObservableCitizens());
        } catch (Exception e) {
            //TODO: handle exception gracefully
            e.printStackTrace();
        }
    }

    @FXML
    private void handleSelectCitizen(ActionEvent actionEvent) {
        Citizen citizen = cmbAssignedCitizens.getSelectionModel().getSelectedItem();
        if (citizen != null) {
            citizenModel.setSelectedCitizen(citizen);
        }
    }
}
