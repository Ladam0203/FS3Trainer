package fs3.gui.model;

import fs3.be.Student;
import fs3.bll.StudentLogic;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentModel {
    private static StudentModel instance;
    private StudentLogic studentLogic;
    private ObservableList<Student> observableStudents;

    private StudentModel (){
        studentLogic = new StudentLogic();
    }

    public static StudentModel getInstance(){
        return instance == null ? instance = new StudentModel() : instance;
    }

    public ObservableList<Student> readAllStudents() throws Exception {
        observableStudents = FXCollections.observableList(studentLogic.readAllStudents());
        return observableStudents;
    }

    public void updateStudent(Student student) throws Exception {
        studentLogic.updateStudent(student);
    }

    public ObservableList<Student> getObservableStudents(){
        return observableStudents;
    }
}
