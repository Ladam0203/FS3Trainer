package fs3.gui.controller.teacher.tabs;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class GeneralInformationController implements Initializable {

    public TextField txfCitizenName;
    public TextArea txaCoping;
    public TextArea txaMotivation;
    public TextArea txaResources;
    public TextArea txaRoles;
    public TextArea txaHabits;
    public TextArea txaEducationAndJobs;
    public TextArea txaLifeStory;
    public TextArea txaHealthInformation;
    public TextArea txaEquipmentAids;
    public TextArea txaHomeLayout;
    public TextArea txaNetwork;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleSave(ActionEvent event) {
    }
}
