package fs3.bll;

import fs3.be.Citizen;
import fs3.be.CitizenInstance;
import fs3.dal.DAOFacade;
import fs3.dal.IDAOFacade;

import java.util.List;

public class CitizenLogic {
   private final IDAOFacade DAOFacade;

   public CitizenLogic(){ DAOFacade = new DAOFacade();}

    public List<CitizenInstance> readAllCitizensInstances() throws Exception {return DAOFacade.readAllCitizenInstances();}

    //update citizen
    public void updateCitizen(Citizen citizen) throws Exception {DAOFacade.updateCitizen(citizen);}
}
