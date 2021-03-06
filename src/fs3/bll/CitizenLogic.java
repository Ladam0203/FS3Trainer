package fs3.bll;

import fs3.be.Citizen;
import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.be.School;
import fs3.dal.DAOFacade;
import fs3.dal.IDAOFacade;

import java.util.List;

public class CitizenLogic {
    private final IDAOFacade daoFacade;

    public CitizenLogic() {
        daoFacade = new DAOFacade();
    }


    public void updateCitizen(Citizen citizen) throws Exception {
        daoFacade.updateCitizen(citizen);
    }

    public void deleteCitizen(Citizen citizen) throws Exception {
        daoFacade.deleteCitizen(citizen);
    }

    //Citizen template only methods
    public List<CitizenTemplate> readAllCitizenTemplates() throws Exception {
        return daoFacade.readAllCitizenTemplates();
    }

    public List<CitizenTemplate> readAllCitizenTemplatesFrom(School school) throws Exception {
        return daoFacade.readAllCitizenTemplatesFrom(school);
    }

    public CitizenTemplate createCitizenTemplate(CitizenTemplate citizenTemplate) throws Exception {
        return (CitizenTemplate) daoFacade.createCitizen(citizenTemplate);
    }

    public void updateCitizenTemplate(CitizenTemplate citizenTemplate) throws Exception {
        daoFacade.updateCitizen(citizenTemplate);
    }

    //Citizen Instances only methods
    public List<CitizenInstance> readAllCitizenInstancesFrom(School school) throws Exception {
        return daoFacade.readAllCitizenInstancesFrom(school);
    }

    public List<CitizenInstance> readAllCitizensInstances() throws Exception {
        return daoFacade.readAllCitizenInstances();
    }

    public CitizenInstance createCitizenInstance(CitizenInstance citizen) throws Exception {
        return (CitizenInstance) daoFacade.createCitizen(citizen);
    }

}
