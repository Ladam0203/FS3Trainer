package fs3.gui.controller.teacher.tabs.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

public class HealthConditionComponentController {
    @FXML
    private TitledPane ttpRoot;
    @FXML
    private ComboBox cmbHealthConditionState;
    @FXML
    private DatePicker dtpFollowUpDate;
    @FXML
    private ComboBox cmbExpectedLevel;
    @FXML
    private TextArea txaProfessionalNote, txaCurrentAssessment, txaObservationNote;
}
