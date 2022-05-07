package fs3.dal;

import fs3.be.Citizen;
import fs3.be.User;

import java.util.List;

public interface IDAOFacade {
    List<Citizen> readAllCitizens() throws Exception;
    void updateCitizen(Citizen citizen) throws Exception;
    User readUser(String username, String password) throws Exception;

}
