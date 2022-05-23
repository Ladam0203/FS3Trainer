package fs3.gui.controller.teacher.tabs.templates;

import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.gui.controller.teacher.tabs.templates.dialog.NewCitizenTemplateDialog;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.CitizenTemplateModel;
import fs3.util.PopUp;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class TemplatesController implements Initializable {
    @FXML
    private ListView<CitizenTemplate> ltvCitizenTemplates;
    @FXML
    private TextField txfFilterTemplates;

    private FilteredList<CitizenTemplate> citizenTemplateFilteredList;

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
        citizenTemplateFilteredList = new FilteredList<>(citizenTemplateModel.getObservableCitizenTemplates());
        ltvCitizenTemplates.setItems(citizenTemplateFilteredList);

    }

    @FXML
    private void handleSelectCitizen(MouseEvent mouseEvent) {
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

            contextMenu.getItems().add(newItem);
            contextMenu.getItems().add(copyTemplateItem);
            contextMenu.getItems().add(createInstance);
            contextMenu.getItems().add(deleteItem);

            ltvCitizenTemplates.setContextMenu(contextMenu);

            contextMenu.show(ltvCitizenTemplates.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());

            deleteItem.setOnAction(event -> {
                try {
                    citizenTemplateModel.deleteCitizenTemplate();
                } catch (Exception e) {
                    PopUp.showError("Couldn't delete citizen template!", e);
                }
            });

            copyTemplateItem.setOnAction(event -> {
                CitizenTemplate copiedCitizenTemplate = new CitizenTemplate(citizenTemplateModel.getSelectedCitizenTemplate());
                try {
                    citizenTemplateModel.createCitizenTemplate(copiedCitizenTemplate);
                } catch (Exception e) {
                    PopUp.showError("Couldn't copy citizen template!", e);
                }
            });

            createInstance.setOnAction(event -> {
                CitizenTemplate citizenTemplateInstance = citizenTemplateModel.getSelectedCitizenTemplate();
                CitizenInstance citizenInstance = new CitizenInstance(citizenTemplateInstance);
                try {
                    citizenInstanceModel.createCitizenInstance(citizenInstance);
                } catch (Exception e) {
                    PopUp.showError("Cannot create Citizen from template!", e);
                }
            });

            newItem.setOnAction(event -> {
                NewCitizenTemplateDialog dialog = new NewCitizenTemplateDialog();
                Optional<CitizenTemplate> result = dialog.showAndWait();
                result.ifPresent(response -> {
                    try {
                        citizenTemplateModel.createCitizenTemplate(response);
                    } catch (Exception e) {
                        PopUp.showError("Couldn't create citizen template!", e);
                    }
                });
            });
        }
    }

    @FXML
    private void handleFilterTemplates(KeyEvent keyEvent) {
        String query = txfFilterTemplates.getText();
        citizenTemplateFilteredList.setPredicate(new Predicate<CitizenTemplate>() {
            @Override
            public boolean test(CitizenTemplate citizenTemplate) {
                return citizenTemplate.getPersonalInformation().getName().toLowerCase().contains(query.toLowerCase());
            }
        });
    }
}
