package fs3.dal.user;

import fs3.be.Student;
import fs3.be.Teacher;
import fs3.be.User;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import jdk.jshell.spi.ExecutionControl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final StudentDAO studentDAO = new StudentDAO();
    private final TeacherDAO teacherDAO = new TeacherDAO();

    private String tableName = "Users";
    private String[] columns = {"id", "username", "password", "roleId"};

    private String select = "SELECT * FROM " + tableName + " WHERE " + columns[1] + " = ? AND " + columns[2] + " = ?";
    private String selectStudents = "SELECT * FROM " + tableName + " WHERE " + columns[3] + " = ?";
    private String insert = "INSERT INTO " + tableName + " (" + columns[1] + ", " + columns[2] + ", " + columns[3] + ") VALUES (?, ?, ?)";
    private String update = "UPDATE " + tableName + " SET " + columns[1] + " = ?, " + columns[2] + " = ? WHERE " + columns[0] + " = ?";
    private String delete = "DELETE FROM " + tableName + " WHERE " + columns[0] + " = ?";

    public List<Student> readAllStudents() throws Exception {
        List<Student> students = new ArrayList<>();
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(selectStudents);
            ps.setInt(1, 3);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = (Student) constructUser(rs);
                studentDAO.set(student);
                students.add(student);
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        return students;
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
            throw new ExecutionControl.NotImplementedException("Teacher update not implemented");
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
                ps.setInt(3, 3);
            } else if (Teacher.class.equals(user.getClass())) {
                ps.setInt(3, 2);
            }
            //TODO: implement admin
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                user.setId(rs.getInt("id"));
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        if (Student.class.equals(user.getClass())) {
            studentDAO.create((Student) user);
        }
        else if (Teacher.class.equals(user.getClass())) {
            throw new ExecutionControl.NotImplementedException("Teacher create not implemented");
        }
        //TODO: create admin
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
