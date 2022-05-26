package fs3.gui.controller.teacher.tabs.assignments;

import fs3.be.FunctionalAbilityData;
import fs3.enums.LimitationLevel;
import fs3.enums.PerceivedLimitationLevel;
import fs3.enums.Performance;
import fs3.gui.model.CitizenInstanceModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AFunctionalAbilityComponentController implements Initializable {
    @FXML
    private TitledPane ttpRoot;
    @FXML
    private Label lblIsRelevant;
    @FXML
    private RadioButton rdbRelevant, rdbNotRelevant;
    @FXML
    private ImageView imgCurrentNo, imgCurrentSlight, imgCurrentModerate, imgCurrentSevere, imgCurrentTotal;
    @FXML
    private ImageView imgExpectedNo, imgExpectedSlight, imgExpectedModerate, imgExpectedSevere, imgExpectedTotal;
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

    private CitizenInstanceModel citizenInstanceModel;

    private ToggleGroup tggRelevant;

    private LimitationLevel currentLimitationLevel;
    private LimitationLevel expectedLimitationLevel;
    private List<ImageView> currentImages;
    private List<ImageView> expectedImages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
        } catch (Exception e) {
            PopUp.showError("Couldn't initialize functional ability component!", e);
        }

        tggRelevant = new ToggleGroup();
        rdbRelevant.setToggleGroup(tggRelevant);
        rdbNotRelevant.setToggleGroup(tggRelevant);

        dtpFollowUpDate.setDisable(true);
        dtpFollowUpDate.setStyle("-fx-opacity: 1");
        dtpFollowUpDate.getEditor().setStyle("-fx-opacity: 1");

        currentImages = List.of(imgCurrentNo, imgCurrentSlight, imgCurrentModerate, imgCurrentSevere, imgCurrentTotal);
        expectedImages = List.of(imgExpectedNo, imgExpectedSlight, imgExpectedModerate, imgExpectedSevere, imgExpectedTotal);

        makeAllOpaque();
    }

    public void setFunctionalAbilityString(String functionalAbilityString) {
        ttpRoot.setText(functionalAbilityString);
        lblIsRelevant.setText("Is " + functionalAbilityString.toLowerCase() + " relevant?");
    }

    public void clearFields() {
        tggRelevant.selectToggle(null);
        currentLimitationLevel = null;
        expectedLimitationLevel = null;
        makeAllOpaque();
        dtpFollowUpDate.setValue(null);
        txaProfessionalNote.clear();
        txaObservationNote.clear();
        cmbPerformanceLevel.setValue(null);
        cmbPerceivedLimitationLevel.setValue(null);
        txaCitizenRequest.clear();
    }

    public void setFields(FunctionalAbilityData functionalAbilityData) {
        currentLimitationLevel = functionalAbilityData.getCurrentLimitationLevel();
        expectedLimitationLevel = functionalAbilityData.getExpectedLimitationLevel();
        if (functionalAbilityData.getCurrentLimitationLevel() == LimitationLevel.NOT_RELEVANT) {
            tggRelevant.selectToggle(rdbNotRelevant);
        } else {
            tggRelevant.selectToggle(rdbRelevant);
            makeOpaqueInExcept(currentImages, currentImages.get(currentLimitationLevel.ordinal()));
            makeOpaqueInExcept(expectedImages, expectedImages.get(expectedLimitationLevel.ordinal()));
        }
        dtpFollowUpDate.setValue(functionalAbilityData.getFollowUpDate());
        txaProfessionalNote.setText(functionalAbilityData.getProfessionalNote());
        txaObservationNote.setText(functionalAbilityData.getObservationNote());
        cmbPerformanceLevel.setValue(functionalAbilityData.getPerformance());
        cmbPerceivedLimitationLevel.setValue(functionalAbilityData.getPerceivedLimitationLevel());
        txaCitizenRequest.setText(functionalAbilityData.getCitizenRequest());
    }

    private void makeAllOpaque() {
        currentImages.forEach(imageView -> imageView.setOpacity(0.3));
        expectedImages.forEach(imageView -> imageView.setOpacity(0.3));
    }

    private void makeOpaqueInExcept(List<ImageView> images, ImageView imageView) {
        images.forEach(img -> {
            if (img != imageView) {
                img.setOpacity(0.6);
            } else {
                img.setOpacity(1);
            }
        });
    }
}
