package fs3.gui.controller.teacher.tabs.templates.dialog;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class CitizenTemplateDialogController {
    @FXML
    private TextField txfName, txfAge;

    public String getName(){
        return txfName.getText();
    }

    public int getAge(){
        return Integer.parseInt(txfAge.getText());
    }

    public boolean isFilled() {
        if (txfName.getText().isEmpty()) {
            return false;
        }
        if (txfAge.getText().isEmpty()) {
            return false;
        }
        return true;
    }

    //check if age is a number greater than 0
    public boolean isAgeValid() {
        try {
            int age = Integer.parseInt(txfAge.getText());
            if (age > 0) {
                return true;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return false;
    }
}
