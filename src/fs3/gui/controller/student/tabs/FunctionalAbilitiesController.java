package fs3.gui.controller.student.tabs;

import fs3.be.FunctionalAbilityData;
import fs3.enums.FunctionalAbility;
import fs3.gui.model.CitizenInstanceModel;
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

public class FunctionalAbilitiesController implements Initializable {
    @FXML
    private Accordion accFunctionalAbilities;

    private EnumMap<FunctionalAbility, FunctionalAbilityComponentController> functionControllerMap = new EnumMap<>(FunctionalAbility.class);

    private CitizenInstanceModel citizenInstanceModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
            //add all functional abilities panes
            for (FunctionalAbility condition : FunctionalAbility.values()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../view/student/tabs/FunctionalAbilityComponentView.fxml"));
                Parent root = loader.load();
                FunctionalAbilityComponentController controller = loader.getController();
                controller.setTitle(condition.toString());
                accFunctionalAbilities.getPanes().add((TitledPane) root);
                functionControllerMap.put(condition, controller);
            }
        } catch (Exception e) {
            //TODO: handle gracefully
            e.printStackTrace();
        }
        citizenInstanceModel.getSelectedCitizenProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            for (Map.Entry<FunctionalAbility, FunctionalAbilityComponentController> entry : functionControllerMap.entrySet()) {
                FunctionalAbilityData functionalAbilityData = newValue.getFunctionalAbilities().get(entry.getKey());
                if (functionalAbilityData == null) {
                    entry.getValue().clearFields();
                    continue;
                }
                entry.getValue().setFields(functionalAbilityData);
            }
        });
    }
}
