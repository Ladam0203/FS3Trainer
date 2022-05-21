package fs3.gui.controller.teacher.tabs.assignments;

import fs3.be.FunctionalAbilityData;
import fs3.enums.FunctionalAbility;
import fs3.gui.controller.student.tabs.FunctionalAbilityComponentController;
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
import java.util.ResourceBundle;

public class AFunctionalAbilitiesController implements Initializable {
    @FXML
    private Accordion accFunctionalAbilities;

    private EnumMap<FunctionalAbility, AFunctionalAbilityComponentController> functionControllerMap = new EnumMap<>(FunctionalAbility.class);

    private CitizenInstanceModel citizenInstanceModel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenInstanceModel = CitizenInstanceModel.getInstance();
            //add all functional abilities panes
            for(FunctionalAbility condition : FunctionalAbility.values()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fs3/gui/view/teacher/tabs/assignments/FunctionalAbilityComponentView.fxml"));
                Parent root = loader.load();
                AFunctionalAbilityComponentController controller = loader.getController();
                controller.setTitle(condition.toString());
                accFunctionalAbilities.getPanes().add((TitledPane) root);
                functionControllerMap.put(condition, controller);
            }
        } catch (Exception e) {
            PopUp.showError("Couldn't initialize functional abilities tab!", e);
        }

        citizenInstanceModel.getSelectedCitizenProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            for (Map.Entry<FunctionalAbility, AFunctionalAbilityComponentController> entry : functionControllerMap.entrySet()) {
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
