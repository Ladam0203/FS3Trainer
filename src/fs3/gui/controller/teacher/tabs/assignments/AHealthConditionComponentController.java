package fs3.gui.controller.teacher.tabs.assignments;

import fs3.enums.ExpectedLevel;
import fs3.enums.HealthConditionState;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

import java.time.LocalDate;

public class AHealthConditionComponentController {
    @FXML
    private TitledPane ttpRoot;
    @FXML
    private ComboBox<HealthConditionState> cmbHealthConditionState;
    @FXML
    private DatePicker dtpFollowUpDate;
    @FXML
    private ComboBox<ExpectedLevel> cmbExpectedLevel;
    @FXML
    private TextArea txaProfessionalNote, txaCurrentAssessment, txaObservationNote;

    public void setTitle(String title) {
        ttpRoot.setText(title);
    }

    public void setHealthConditionState(HealthConditionState state) {
        if (state != null) {
            cmbHealthConditionState.getSelectionModel().select(state);
        }
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

    public void clearFields() {
        cmbHealthConditionState.getSelectionModel().select(null);
        cmbExpectedLevel.getSelectionModel().select(null);
        txaProfessionalNote.clear();
        txaCurrentAssessment.clear();
        txaObservationNote.clear();
        dtpFollowUpDate.setValue(null);
    }

}
