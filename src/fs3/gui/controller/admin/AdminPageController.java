package fs3.gui.controller.admin;

import fs3.be.Admin;
import fs3.be.School;
import fs3.be.Teacher;
import fs3.gui.controller.admin.dialog.AdminDialog;
import fs3.gui.controller.admin.dialog.SchoolDialog;
import fs3.gui.controller.admin.dialog.TeacherDialog;
import fs3.gui.controller.dialog.CUDialog;
import fs3.gui.model.AdminModel;
import fs3.gui.model.SchoolModel;
import fs3.gui.model.TeacherModel;
import fs3.util.PopUp;
import javafx.collections.transformation.FilteredList;
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
    private ListView<Admin> ltvAdmins;
    @FXML
    private ListView<Teacher> ltvTeachers;

    @FXML
    private ListView<School> ltvSchools;

    private SchoolModel schoolModel;
    private TeacherModel teacherModel;
    private AdminModel adminModel;

    private ContextMenu contextMenu;
    private MenuItem newItem;
    private MenuItem editItem;
    private MenuItem deleteItem;

    private FilteredList<Teacher> teachersInSchool;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            adminModel = new AdminModel();
            schoolModel = new SchoolModel();
        } catch (Exception e) {
            PopUp.showError("Cannot load", e);
        }
        ltvSchools.setItems(schoolModel.getAllSchools());
        ltvAdmins.setItems(adminModel.getAllAdmin());
        teacherModel = TeacherModel.getInstance();

        teachersInSchool = new FilteredList<>(teacherModel.getObservableTeachers());
        teachersInSchool.setPredicate(null);
        ltvTeachers.setItems(teachersInSchool);


        setupContextMenu();
    }

    private void setupContextMenu() {
        contextMenu = new ContextMenu();
        newItem = new MenuItem();
        editItem = new MenuItem();
        deleteItem = new MenuItem();

        contextMenu.getItems().add(newItem);
        contextMenu.getItems().add(editItem);
        contextMenu.getItems().add(deleteItem);

        ltvTeachers.setContextMenu(contextMenu);
        ltvSchools.setContextMenu(contextMenu);
        ltvAdmins.setContextMenu(contextMenu);
    }

    @FXML
    private void handleSelectSchool(MouseEvent mouseEvent) {
        School selected = ltvSchools.getSelectionModel().getSelectedItem();

        if (selected != null) {
            filterTeachersInSchool(selected);
        }

        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            newItem.setText("New School");
            editItem.setText("Edit School");
            deleteItem.setText("Delete School");

            contextMenu.show(ltvSchools.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());

            newItem.setOnAction(event -> {
                newSchoolDialog();
            });

            editItem.setOnAction(event -> {
                editSchoolDialog();
            });

            deleteItem.setOnAction(event -> {
                deleteSchool();
            });
        }
    }

    private void filterTeachersInSchool(School selected) {
        teachersInSchool.setPredicate(teacher -> {
            if (teacher.getSchool() == null)
                return false;
            return teacher.getSchool().equals(selected);
        });
    }

    @FXML
    private void handleNewSchool(ActionEvent event) {
        newSchoolDialog();
    }

    @FXML
    private void handleEditSchool(ActionEvent event) {
        editSchoolDialog();
    }

    @FXML
    private void handleDeleteSchool(ActionEvent event) {
        deleteSchool();
    }

    private void newSchoolDialog() {
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

    private void editSchoolDialog() {
        School school = ltvSchools.getSelectionModel().getSelectedItem();
        if (school != null) {
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

    private void deleteSchool() {
        School school = ltvSchools.getSelectionModel().getSelectedItem();
        if (school != null) {
            try {
                schoolModel.deleteSchool(school);
            } catch (Exception e) {
                PopUp.showError("Cannot delete school", e);
            }
        }
    }

    @FXML
    private void handleSelectTeacher(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            newItem.setText("New Teacher");
            editItem.setText("Edit Teacher");
            deleteItem.setText("Delete Teacher");

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

    @FXML
    private void handleNewTeacher(ActionEvent event) {
        newTeacherDialog();
    }

    @FXML
    private void handleEditTeacher(ActionEvent event) {
        editTeacherDialog();
    }

    @FXML
    private void handleDeleteTeacher(ActionEvent event) {
        deleteTeacher();
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
        if (selected != null) {
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
        if (selected != null) {
            try {
                teacherModel.deleteTeacher(selected);
            } catch (Exception e) {
                PopUp.showError("Couldn't delete teacher!", e);
            }
        } else {
            PopUp.showError("Please select a teacher to delete first");
        }
    }

    @FXML
    private void handleSelectAdmin(MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            newItem.setText("New Admin");
            editItem.setText("Edit Admin");
            deleteItem.setText("Delete Admin");

            contextMenu.show(ltvAdmins.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());

            newItem.setOnAction(event -> {
                newAdminDialog();
            });
            editItem.setOnAction(event -> {
                editAdminDialog();
            });
            deleteItem.setOnAction(event -> {
                deleteAdmin();
            });
        }
    }

    @FXML
    private void handleNewAdmin(ActionEvent event) {
        newAdminDialog();
    }

    @FXML
    private void handleEditAdmin(ActionEvent event) {
        editAdminDialog();
    }

    @FXML
    private void handleDeleteAdmin(ActionEvent event) {
        deleteAdmin();
    }

    private void newAdminDialog() {
        CUDialog<Admin> dialog = new AdminDialog();
        Optional<Admin> result = dialog.showAndWait();
        result.ifPresent(response -> {
            try {
                //pass the new created admin to admin model
                adminModel.createAdmin(response);
            } catch (Exception e) {
                PopUp.showError("Couldn't create new admin!", e);
            }
        });
    }

    private void editAdminDialog() {
        Admin selected = ltvAdmins.getSelectionModel().getSelectedItem();
        if (selected != null) {
            CUDialog<Admin> dialog = new AdminDialog();
            dialog.passObject(selected);
            Optional<Admin> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    adminModel.updateAdmin(response);
                } catch (Exception e) {
                    PopUp.showError("Couldn't edit admin!", e);
                }
            });
        } else {
            PopUp.showError("Please select an admin to edit first!");
        }
    }

    private void deleteAdmin() {
        Admin selected = ltvAdmins.getSelectionModel().getSelectedItem();
        if (selected != null) {
            try {
                adminModel.deleteAdmin(selected);
            } catch (Exception e) {
                PopUp.showError("Couldn't delete admin!", e);
            }
        } else {
            PopUp.showError("Please select an admin to delete first");
        }
    }
}
