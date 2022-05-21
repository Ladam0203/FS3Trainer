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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class FunctionalAbilityComponentController implements Initializable {
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

    private CitizenInstanceModel citizenInstanceModel;

    private List<Image> limitationImages;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cmbCurrentLimitationLevel.getItems().addAll(LimitationLevel.values());
        cmbExpectedLimitationLevel.getItems().addAll(LimitationLevel.values());
        cmbPerformanceLevel.getItems().addAll(Performance.values());
        cmbPerceivedLimitationLevel.getItems().addAll(PerceivedLimitationLevel.values());
        cmbCurrentLimitationLevel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> changeState(newValue));

        cmbCurrentLimitationLevel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            changePictogram(imgCurrent, newValue);
        });
        cmbExpectedLimitationLevel.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            changePictogram(imgExpected, newValue);
        });

        limitationImages = List.of(
                new Image(getClass().getResource("../../../view/resources/0.png").toExternalForm()),
                new Image(getClass().getResource("../../../view/resources/1.png").toExternalForm()),
                new Image(getClass().getResource("../../../view/resources/2.png").toExternalForm()),
                new Image(getClass().getResource("../../../view/resources/3.png").toExternalForm()),
                new Image(getClass().getResource("../../../view/resources/4.png").toExternalForm())
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
        cmbCurrentLimitationLevel.getSelectionModel().clearSelection();
        cmbExpectedLimitationLevel.getSelectionModel().clearSelection();
        dtpFollowUpDate.getEditor().clear();
        txaProfessionalNote.clear();
        txaObservationNote.clear();
        cmbPerformanceLevel.getSelectionModel().clearSelection();
        cmbPerceivedLimitationLevel.getSelectionModel().clearSelection();
        txaCitizenRequest.clear();
    }

    private void changeState(LimitationLevel newValue) {
        disableFields(newValue == LimitationLevel.NOT_RELEVANT);
    }

    private void disableFields(boolean disable) {
        if (disable) {
            imgExpected.setImage(null);
        } else {
            changePictogram(imgExpected, cmbExpectedLimitationLevel.getSelectionModel().getSelectedItem());
        }
        cmbExpectedLimitationLevel.setDisable(disable);
        dtpFollowUpDate.setDisable(disable);
        txaProfessionalNote.setDisable(disable);
        txaObservationNote.setDisable(disable);
        cmbPerformanceLevel.setDisable(disable);
        cmbPerceivedLimitationLevel.setDisable(disable);
        txaCitizenRequest.setDisable(disable);
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

    @FXML
    private void handleSave() {
        CitizenInstance citizenInstance = citizenInstanceModel.getSelectedCitizenInstance();
        if (areFieldsFilled()) {
            FunctionalAbility functionalAbility = FunctionalAbility.fromString(ttpRoot.getText());
            FunctionalAbilityData functionalAbilityData = new FunctionalAbilityData();
            functionalAbilityData.setCurrentLimitationLevel(cmbCurrentLimitationLevel.getSelectionModel().getSelectedItem());
            if (cmbCurrentLimitationLevel.getSelectionModel().getSelectedItem() == LimitationLevel.NOT_RELEVANT) { //sync model with db
                cmbExpectedLimitationLevel.getSelectionModel().clearSelection();
                dtpFollowUpDate.getEditor().clear();
                txaProfessionalNote.clear();
                txaObservationNote.clear();
                cmbPerformanceLevel.getSelectionModel().clearSelection();
                cmbPerceivedLimitationLevel.getSelectionModel().clearSelection();
                txaCitizenRequest.clear();
            }
            functionalAbilityData.setExpectedLimitationLevel(cmbExpectedLimitationLevel.getSelectionModel().getSelectedItem());
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
                e.printStackTrace();
                //TODO: handle gracefully
            }
        }

    }

    private boolean areFieldsFilled() {
        if (!isCitizenSelected() || !isCurrentLimitationLevelSelected()) {
            return false;
        }
        if (!cmbCurrentLimitationLevel.getSelectionModel().getSelectedItem().equals(LimitationLevel.NOT_RELEVANT)) {
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
        if (cmbCurrentLimitationLevel.getSelectionModel().getSelectedItem() != null) {
            return true;
        } else {
            PopUp.showError("Select current limitation level!");
        }
        return false;
    }

    private boolean isExpectedLimitationLevelSelected() {
        if (cmbExpectedLimitationLevel.getSelectionModel().getSelectedItem() != null) {
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
}
