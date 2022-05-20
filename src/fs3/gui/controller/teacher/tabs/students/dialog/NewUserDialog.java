package fs3.gui.controller.teacher.tabs.students.dialog;

import fs3.be.Student;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;


public class NewUserDialog extends Dialog<Student> {

    private NewUserDialogController controller;

    public NewUserDialog() {
        super();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fs3/gui/view/teacher/tabs/students/dialog/NewUserDialogView.fxml"));
            DialogPane dp = loader.load();
            controller = loader.getController();
            this.setTitle("New User");
            this.setDialogPane(dp);
            this.setResultConverter(buttonType -> {
                if (buttonType.equals(ButtonType.APPLY)) {
                    return controller.constructStudent();
                }
                return null;
            });
        } catch (Exception e) {

        }
    }

    public void setStudent(Student selected){
        this.setTitle("Edit User");
        controller.setStudent(selected);
    }
}
