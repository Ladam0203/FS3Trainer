package fs3.gui.model;

import fs3.be.User;
import fs3.bll.UserLogic;

public class LoginModel {
    private static LoginModel instance;

    private final UserLogic userLogic;

    private User loggedUser;

    private LoginModel() {
        userLogic = new UserLogic();
    }

    public static LoginModel getInstance() {
        return instance == null ? instance = new LoginModel() : instance;
    }

    public User tryLogin(String username, String password) throws Exception {
        User user = userLogic.tryLogin(username, password);
        if (user == null) {
            return null;
        }
        return loggedUser = user;
    }

    public User getLoggedUser() {
        return loggedUser;
    }
}
