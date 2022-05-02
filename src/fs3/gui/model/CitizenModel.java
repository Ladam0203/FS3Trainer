package fs3.gui.model;

import fs3.be.Citizen;
import fs3.bll.CitizenLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class CitizenModel {
    private CitizenLogic citizenLogic;
    private ObservableList<Citizen> observableCitizens;

    public CitizenModel() throws Exception {
        citizenLogic = new CitizenLogic();
        observableCitizens = FXCollections.observableList(citizenLogic.readAllCitizens());
    }

    public ObservableList<Citizen> getObservableCitizens() {
        return observableCitizens;
    }
}
