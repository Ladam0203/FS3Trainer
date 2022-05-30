package fs3.gui.controller.student;

import fs3.be.CitizenInstance;
import fs3.be.Student;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.LoginModel;
import fs3.util.PopUp;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class StudentPageController implements Initializable {
    @FXML
    private ListView<CitizenInstance> ltvAssignedCitizens;

    private CitizenInstanceModel citizenInstanceModel;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
            LoginModel loginModel = LoginModel.getInstance();
            ltvAssignedCitizens.setItems(FXCollections.observableList(((Student) loginModel.getLoggedUser()).getAssignedCitizens()));
        } catch (Exception e) {
            PopUp.showError("Cannot load, check your internet connection!" ,e);
        }
    }

    @FXML
    private void handleSelectCitizen(MouseEvent actionEvent) {
        CitizenInstance citizenInstance = ltvAssignedCitizens.getSelectionModel().getSelectedItem();
        if (citizenInstance != null) {
            citizenInstanceModel.setSelectedCitizenInstance(citizenInstance);
        }
    }
}
