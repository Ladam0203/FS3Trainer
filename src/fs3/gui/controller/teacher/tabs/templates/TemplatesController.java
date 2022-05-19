package fs3.gui.controller.teacher.tabs.templates;

import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.gui.controller.teacher.tabs.templates.dialog.NewCitizenTemplateDialog;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.CitizenTemplateModel;
import fs3.util.PopUp;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
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

    public void handleSelectCitizen(MouseEvent mouseEvent) {
        CitizenTemplate citizenTemplate = ltvCitizenTemplates.getSelectionModel().getSelectedItem();
        if (citizenTemplate != null) {
            citizenTemplateModel.setSelectedCitizenTemplate(citizenTemplate);
        }

        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {

            ContextMenu contextMenu = new ContextMenu();
            MenuItem deleteItem = new MenuItem("Delete");
            MenuItem copyTemplateItem = new MenuItem("Copy template");
            MenuItem createInstance = new MenuItem("Create instance");
            MenuItem newItem = new MenuItem("New template");

            contextMenu.getItems().add(deleteItem);
            contextMenu.getItems().add(copyTemplateItem);
            contextMenu.getItems().add(createInstance);
            contextMenu.getItems().add(newItem);

            ltvCitizenTemplates.setContextMenu(contextMenu);

            contextMenu.show(ltvCitizenTemplates.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());

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

            newItem.setOnAction(event -> {
                NewCitizenTemplateDialog dialog = new NewCitizenTemplateDialog();
                Optional<CitizenTemplate> result = dialog.showAndWait();
                result.ifPresent(response -> {
                    try {
                        citizenTemplateModel.createCitizenTemplate(response);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            });
        }
    }
}
