package fs3.gui.controller.admin.dialog;

import fs3.be.School;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;


public class SchoolDialog extends Dialog<School> {
    private SchoolDialogController controller;

    public SchoolDialog(){
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fs3/gui/view/admin/dialog/SchoolDialog.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("School");
            setDialogPane(dp);
            final Button btnApply = (Button) dp.lookupButton(ButtonType.APPLY);
            btnApply.addEventFilter(ActionEvent.ACTION, event -> {
                if(controller.getSchoolName().isEmpty()){
                    PopUp.showError("Enter the name!");
                    event.consume();
                    return;
                }
                this.setResultConverter(buttonType ->{
                    if(buttonType.equals(ButtonType.APPLY)){
                        return controller.constructSchool();
                    }
                    return null;
                } );
            });
        } catch (Exception e) {
            PopUp.showError("Couldn't load the school dialog!", e);
        }
    }

    public void passSchool(School school){
        controller.passSchool(school);
    }
}
