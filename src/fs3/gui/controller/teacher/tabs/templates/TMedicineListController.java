package fs3.gui.controller.teacher.tabs.templates;

import fs3.be.CitizenTemplate;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.CitizenTemplateModel;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class TMedicineListController implements Initializable {
    @FXML
    private TextArea txaMedicine;

    private CitizenTemplateModel citizenTemplateModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenTemplateModel = CitizenTemplateModel.getInstance();
        } catch (Exception e) {
            PopUp.showError("Cannot load citizen template model!", e);
        }

        citizenTemplateModel.getSelectedCitizenTemplateProperty().addListener(
                (observable, oldValue, newValue) -> txaMedicine.setText(newValue.getMedicineList())
        );
    }

    @FXML
    private void handleSave(ActionEvent event) {
        CitizenTemplate selected = citizenTemplateModel.getSelectedCitizenTemplate();
        if(selected == null){
            return;
        }
        selected.setMedicineList(txaMedicine.getText());
        try {
            citizenTemplateModel.updateCitizenTemplate(selected);
        } catch (Exception e) {
            PopUp.showError("Cannot update citizen template!", e);
        }
    }
}
