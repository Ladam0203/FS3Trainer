package fs3.gui.model;

import fs3.be.User;
import fs3.bll.LoginLogic;

public class LoginModel {
    private static LoginModel instance;

    private LoginLogic loginLogic;

    private User loggedUser;

    private LoginModel() {
        loginLogic = new LoginLogic();
    }

    public static LoginModel getInstance() {
        return instance == null ? instance = new LoginModel() : instance;
    }

    public User tryLogin(String username, String password) throws Exception {
        User user = loginLogic.tryLogin(username, password);
        if (user == null) {
            return null;
        }
        return loggedUser = user;
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}
