package fs3.dal;

import fs3.be.Citizen;
import fs3.dal.citizen.CitizenDAO;

import java.util.List;

public class DAOFacade implements IDAOFacade {
    DAO<Citizen> citizenDAO = new CitizenDAO();

    @Override
    public List<Citizen> readAllCitizens() throws Exception {
        return citizenDAO.readAll();
    }
}
