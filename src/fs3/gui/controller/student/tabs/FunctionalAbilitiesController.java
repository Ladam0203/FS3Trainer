package fs3.gui.controller.student.tabs;

import fs3.enums.FunctionalAbility;
import fs3.gui.model.CitizenModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;

import java.net.URL;
import java.util.EnumMap;
import java.util.ResourceBundle;

public class FunctionalAbilitiesController implements Initializable {
    @FXML
    private Accordion accFunctionalAbilities;

    private EnumMap<FunctionalAbility, FunctionalAbilityComponentViewController> functionControllerMap = new EnumMap<>(FunctionalAbility.class);

    private CitizenModel citizenModel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenModel = CitizenModel.getInstance();
            //add all functional abilities panes
            for(FunctionalAbility condition : FunctionalAbility.values()){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../../view/student/tabs/FunctionalAbilityComponentView.fxml"));
                Parent root = loader.load();
                FunctionalAbilityComponentViewController controller = loader.getController();
                controller.setTitle(condition.toString());
                accFunctionalAbilities.getPanes().add((TitledPane) root);
                functionControllerMap.put(condition, controller);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
