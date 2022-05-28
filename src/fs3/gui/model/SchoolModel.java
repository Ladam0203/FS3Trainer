package fs3.gui.model;

import fs3.be.School;
import fs3.bll.SchoolLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SchoolModel {
    private final SchoolLogic schoolLogic;
    private ObservableList<School> observableSchools;

    public SchoolModel() throws Exception {
        schoolLogic = new SchoolLogic();
        readAllSchools();
    }

    public ObservableList<School> readAllSchools() throws Exception {
        observableSchools = FXCollections.observableList(schoolLogic.readAllSchools());
        return observableSchools;
    }

    public ObservableList<School> getAllSchools() {
        return observableSchools;
    }

    public void updateSchool(School school) throws Exception {
        schoolLogic.updateSchool(school);
        observableSchools.set(observableSchools.indexOf(school), school);
    }

    public void deleteSchool(School school) throws Exception {
        schoolLogic.deleteSchool(school);
        observableSchools.remove(school);
    }

    public void createSchool(School school) throws Exception {
        School created = schoolLogic.createSchool(school);
        if (created != null) {
            observableSchools.add(created);
        }
    }

}
