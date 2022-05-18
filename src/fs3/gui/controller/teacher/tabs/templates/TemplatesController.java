package fs3.gui.controller.teacher.tabs.templates;

import fs3.be.*;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.CitizenTemplateModel;
import fs3.util.PopUp;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TemplatesController implements Initializable {
    public ListView<CitizenTemplate> ltvCitizenTemplates;
    public TextField txfTemplateName;

    private CitizenTemplateModel citizenTemplateModel;
    private CitizenInstanceModel citizenInstanceModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            citizenTemplateModel = CitizenTemplateModel.getInstance();
            citizenInstanceModel = CitizenInstanceModel.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ltvCitizenTemplates.setItems(citizenTemplateModel.getObservableCitizenTemplates());
    }

    public void handleAddTemplate(ActionEvent event) {
        String name = txfTemplateName.getText();
        if (!name.isEmpty()) {
            CitizenTemplate citizenTemplate = new CitizenTemplate();
            PersonalInformation personalInformation = new PersonalInformation();
            GeneralInformation generalInformation = new GeneralInformation();
            personalInformation.setName(name);
            citizenTemplate.setPersonalInformation(personalInformation);
            try {
                citizenTemplateModel.createCitizenTemplate(citizenTemplate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

//    public void handleUpdateTemplate(ActionEvent event) {
//        CitizenTemplate updatedCitizenTemplate = ltvCitizenTemplates.getSelectionModel().getSelectedItem();
//        PersonalInformation personalInformation = new PersonalInformation();
//        GeneralInformation generalInformation = new GeneralInformation();
//
//        String name = txfTemplateName.getText();
//        if (!name.isEmpty() && updatedCitizenTemplate != null) {
//            personalInformation.setName(name);
//            updatedCitizenTemplate.setPersonalInformation(personalInformation);
//            updatedCitizenTemplate.setGeneralInformation(generalInformation);
//            try {
//                citizenTemplateModel.updateSelectedCitizenTemplate(updatedCitizenTemplate);
//            } catch (Exception e) {
//                PopUp.showError("Cannot update the template!");
//            }
//        }
//    }


    public void handleClick(MouseEvent mouseEvent) {
    }

    public void handleSelectCitizen(MouseEvent mouseEvent) {
            CitizenTemplate citizenTemplate = ltvCitizenTemplates.getSelectionModel().getSelectedItem();
            if (citizenTemplate != null) {
                citizenTemplateModel.setSelectedCitizenTemplate(citizenTemplate);
                //System.out.println(citizenTemplate);
            }

         if (mouseEvent.getButton().equals(MouseButton.SECONDARY)){

            MenuItem deleteItem = new MenuItem("Delete");
            MenuItem copyTemplateItem = new MenuItem("Copy template");
            MenuItem createInstance = new MenuItem("Create instance");
            ContextMenu contextMenu = new ContextMenu();
            contextMenu.getItems().add(deleteItem);
            contextMenu.getItems().add(copyTemplateItem);
            contextMenu.getItems().add(createInstance);
            ltvCitizenTemplates.setContextMenu(contextMenu);
            contextMenu.show(ltvCitizenTemplates.getPlaceholder(),mouseEvent.getX(), mouseEvent.getY());
            deleteItem.setOnAction(event -> {
                try {
                    citizenTemplateModel.deleteCitizenTemplate(citizenTemplateModel.getSelectedCitizenTemplate());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            copyTemplateItem.setOnAction(event -> {
                CitizenTemplate copiedCitizenTemplate = new CitizenTemplate(citizenTemplateModel.getSelectedCitizenTemplate());
                try {
                    citizenTemplateModel.createCitizenTemplate(copiedCitizenTemplate);
                } catch (Exception e) {
                    PopUp.showError("Cannot copy citizen template!");
                    e.printStackTrace();
                }
            });
            createInstance.setOnAction(event -> {
                CitizenTemplate citizenTemplateInstance = citizenTemplateModel.getSelectedCitizenTemplate();
                CitizenInstance citizenInstance = new CitizenInstance(citizenTemplateInstance);
                try {
                    citizenInstanceModel.createCitizenInstance(citizenInstance);
                } catch (Exception e) {
                    PopUp.showError("Cannot create Citizen from template!");
                    e.printStackTrace();
                }
            });
        }
    }

    public void handleCopyTemplate(ActionEvent event) {
        CitizenTemplate citizenTemplate = citizenTemplateModel.getSelectedCitizenTemplate();
        CitizenInstance citizenInstance = new CitizenInstance(citizenTemplate);
        try {
            citizenInstanceModel.createCitizenInstance(citizenInstance);
        } catch (Exception e) {
            PopUp.showError("Cannot create Citizen from template!");
            e.printStackTrace();
        }
    }

    public void handleCreateInstance(ActionEvent event) {
        CitizenTemplate citizenTemplate = citizenTemplateModel.getSelectedCitizenTemplate();
        CitizenInstance citizenInstance = new CitizenInstance(citizenTemplate);
        try {
            citizenInstanceModel.createCitizenInstance(citizenInstance);
        } catch (Exception e) {
            PopUp.showError("Cannot create Citizen from template!");
            e.printStackTrace();
        }
    }

}
