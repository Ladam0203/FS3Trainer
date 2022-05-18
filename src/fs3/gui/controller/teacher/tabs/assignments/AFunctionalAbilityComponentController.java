package fs3.gui.controller.teacher.tabs.assignments;

import fs3.be.FunctionalAbilityData;
import fs3.enums.LimitationLevel;
import fs3.enums.PerceivedLimitationLevel;
import fs3.enums.Performance;
import fs3.gui.model.CitizenInstanceModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AFunctionalAbilityComponentController implements Initializable {
    @FXML
    private TitledPane ttpRoot;
    @FXML
    private ComboBox<LimitationLevel> cmbCurrentLimitationLevel;
    @FXML
    private ComboBox<LimitationLevel> cmbExpectedLimitationLevel;
    @FXML
    private ImageView imgCurrent, imgExpected;
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

    private List<Image> limitationImages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbCurrentLimitationLevel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            changePictogram(imgCurrent, newValue);
        });
        cmbExpectedLimitationLevel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            changePictogram(imgExpected, newValue);
        });

        limitationImages = List.of(
                new Image(getClass().getResource("../../../../view/resources/0.png").toExternalForm()),
                new Image(getClass().getResource("../../../../view/resources/1.png").toExternalForm()),
                new Image(getClass().getResource("../../../../view/resources/2.png").toExternalForm()),
                new Image(getClass().getResource("../../../../view/resources/3.png").toExternalForm()),
                new Image(getClass().getResource("../../../../view/resources/4.png").toExternalForm())
        );
    }

    private void changePictogram(ImageView img, LimitationLevel limitationLevel) {
        if (limitationLevel == null || limitationLevel == LimitationLevel.NOT_RELEVANT) {
            img.setImage(null);
            return;
        }
        img.setImage(limitationImages.get(limitationLevel.ordinal()));
    }

    public void setTitle(String title) {
        ttpRoot.setText(title);
    }

    public void clearFields() {
        imgCurrent.setImage(null);
        imgExpected.setImage(null);
        cmbCurrentLimitationLevel.getSelectionModel().select(null);
        cmbExpectedLimitationLevel.getSelectionModel().select(null);
        dtpFollowUpDate.getEditor().clear();
        txaProfessionalNote.clear();
        txaObservationNote.clear();
        cmbPerformanceLevel.getSelectionModel().select(null);
        cmbPerceivedLimitationLevel.getSelectionModel().select(null);
        txaCitizenRequest.clear();
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
}
