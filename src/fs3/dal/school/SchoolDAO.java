package fs3.dal.school;

import fs3.be.School;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAO {
    private final String tableName = "Schools";
    private final String[] columns = {"id", "name"};

    private final String selectAll = "SELECT * FROM " + tableName;
    private final String selectById = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private final String insert = "INSERT INTO " + tableName + " (" + columns[1] + ") VALUES (?)";
    private final String update = "UPDATE " + tableName + " SET " + columns[1] + " = ? WHERE " + columns[0] + " = ?";
    private final String delete = "DELETE FROM " + tableName + " WHERE " + columns[0] + " = ?";

    public List<School> readAll() throws Exception {
        List<School> schools = new ArrayList<>();
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(selectAll);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                schools.add(construct(rs));
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        return schools;
    }

    public School read(int id) throws Exception {
        School school = null;
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(selectById);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                school = construct(rs);
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        return school;
    }

    public School create(School school) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, school.getName());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                school.setId(rs.getInt(1));
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        return school;
    }

    public void update(School school) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, school.getName());
            ps.setInt(2, school.getId());
            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public void delete(School school) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, school.getId());
            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    private School construct(ResultSet rs) throws Exception {
        School school = new School();
        school.setId(rs.getInt("id"));
        school.setName(rs.getString("name"));
        return school;
    }
}
