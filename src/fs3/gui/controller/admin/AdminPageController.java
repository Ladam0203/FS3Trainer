package fs3.gui.controller.admin;

import fs3.be.Teacher;
import fs3.gui.controller.admin.dialog.TeacherDialog;
import fs3.gui.controller.dialog.UserDialog;
import fs3.gui.model.TeacherModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {
    @FXML
    private ListView<Teacher> ltvTeachers;
    @FXML
    private ListView ltvSchools;

    private TeacherModel teacherModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teacherModel = TeacherModel.getInstance();
        ltvTeachers.setItems(teacherModel.getObservableTeachers());
    }

    @FXML
    private void handleSelectTeacher(MouseEvent mouseEvent) {
        Teacher selected = ltvTeachers.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            ContextMenu contextMenu = new ContextMenu();
            MenuItem newItem = new MenuItem("New Teacher");
            MenuItem editItem = new MenuItem("Edit Teacher");
            MenuItem deleteItem = new MenuItem("Delete Teacher");

            contextMenu.getItems().add(newItem);
            contextMenu.getItems().add(editItem);
            contextMenu.getItems().add(deleteItem);

            ltvTeachers.setContextMenu(contextMenu);
            contextMenu.show(ltvTeachers.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());

            newItem.setOnAction(event -> {
                UserDialog<Teacher> dialog = new TeacherDialog();
                Optional<Teacher> result = dialog.showAndWait();
                result.ifPresent(response -> {
                    try {
                        //pass the new created teacher to teacher model
                        teacherModel.createTeacher(response);
                    } catch (Exception e) {
                        PopUp.showError("Cannot create new teacher!");
                        e.printStackTrace();
                    }
                });
            });

            editItem.setOnAction(event -> {
                try {
                    UserDialog<Teacher> dialog = new TeacherDialog();
                    dialog.passUser(selected);
                    Optional<Teacher> result = dialog.showAndWait();
                    result.ifPresent(response -> {
                        try {
                            //update teacher info
                            teacherModel.updateTeacher(response);
                        } catch (Exception e) {
                            PopUp.showError("Cannot edit teacher!");
                            e.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    PopUp.showError("Cannot edit teacher!");
                }
            });

            deleteItem.setOnAction(event -> {
                try {
                    teacherModel.deleteTeacher(selected);
                } catch (Exception e) {
                    PopUp.showError("Cannot delete teacher!");
                }
            });
        }
    }

    @FXML
    private void handleSelectSchool(MouseEvent mouseEvent) {
    }
}
