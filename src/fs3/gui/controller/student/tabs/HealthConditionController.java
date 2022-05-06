package fs3.gui.controller.student.tabs;

import fs3.be.Citizen;
import fs3.be.HealthConditionData;
import fs3.enums.ExpectedLevel;
import fs3.enums.HealthCondition;
import fs3.enums.HealthConditionState;
import fs3.gui.controller.student.StudentPageController;
import fs3.gui.model.CitizenModel;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.HashMap;
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

    private CitizenModel citizenModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            citizenModel = CitizenModel.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

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
        Citizen citizen = citizenModel.getSelectedCitizen();
        if (citizen != null) {
            HealthCondition healthCondition = HealthCondition.fromString(ttpRoot.getText());
            HealthConditionData healthConditionData = new HealthConditionData();
            healthConditionData.setHealthConditionState(cmbHealthConditionState.getSelectionModel().getSelectedItem());
            healthConditionData.setProfessionalNote(txaProfessionalNote.getText());
            healthConditionData.setCurrentAssessment(txaCurrentAssessment.getText());
            healthConditionData.setExpectedLevel(cmbExpectedLevel.getSelectionModel().getSelectedItem());
            healthConditionData.setFollowUpDate(dtpFollowUpDate.getValue());
            healthConditionData.setObservationNote(txaObservationNote.getText());
            HashMap<HealthCondition, HealthConditionData> healthConditions = new HashMap<>();
            healthConditions.put(healthCondition, healthConditionData);
            citizen.setHealthConditions(healthConditions);

            try {
                citizenModel.updateSelectedCitizen();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else
            PopUp.showError("kokot");
    }
}
