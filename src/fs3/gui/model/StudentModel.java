package fs3.gui.model;

import fs3.be.School;
import fs3.be.Student;
import fs3.bll.UserLogic;
import fs3.util.PopUp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentModel {
    private static StudentModel instance;
    private UserLogic userLogic;
    private ObservableList<Student> observableStudents;

    private StudentModel() throws Exception {
        userLogic = new UserLogic();
        observableStudents = FXCollections.observableList(userLogic.readAllStudents());
    }

    private StudentModel(School school) throws Exception {
        userLogic = new UserLogic();
        observableStudents = FXCollections.observableList(userLogic.readAllStudentsFrom(school));
    }

    public static StudentModel getInstance() throws Exception {
        return instance == null ? instance = new StudentModel() : instance;
    }

    public static StudentModel getInstance(School school) throws Exception {
        System.out.println("request for school: " + school.getName());
        return instance == null ? instance = new StudentModel(school) : instance;
    }

    public void updateStudent(Student student) throws Exception {
        userLogic.updateUser(student);
        observableStudents.set(observableStudents.indexOf(student), student);
    }

    public ObservableList<Student> getObservableStudents() {
        return observableStudents;
    }

    public void createStudent(Student student) throws Exception {
        Student created = (Student) userLogic.createUser(student);
        if (created != null) {
            observableStudents.add(created);
        }
    }

    public void deleteStudent(Student student) throws Exception {
        userLogic.deleteUser(student);
        observableStudents.remove(student);
    }
}
