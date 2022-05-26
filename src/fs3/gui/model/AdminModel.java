package fs3.gui.model;

import fs3.be.Admin;
import fs3.be.School;
import fs3.bll.UserLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AdminModel {
    private UserLogic userLogic;
    private ObservableList<Admin> observableAdmins;

    public AdminModel() throws Exception {
        userLogic = new UserLogic();
        observableAdmins = FXCollections.observableList(userLogic.readAllAdmins());
    }

    public ObservableList<Admin> getAllAdmin(){
        return observableAdmins;
    }

    public void createAdmin(Admin admin) throws Exception {
        Admin created = (Admin)userLogic.createUser(admin);
        if(created != null){
            observableAdmins.add(created);
        }
    }

    public void updateAdmin(Admin admin) throws Exception {
        userLogic.updateUser(admin);
        observableAdmins.set(observableAdmins.indexOf(admin), admin);
    }
}
