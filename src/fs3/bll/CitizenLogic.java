package fs3.bll;

import fs3.be.Citizen;
import fs3.dal.CitizenDAO;
import fs3.dal.DAO;

import java.util.List;
import java.util.Optional;

public class CitizenLogic {
   private DAO<Citizen> citizenDAO;

   public CitizenLogic(){ citizenDAO = new CitizenDAO();}

    public Optional<Citizen> getCitizen(int id){ return citizenDAO.read(id);}

    public List<Citizen> getAllCitizens(){return citizenDAO.readAll();}
}
