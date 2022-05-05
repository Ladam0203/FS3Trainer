package fs3.gui.controller.student.tabs;

import fs3.enums.ExpectedLevel;
import fs3.enums.HealthConditionState;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HealthConditionController implements Initializable {
    @FXML
    private TitledPane ttpRoot;

    @FXML
    private ComboBox<HealthConditionState> cmbHealthConditionState;
    @FXML
    private TextArea txaProfessionalNote;

    @FXML
    private DatePicker dtpFollowUpDate;
    @FXML
    private TextArea txaCurrentAssessment;

    @FXML
    private ComboBox<ExpectedLevel> cmbExpectedLevel;
    @FXML
    private TextArea txaObservationNote;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbHealthConditionState.getItems().addAll(HealthConditionState.values());
        cmbExpectedLevel.getItems().addAll(ExpectedLevel.values());
        cmbHealthConditionState.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> changeState(newValue));
    }

    private void changeState(HealthConditionState newValue) {
        switch (newValue) {
            case INACTIVE -> disableFields(true);
            case ACTIVE, POTENTIAL -> disableFields(false);
        }
    }

    private void disableFields(boolean disable) {
        dtpFollowUpDate.setDisable(disable);
        txaProfessionalNote.setDisable(disable);
        txaCurrentAssessment.setDisable(disable);
        cmbExpectedLevel.setDisable(disable);
        txaObservationNote.setDisable(disable);
    }

    public void setTitle(String title) {
        ttpRoot.setText(title);
    }

    public void setHealthConditionState(HealthConditionState state){
        cmbHealthConditionState.getSelectionModel().select(state);
    }

    public void setExpectedLevel(ExpectedLevel level){
        cmbExpectedLevel.getSelectionModel().select(level);
    }

    public void setProfessionalNote(String note) {
        txaProfessionalNote.setText(note);
    }

    public void setCurrentAssessment(String assessment){
        txaCurrentAssessment.setText(assessment);
    }

    public void setObservationNote(String note){
        txaObservationNote.setText(note);
    }

    public void handleSave(ActionEvent event) {

    }
}
