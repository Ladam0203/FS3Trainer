package fs3.gui.controller.student.tabs;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class ObservationsController implements Initializable {
    @FXML
    private ComboBox cmbType;
    @FXML
    private DatePicker dtpFrom;
    @FXML
    private DatePicker dtpTo;
    @FXML
    private TableView tbvObservations;
    @FXML
    private TableColumn colDate;
    @FXML
    private TableColumn colType;
    @FXML
    private ScatterChart chrObservation;
    @FXML
    private CategoryAxis axsCategory;
    @FXML
    private NumberAxis axsNumber;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
