package fs3.gui.controller.admin.dialog;

import fs3.be.School;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SchoolDialogController {
    @FXML
    private TextField txfSchoolName;

    private School school;

    public SchoolDialogController(){
        school = new School();
    }

    public void setTxfSchoolName(String name){
        txfSchoolName.setText(name);
    }

    public String getSchoolName(){
        return txfSchoolName.getText();
    }

    public void passSchool(School school){
        this.school = school;
        txfSchoolName.setText(school.getName());
    }

    public School constructSchool(){
        school.setName(txfSchoolName.getText());
        return school;
    }
}
