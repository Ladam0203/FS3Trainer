package fs3.gui.controller.admin;


import fs3.be.School;
import fs3.gui.model.SchoolModel;
import fs3.gui.view.admin.SchoolDialog.SchoolDialog;
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

public class AdminPageController implements Initializable {

    @FXML
    private ListView<School> ltvSchools;

    private SchoolModel schoolModel;

    //setting up context Menu
    private ContextMenu contextMenu;
    private MenuItem newItem;
    private MenuItem editItem;
    private MenuItem deleteItem;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        schoolModel = new SchoolModel();
        ltvSchools.setItems(schoolModel.getAllSchools());
        contextMenu = new ContextMenu();
        newItem = new MenuItem();
        editItem = new MenuItem();
        deleteItem = new MenuItem();

        contextMenu.getItems().add(newItem);
        contextMenu.getItems().add(editItem);
        contextMenu.getItems().add(deleteItem);
    }

    @FXML
    private void handleSelectSchool(MouseEvent mouseEvent) {
    School selected = ltvSchools.getSelectionModel().getSelectedItem();
        if (selected == null) {
            return;
        }
        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            deleteItem.setText("Delete School");
            editItem.setText("Edit School");
            newItem.setText("New School");
            ltvSchools.setContextMenu(contextMenu);
            contextMenu.show(ltvSchools.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());

            newItem.setOnAction(event -> {
                newSchool();
            });

            editItem.setOnAction(event -> {
                editSchool();
            });

            deleteItem.setOnAction(event -> {
                deleteSchool();
            });
        }
    }

    @FXML
    private void handleNewSchool(ActionEvent event) {
        newSchool();
    }

    @FXML
    private void handleEditSchool(ActionEvent event) {
        editSchool();
    }

    @FXML
        private void handleDeleteSchool(ActionEvent event) {
        deleteSchool();
    }

    private void newSchool(){
        SchoolDialog dialog = new SchoolDialog();
        Optional<School> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                schoolModel.createSchool(response);
            } catch (Exception e) {
                PopUp.showError("Couldn't create school!", e);
            }
        });
    }

    private void editSchool(){
        School school = ltvSchools.getSelectionModel().getSelectedItem();
        if(school != null) {
            SchoolDialog dialog = new SchoolDialog();
            dialog.passSchool(school);
            Optional<School> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    schoolModel.updateSchool(response);
                } catch (Exception e) {
                    PopUp.showError("Couldn't edit school!", e);
                }
            });
        }
    }

    private void deleteSchool(){
        School school = ltvSchools.getSelectionModel().getSelectedItem();
        if (school != null) {
            try {
                schoolModel.deleteSchool(school);
            } catch (Exception e) {
                PopUp.showError("Cannot delete school", e);
            }
        }
    }
}
