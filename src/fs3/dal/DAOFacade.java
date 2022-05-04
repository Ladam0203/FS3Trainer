package fs3.dal;

import fs3.be.Citizen;
import fs3.dal.citizen.CitizenDAO;

import java.util.List;

public class DAOFacade implements IDAOFacade {
    CitizenDAO citizenDAO = new CitizenDAO();

    @Override
    public List<Citizen> readAllCitizens() throws Exception {
        return citizenDAO.readAll();
    }

    @Override
    public void updateCitizen(Citizen citizen) throws Exception {
        citizenDAO.update(citizen);
    }
}
