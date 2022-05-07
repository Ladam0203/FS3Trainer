package fs3.bll;

import fs3.be.User;
import fs3.dal.DAOFacade;
import fs3.util.PopUp;

public class LoginLogic {
    private DAOFacade daoFacade;

    public LoginLogic(){
        daoFacade = new DAOFacade();
    }

    public User tryLogin(String username, String password) throws Exception {
            return daoFacade.readUser(username, password);
    }
}
