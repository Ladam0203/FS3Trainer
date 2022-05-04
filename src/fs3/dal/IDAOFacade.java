package fs3.dal;

import fs3.be.Citizen;

import java.util.List;

public interface IDAOFacade {
    List<Citizen> readAllCitizens() throws Exception;
    void updateCitizen(Citizen citizen) throws Exception;
}
