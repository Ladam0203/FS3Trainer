package fs3.gui.controller.student.tabs;

import fs3.be.Citizen;
import fs3.be.HealthConditionData;
import fs3.enums.HealthCondition;
import fs3.gui.controller.student.StudentPageController;
import fs3.gui.model.CitizenModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.*;
import java.util.concurrent.locks.Condition;

public class HealthConditionsController implements Initializable {
    @FXML
    private Accordion accHealthConditions;
    private CitizenModel citizenModel;

    private EnumMap<HealthCondition, HealthConditionController> conditionControllerMap = new EnumMap<>(HealthCondition.class);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = CitizenModel.getInstance();
            //add all health condition panes
            for (HealthCondition condition :
                    HealthCondition.values()) {
                //create new loader to get the controller
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../view/student/tabs/HealthConditionView.fxml"));
                //load view
                Parent root = loader.load();
                //get the controller
                HealthConditionController controller = loader.getController();
                //set title of pane
                controller.setTitle(condition.toString());
                //can I access it like this to get the citizen selected ?



                accHealthConditions.getPanes().add((TitledPane) root);
                conditionControllerMap.put(condition, controller);
            }

            //selected citizen listener
            citizenModel.getSelectedCitizenProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue != null) {
                    for (Map.Entry<HealthCondition, HealthConditionController> entry :
                            conditionControllerMap.entrySet()) {
                        HealthConditionData healthConditionData = newValue.getHealthConditions().get(entry.getKey());
                        if (healthConditionData != null) {
                            entry.getValue().setProfessionalNote(healthConditionData.getProfessionalNote());
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