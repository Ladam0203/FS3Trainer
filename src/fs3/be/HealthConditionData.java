package fs3.be;

import fs3.enums.ExpectedLevel;
import fs3.enums.HealthConditionState;
import fs3.enums.HealthCondition;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class HealthConditionData {
    private ObjectProperty<HealthConditionState> healthConditionState;
    private StringProperty professionalNote;
    private StringProperty currentAssessment;
    private ObjectProperty<ExpectedLevel> expectedLevel;
    private ObjectProperty<LocalDateTime> followUpDate;
    private StringProperty observationNote;

    public HealthConditionData() {
        this.healthConditionState = new SimpleObjectProperty<>();
        this.professionalNote = new SimpleStringProperty();
        this.currentAssessment = new SimpleStringProperty();
        this.expectedLevel = new SimpleObjectProperty<>();
        this.followUpDate = new SimpleObjectProperty<>();
        this.observationNote = new SimpleStringProperty();
    }
}
