package fs3.bll;

import fs3.be.Citizen;
import fs3.be.CitizenTemplate;
import fs3.dal.DAOFacade;
import fs3.dal.IDAOFacade;

import java.util.List;

public class CitizenTemplateLogic {
    private final IDAOFacade daoFacade;

    public CitizenTemplateLogic(){this.daoFacade = new DAOFacade();}

     public List<CitizenTemplate> readAllCitizenTemplates() throws Exception {return daoFacade.readAllCitizenTemplates();}

    public CitizenTemplate createCitizenTemplate(CitizenTemplate citizenTemplate) throws Exception { return (CitizenTemplate) daoFacade.createCitizen(citizenTemplate);}

    public void updateCitizenTemplate(CitizenTemplate citizenTemplate) throws Exception{ daoFacade.updateCitizen(citizenTemplate);}
}
