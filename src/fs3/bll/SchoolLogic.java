package fs3.bll;

import fs3.be.School;
import fs3.dal.DAOFacade;

import java.util.List;

public class SchoolLogic {
    private DAOFacade daoFacade;

    public SchoolLogic(){
        daoFacade = new DAOFacade();
    }

    public List<School> readAllSchools() throws Exception {
        return daoFacade.readAllSchools();
    }
}
