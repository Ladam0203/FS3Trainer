package fs3.dal.user;

import fs3.be.*;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.enums.UserRole;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserDAO {
    private final SubUserDAO<Student> studentDAO = new StudentDAO();
    private final SubUserDAO<Teacher> teacherDAO = new TeacherDAO();
    private final SubUserDAO<Admin> adminDAO = new AdminDAO();

    private String tableName = "Users";
    private String[] columns = {"id", "username", "password", "roleId"};

    private String select = "SELECT * FROM " + tableName + " WHERE " + columns[1] + " = ? AND " + columns[2] + " = ?";
    private String selectStudents = "SELECT * FROM " + tableName + " WHERE " + columns[3] + " = " + UserRole.STUDENT.getId();
    private String selectTeachers = "SELECT * FROM " + tableName + " WHERE " + columns[3] + " = " + UserRole.TEACHER.getId();
    private String selectAdmins = "SELECT * FROM " + tableName + " WHERE " + columns[3] + " = " + UserRole.ADMIN.getId();
    private String insert = "INSERT INTO " + tableName + " (" + columns[1] + ", " + columns[2] + ", " + columns[3] + ") VALUES (?, ?, ?)";
    private String update = "UPDATE " + tableName + " SET " + columns[1] + " = ?, " + columns[2] + " = ? WHERE " + columns[0] + " = ?";
    private String delete = "DELETE FROM " + tableName + " WHERE " + columns[0] + " = ?";

    public List<Student> readAllStudentsFrom(School school) throws Exception {
        List<Student> students = new ArrayList<>();
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(selectStudents);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = (Student) constructUser(rs);
                studentDAO.set(student);
                if (school.equals(student.getSchool())) {
                    students.add(student);
                }
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        return students;
    }

    public List<Student> readAllStudents() throws Exception {
        return readAllSubUsers(studentDAO, selectStudents);
    }

    public List<Teacher> readAllTeachers() throws Exception {
        return readAllSubUsers(teacherDAO, selectTeachers);
    }

    public List<Admin> readAllAdmins() throws Exception {
        return readAllSubUsers(adminDAO, selectAdmins);
    }

    private <T extends User> List<T> readAllSubUsers(SubUserDAO<T> subUserDAO, String query) throws Exception {
        List<T> subUsers = new ArrayList<>();
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                T subUser = (T) constructUser(rs);
                subUserDAO.set(subUser);
                subUsers.add(subUser);
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        return subUsers;
    }

    public User read(String username, String password) throws Exception {
        User user = null;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = constructUser(rs);
            }
            else {
                return null;
            }

            if (Student.class.equals(user.getClass())) {
                studentDAO.set(user);
            }
            else if (Teacher.class.equals(user.getClass())) {
                teacherDAO.set(user);
            } else  if (Admin.class.equals(user.getClass())) {
                adminDAO.set(user);
            }

        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }

        return user;
    }

    public void update(User user) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());

            ps.setInt(3, user.getId());
            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        if (Student.class.equals(user.getClass())) {
            studentDAO.update((Student) user);
        }
        else if (Teacher.class.equals(user.getClass())) {
            teacherDAO.update((Teacher) user);
        } else if (Admin.class.equals(user.getClass())) {
            adminDAO.update((Admin) user);
        }
        //TODO: implement admin
    }

    public User create(User user) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            if (Student.class.equals(user.getClass())) {
                ps.setInt(3, UserRole.STUDENT.getId());
            } else if (Teacher.class.equals(user.getClass())) {
                ps.setInt(3, UserRole.TEACHER.getId());
            } else if (Admin.class.equals(user.getClass())) {
                ps.setInt(3, UserRole.ADMIN.getId());
            }

            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt(1));
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        if (Student.class.equals(user.getClass())) {
            studentDAO.create((Student) user);
        }
        else if (Teacher.class.equals(user.getClass())) {
            teacherDAO.create((Teacher) user);
        } else if (Admin.class.equals(user.getClass())) {
            adminDAO.create((Admin) user);
        }
        return user;
    }

    public void delete(User user) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, user.getId());
            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    private User constructUser(ResultSet rs) throws Exception {
        User user = UserFactory.createUser(rs.getString(columns[1]), rs.getString(columns[2]), rs.getInt(columns[3]));
        user.setId(rs.getInt(columns[0]));
        return user;
    }
}
