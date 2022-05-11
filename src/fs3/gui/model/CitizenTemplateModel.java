package fs3.gui.model;

import fs3.be.CitizenTemplate;
import fs3.bll.CitizenTemplateLogic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CitizenTemplateModel {
    private ObservableList<CitizenTemplate> observableCitizenTemplates;
    private ObjectProperty<CitizenTemplate> selectedCitizenTemplate;
    private CitizenTemplateLogic citizenTemplateLogic;

    public CitizenTemplateModel() throws Exception {
        citizenTemplateLogic = new CitizenTemplateLogic();
        observableCitizenTemplates = FXCollections.observableList(citizenTemplateLogic.readAllCitizenTemplates());
        selectedCitizenTemplate = new SimpleObjectProperty<>();
    }

    public ObservableList<CitizenTemplate> getObservableCitizenTemplates(){return observableCitizenTemplates;}

    public CitizenTemplate getSelectedCitizenTemplate(){return selectedCitizenTemplate.get();}

    public ObjectProperty<CitizenTemplate> getSelectedCitizenTemplateProperty(){return selectedCitizenTemplate;}

    public void setSelectedCitizenTemplate(CitizenTemplate selectedCitizenTemplate){
        this.selectedCitizenTemplate.set(selectedCitizenTemplate);
    }

    public void updateSelectedCitizenTemplate(){}
}
