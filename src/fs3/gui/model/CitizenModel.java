package fs3.gui.model;

import fs3.be.Citizen;
import fs3.bll.CitizenLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Optional;

public class CitizenModel {
    private CitizenLogic citizenLogic;
    private ObservableList<Citizen> allCitizens;

    public CitizenModel() throws Exception {
        citizenLogic = new CitizenLogic();
        allCitizens = FXCollections.observableList(citizenLogic.getAllCitizens());
    }

    public Optional<Citizen> getCitizen(int id) throws Exception {
        return citizenLogic.getCitizen(id);
    }

    public ObservableList<Citizen> getAllCitizens() throws Exception {
        return allCitizens;
    }

    public void createCitizen(Citizen citizen) {
        Optional<Citizen> citizen1 = citizenLogic.createCitizen(citizen);
        citizen1.ifPresent(value -> allCitizens.add(value));
    }

    public void updateCitizen(Citizen citizen) throws Exception {
        citizenLogic.updateCitizen(citizen);
        allCitizens.set(allCitizens.indexOf(citizen), citizen);
    }

    public void deleteCitizen(Citizen citizen) throws Exception {
        citizenLogic.deleteCitizen(citizen);
        allCitizens.remove(citizen);
    }
}
