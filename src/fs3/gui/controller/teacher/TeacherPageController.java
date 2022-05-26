package fs3.gui.controller.teacher;

import fs3.be.School;
import fs3.be.Teacher;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.CitizenTemplateModel;
import fs3.gui.model.LoginModel;
import fs3.gui.model.StudentModel;
import fs3.util.PopUp;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherPageController {
    public TeacherPageController() {
        try {
            LoginModel loginModel = LoginModel.getInstance();
            School school = ((Teacher) loginModel.getLoggedUser()).getSchool();
            StudentModel.getInstance(school);
            CitizenInstanceModel.getInstance(school);
            CitizenTemplateModel.getInstance(school);
        } catch (Exception e) {
            PopUp.showError("Couldn't load  data from the database!", e);
        }
    }
}
