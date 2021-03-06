package fs3.gui.controller.student.tabs;

import fs3.be.GeneralInformation;
import fs3.be.PersonalInformation;
import fs3.gui.model.CitizenInstanceModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GeneralInformationController implements Initializable {
    @FXML
    private TextArea txaCoping, txaMotivation, txaResources,
            txaRoles, txaHabits, txaEducationAndJobs,
            txaLifeStory, txaHealthInformation, txaEquipmentAids,
            txaHomeLayout, txaNetwork;

    private CitizenInstanceModel citizenInstanceModel;

    //not sure if adding it into list is the right, but I think it is more practical
    private List<TextArea> textAreas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
        } catch (Exception e) {
            PopUp.showError("Couldn't initialize general information tab!", e);
        }
        textAreas = new ArrayList<>();
        textAreas.add(txaCoping);
        textAreas.add(txaMotivation);
        textAreas.add(txaResources);
        textAreas.add(txaRoles);
        textAreas.add(txaHabits);
        textAreas.add(txaEducationAndJobs);
        textAreas.add(txaLifeStory);
        textAreas.add(txaHealthInformation);
        textAreas.add(txaEquipmentAids);
        textAreas.add(txaHomeLayout);
        textAreas.add(txaNetwork);

        citizenInstanceModel.getSelectedCitizenProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                GeneralInformation generalInformation = newValue.getGeneralInformation();
                if (generalInformation != null) {
                    txaCoping.setText(generalInformation.getCoping());
                    txaMotivation.setText(generalInformation.getMotivation());
                    txaResources.setText(generalInformation.getResources());
                    txaRoles.setText(generalInformation.getRoles());
                    txaHabits.setText(generalInformation.getHabits());
                    txaEducationAndJobs.setText(generalInformation.getEducationAndJobs());
                    txaLifeStory.setText(generalInformation.getLifeStory());
                    txaHealthInformation.setText(generalInformation.getHealthInformation());
                    txaEquipmentAids.setText(generalInformation.getEquipmentAids());
                    txaHomeLayout.setText(generalInformation.getHomeLayout());
                    txaNetwork.setText(generalInformation.getNetwork());
                } else {
                    clearFields();
                }
            }
        });
    }

    @FXML
    private void handleSave() throws Exception {
        if (citizenInstanceModel.getSelectedCitizenProperty().get() == null) {
            return;
        }

        GeneralInformation generalInformation = citizenInstanceModel.getSelectedCitizenInstance().getGeneralInformation();
        if (areTextAreasEmpty()) {
            generalInformation.setCoping(txaCoping.getText());
            generalInformation.setMotivation(txaMotivation.getText());
            generalInformation.setResources(txaResources.getText());
            generalInformation.setRoles(txaRoles.getText());
            generalInformation.setHabits(txaHabits.getText());
            generalInformation.setEducationAndJobs(txaEducationAndJobs.getText());
            generalInformation.setLifeStory(txaLifeStory.getText());
            generalInformation.setHealthInformation(txaHealthInformation.getText());
            generalInformation.setEquipmentAids(txaEquipmentAids.getText());
            generalInformation.setHomeLayout(txaHomeLayout.getText());
            generalInformation.setNetwork(txaNetwork.getText());

            citizenInstanceModel.updateSelectedCitizen();
        }
    }

    private boolean areTextAreasEmpty() {
        for (TextArea t : textAreas) {
            if (t.getText().isEmpty()) {
                PopUp.showError("Fill all fields!");
                return false;
            }
        }
        return true;
    }

    public void clearFields() {
        for (TextArea textArea : textAreas) {
            textArea.clear();
        }
    }
}
