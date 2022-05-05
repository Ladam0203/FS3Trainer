package fs3.gui;

import fs3.be.Student;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {
    public TextField txfUsername;
    public PasswordField psfPassword;
    Student student = new Student("jano", "jano");
    public void handleLogin(ActionEvent event) {
        String username = txfUsername.getText();
        String password = psfPassword.getText();

        if(username != null && password != null){
            if(username.equals(student.getUsername())&& password.equals(student.getPassword())){
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("view/student/StudentPageViewNew.fxml"));
                    Stage stage =  (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Scene scene = new Scene (root);
                    stage.setScene(scene);
                    stage.show();
                } catch (IOException e) {
                    PopUp.showError("Cannot load Student View!");
                }
            }
        }
    }


}
