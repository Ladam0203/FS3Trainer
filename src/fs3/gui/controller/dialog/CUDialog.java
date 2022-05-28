package fs3.gui.controller.dialog;

import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

/* Generalized version of dialog for the users (Admin, Teacher, Student) */
public abstract class CUDialog<T> extends Dialog<T> {

    private CUDialogController<T> controller;
    private String fxmlFile;
    private String objectClassName;

    /* Implementation has to provide the following: */
    public CUDialog(CUDialogController<T> controller, String fxmlFile, String objectClassName) {
        this.controller = controller;
        this.fxmlFile = fxmlFile;
        this.objectClassName = objectClassName;
        load();
    }

    /* Load the dialog view with the values specified in the controller. */
    private void load() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            loader.setController(controller);
            DialogPane dp = loader.load();
            this.setTitle(objectClassName);
            this.setDialogPane(dp);
            final Button btApply = (Button) dp.lookupButton(ButtonType.APPLY);
            btApply.addEventFilter(ActionEvent.ACTION, event -> { //This keeps the Dialog open, if the provided data is not valid.
                if (!controller.isValid()) {
                    event.consume();
                }
            });
            this.setResultConverter(buttonType -> {
                if (buttonType.equals(ButtonType.APPLY)) {
                    return this.controller.constructObject();
                }
                return null;
            });
        } catch (Exception e) {
            PopUp.showError("Error loading dialog!", e);
        }
    }

    /* Load the user to the dialog */
    public void passObject(T object){
        controller.passObject(object);
    }
}
