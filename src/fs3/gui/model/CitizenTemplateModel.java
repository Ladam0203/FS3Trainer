package fs3.gui.model;

import fs3.be.CitizenTemplate;
import fs3.be.School;
import fs3.bll.CitizenLogic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CitizenTemplateModel {
    private static CitizenTemplateModel instance;

    private final ObservableList<CitizenTemplate> observableCitizenTemplates;
    private final ObjectProperty<CitizenTemplate> selectedCitizenTemplate;
    private final CitizenLogic citizenLogic;

    private CitizenTemplateModel() throws Exception {
        citizenLogic = new CitizenLogic();
        observableCitizenTemplates = FXCollections.observableList(citizenLogic.readAllCitizenTemplates());
        selectedCitizenTemplate = new SimpleObjectProperty<>();
    }

    private CitizenTemplateModel(School school) throws Exception {
        citizenLogic = new CitizenLogic();
        observableCitizenTemplates = FXCollections.observableList(citizenLogic.readAllCitizenTemplatesFrom(school));
        selectedCitizenTemplate = new SimpleObjectProperty<>();
    }

    public static CitizenTemplateModel getInstance() throws Exception {
        return instance == null ? instance = new CitizenTemplateModel() : instance;
    }

    public static CitizenTemplateModel getInstance(School school) throws Exception {
        return instance == null ? instance = new CitizenTemplateModel(school) : instance;
    }

    public ObservableList<CitizenTemplate> getObservableCitizenTemplates() {
        return observableCitizenTemplates;
    }

    public CitizenTemplate getSelectedCitizenTemplate() {
        return selectedCitizenTemplate.get();
    }

    public ObjectProperty<CitizenTemplate> getSelectedCitizenTemplateProperty() {
        return selectedCitizenTemplate;
    }

    public void setSelectedCitizenTemplate(CitizenTemplate selectedCitizenTemplate) {
        this.selectedCitizenTemplate.set(selectedCitizenTemplate);
    }

    public void createCitizenTemplate(CitizenTemplate citizenTemplate) throws Exception {
        CitizenTemplate newCitizenTemplate = citizenLogic.createCitizenTemplate(citizenTemplate);
        observableCitizenTemplates.add(newCitizenTemplate);

    }

    public void updateCitizenTemplate(CitizenTemplate citizenTemplate) throws Exception {
        citizenLogic.updateCitizenTemplate(citizenTemplate);
        observableCitizenTemplates.set(observableCitizenTemplates.indexOf(citizenTemplate), citizenTemplate);
    }

    public void deleteCitizenTemplate() throws Exception {
        CitizenTemplate template = selectedCitizenTemplate.get();
        citizenLogic.deleteCitizen(template);
        observableCitizenTemplates.remove(template);
    }
}
