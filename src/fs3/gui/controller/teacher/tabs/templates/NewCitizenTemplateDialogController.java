package fs3.gui.controller.teacher.tabs.templates;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NewCitizenTemplateDialogController {
    @FXML
    private TextField txfCitizenName;

    public String getName(){
        return txfCitizenName.getText();
    }

}
