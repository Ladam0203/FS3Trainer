package fs3.gui.controller.student.tabs;

import fs3.be.GeneralInformation;
import fs3.be.PersonalInformation;
import fs3.gui.model.CitizenModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneralInformationController implements Initializable {
    @FXML
    private TextArea txaCoping, txaMotivation, txaResources, txaRoles, txaHabits, txaEducation, txaJobs, txaLifeStory, txaHealthInformation, txaEquipmentAids, txaHomeLayout, txaNetwork;
    @FXML
    private Label lblName;

    private CitizenModel citizenModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = CitizenModel.getInstance();
        } catch (Exception e) {
            //TODO: handle gracefully
        }

        citizenModel.getSelectedCitizenProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                PersonalInformation personalInformation = newValue.getPersonalInformation();
                lblName.setText(personalInformation.getName());

                GeneralInformation generalInformation = newValue.getGeneralInformation();
                txaCoping.setText(generalInformation.getCoping());
                txaMotivation.setText(generalInformation.getMotivation());
                txaResources.setText(generalInformation.getResources());
                txaRoles.setText(generalInformation.getRoles());
                txaHabits.setText(generalInformation.getHabits());
                txaEducation.setText(generalInformation.getEducation());
                txaJobs.setText(generalInformation.getJobs());
                txaLifeStory.setText(generalInformation.getLifeStory());
                txaHealthInformation.setText(generalInformation.getHealthInformation());
                txaEquipmentAids.setText(generalInformation.getEquipmentAids());
                txaHomeLayout.setText(generalInformation.getHomeLayout());
                txaNetwork.setText(generalInformation.getNetwork());
            }
        });
    }

    @FXML
    private void handleSave() throws Exception {
        GeneralInformation generalInformation = citizenModel.getSelectedCitizen().getGeneralInformation();

        generalInformation.setCoping(txaCoping.getText());
        generalInformation.setMotivation(txaMotivation.getText());
        generalInformation.setResources(txaResources.getText());
        generalInformation.setRoles(txaRoles.getText());
        generalInformation.setHabits(txaHabits.getText());
        generalInformation.setEducation(txaEducation.getText());
        generalInformation.setJobs(txaJobs.getText());
        generalInformation.setLifeStory(txaLifeStory.getText());
        generalInformation.setHealthInformation(txaHealthInformation.getText());
        generalInformation.setEquipmentAids(txaEquipmentAids.getText());
        generalInformation.setHomeLayout(txaHomeLayout.getText());
        generalInformation.setNetwork(txaNetwork.getText());

        citizenModel.updateSelectedCitizen();
    }
}
