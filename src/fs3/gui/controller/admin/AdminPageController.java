package fs3.gui.controller.admin;

import fs3.be.School;
import fs3.gui.model.SchoolModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminPageController implements Initializable {
    @FXML
    private ListView<School> ltvSchools;

    private SchoolModel schoolModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        schoolModel = new SchoolModel();
        ltvSchools.setItems(schoolModel.getAllSchools());
    }

    @FXML
    private void handleSelectSchool(MouseEvent mouseEvent) {
    }
}
