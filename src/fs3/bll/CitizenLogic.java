package fs3.bll;

import fs3.be.Citizen;
import fs3.dal.DAOFacade;
import fs3.dal.IDAOFacade;

import java.util.List;

public class CitizenLogic {
   private final IDAOFacade DAOFacade;

   public CitizenLogic(){ DAOFacade = new DAOFacade();}

    public List<Citizen> readAllCitizens() throws Exception {return DAOFacade.readAllCitizens();}
}
