package fs3.gui.controller.student.tabs;

import fs3.be.CitizenInstance;
import fs3.be.FunctionalAbilityData;
import fs3.enums.FunctionalAbility;
import fs3.enums.LimitationLevel;
import fs3.enums.PerceivedLimitationLevel;
import fs3.enums.Performance;
import fs3.gui.model.CitizenInstanceModel;
import fs3.util.PopUp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FunctionalAbilityComponentController implements Initializable {
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

    LimitationLevel currentLimitationLevel;
    LimitationLevel expectedLimitationLevel;
    List<ImageView> currentImages;
    List<ImageView> expectedImages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
        } catch (Exception e) {
            PopUp.showError("Couldn't initialize functional ability component!", e);
        }

        cmbPerformanceLevel.getItems().addAll(Performance.values());
        cmbPerceivedLimitationLevel.getItems().addAll(PerceivedLimitationLevel.values());

        List<Image> limitationImages = List.of(
                new Image(getClass().getResource("../../../view/resources/0.png").toExternalForm()),
                new Image(getClass().getResource("../../../view/resources/1.png").toExternalForm()),
                new Image(getClass().getResource("../../../view/resources/2.png").toExternalForm()),
                new Image(getClass().getResource("../../../view/resources/3.png").toExternalForm()),
                new Image(getClass().getResource("../../../view/resources/4.png").toExternalForm())
        );

        tggRelevant = new ToggleGroup();
        rdbRelevant.setToggleGroup(tggRelevant);
        rdbNotRelevant.setToggleGroup(tggRelevant);

        tggRelevant.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (tggRelevant.getSelectedToggle() != null) {
                if (tggRelevant.getSelectedToggle().equals(rdbRelevant)) {
                    disableFields(false);
                }
                else if (tggRelevant.getSelectedToggle().equals(rdbNotRelevant)) {
                    disableFields(true);
                }
            }
        });

        currentImages = List.of(imgCurrentNo, imgCurrentSlight, imgCurrentModerate, imgCurrentSevere, imgCurrentTotal);
        expectedImages = List.of(imgExpectedNo, imgExpectedSlight, imgExpectedModerate, imgExpectedSevere, imgExpectedTotal);
        //TODO: REMOVE IF THEY ARE SET IN THE VIEW
        for (int i = 0; i < 5; i++) {
            currentImages.get(i).setImage(limitationImages.get(i));
            expectedImages.get(i).setImage(limitationImages.get(i));
        }

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
        cmbPerformanceLevel.getSelectionModel().clearSelection();
        cmbPerceivedLimitationLevel.getSelectionModel().clearSelection();
        txaCitizenRequest.clear();
    }

    private void disableFields(boolean disable) {
        if (disable) {
            makeAllOpaque();
        } else {
            if (currentLimitationLevel != null && currentLimitationLevel != LimitationLevel.NOT_RELEVANT) {
                makeOpaqueInExcept(currentImages, currentImages.get(currentLimitationLevel.ordinal()));
            } else {
                makeAllHalfOpaqueIn(currentImages);
            }
            if (expectedLimitationLevel != null)
                makeOpaqueInExcept(expectedImages, expectedImages.get(expectedLimitationLevel.ordinal()));
            else {
                makeAllHalfOpaqueIn(expectedImages);
            }
        }
        currentImages.forEach(image -> image.setDisable(disable));
        expectedImages.forEach(image -> image.setDisable(disable));
        dtpFollowUpDate.setDisable(disable);
        txaProfessionalNote.setDisable(disable);
        txaObservationNote.setDisable(disable);
        cmbPerformanceLevel.setDisable(disable);
        cmbPerceivedLimitationLevel.setDisable(disable);
        txaCitizenRequest.setDisable(disable);
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
        cmbPerformanceLevel.getSelectionModel().select(functionalAbilityData.getPerformance());
        cmbPerceivedLimitationLevel.getSelectionModel().select(functionalAbilityData.getPerceivedLimitationLevel());
        txaCitizenRequest.setText(functionalAbilityData.getCitizenRequest());
    }


    @FXML
    private void handleSave() {
        CitizenInstance citizenInstance = citizenInstanceModel.getSelectedCitizenInstance();
        currentLimitationLevel = tggRelevant.getSelectedToggle().equals(rdbRelevant) ? currentLimitationLevel : LimitationLevel.NOT_RELEVANT;
        if (!areFieldsFilled()) {
            return;
        }
        if (currentLimitationLevel.equals(LimitationLevel.NOT_RELEVANT)) { //sync model with db
            expectedLimitationLevel = null;
            dtpFollowUpDate.setValue(null);
            txaProfessionalNote.clear();
            txaObservationNote.clear();
            cmbPerformanceLevel.getSelectionModel().clearSelection();
            cmbPerceivedLimitationLevel.getSelectionModel().clearSelection();
            txaCitizenRequest.clear();
        }
        FunctionalAbility functionalAbility = FunctionalAbility.fromString(ttpRoot.getText());
        FunctionalAbilityData functionalAbilityData = new FunctionalAbilityData();
        functionalAbilityData.setCurrentLimitationLevel(currentLimitationLevel);
        functionalAbilityData.setExpectedLimitationLevel(expectedLimitationLevel);
        functionalAbilityData.setFollowUpDate(dtpFollowUpDate.getValue());
        functionalAbilityData.setProfessionalNote(txaProfessionalNote.getText());
        functionalAbilityData.setObservationNote(txaObservationNote.getText());
        functionalAbilityData.setPerformance(cmbPerformanceLevel.getSelectionModel().getSelectedItem());
        functionalAbilityData.setPerceivedLimitationLevel(cmbPerceivedLimitationLevel.getSelectionModel().getSelectedItem());
        functionalAbilityData.setCitizenRequest(txaCitizenRequest.getText());

        citizenInstance.getFunctionalAbilities().put(functionalAbility, functionalAbilityData);
        try {
            citizenInstanceModel.updateSelectedCitizen();
        } catch (Exception e) {
            PopUp.showError("Couldn't save citizen!", e);
        }

    }

    //event handler for image button
    @FXML
    private void handleSelectLimitationLevel(MouseEvent event) {
        if (tggRelevant.getSelectedToggle() != null)
        {
            ImageView selected = (ImageView) event.getSource();

            if (currentImages.contains(selected))
            {
                currentLimitationLevel = LimitationLevel.fromInt(currentImages.indexOf(selected));
                makeOpaqueInExcept(currentImages, selected);
            } else if (expectedImages.contains(selected)) {
                expectedLimitationLevel = LimitationLevel.fromInt(expectedImages.indexOf(selected));
                makeOpaqueInExcept(expectedImages, selected);
            }
        }
    }

    private boolean areFieldsFilled() {
        if (!isCitizenSelected() || !isCurrentLimitationLevelSelected()) {
            return false;
        }
        if (!currentLimitationLevel.equals(LimitationLevel.NOT_RELEVANT)) {
            if (!isExpectedLimitationLevelSelected() || !isDateValid() || !isPerformanceLevelSelected() || !isPerceivedLimitationLevelSelected()) {
                return false;
            }
        }
        return true;
    }

    private boolean isCitizenSelected() {
        if (citizenInstanceModel.getSelectedCitizenInstance() != null) {
            return true;
        } else {
            PopUp.showError("Select a citizen!");
        }
        return false;
    }

    private boolean isCurrentLimitationLevelSelected() {
        if (currentLimitationLevel != null) {
            return true;
        } else {
            PopUp.showError("Select current limitation level!");
        }
        return false;
    }

    private boolean isExpectedLimitationLevelSelected() {
        if (expectedLimitationLevel != null) {
            return true;
        } else {
            PopUp.showError("Select expected limitation level!");
        }
        return false;
    }

    private boolean isDateValid() {
        if (dtpFollowUpDate.getValue() != null) {
            if (dtpFollowUpDate.getValue().isAfter(LocalDate.now())) {
                return true;
            } else {
                PopUp.showError("Follow up date cannot be in past!");
            }
        } else {
            PopUp.showError("Pick a date!");
        }
        return false;
    }

    private boolean isPerformanceLevelSelected() {
        if (cmbPerformanceLevel.getSelectionModel().getSelectedItem() != null) {
            return true;
        } else {
            PopUp.showError("Select performance level!");
        }
        return false;
    }

    private boolean isPerceivedLimitationLevelSelected() {
        if (cmbPerceivedLimitationLevel.getSelectionModel().getSelectedItem() != null) {
            return true;
        } else {
            PopUp.showError("Select PerceivedLimitationLevel");
        }
        return false;
    }

    private void makeAllHalfOpaqueIn(List<ImageView> images) {
        images.forEach(imageView -> imageView.setOpacity(0.3));
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
