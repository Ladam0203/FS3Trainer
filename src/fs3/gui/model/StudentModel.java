package fs3.gui.model;

import fs3.be.Student;
import fs3.be.User;
import fs3.bll.UserLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentModel {
    private static StudentModel instance;
    private UserLogic userLogic;
    private ObservableList<Student> observableStudents;

    private StudentModel (){
        userLogic = new UserLogic();
    }

    public static StudentModel getInstance(){
        return instance == null ? instance = new StudentModel() : instance;
    }

    public ObservableList<Student> readAllStudents() throws Exception {
        observableStudents = FXCollections.observableList(userLogic.readAllStudents());
        return observableStudents;
    }

    public void updateStudent(Student student) throws Exception {
        userLogic.updateStudent(student);
    }

    public ObservableList<Student> getObservableStudents(){
        return observableStudents;
    }

    public Student createStudent(Student student) throws Exception {
        return  null;
    }
}
