package fs3.gui.model;

import fs3.be.Teacher;
import fs3.bll.UserLogic;

import java.util.List;

public class TeacherModel {
    private UserLogic userLogic;

    public TeacherModel() {
        userLogic = new UserLogic();
    }

    public List<Teacher> readAllTeachers() throws Exception {
        return userLogic.readAllTeachers();
    }
}
