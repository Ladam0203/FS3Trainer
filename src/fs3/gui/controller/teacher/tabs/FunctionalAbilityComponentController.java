package fs3.gui.controller.teacher.tabs;

import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.be.FunctionalAbilityData;
import fs3.enums.FunctionalAbility;
import fs3.enums.LimitationLevel;
import fs3.enums.PerceivedLimitationLevel;
import fs3.enums.Performance;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.CitizenTemplateModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class FunctionalAbilityComponentController implements Initializable {
    @FXML
    private TitledPane ttpRoot;
    @FXML
    private ComboBox<LimitationLevel> cmbCurrentLimitationLevel;
    @FXML
    private ComboBox<LimitationLevel> cmbExpectedLimitationLevel;
    @FXML
    private DatePicker dtpFollowUpDate;
    @FXML
    private TextArea txaProfessionalNote;
    @FXML
    private TextArea txaObservationNote;
    @FXML
    private ComboBox<Performance> cmbPerformanceLevel;
    @FXML
    private ComboBox<PerceivedLimitationLevel> cmbPerceivedLimitationLevel;
    @FXML
    private TextArea txaCitizenRequest;

    private CitizenTemplateModel citizenTemplateModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenTemplateModel = CitizenTemplateModel.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmbCurrentLimitationLevel.getItems().addAll(LimitationLevel.values());
        cmbExpectedLimitationLevel.getItems().addAll(LimitationLevel.values());
        cmbPerformanceLevel.getItems().addAll(Performance.values());
        cmbPerceivedLimitationLevel.getItems().addAll(PerceivedLimitationLevel.values());
        cmbCurrentLimitationLevel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> changeState(newValue));

    }

    public void setTitle(String title) {
        ttpRoot.setText(title);
    }

    public void setCmbCurrentLimitationLevel(ComboBox cmbCurrentLimitationLevel) {
        this.cmbCurrentLimitationLevel = cmbCurrentLimitationLevel;
    }

    public void setCmbExpectedLimitationLevel(ComboBox cmbExpectedLimitationLevel) {
        this.cmbExpectedLimitationLevel = cmbExpectedLimitationLevel;
    }

    public void setDtpFollowUpDate(DatePicker dtpFollowUpDate) {
        this.dtpFollowUpDate = dtpFollowUpDate;
    }

    public void setTxaProfessionalNote(TextArea txaProfessionalNote) {
        this.txaProfessionalNote = txaProfessionalNote;
    }

    public void setTxaObservationNote(TextArea txaObservationNote) {
        this.txaObservationNote = txaObservationNote;
    }

    public void setCmbPerformanceLevel(ComboBox cmbPerformanceLevel) {
        this.cmbPerformanceLevel = cmbPerformanceLevel;
    }

    public void setCmbPerceivedLimitationLevel(ComboBox cmbPerceivedLimitationLevel) {
        this.cmbPerceivedLimitationLevel = cmbPerceivedLimitationLevel;
    }

    public void setTxaCitizenRequest(TextArea txaCitizenRequest) {
        this.txaCitizenRequest = txaCitizenRequest;
    }

    public void clearFields() {
        cmbCurrentLimitationLevel.getSelectionModel().clearSelection();
        cmbExpectedLimitationLevel.getSelectionModel().clearSelection();
        dtpFollowUpDate.getEditor().clear();
        txaProfessionalNote.clear();
        txaObservationNote.clear();
        cmbPerformanceLevel.getSelectionModel().clearSelection();
        cmbPerceivedLimitationLevel.getSelectionModel().clearSelection();
        txaCitizenRequest.clear();
    }
    private void changeState(LimitationLevel newValue){
        disableFields(newValue == LimitationLevel.NOT_RELEVANT);
    }
    private void disableFields(boolean disable){
        cmbExpectedLimitationLevel.setDisable(disable);
        dtpFollowUpDate.setDisable(disable);
        txaProfessionalNote.setDisable(disable);
        txaObservationNote.setDisable(disable);
        cmbPerformanceLevel.setDisable(disable);
        cmbPerceivedLimitationLevel.setDisable(disable);
        txaCitizenRequest.setDisable(disable);
    }

    public void setFields(FunctionalAbilityData functionalAbilityData) {
        cmbCurrentLimitationLevel.getSelectionModel().select(functionalAbilityData.getCurrentLimitationLevel());
        cmbExpectedLimitationLevel.getSelectionModel().select(functionalAbilityData.getExpectedLimitationLevel());
        dtpFollowUpDate.setValue(functionalAbilityData.getFollowUpDate());
        txaProfessionalNote.setText(functionalAbilityData.getProfessionalNote());
        txaObservationNote.setText(functionalAbilityData.getObservationNote());
        cmbPerformanceLevel.getSelectionModel().select(functionalAbilityData.getPerformance());
        cmbPerceivedLimitationLevel.getSelectionModel().select(functionalAbilityData.getPerceivedLimitationLevel());
        txaCitizenRequest.setText(functionalAbilityData.getCitizenRequest());
    }

    public void handleSave() {
        CitizenTemplate citizenTemplate = citizenTemplateModel.getSelectedCitizenTemplate();
        if(areFieldsFilled()){
            FunctionalAbility functionalAbility = FunctionalAbility.fromString(ttpRoot.getText());
            FunctionalAbilityData functionalAbilityData = new FunctionalAbilityData();
            functionalAbilityData.setCurrentLimitationLevel(cmbCurrentLimitationLevel.getSelectionModel().getSelectedItem());
            if (cmbCurrentLimitationLevel.getSelectionModel().getSelectedItem() == LimitationLevel.NOT_RELEVANT) { //sync model with db
                cmbExpectedLimitationLevel.getSelectionModel().clearSelection();
                dtpFollowUpDate.getEditor().clear();
                txaProfessionalNote.clear();
                txaObservationNote.clear();
                cmbPerformanceLevel.getSelectionModel().clearSelection();
                cmbPerceivedLimitationLevel.getSelectionModel().clearSelection();
                txaCitizenRequest.clear();
            }
            functionalAbilityData.setExpectedLimitationLevel(cmbExpectedLimitationLevel.getSelectionModel().getSelectedItem());
            functionalAbilityData.setFollowUpDate(dtpFollowUpDate.getValue());
            functionalAbilityData.setProfessionalNote(txaProfessionalNote.getText());
            functionalAbilityData.setObservationNote(txaObservationNote.getText());
            functionalAbilityData.setPerformance(cmbPerformanceLevel.getSelectionModel().getSelectedItem());
            functionalAbilityData.setPerceivedLimitationLevel(cmbPerceivedLimitationLevel.getSelectionModel().getSelectedItem());
            functionalAbilityData.setCitizenRequest(txaCitizenRequest.getText());

            citizenTemplate.getFunctionalAbilities().put(functionalAbility, functionalAbilityData);
            try {
                citizenTemplateModel.updateSelectedCitizenTemplate(citizenTemplate);
            } catch (Exception e) {
                e.printStackTrace();
                //TODO: handle gracefully
            }
        }

    }

    private boolean areFieldsFilled(){
        if(!isCitizenSelected() || !isCurrentLimitationLevelSelected()){
            return false;
        }
        if(!cmbCurrentLimitationLevel.getSelectionModel().getSelectedItem().equals(LimitationLevel.NOT_RELEVANT)){
            if(!isExpectedLimitationLevelSelected() || !isDateValid() ||  !isPerformanceLevelSelected() || !isPerceivedLimitationLevelSelected()){
                return false;
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

    private boolean isCurrentLimitationLevelSelected(){
        if(cmbCurrentLimitationLevel.getSelectionModel().getSelectedItem() != null){
            return  true;
        }
        else{
            PopUp.showError("Select current limitation level!");
        }
        return  false;
    }

    private boolean isExpectedLimitationLevelSelected(){
        if (cmbExpectedLimitationLevel.getSelectionModel().getSelectedItem() != null){
            return true;
        }
        else{
            PopUp.showError("Select expected limitation level!");
        }
        return  false;
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

    private boolean isPerformanceLevelSelected(){
        if(cmbPerformanceLevel.getSelectionModel().getSelectedItem() != null){
            return true;
        }
        else{
            PopUp.showError("Select performance level!");
        }
        return false;
    }

    private boolean isPerceivedLimitationLevelSelected(){
        if(cmbPerceivedLimitationLevel.getSelectionModel().getSelectedItem() != null){
            return true;
        }
        else{
            PopUp.showError("Select PerceivedLimitationLevel");
        }
        return false;
    }
}
