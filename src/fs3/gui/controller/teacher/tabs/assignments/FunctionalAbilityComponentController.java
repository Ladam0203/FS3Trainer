package fs3.gui.controller.teacher.tabs.assignments;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;

public class FunctionalAbilityComponentController {
    @FXML
    private TitledPane ttpRoot;
    @FXML
    private ComboBox cmbCurrentLimitationLevel;
    @FXML
    private ImageView imgCurrent, imgExpected;
    @FXML
    private ComboBox cmbExpectedLimitationLevel;
    @FXML
    private TextArea txaProfessionalNote, txaObservationNote, txaCitizenRequest;
    @FXML
    private ComboBox cmbPerformanceLevel;
    @FXML
    private ComboBox cmbPerceivedLimitationLevel;
    @FXML
    private DatePicker dtpFollowUpDate;
}
