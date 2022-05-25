package fs3.dal.school;

import fs3.be.Admin;
import fs3.be.School;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SchoolDAO {
    private String tableName = "Schools";
    private String[] columns = {"id", "name"};

    private String selectAll = "SELECT * FROM " + tableName;
    private String selectById = "SELECT * FROM " + tableName + " WHERE id = ?";
    private String insert = "INSERT INTO " + tableName + " (name) VALUES (?)";
    private String update = "UPDATE " + tableName + " SET name = ? WHERE id = ?";
    private String delete = "DELETE FROM " + tableName + " WHERE id = ?";

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

    private School construct(ResultSet rs) throws Exception {
        School school = new School();
        school.setId(rs.getInt("id"));
        school.setName(rs.getString("name"));
        return school;
    }
}
