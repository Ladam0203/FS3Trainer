package fs3.gui.controller.teacher.tabs.students.dialog;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class NewStudentDialogController {

    @FXML
    private TextField txfStudentName, txfUsername, txfPassword;



    public TextField getTxfStudentName() {
        return txfStudentName;
    }

    public void setTxfStudentName(TextField txfStudentName) {
        this.txfStudentName = txfStudentName;
    }

    public TextField getTxfUsername() {
        return txfUsername;
    }

    public void setTxfUsername(TextField txfUsername) {
        this.txfUsername = txfUsername;
    }

    public TextField getTxfPassword() {
        return txfPassword;
    }

    public void setTxfPassword(TextField txfPassword) {
        this.txfPassword = txfPassword;
    }
}
