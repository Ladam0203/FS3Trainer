package fs3.gui.model;

import fs3.be.CitizenInstance;
import fs3.be.School;
import fs3.bll.CitizenLogic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CitizenInstanceModel {
    private static CitizenInstanceModel instance;

    private final CitizenLogic citizenLogic;
    private final ObservableList<CitizenInstance> observableCitizensInstances;
    private final ObjectProperty<CitizenInstance> selectedCitizenInstance;

    private CitizenInstanceModel() throws Exception {
        citizenLogic = new CitizenLogic();
        observableCitizensInstances = FXCollections.observableList(citizenLogic.readAllCitizensInstances());
        selectedCitizenInstance = new SimpleObjectProperty<>();
    }

    private CitizenInstanceModel(School school) throws Exception {
        citizenLogic = new CitizenLogic();
        observableCitizensInstances = FXCollections.observableList(citizenLogic.readAllCitizenInstancesFrom(school));
        selectedCitizenInstance = new SimpleObjectProperty<>();
    }

    public static CitizenInstanceModel getInstance() throws Exception {
        return instance == null ? instance = new CitizenInstanceModel() : instance;
    }

    public static CitizenInstanceModel getInstance(School school) throws Exception {
        return instance == null ? instance = new CitizenInstanceModel(school) : instance;
    }

    public ObservableList<CitizenInstance> getObservableCitizens() {
        return observableCitizensInstances;
    }

    public CitizenInstance getSelectedCitizenInstance() {
        return selectedCitizenInstance.get();
    }

    public ObjectProperty<CitizenInstance> getSelectedCitizenProperty() {
        return selectedCitizenInstance;
    }

    public void setSelectedCitizenInstance(CitizenInstance selectedCitizenInstance) {
        this.selectedCitizenInstance.set(selectedCitizenInstance);
    }

    public void createCitizenInstance(CitizenInstance citizenInstance) throws Exception {
        observableCitizensInstances.add(citizenLogic.createCitizenInstance(citizenInstance));
    }

    public void updateSelectedCitizen() throws Exception {
        citizenLogic.updateCitizen(selectedCitizenInstance.get());
    }

    public void deleteCitizenInstance(CitizenInstance citizenInstance) throws Exception {
        observableCitizensInstances.remove(citizenInstance);
        citizenLogic.deleteCitizen(citizenInstance);
    }
}
