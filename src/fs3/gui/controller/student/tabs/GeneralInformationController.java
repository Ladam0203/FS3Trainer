package fs3.gui.controller.student.tabs;

import fs3.gui.model.CitizenModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneralInformationController implements Initializable {
    @FXML
    private TextArea txaCoping, txaMotivation, txaResources, txaRoles, txaHabits, txaEducation, txaJobs, txaLifeStory, txaHealthInformation, txaEquipmentAids, txaHomeLayout, txaNetwork;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CitizenModel citizenModel = null;
        try {
            citizenModel = CitizenModel.getInstance();
        } catch (Exception e) {
            //TODO: handle gracefully
        }

        citizenModel.getSelectedCitizenProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txaCoping.setText(newValue.getGeneralInformation().getCoping());
                txaMotivation.setText(newValue.getGeneralInformation().getMotivation());
                txaResources.setText(newValue.getGeneralInformation().getResources());
                txaRoles.setText(newValue.getGeneralInformation().getRoles());
                txaHabits.setText(newValue.getGeneralInformation().getHabits());
                txaEducation.setText(newValue.getGeneralInformation().getEducation());
                txaJobs.setText(newValue.getGeneralInformation().getJobs());
                txaLifeStory.setText(newValue.getGeneralInformation().getLifeStory());
                txaHealthInformation.setText(newValue.getGeneralInformation().getHealthInformation());
                txaEquipmentAids.setText(newValue.getGeneralInformation().getEquipmentAids());
                txaHomeLayout.setText(newValue.getGeneralInformation().getHomeLayout());
                txaNetwork.setText(newValue.getGeneralInformation().getNetwork());
            }
        });
    }
}
