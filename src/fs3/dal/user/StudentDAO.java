package fs3.dal.user;

import fs3.be.CitizenInstance;
import fs3.be.Student;
import fs3.be.User;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.dal.citizen.CitizenDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    private CitizenDAO citizenDAO = new CitizenDAO();

    private String tableName = "Students";
    private String juntionTableName = "StudentsCitizenInstances";

    private String[] columns = {"userId", "name"};
    private String[] junctionColumns = {"studentId", "citizenInstanceId"};

    private String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String insert = "INSERT INTO " + tableName + " VALUES (?, ?)";
    private String update = "UPDATE " + tableName + " SET " + columns[1] + " = ? WHERE " + columns[0] + " = ?";

    private String selectJunction = "SELECT * FROM " + juntionTableName + " WHERE " + junctionColumns[0] + " = ?";
    private String deleteLinks = "DELETE FROM " + juntionTableName + " WHERE " + junctionColumns[0] + " = ?";
    private String insertLinks = "INSERT INTO " + juntionTableName + " (" + junctionColumns[0] + ", " + junctionColumns[1] + ") VALUES (?, ?)";

    public void set(User user) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, user.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Student student = (Student) user;
                student.setName(rs.getString("name"));
                PreparedStatement psJunction = con.prepareStatement(selectJunction);
                psJunction.setInt(1, student.getId());
                ResultSet rsJunction = psJunction.executeQuery();
                while (rsJunction.next()) {
                    student.assignCitizen((CitizenInstance) citizenDAO.read(rsJunction.getInt("citizenInstanceId")));
                }
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public void update(Student student) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, student.getName());
            ps.setInt(2, student.getId());
            ps.executeUpdate();

            con.setAutoCommit(false);

            PreparedStatement psDelete = con.prepareStatement(deleteLinks);
            psDelete.setInt(1, student.getId());
            psDelete.executeUpdate();

            PreparedStatement psInsert = con.prepareStatement(insertLinks);
            for (CitizenInstance ci : student.getAssignedCitizens()) {
                psInsert.setInt(1, student.getId());
                psInsert.setInt(2, ci.getId());
                psInsert.addBatch();
            }
            psInsert.executeBatch();

            con.commit();
            con.setAutoCommit(true);
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public void create(Student student) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1, student.getId());
            ps.setString(2, student.getName());
            ps.executeUpdate();

            con.commit();

            PreparedStatement psInsert = con.prepareStatement(insertLinks);
            for (CitizenInstance ci : student.getAssignedCitizens()) {
                psInsert.setInt(1, student.getId());
                psInsert.setInt(2, ci.getId());
                psInsert.addBatch();
            }
            psInsert.executeBatch();

            con.commit();
            con.setAutoCommit(true);
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

}
