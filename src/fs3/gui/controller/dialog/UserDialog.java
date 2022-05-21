package fs3.gui.controller.dialog;

import fs3.be.User;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

/* Generalized version of dialog for the users (Admin, Teacher, Student) */
public abstract class UserDialog<T extends User> extends Dialog<T> {

    private UserDialogController<T> controller;
    private String fxmlFile;
    private String userClassName;

    /* Implementation has to provide the following: */
    public UserDialog(UserDialogController<T> controller, String fxmlFile, String userClassName) {
        this.controller = controller;
        this.fxmlFile = fxmlFile;
        this.userClassName = userClassName;
        load();
    }

    /* Load the dialog view with the values specified in the controller */
    private void load() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setController(controller);
            DialogPane dp = loader.load();
            this.setTitle("New " + userClassName);
            this.setDialogPane(dp);
            final Button btApply = (Button) dp.lookupButton(ButtonType.APPLY);
            btApply.addEventFilter(ActionEvent.ACTION, event -> {
                if (!controller.isValid()) {
                    event.consume();
                    PopUp.showError("Please fill in all mandatory fields! (*)");
                }
            });
            this.setResultConverter(buttonType -> {
                if (buttonType.equals(ButtonType.APPLY)) {
                    return this.controller.constructUser();
                }
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
            PopUp.showError("Error loading dialog");
        }
    }

    /* Load the user to the dialog */
    public void passUser(T selected){
        this.setTitle("Edit " + userClassName);
        controller.passUser(selected);
    }
}
