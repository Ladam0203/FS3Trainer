package fs3.gui.controller;

import fs3.be.Citizen;
import fs3.gui.model.CitizenModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {
    @FXML
    private ListView<Citizen> ltvAssignedCitizen;

    private CitizenModel citizenModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = new CitizenModel();
            ltvAssignedCitizen.setItems(citizenModel.getObservableCitizens());
        } catch (Exception e) {
            //TODO: handle exception gracefully
            e.printStackTrace();
        }
    }


}
