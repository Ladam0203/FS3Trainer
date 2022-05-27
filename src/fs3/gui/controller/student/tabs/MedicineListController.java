package fs3.gui.controller.student.tabs;

import fs3.be.CitizenInstance;
import fs3.gui.model.CitizenInstanceModel;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class MedicineListController implements Initializable {
    @FXML
    private TextArea txaMedicine;
    private CitizenInstanceModel citizenInstanceModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
        } catch (Exception e) {
            PopUp.showError("Cannot load citizen instance model", e);
        }
    }

    public void handleSave(ActionEvent event) {
        CitizenInstance selected = citizenInstanceModel.getSelectedCitizenInstance();
        if(selected == null){
            return;
        }
        selected.setMedicineList(txaMedicine.getText());
        try {
            citizenInstanceModel.updateSelectedCitizen();
        } catch (Exception e) {
            PopUp.showError("Cannot update citizen!", e);
        }
    }
}
