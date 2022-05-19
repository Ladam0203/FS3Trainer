package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.Student;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;


public class NewStudentDialog extends Dialog<Student> {

    private NewStudentDialogController controller;

    public NewStudentDialog() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fs3/gui/view/teacher/tabs/students/dialog/NewStudentDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("New citizen template");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if (buttonType.equals(ButtonType.APPLY)) {

                }
                return null;
            });

        } catch (Exception e) {

        }
    }
}
