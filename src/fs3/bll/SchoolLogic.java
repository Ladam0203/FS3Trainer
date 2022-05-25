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

    public void updateSchool(School school) throws Exception {
        daoFacade.updateSchool(school);
    }

    public void deleteSchool(School school) throws Exception{
        daoFacade.deleteSchool(school);
    }

    public School createSchool(School school) throws Exception {
        return  daoFacade.createSchool(school);
    }
}
