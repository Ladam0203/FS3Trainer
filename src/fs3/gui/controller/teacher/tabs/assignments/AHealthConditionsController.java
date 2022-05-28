package fs3.gui.controller.teacher.tabs.assignments;

import fs3.be.HealthConditionData;
import fs3.enums.HealthCondition;
import fs3.gui.model.CitizenInstanceModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;
import java.util.ResourceBundle;

public class AHealthConditionsController implements Initializable {
    @FXML
    private Accordion accHealthConditions;
    private CitizenInstanceModel citizenInstanceModel;

    private final EnumMap<HealthCondition, AHealthConditionComponentController> conditionControllerMap = new EnumMap<>(HealthCondition.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();

            //add all health condition panes
            String header = null;
            for (HealthCondition condition :
                    HealthCondition.values()) {
                //create new loader to get the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fs3/gui/view/teacher/tabs/assignments/HealthConditionComponentView.fxml"));
                //load view
                Parent root = loader.load();
                //get the controller
                AHealthConditionComponentController controller = loader.getController();
                //set title of pane
                controller.setTitle(condition.toString());
                //can I access it like this to get the citizen selected ?

                if (!Objects.equals(header, condition.getMain()) && condition.getMain() != null) {
                    header = condition.getMain();

                    TitledPane headerRoot = FXMLLoader.load(getClass().getResource("/fs3/gui/view/TitledPaneHeader.fxml"));
                    headerRoot.setText(condition.getMain());
                    accHealthConditions.getPanes().add(headerRoot);
                }

                accHealthConditions.getPanes().add((TitledPane) root);
                conditionControllerMap.put(condition, controller);
            }

            //selected citizen listener
            citizenInstanceModel.getSelectedCitizenProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    for (Map.Entry<HealthCondition, AHealthConditionComponentController> entry :
                            conditionControllerMap.entrySet()) {
                        HealthConditionData healthConditionData = newValue.getHealthConditions().get(entry.getKey());
                        if (healthConditionData != null) {
                            entry.getValue().setProfessionalNote(healthConditionData.getProfessionalNote());
                            entry.getValue().setHealthConditionState(healthConditionData.getHealthConditionState());
                            entry.getValue().setExpectedLevel(healthConditionData.getExpectedLevel());
                            entry.getValue().setDtpFollowUpDate(healthConditionData.getFollowUpDate());
                            entry.getValue().setCurrentAssessment(healthConditionData.getCurrentAssessment());
                            entry.getValue().setObservationNote(healthConditionData.getObservationNote());
                        } else {
                            entry.getValue().clearFields();
                        }
                    }
                }
            });

        } catch (Exception e) {
            PopUp.showError("Couldn't initialize health conditions tab", e);
        }
    }
}
