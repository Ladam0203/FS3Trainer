package fs3.dal;

import fs3.be.*;

import java.util.List;

public interface IDAOFacade {
    void updateCitizen(Citizen citizen) throws Exception;
    User readUser(String username, String password) throws Exception;
    List<CitizenInstance> readAllCitizenInstances() throws Exception;
    List<CitizenTemplate> readAllCitizenTemplates() throws Exception;
    Citizen createCitizen(Citizen citizen) throws Exception;
    void deleteCitizen(Citizen citizen) throws Exception;
    List<Student> readAllStudents() throws Exception;
}
