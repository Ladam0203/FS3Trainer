package fs3.gui.controller.teacher;

import fs3.be.CitizenTemplate;
import fs3.gui.model.CitizenTemplateModel;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherPageController implements Initializable {
    public ListView<CitizenTemplate> ltvCitizenTemplates;
    public TextField txfTemplateName;

    private CitizenTemplateModel citizenTemplateModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenTemplateModel = new CitizenTemplateModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ltvCitizenTemplates.setItems(citizenTemplateModel.getObservableCitizenTemplates());
    }
    public void handleAddTemplate(ActionEvent event) {
        
    }


}
