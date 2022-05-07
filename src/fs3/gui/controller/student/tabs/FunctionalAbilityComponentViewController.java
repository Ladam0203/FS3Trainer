package fs3.gui.controller.student.tabs;

import fs3.enums.FunctionalAbility;
import fs3.enums.LimitationLevel;
import fs3.enums.PerceivedLimitationLevel;
import fs3.enums.Performance;
import fs3.gui.model.CitizenModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.ResourceBundle;

public class FunctionalAbilityComponentViewController implements Initializable {
    @FXML
    private TitledPane ttpRoot;
    @FXML
    private ComboBox cmbCurrentLimitationLevel;
    @FXML
    private ComboBox cmbExpectedLimitationLevel;
    @FXML
    private DatePicker dtpFollowUpDate;
    @FXML
    private TextArea txaProfessionalNote;
    @FXML
    private TextArea txaObservationNote;
    @FXML
    private ComboBox cmbPerformanceLevel;
    @FXML
    private ComboBox cmbPerceivedLimitationLevel;
    @FXML
    private TextArea txaCitizenRequest;

    private CitizenModel citizenModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = CitizenModel.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmbCurrentLimitationLevel.getItems().addAll(LimitationLevel.values());
        cmbExpectedLimitationLevel.getItems().addAll(LimitationLevel.values());
        cmbPerformanceLevel.getItems().addAll(Performance.values());
        cmbPerceivedLimitationLevel.getItems().addAll(PerceivedLimitationLevel.values());

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

    public void handleSave(ActionEvent event) {
    }
}
