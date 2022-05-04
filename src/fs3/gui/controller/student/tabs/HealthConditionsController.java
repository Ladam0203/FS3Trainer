package fs3.gui.controller.student.tabs;

import fs3.enums.HealthCondition;
import fs3.gui.model.CitizenModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class HealthConditionsController implements Initializable {
    CitizenModel citizenModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = CitizenModel.getInstance();
        } catch (Exception e) {
            //TODO: handle gracefully
            e.printStackTrace();
        }
    }
}
