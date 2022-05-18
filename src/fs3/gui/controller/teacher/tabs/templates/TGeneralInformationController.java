package fs3.gui.controller.teacher.tabs.templates;

import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.be.GeneralInformation;
import fs3.be.PersonalInformation;
import fs3.gui.model.CitizenTemplateModel;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TGeneralInformationController implements Initializable {

    public TextField txfCitizenName;
    public TextArea txaCoping;
    public TextArea txaMotivation;
    public TextArea txaResources;
    public TextArea txaRoles;
    public TextArea txaHabits;
    public TextArea txaEducationAndJobs;
    public TextArea txaLifeStory;
    public TextArea txaHealthInformation;
    public TextArea txaEquipmentAids;
    public TextArea txaHomeLayout;
    public TextArea txaNetwork;
    List<TextArea> textAreaList;
    private CitizenTemplateModel citizenTemplateModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenTemplateModel = CitizenTemplateModel.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        textAreaList = new ArrayList<>();
        textAreaList.add(txaCoping);
        textAreaList.add(txaMotivation);
        textAreaList.add(txaResources);
        textAreaList.add(txaRoles);
        textAreaList.add(txaHabits);
        textAreaList.add(txaEducationAndJobs);
        textAreaList.add(txaLifeStory);
        textAreaList.add(txaHealthInformation);
        textAreaList.add(txaEquipmentAids);
        textAreaList.add(txaHomeLayout);
        textAreaList.add(txaNetwork);

        citizenTemplateModel.getSelectedCitizenTemplateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                PersonalInformation personalInformation = newValue.getPersonalInformation();
                txfCitizenName.setText(personalInformation.getName());

                GeneralInformation generalInformation = newValue.getGeneralInformation();
                if(generalInformation != null) {
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
                else{
                    for(TextArea textArea : textAreaList){
                        textArea.setText("");
                    }
                }
            }
        });
    }


    public void handleSave(ActionEvent event) {
        CitizenTemplate selectedCitizenTemplate = citizenTemplateModel.getSelectedCitizenTemplate();

        if(selectedCitizenTemplate == null){
            PopUp.showError("Select template!");
        }
        GeneralInformation newGeneralInformation = new GeneralInformation();
        selectedCitizenTemplate.setGeneralInformation(newGeneralInformation);
        selectedCitizenTemplate.getPersonalInformation().setName(txfCitizenName.getText());
        newGeneralInformation.setCoping(txaCoping.getText());
        newGeneralInformation.setMotivation(txaMotivation.getText());
        newGeneralInformation.setResources(txaResources.getText());
        newGeneralInformation.setRoles(txaRoles.getText());
        newGeneralInformation.setHabits(txaHabits.getText());
        newGeneralInformation.setEducationAndJobs(txaEducationAndJobs.getText());
        newGeneralInformation.setLifeStory(txaLifeStory.getText());
        newGeneralInformation.setHealthInformation(txaHealthInformation.getText());
        newGeneralInformation.setEquipmentAids(txaEquipmentAids.getText());
        newGeneralInformation.setHomeLayout(txaHomeLayout.getText());
        newGeneralInformation.setNetwork(txaNetwork.getText());
        selectedCitizenTemplate.setGeneralInformation(newGeneralInformation);
        try {
            citizenTemplateModel.updateSelectedCitizenTemplate(selectedCitizenTemplate);
        } catch (Exception e) {
            PopUp.showError("Cannot update template!");
            e.printStackTrace();
        }
    }


}
