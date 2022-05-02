package fs3.gui.controller;

import fs3.gui.model.CitizenModel;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {
    private CitizenModel citizenModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = new CitizenModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
}
