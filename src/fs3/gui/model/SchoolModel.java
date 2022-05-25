package fs3.gui.model;

import com.sun.source.tree.Scope;
import fs3.be.School;
import fs3.bll.SchoolLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SchoolModel {
    private SchoolLogic schoolLogic;
    private ObservableList<School> observableSchools;

    public SchoolModel(){
        schoolLogic = new SchoolLogic();
        try {
            readAllSchools();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<School> readAllSchools() throws Exception {
        observableSchools = FXCollections.observableList(schoolLogic.readAllSchools());
        return observableSchools;
    }

    public ObservableList<School> getAllSchools(){
        return observableSchools;
    }

}
