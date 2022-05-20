package fs3.gui.model;

import fs3.be.Student;
import fs3.bll.UserLogic;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentModel {
    private static StudentModel instance;
    private UserLogic userLogic;
    private ObservableList<Student> observableStudents;
    private ObjectProperty<Student> selectedStudent;

    private StudentModel() {
        userLogic = new UserLogic();
        selectedStudent = new SimpleObjectProperty<>();
    }

    public static StudentModel getInstance() {
        return instance == null ? instance = new StudentModel() : instance;
    }

    public ObservableList<Student> readAllStudents() throws Exception {
        observableStudents = FXCollections.observableList(userLogic.readAllStudents());
        return observableStudents;
    }

    public void updateStudent(Student student) throws Exception {
        userLogic.updateUser(student);
        observableStudents.set(observableStudents.indexOf(student), student);
    }

    public Student getSelectedStudent() {
        return selectedStudent.get();
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

    public void deleteStudent() {
        //TODO: delete selected student from DB
        //userLogic.
        observableStudents.remove(selectedStudent.get());
    }

    public void setSelectedStudent(Student selected) {
        selectedStudent.set(selected);
    }
}
