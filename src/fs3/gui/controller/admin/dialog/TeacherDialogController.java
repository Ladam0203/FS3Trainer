package fs3.gui.controller.admin.dialog;

import fs3.be.School;
import fs3.be.Teacher;
import fs3.gui.controller.dialog.UserDialogController;
import fs3.gui.model.SchoolModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class TeacherDialogController extends UserDialogController<Teacher>  implements Initializable {
    @FXML
    protected TextField txfName, txfUsername, txfPassword;
    @FXML
    protected ComboBox<School> cmbSchool;

    private SchoolModel schoolModel;

  @Override
    public void initialize(URL location, ResourceBundle resources) {
        schoolModel = new SchoolModel();
        System.out.println(schoolModel.getAllSchools());
        cmbSchool.getItems().addAll(schoolModel.getAllSchools());
    }

    @Override
    public Teacher constructEmptyUser() {
        return new Teacher("", "");
    }

    @Override
    public void setFields(Teacher teacher) {
        txfName.setText(teacher.getName());
        txfUsername.setText(teacher.getUsername());
        txfPassword.setText(teacher.getPassword());
        cmbSchool.getSelectionModel().select(teacher.getSchool());
    }

    @Override
    public Teacher constructUser() {
        getUser().setName(txfName.getText());
        getUser().setUsername(txfUsername.getText());
        getUser().setPassword(txfPassword.getText());
        getUser().setSchool(cmbSchool.getSelectionModel().getSelectedItem());
        return getUser();
    }

    @Override
    public boolean isValid() {
        if (txfName.getText().isEmpty()) {
            return false;
        }
        if (txfUsername.getText().isEmpty()) {
            return false;
        }
        if (txfPassword.getText().isEmpty()) {
            return false;
        }
        if(cmbSchool.getValue() == null){
            return false;
        }
        return true;
    }


}
