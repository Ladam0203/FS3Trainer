package fs3.gui.controller.teacher.tabs.templates;

import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.be.Teacher;
import fs3.gui.controller.teacher.tabs.templates.dialog.CitizenTemplateDialog;
import fs3.gui.model.CitizenInstanceModel;
import fs3.gui.model.CitizenTemplateModel;
import fs3.gui.model.LoginModel;
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

    private ContextMenu contextMenu;

    private FilteredList<CitizenTemplate> citizenTemplateFilteredList;

    private LoginModel loginModel;
    private CitizenTemplateModel citizenTemplateModel;
    private CitizenInstanceModel citizenInstanceModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            loginModel = LoginModel.getInstance();
            citizenTemplateModel = CitizenTemplateModel.getInstance();
            citizenInstanceModel = CitizenInstanceModel.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        citizenTemplateFilteredList = new FilteredList<>(citizenTemplateModel.getObservableCitizenTemplates());
        ltvCitizenTemplates.setItems(citizenTemplateFilteredList);

        contextMenu = new ContextMenu();
        MenuItem newItem = new MenuItem("New template...");
        MenuItem editItem = new MenuItem("Edit template...");
        MenuItem copyTemplateItem = new MenuItem("Create copy");
        MenuItem createInstance = new MenuItem("Create assignment");
        MenuItem deleteItem = new MenuItem("Delete");

        contextMenu.getItems().add(newItem);
        contextMenu.getItems().add(editItem);
        contextMenu.getItems().add(copyTemplateItem);
        contextMenu.getItems().add(createInstance);
        contextMenu.getItems().add(deleteItem);

        ltvCitizenTemplates.setContextMenu(contextMenu);

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
                PopUp.showError("Couldn't create assignment from template!", e);
            }
        });

        newItem.setOnAction(event -> {
            CitizenTemplateDialog dialog = new CitizenTemplateDialog();
            Optional<CitizenTemplate> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    response.setSchool(((Teacher) loginModel.getLoggedUser()).getSchool());
                    citizenTemplateModel.createCitizenTemplate(response);
                } catch (Exception e) {
                    PopUp.showError("Couldn't create citizen template!", e);
                }
            });
        });

        editItem.setOnAction(event -> {
            CitizenTemplateDialog dialog = new CitizenTemplateDialog();
            dialog.passCitizenTemplate(citizenTemplateModel.getSelectedCitizenTemplate());
            Optional<CitizenTemplate> result = dialog.showAndWait();
            result.ifPresent(response -> {
                try {
                    citizenTemplateModel.updateCitizenTemplate(response);
                } catch (Exception e) {
                    PopUp.showError("Couldn't update citizen template!", e);
                }
            });
        });
    }

    @FXML
    private void handleSelectCitizen(MouseEvent mouseEvent) {
        CitizenTemplate citizenTemplate = ltvCitizenTemplates.getSelectionModel().getSelectedItem();
        if (citizenTemplate != null) {
            citizenTemplateModel.setSelectedCitizenTemplate(citizenTemplate);
        }
        if (mouseEvent.getButton().equals(MouseButton.SECONDARY)) {
            contextMenu.show(ltvCitizenTemplates.getPlaceholder(), mouseEvent.getX(), mouseEvent.getY());
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
