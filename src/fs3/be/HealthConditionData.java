package fs3.be;

import fs3.enums.ExpectedLevel;
import fs3.enums.HealthConditionState;
import fs3.enums.HealthCondition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class HealthConditionData {
    private ObjectProperty<HealthConditionState> healthConditionState;
    private StringProperty professionalNote;
    private StringProperty currentAssessment;
    private ObjectProperty<ExpectedLevel> expectedLevel;
    private ObjectProperty<LocalDate> followUpDate;
    private StringProperty observationNote;

    public HealthConditionData() {
        this.healthConditionState = new SimpleObjectProperty<>();
        this.professionalNote = new SimpleStringProperty();
        this.currentAssessment = new SimpleStringProperty();
        this.expectedLevel = new SimpleObjectProperty<>();
        this.followUpDate = new SimpleObjectProperty<>();
        this.observationNote = new SimpleStringProperty();
    }

    public HealthConditionState getHealthConditionState() {
        return healthConditionState.get();
    }

    public void setHealthConditionState(HealthConditionState healthConditionState) {
        this.healthConditionState.set(healthConditionState);
    }

    public String getProfessionalNote() {
        return professionalNote.get();
    }

    public void setProfessionalNote(String professionalNote) {
        this.professionalNote.set(professionalNote);
    }

    public String getCurrentAssessment() {
        return currentAssessment.get();
    }

    public void setCurrentAssessment(String currentAssessment) {
        this.currentAssessment.set(currentAssessment);
    }

    public ExpectedLevel getExpectedLevel() {
        return expectedLevel.get();
    }

    public void setExpectedLevel(ExpectedLevel expectedLevel) {
        this.expectedLevel.set(expectedLevel);
    }

    public LocalDate getFollowUpDate() {
        return followUpDate.get();
    }

    public void setFollowUpDate(LocalDate followUpDate) {
        this.followUpDate.set(followUpDate);
    }

    public String getObservationNote() {
        return observationNote.get();
    }

    public void setObservationNote(String observationNote) {
        this.observationNote.set(observationNote);
    }
}