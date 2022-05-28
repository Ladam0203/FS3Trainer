package fs3.gui.controller.admin.tabs;

import fs3.be.Teacher;
import fs3.gui.controller.admin.dialog.TeacherDialog;
import fs3.gui.controller.dialog.CUDialog;
import fs3.gui.model.TeacherModel;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
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

public class TeacherManagementController implements Initializable {
    @FXML
    private ListView<Teacher> ltvTeachers;

    private TeacherModel teacherModel;

    private ContextMenu contextMenu;
    private MenuItem newItem;
    private MenuItem editItem;
    private MenuItem deleteItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teacherModel = TeacherModel.getInstance();
        ltvTeachers.setItems(teacherModel.getObservableTeachers());

        setupContextMenu();
    }

    private void setupContextMenu() {
        contextMenu = new ContextMenu();
        newItem = new MenuItem("New Teacher");
        editItem = new MenuItem("Edit Teacher");
        deleteItem = new MenuItem("Delete Teacher");

        contextMenu.getItems().add(newItem);
        contextMenu.getItems().add(editItem);
        contextMenu.getItems().add(deleteItem);

        ltvTeachers.setContextMenu(contextMenu);
    }

    @FXML
    private void handleSelectTeacher(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            contextMenu.show(ltvTeachers.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());

            newItem.setOnAction(event -> {
                newTeacherDialog();
            });

            editItem.setOnAction(event -> {
                editTeacherDialog();
            });

            deleteItem.setOnAction(event -> {
                deleteTeacher();
            });
        }
    }

    private void newTeacherDialog() {
        CUDialog<Teacher> dialog = new TeacherDialog();
        Optional<Teacher> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                //pass the new created teacher to teacher model
                teacherModel.createTeacher(response);
            } catch (Exception e) {
                PopUp.showError("Couldn't create new teacher!", e);
            }
        });
    }

    private void editTeacherDialog() {
        Teacher selected = ltvTeachers.getSelectionModel().getSelectedItem();
        if(selected != null){
            CUDialog<Teacher> dialog = new TeacherDialog();
            dialog.passObject(selected);
            Optional<Teacher> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    //update teacher info
                    teacherModel.updateTeacher(response);
                } catch (Exception e) {
                    PopUp.showError("Couldn't edit teacher!", e);
                }
            });
        } else {
         PopUp.showError("Please select a teacher to edit first!");
        }
    }

    private void deleteTeacher() {
        Teacher selected = ltvTeachers.getSelectionModel().getSelectedItem();
        if(selected != null){
            try {
                teacherModel.deleteTeacher(selected);
            } catch (Exception e) {
                PopUp.showError("Couldn't delete teacher!", e);
            }
        } else {
            PopUp.showError("Please select a teacher to delete first");
        }
    }

    public void handleNewTeacher(ActionEvent event) {
        newTeacherDialog();
    }

    public void handleEditTeacher(ActionEvent event) {
        editTeacherDialog();
    }

    public void handleDeleteTeacher(ActionEvent event) {
        deleteTeacher();
    }
}
