package fs3.dal.user;

import fs3.be.Student;
import fs3.be.User;

public class UserFactory {
    public static User createUser(String username, String password, int roleId) {
        switch (roleId) {
            case 1:
                throw new UnsupportedOperationException("Not supported yet.");
            case 2:
                throw new UnsupportedOperationException("Not supported yet.");
            case 3:
                return new Student(username, password);
        }
        throw new IllegalArgumentException("Unknown role: " + roleId);
    }
}
