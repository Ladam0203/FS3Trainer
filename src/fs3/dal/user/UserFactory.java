package fs3.dal.user;

import fs3.be.Student;
import fs3.be.Teacher;
import fs3.be.User;
import fs3.enums.UserRole;
import jdk.jshell.spi.ExecutionControl;

public class UserFactory {
    public static User createUser(String username, String password, int roleId) {
        if (roleId == UserRole.STUDENT.getId()) {
            return new Student(username, password);
        } else if (roleId == UserRole.TEACHER.getId()) {
            return new Teacher(username, password);
        } else if (roleId == UserRole.ADMIN.getId()) {
            throw new UnsupportedOperationException("Admin is not implemented yet");
        }
        throw new IllegalArgumentException("Unknown role: " + roleId);
    }
}
