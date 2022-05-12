package fs3.gui.controller.teacher.tabs;

import fs3.be.CitizenTemplate;
import fs3.be.GeneralInformation;
import fs3.be.PersonalInformation;
import fs3.gui.model.CitizenTemplateModel;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TemplatesController implements Initializable {
    public ListView<CitizenTemplate> ltvCitizenTemplates;
    public TextField txfTemplateName;

    private CitizenTemplateModel citizenTemplateModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenTemplateModel = CitizenTemplateModel.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ltvCitizenTemplates.setItems(citizenTemplateModel.getObservableCitizenTemplates());
    }

    public void handleAddTemplate(ActionEvent event) {
        String name = txfTemplateName.getText();
        if (!name.isEmpty()) {
            CitizenTemplate citizenTemplate = new CitizenTemplate();
            PersonalInformation personalInformation = new PersonalInformation();
            GeneralInformation generalInformation = new GeneralInformation();
            personalInformation.setName(name);
            citizenTemplate.setPersonalInformation(personalInformation);
            try {
                citizenTemplateModel.createCitizenTemplate(citizenTemplate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void handleUpdateTemplate(ActionEvent event) {
        CitizenTemplate updatedCitizenTemplate = ltvCitizenTemplates.getSelectionModel().getSelectedItem();
        PersonalInformation personalInformation = new PersonalInformation();
        GeneralInformation generalInformation = new GeneralInformation();

        String name = txfTemplateName.getText();
        if (!name.isEmpty() && updatedCitizenTemplate != null) {
            personalInformation.setName(name);
            updatedCitizenTemplate.setPersonalInformation(personalInformation);
            updatedCitizenTemplate.setGeneralInformation(generalInformation);
            try {
                citizenTemplateModel.updateSelectedCitizenTemplate(updatedCitizenTemplate);
            } catch (Exception e) {
                PopUp.showError("Cannot update the template!");
            }
        }
    }
}
