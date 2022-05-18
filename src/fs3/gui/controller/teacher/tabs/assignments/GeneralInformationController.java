package fs3.gui.controller.teacher.tabs.assignments;

import fs3.be.GeneralInformation;
import fs3.be.PersonalInformation;
import fs3.gui.model.CitizenInstanceModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GeneralInformationController implements Initializable {
    @FXML
    private Label lblName;
    @FXML
    private TextArea txaCoping, txaMotivation, txaResources, txaRoles,
            txaHabits, txaEducationAndJobs, txaLifeStory, txaHealthInformation,
            txaEquipmentAids, txaHomeLayout, txaNetwork;

    private CitizenInstanceModel citizenInstanceModel;

    //not sure if adding it into list is the right, but I think it is more practical
    private List<TextArea> textAreas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
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
        } catch (Exception e) {
            //TODO: handle gracefully
            e.printStackTrace();
        }

        citizenInstanceModel.getSelectedCitizenProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                PersonalInformation personalInformation = newValue.getPersonalInformation();
                lblName.setText(personalInformation.getName());

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
                }
                else {
                    clearFields();
                }
            }
        });
    }

    public void clearFields() {
        for (TextArea textArea : textAreas) {
            textArea.clear();
        }
    }
}
