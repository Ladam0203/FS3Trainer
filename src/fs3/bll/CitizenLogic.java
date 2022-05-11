package fs3.bll;

import fs3.be.Citizen;
import fs3.be.CitizenInstance;
import fs3.dal.DAOFacade;
import fs3.dal.IDAOFacade;

import java.util.List;

public class CitizenLogic {
   private final IDAOFacade daoFacade;

   public CitizenLogic(){ daoFacade = new DAOFacade();}

    public List<CitizenInstance> readAllCitizensInstances() throws Exception {return daoFacade.readAllCitizenInstances();}

    //update citizen
    public void updateCitizen(Citizen citizen) throws Exception {
        daoFacade.updateCitizen(citizen);}
}
