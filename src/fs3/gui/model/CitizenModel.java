package fs3.gui.model;

import fs3.be.Citizen;
import fs3.bll.CitizenLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;
import java.util.Optional;

public class CitizenModel {
    private CitizenLogic citizenLogic;
    private ObservableList<Citizen> allCitizens;

    public CitizenModel() throws Exception {
        citizenLogic = new CitizenLogic();
        allCitizens = FXCollections.observableList(citizenLogic.getAllCitizens());
    }

    public Optional<Citizen> getCitizen(int id) throws Exception {return citizenLogic.getCitizen(id);}

    public ObservableList<Citizen> getAllCitizens() throws Exception {
        return allCitizens;
    }

    public Optional<Citizen> createCitizen(Citizen citizen){ return citizenLogic.createCitizen(citizen);}

    public void updateCitizen(Citizen citizen) throws Exception {citizenLogic.updateCitizen(citizen);}

     public void deleteCitizen(Citizen citizen) throws Exception{citizenLogic.deleteCitizen(citizen);}
}
