package fs3.dal.user;

import fs3.be.School;
import fs3.be.Student;
import fs3.be.Teacher;
import fs3.be.User;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.dal.school.SchoolDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TeacherDAO implements SubUserDAO<Teacher> {
    private String tableName = "Teachers";
    private String[] columns = {"userId", "name", "schoolId"};

    private String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String insert = "INSERT INTO " + tableName + " (" + columns[0] + ", " + columns[1] + ", " + columns[2] +") VALUES (?, ?, ?)";
    private String update = "UPDATE " + tableName + " SET " + columns[1] + " = ?, " + columns[2] + " = ?  WHERE " + columns[0] + " = ?";

    private SchoolDAO schoolDAO = new SchoolDAO();

    public void set(User user) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Teacher teacher = (Teacher) user;
                teacher.setName(rs.getString("name"));
                teacher.setSchool(schoolDAO.read(rs.getInt("schoolId")));
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
            if (teacher.getSchool() != null) {
                ps.setInt(3, teacher.getSchool().getId());
            } else {
                ps.setNull(3, java.sql.Types.INTEGER);
            }
            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public void update(Teacher teacher) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, teacher.getName());
            if (teacher.getSchool() != null) {
                ps.setInt(2, teacher.getSchool().getId());
            } else {
                ps.setNull(2, java.sql.Types.INTEGER);
            }
            ps.setInt(3, teacher.getId());
            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }
}
