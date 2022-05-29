package fs3.be;

import fs3.enums.LimitationLevel;
import fs3.enums.PerceivedLimitationLevel;
import fs3.enums.Performance;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class FunctionalAbilityData {
    ObjectProperty<LimitationLevel> currentLimitationLevel;
    ObjectProperty<LimitationLevel> expectedLimitationLevel;
    StringProperty professionalNote;
    ObjectProperty<Performance> performance;
    ObjectProperty<PerceivedLimitationLevel> perceivedLimitationLevel;
    StringProperty citizenRequest;
    ObjectProperty<LocalDate> followUpDate;
    StringProperty observationNote;

    public FunctionalAbilityData() {
        currentLimitationLevel = new SimpleObjectProperty<>();
        expectedLimitationLevel = new SimpleObjectProperty<>();
        professionalNote = new SimpleStringProperty();
        performance = new SimpleObjectProperty<>();
        perceivedLimitationLevel = new SimpleObjectProperty<>();
        citizenRequest = new SimpleStringProperty();
        followUpDate = new SimpleObjectProperty<>();
        observationNote = new SimpleStringProperty();
    }

    //copy constructor
    public FunctionalAbilityData(FunctionalAbilityData other) {
        this.currentLimitationLevel = new SimpleObjectProperty<>(other.currentLimitationLevel.get());
        this.expectedLimitationLevel = new SimpleObjectProperty<>(other.expectedLimitationLevel.get());
        this.professionalNote = new SimpleStringProperty(other.professionalNote.get());
        this.performance = new SimpleObjectProperty<>(other.performance.get());
        this.perceivedLimitationLevel = new SimpleObjectProperty<>(other.perceivedLimitationLevel.get());
        this.citizenRequest = new SimpleStringProperty(other.citizenRequest.get());
        this.followUpDate = new SimpleObjectProperty<>(other.followUpDate.get());
        this.observationNote = new SimpleStringProperty(other.observationNote.get());
    }

    public LimitationLevel getCurrentLimitationLevel() {
        return currentLimitationLevel.get();
    }

    public void setCurrentLimitationLevel(LimitationLevel currentLimitationLevel) {
        this.currentLimitationLevel.set(currentLimitationLevel);
    }

    public LimitationLevel getExpectedLimitationLevel() {
        return expectedLimitationLevel.get();
    }

    public void setExpectedLimitationLevel(LimitationLevel expectedLimitationLevel) {
        this.expectedLimitationLevel.set(expectedLimitationLevel);
    }

    public String getProfessionalNote() {
        return professionalNote.get();
    }


    public void setProfessionalNote(String professionalNote) {
        this.professionalNote.set(professionalNote);
    }

    public Performance getPerformance() {
        return performance.get();
    }

    public void setPerformance(Performance performance) {
        this.performance.set(performance);
    }

    public PerceivedLimitationLevel getPerceivedLimitationLevel() {
        return perceivedLimitationLevel.get();
    }

    public void setPerceivedLimitationLevel(PerceivedLimitationLevel perceivedLimitationLevel) {
        this.perceivedLimitationLevel.set(perceivedLimitationLevel);
    }

    public String getCitizenRequest() {
        return citizenRequest.get();
    }

    public void setCitizenRequest(String citizenRequest) {
        this.citizenRequest.set(citizenRequest);
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
