package fs3.gui.controller.teacher.tabs;

import fs3.be.Citizen;
import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.be.HealthConditionData;
import fs3.enums.ExpectedLevel;
import fs3.enums.HealthCondition;
import fs3.enums.HealthConditionState;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.CitizenTemplateModel;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class HealthConditionComponentController implements Initializable {
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

    private CitizenTemplateModel citizenTemplateModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            citizenTemplateModel = CitizenTemplateModel.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
            //handle gracefully
        }

        cmbHealthConditionState.getItems().addAll(HealthConditionState.values());
        cmbExpectedLevel.getItems().addAll(ExpectedLevel.values());
        cmbHealthConditionState.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> changeState(newValue));
    }

    private void changeState(HealthConditionState newValue) {
        disableFields(newValue == HealthConditionState.INACTIVE); //unkown state also keeps the field enabled
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

    public void setHealthConditionState(HealthConditionState state) {
        cmbHealthConditionState.getSelectionModel().select(state);
    }

    public void setExpectedLevel(ExpectedLevel level) {
        cmbExpectedLevel.getSelectionModel().select(level);
    }

    public void setProfessionalNote(String note) {
        txaProfessionalNote.setText(note);
    }

    public void setCurrentAssessment(String assessment) {
        txaCurrentAssessment.setText(assessment);
    }

    public void setObservationNote(String note) {
        txaObservationNote.setText(note);
    }

    public void setDtpFollowUpDate(LocalDate date){
        dtpFollowUpDate.setValue(date);
    }


    public void handleSave(ActionEvent event) {
        CitizenTemplate citizenTemplate = citizenTemplateModel.getSelectedCitizenTemplate();
        if (areFieldsFilled()) {
            HealthCondition healthCondition = HealthCondition.fromString(ttpRoot.getText());
            HealthConditionData healthConditionData = new HealthConditionData();
            healthConditionData.setHealthConditionState(cmbHealthConditionState.getSelectionModel().getSelectedItem());
            if (healthConditionData.getHealthConditionState() == HealthConditionState.INACTIVE) {  //sync model with db
                txaProfessionalNote.clear();
                txaObservationNote.clear();
                txaCurrentAssessment.clear();
                cmbExpectedLevel.getSelectionModel().clearSelection();
                dtpFollowUpDate.getEditor().clear();
            }

            healthConditionData.setProfessionalNote(txaProfessionalNote.getText());
            healthConditionData.setObservationNote(txaObservationNote.getText());
            healthConditionData.setCurrentAssessment(txaCurrentAssessment.getText());
            healthConditionData.setExpectedLevel(cmbExpectedLevel.getSelectionModel().getSelectedItem());
            healthConditionData.setFollowUpDate(dtpFollowUpDate.getValue());

            citizenTemplate.getHealthConditions().put(healthCondition, healthConditionData);

            try {
                citizenTemplateModel.updateSelectedCitizenTemplate(citizenTemplate);
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: handle gracefully
            }
        }

    }

    private boolean areFieldsFilled(){
        if (!isCitizenSelected() || !isCurrentStateSelected()){
            return  false;
        }
        //fromString(cmbHealthConditionState.getSelectionModel().getSelectedItem().toString())
        if(!cmbHealthConditionState.getSelectionModel().getSelectedItem().equals(HealthConditionState.INACTIVE)){
            if(!isDateValid() || !isExpectedStateSelected() || !isCurrentAssessmentFilled()){
                return  false;
            }
        }
        return true;

    }

    private boolean isCitizenSelected(){
        if(citizenTemplateModel.getSelectedCitizenTemplate() != null){
            return true;
        }
        else {
            PopUp.showError("Select a citizen!");
        }
        return false;
    }

    private boolean isCurrentStateSelected (){
        if(cmbHealthConditionState.getSelectionModel().getSelectedItem()!= null){
            return true;
        }
        else {
            PopUp.showError("Select current status!");
        }
        return false;
    }

    private boolean isExpectedStateSelected (){
        if(cmbExpectedLevel.getSelectionModel().getSelectedItem()!= null){
            return true;
        }
        else {
            PopUp.showError("Select expected status!");
        }
        return false;
    }



    private boolean isDateValid(){
        if(dtpFollowUpDate.getValue() != null){
            if(dtpFollowUpDate.getValue().isAfter(LocalDate.now())){
                return true;

            }
            else{
                PopUp.showError("Follow up date cannot be in past!");
            }
        }
        else{
            PopUp.showError("Pick a date!");
        }
        return false;
    }

    private boolean isCurrentAssessmentFilled(){
        if(!txaCurrentAssessment.getText().isEmpty()){
            return true;
        }
        else{
            PopUp.showError("Current assessment needs to be filled out!");
        }
        return false;
    }

    public void clearFields() {
        cmbHealthConditionState.getSelectionModel().clearSelection();
        cmbExpectedLevel.getSelectionModel().clearSelection();
        txaProfessionalNote.clear();
        txaCurrentAssessment.clear();
        txaObservationNote.clear();
        dtpFollowUpDate.setValue(null);
    }

}
