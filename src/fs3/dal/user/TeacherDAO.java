package fs3.dal.user;

import fs3.be.Student;
import fs3.be.Teacher;
import fs3.be.User;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDAO {
    private String tableName = "Teachers";
    private String[] columns = {"userId", "name"};

    private String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String insert = "INSERT INTO " + tableName + " (" + columns[0] + ", " + columns[1] + ") VALUES (?, ?)";
    private String update = "UPDATE " + tableName + " SET " + columns[1] + " = ? WHERE " + columns[0] + " = ?";

    public void set(User user) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Teacher teacher = (Teacher) user;
                teacher.setName(rs.getString("name"));
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public void create(Teacher teacher) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1, teacher.getId());
            ps.setString(2, teacher.getName());
            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public void update(Teacher user) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, user.getName());
            ps.setInt(2, user.getId());
            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }
}
