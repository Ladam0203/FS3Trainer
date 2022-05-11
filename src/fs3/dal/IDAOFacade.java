package fs3.dal;

import fs3.be.Citizen;
import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.be.User;

import java.util.List;

public interface IDAOFacade {
    void updateCitizen(Citizen citizen) throws Exception;
    User readUser(String username, String password) throws Exception;
    List<CitizenInstance> readAllCitizenInstances() throws Exception;
    List<CitizenTemplate> readAllCitizenTemplates() throws Exception;
}
