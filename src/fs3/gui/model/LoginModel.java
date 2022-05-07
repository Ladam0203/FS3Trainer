package fs3.gui.model;

import fs3.be.User;
import fs3.bll.LoginLogic;

public class LoginModel {
    private LoginLogic loginLogic;

    public LoginModel() {
        loginLogic = new LoginLogic();
    }

    public User tryLogin(String username, String password) throws Exception {
            return loginLogic.tryLogin(username,password);
    }
}
