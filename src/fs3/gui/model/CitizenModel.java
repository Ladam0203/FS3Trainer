package fs3.gui.model;

import fs3.be.Citizen;
import fs3.bll.CitizenLogic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLOutput;
import java.util.Optional;

public class CitizenModel {
    private static CitizenModel instance;

    private CitizenLogic citizenLogic;
    private ObservableList<Citizen> observableCitizens;
    private ObjectProperty<Citizen> selectedCitizen;

    private CitizenModel() throws Exception {
        citizenLogic = new CitizenLogic();
        observableCitizens = FXCollections.observableList(citizenLogic.readAllCitizens());
        selectedCitizen = new SimpleObjectProperty<>();
    }

    public static CitizenModel getInstance() throws Exception {
        return instance == null ? instance = new CitizenModel() : instance;
    }

    public ObservableList<Citizen> getObservableCitizens() {
        return observableCitizens;
    }

    public Citizen getSelectedCitizen() {
        return selectedCitizen.get();
    }

    public ObjectProperty<Citizen> getSelectedCitizenProperty() {
        return selectedCitizen;
    }

    public void setSelectedCitizen(Citizen selectedCitizen) {
        this.selectedCitizen.set(selectedCitizen);
    }

    public void updateSelectedCitizen() throws Exception {
        citizenLogic.updateCitizen(selectedCitizen.get());
    }
}
