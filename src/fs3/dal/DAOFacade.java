package fs3.dal;

import fs3.be.Citizen;
import fs3.be.User;
import fs3.dal.citizen.CitizenDAO;
import fs3.dal.user.UserDAO;

import java.util.List;

public class DAOFacade implements IDAOFacade {
    CitizenDAO citizenDAO = new CitizenDAO();
    UserDAO userDAO = new UserDAO();

    @Override
    public List<Citizen> readAllCitizens() throws Exception {
        return citizenDAO.readAll();
    }

    @Override
    public void updateCitizen(Citizen citizen) throws Exception {
        citizenDAO.update(citizen);
    }

    @Override
    public User readUser(String username, String password) throws Exception {
        return userDAO.readUser(username, password);
    }


}
