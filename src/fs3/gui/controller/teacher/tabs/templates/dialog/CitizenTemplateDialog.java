package fs3.gui.controller.teacher.tabs.templates.dialog;

import fs3.be.CitizenTemplate;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;


public class CitizenTemplateDialog extends Dialog<CitizenTemplate> {

    private CitizenTemplateDialogController controller;

    public CitizenTemplateDialog() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fs3/gui/view/teacher/tabs/templates/dialog/CitizenTemplateDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("Citizen Template");
            this.setDialogPane(dp);
            final Button btApply = (Button) dp.lookupButton(ButtonType.APPLY);
            btApply.addEventFilter(ActionEvent.ACTION, event -> {
                if (!controller.isFilled()) {
                    PopUp.showError("Please fill in all mandatory fields! (*)");
                    event.consume();
                    return;
                }
                if (!controller.isAgeValid()) {
                    PopUp.showError("The age has to be a number that is greater than 0!");
                    event.consume();
                    return;
                }
            });
            this.setResultConverter(buttonType -> {
                if (buttonType.equals(ButtonType.APPLY)) {
                    return controller.constructCitizenTemplate();
                }
                return null;
            });

        } catch (Exception e) {
            PopUp.showError("Couldn't load the citizen template dialog!", e);
        }
    }

    public void passCitizenTemplate(CitizenTemplate citizenTemplate) {
        controller.passCitizenTemplate(citizenTemplate);
    }
}
