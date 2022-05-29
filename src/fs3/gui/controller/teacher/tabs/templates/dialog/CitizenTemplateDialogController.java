package fs3.gui.controller.teacher.tabs.templates.dialog;

import fs3.be.CitizenTemplate;
import fs3.be.PersonalInformation;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CitizenTemplateDialogController {
    private CitizenTemplate template;

    @FXML
    private TextField txfName, txfAge;

    public CitizenTemplateDialogController() {
        template = new CitizenTemplate();
        template.setPersonalInformation(new PersonalInformation());
    }

    public String getName() {
        return txfName.getText();
    }

    public int getAge() {
        return Integer.parseInt(txfAge.getText());
    }

    public boolean isFilled() {
        if (txfName.getText().isEmpty()) {
            return false;
        }
        return !txfAge.getText().isEmpty();
    }

    //check if age is a number greater than 0
    public boolean isAgeValid() {
        try {
            int age = Integer.parseInt(txfAge.getText());
            if (age > 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }

    public void passCitizenTemplate(CitizenTemplate citizenTemplate) {
        this.template = citizenTemplate;
        txfName.setText(citizenTemplate.getPersonalInformation().getName());
        txfAge.setText(String.valueOf(citizenTemplate.getPersonalInformation().getAge()));
    }

    public CitizenTemplate constructCitizenTemplate() {
        template.getPersonalInformation().setName(txfName.getText());
        template.getPersonalInformation().setAge(Integer.parseInt(txfAge.getText()));
        return template;
    }
}
