package fs3.bll;

import fs3.be.Citizen;
import fs3.dal.DAO;
import fs3.dal.citizen.CitizenDAO;

import java.util.List;
import java.util.Optional;

public class CitizenLogic {
   private DAO<Citizen> citizenDAO;

   public CitizenLogic(){ citizenDAO = new CitizenDAO();}

    public Optional<Citizen> getCitizen(int id){ return citizenDAO.read(id);}

    public List<Citizen> getAllCitizens() throws Exception {return citizenDAO.readAll();}

    public Optional<Citizen> createCitizen(Citizen citizen){ return citizenDAO.create(citizen);}

    public void updateCitizen (Citizen citizen) throws Exception{ citizenDAO.update(citizen);}

    public void deleteCitizen(Citizen citizen) throws Exception{citizenDAO.delete(citizen);}
}
