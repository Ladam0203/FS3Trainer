package fs3.bll;

import fs3.be.Student;
import fs3.dal.DAOFacade;
import fs3.dal.IDAOFacade;

import java.util.ArrayList;
import java.util.List;

public class StudentLogic {
    private IDAOFacade daoFacade;

    public StudentLogic (){
        daoFacade = new DAOFacade();
    }

    public List<Student> readAllStudents() throws Exception {
        return daoFacade.readAllStudents();
    }
}
