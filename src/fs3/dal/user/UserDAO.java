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
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final StudentDAO studentDAO = new StudentDAO();
    private final TeacherDAO teacherDAO = new TeacherDAO();

    private String tableName = "Users";
    private String[] columns = {"id", "username", "password", "roleId"};
    private String select = "SELECT * FROM " + tableName + " WHERE " + columns[1] + " = ? AND " + columns[2] + " = ?";
    private String selectStudents = "SELECT * FROM " + tableName + " WHERE " + columns[3] + " = ?";

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

    public void update(User user) throws Exception {
        if (Student.class.equals(user.getClass())) {
            studentDAO.update((Student) user);
        }
        else if (Teacher.class.equals(user.getClass())) {
            throw new ExecutionControl.NotImplementedException("Teacher update not implemented");
        }
        //TODO: implement admin
    }

    public User readUser(String username, String password) throws Exception {
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

    private User constructUser(ResultSet rs) throws Exception {
        User user = UserFactory.createUser(rs.getString(columns[1]), rs.getString(columns[2]), rs.getInt(columns[3]));
        user.setId(rs.getInt(columns[0]));
        return user;
    }
}
