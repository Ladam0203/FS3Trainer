package fs3.gui.controller.admin;

import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.be.Teacher;
import fs3.be.User;
import fs3.gui.controller.teacher.tabs.templates.dialog.NewCitizenTemplateDialog;
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

public class AdminPageViewController implements Initializable {
    @FXML
    private ListView<Teacher> ltvTeachers;

    private TeacherModel teacherModel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        teacherModel = TeacherModel.getInstance();

        ltvTeachers.setItems(teacherModel.getObservableTeachers());
    }

    public void handleSelectTeacher(MouseEvent mouseEvent) {
        Teacher teacher = ltvTeachers.getSelectionModel().getSelectedItem();
        if(teacher == null){
            return;
        }
        if(mouseEvent.getButton().equals(MouseButton.SECONDARY)){
            ContextMenu contextMenu = new ContextMenu();
            MenuItem deleteItem = new MenuItem("Delete");
            MenuItem editItem = new MenuItem("Edit");
            MenuItem newItem = new MenuItem("New");

            contextMenu.getItems().add(deleteItem);
            contextMenu.getItems().add(editItem);
            contextMenu.getItems().add(newItem);

            ltvTeachers.setContextMenu(contextMenu);

            contextMenu.show(ltvTeachers.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());

            deleteItem.setOnAction(event -> {
                try {
                    teacherModel.deleteTeacher(teacher);
                } catch (Exception e) {
                    PopUp.showError("Cannot delete teacher!");
                }
            });

            editItem.setOnAction(event -> {
                try {

                } catch (Exception e) {
                    PopUp.showError("Cannot edit teacher!");
                }
            });

            newItem.setOnAction(event -> {

            });

        }
    }
}
