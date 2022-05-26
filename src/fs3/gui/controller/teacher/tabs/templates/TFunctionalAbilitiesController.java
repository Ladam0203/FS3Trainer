package fs3.gui.controller.teacher.tabs.templates;

import fs3.be.FunctionalAbilityData;
import fs3.enums.FunctionalAbility;
import fs3.gui.model.CitizenTemplateModel;
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

public class TFunctionalAbilitiesController implements Initializable {
    @FXML
    private Accordion accFunctionalAbilities;

    private EnumMap<FunctionalAbility, TFunctionalAbilityComponentController> functionControllerMap = new EnumMap<>(FunctionalAbility.class);

    private CitizenTemplateModel citizenTemplateModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenTemplateModel = CitizenTemplateModel.getInstance();

            //add all functional abilities panes
            String header = null;
            for (FunctionalAbility condition : FunctionalAbility.values()) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fs3/gui/view/teacher/tabs/templates/FunctionalAbilityComponentView.fxml"));
                Parent root = loader.load();
                TFunctionalAbilityComponentController controller = loader.getController();
                controller.setFunctionalAbilityString(condition.toString());

                if (!Objects.equals(header, condition.getMain()) && condition.getMain() != null) {
                    header = condition.getMain();

                    TitledPane headerRoot = FXMLLoader.load(getClass().getResource("/fs3/gui/view/TitledPaneHeader.fxml"));
                    headerRoot.setText(condition.getMain());
                    accFunctionalAbilities.getPanes().add(headerRoot);
                }

                accFunctionalAbilities.getPanes().add((TitledPane) root);
                functionControllerMap.put(condition, controller);
            }
        } catch (Exception e) {
            PopUp.showError("Couldn't initialize functional abilities tab!", e);
        }

        citizenTemplateModel.getSelectedCitizenTemplateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                return;
            }
            for (Map.Entry<FunctionalAbility, TFunctionalAbilityComponentController> entry : functionControllerMap.entrySet()) {
                FunctionalAbilityData functionalAbilityData = newValue.getFunctionalAbilities().get(entry.getKey());
                if (functionalAbilityData != null) {
                    entry.getValue().setFields(functionalAbilityData);
                } else {
                    entry.getValue().clearFields();
                }
            }
        });
    }
}
