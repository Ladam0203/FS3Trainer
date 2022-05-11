package fs3.gui.controller.login;

import fs3.be.Student;
import fs3.be.Teacher;
import fs3.be.User;
import fs3.gui.model.LoginModel;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginViewController {
    @FXML
    private TextField txfUsername;
    @FXML
    private PasswordField psfPassword;
    private LoginModel loginModel;

    public LoginViewController() {
        loginModel = new LoginModel();
    }

    public void handleLogin(ActionEvent event) {
        String username = txfUsername.getText();
        String password = psfPassword.getText();

        try {
            User loggedUser = loginModel.tryLogin(username, password);
            if (loggedUser == null) {
                PopUp.showError("Wrong login credentials!");
                return;
            }
            try {
                boolean isStudent = loggedUser.getClass().equals(Student.class);
                boolean isTeacher = loggedUser.getClass().equals(Teacher.class);
//                boolean isAdmin = loggedUser.getClass().equals(Admin.class);

                Parent root = isStudent ? FXMLLoader.load(getClass().getResource("../../view/student/StudentPageView.fxml"))
                : isTeacher ? FXMLLoader.load(getClass().getResource("../../view/teacher/TeacherPageView.fxml"))
                        :null;
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                PopUp.showError("Cannot load the page!");
            }
        } catch (Exception e) {
            PopUp.showError("Cannot login");
        }
    }
}
