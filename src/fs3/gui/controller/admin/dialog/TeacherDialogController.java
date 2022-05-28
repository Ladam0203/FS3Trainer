package fs3.gui.controller.admin.dialog;

import fs3.be.School;
import fs3.be.Teacher;
import fs3.gui.controller.dialog.CUDialogController;
import fs3.gui.model.SchoolModel;
import fs3.util.PopUp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class TeacherDialogController extends CUDialogController<Teacher> implements Initializable {
    @FXML
    protected TextField txfName, txfUsername, txfPassword;
    @FXML
    protected ComboBox<School> cmbSchool;

    private SchoolModel schoolModel;

  @Override
    public void initialize(URL location, ResourceBundle resources) {
      try {
          schoolModel = new SchoolModel();
      } catch (Exception e) {
          PopUp.showError("Cannot load", e);
      }
      System.out.println(schoolModel.getAllSchools());
        cmbSchool.getItems().addAll(schoolModel.getAllSchools());
    }

    @Override
    public Teacher constructEmptyObject() {
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
    public Teacher constructObject() {
        getObject().setName(txfName.getText());
        getObject().setUsername(txfUsername.getText());
        getObject().setPassword(txfPassword.getText());
        getObject().setSchool(cmbSchool.getSelectionModel().getSelectedItem());
        return getObject();
    }

    @Override
    public boolean isValid() {
        if (txfName.getText().isEmpty()     ||
            txfUsername.getText().isEmpty() ||
            txfPassword.getText().isEmpty() ||
            cmbSchool.getValue() == null    ){
                PopUp.showError("Please fill in all mandatory fields! (*)");
                return false;
        }
        return true;
    }


}
