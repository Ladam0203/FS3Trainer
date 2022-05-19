package fs3.gui.controller.teacher.tabs.templates.dialog;

import fs3.be.CitizenTemplate;
import fs3.be.PersonalInformation;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;


public class NewCitizenTemplateDialog extends Dialog<CitizenTemplate> {

    private NewCitizenTemplateDialogController controller;

    public NewCitizenTemplateDialog() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fs3/gui/view/teacher/tabs/templates/dialog/NewCitizenTemplateDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("New citizen template");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if(buttonType.equals(ButtonType.APPLY)){
                    CitizenTemplate newCitizenTemplate = new CitizenTemplate();
                    PersonalInformation personalInformation = new PersonalInformation();
                    personalInformation.setName(controller.getName());
                    newCitizenTemplate.setPersonalInformation(personalInformation);
                    return newCitizenTemplate;
                }
                return null;
            });

        } catch (Exception e) {

        }
    }
}
