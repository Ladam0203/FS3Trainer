package fs3.gui.controller.teacher.tabs.assignments;

import fs3.gui.model.CitizenInstanceModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class AMedicineListController implements Initializable {

    @FXML
    private TextArea txaMedicine;
    private CitizenInstanceModel citizenInstanceModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
        } catch (Exception e) {
            PopUp.showError("Cannot load citizen instance model!", e);
        }

        citizenInstanceModel.getSelectedCitizenProperty().addListener(
                (observable, oldValue, newValue) -> txaMedicine.setText(newValue.getMedicineList())
        );
    }
}
