package fs3.gui.controller.teacher.tabs;


import fs3.be.HealthConditionData;
import fs3.enums.HealthCondition;
import fs3.gui.model.CitizenTemplateModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.ResourceBundle;

public class THealthConditionsController implements Initializable {
    @FXML
    private Accordion accHealthConditions;
    private CitizenTemplateModel citizenTemplateModel;

    private EnumMap<HealthCondition, THealthConditionComponentController> conditionControllerMap = new EnumMap<>(HealthCondition.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenTemplateModel = CitizenTemplateModel.getInstance();
            //add all health condition panes
            for (HealthCondition condition :
                    HealthCondition.values()) {
                //create new loader to get the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../view/teacher/tabs/HealthConditionComponentView.fxml"));
                //load view
                Parent root = loader.load();
                //get the controller
                THealthConditionComponentController controller = loader.getController();
                //set title of pane
                controller.setTitle(condition.toString());
                //can I access it like this to get the citizen selected ?



                accHealthConditions.getPanes().add((TitledPane) root);
                conditionControllerMap.put(condition, controller);
            }

            //selected citizen listener
            citizenTemplateModel.getSelectedCitizenTemplateProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    for (Map.Entry<HealthCondition, THealthConditionComponentController> entry :
                            conditionControllerMap.entrySet()) {
                        HealthConditionData healthConditionData = newValue.getHealthConditions().get(entry.getKey());
                        if (healthConditionData != null) {
                            entry.getValue().setProfessionalNote(healthConditionData.getProfessionalNote());
                            entry.getValue().setHealthConditionState(healthConditionData.getHealthConditionState());
                            entry.getValue().setExpectedLevel(healthConditionData.getExpectedLevel());
                            entry.getValue().setDtpFollowUpDate(healthConditionData.getFollowUpDate());
                            entry.getValue().setCurrentAssessment(healthConditionData.getCurrentAssessment());
                            entry.getValue().setObservationNote(healthConditionData.getObservationNote());
                        }
                        else {
                            entry.getValue().clearFields();
                        }
                    }
                }
            });

        } catch (Exception e) {
            //TODO: handle gracefully
            e.printStackTrace();
        }

    }



}