package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.User;
import fs3.util.PopUp;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;


public abstract class UserDialog<T extends User> extends Dialog<T> {

    private UserDialogController<T> controller;
    private String fxmlFile;
    private String userClassName;

    public UserDialog(UserDialogController<T> controller, String fxmlFile, String userClassName) {
        this.controller = controller;
        this.fxmlFile = fxmlFile;
        this.userClassName = userClassName;
        load();
    }

    private void load() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            DialogPane dp = loader.load();
            this.setTitle("New " + userClassName);
            this.setDialogPane(dp);
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

    public void setUser(T selected){
        this.setTitle("Edit " + userClassName);
        controller.setUser(selected);
    }
}
