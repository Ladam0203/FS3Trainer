package fs3.gui.model;

import fs3.be.Teacher;
import fs3.bll.UserLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TeacherModel {
    private static TeacherModel instance;
    private final UserLogic userLogic;
    private ObservableList<Teacher> observableTeachers;

    private TeacherModel() {
        userLogic = new UserLogic();
    }

    public static TeacherModel getInstance() {
        return (instance == null) ? instance = new TeacherModel() : instance;
    }

    /* Reads teacher from db and updates the observable lsit with it */
    public List<Teacher> readAllTeachers() throws Exception {
        observableTeachers = FXCollections.observableList(userLogic.readAllTeachers());
        return userLogic.readAllTeachers();
    }

    public void updateTeacher(Teacher teacher) throws Exception {
        userLogic.updateUser(teacher);
    }

    public void createTeacher(Teacher teacher) throws Exception {
        Teacher created = (Teacher) userLogic.createUser(teacher);
        if (created != null) {
            observableTeachers.add(created);
        }
    }

    public void deleteTeacher(Teacher teacher) throws Exception {
        userLogic.deleteUser(teacher);
    }

    /* Returns already loaded teachers */
    public ObservableList<Teacher> getObservableTeachers() {
        return observableTeachers;
    }
}
