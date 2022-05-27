package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MedicineListDAO {
    private String tableName = "medicineLists";
    private String[] columns = {"id", "medicineList"};

    private String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String insert = "INSERT INTO " + tableName + " " + "VALUES (?, ?)";
    private String update = "UPDATE " + tableName + " SET " + columns[1] + " = ? WHERE " + columns[0] + " = ?";

    public String read(Citizen citizen) throws Exception {
        String medicineList = null;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, citizen.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                medicineList = rs.getString(columns[1]);
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }

        return medicineList;
    }

    public void create(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1, citizen.getId());
            ps.setString(2, citizen.getMedicineList());

            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public void update(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, citizen.getMedicineList());
            ps.setInt(2, citizen.getId());

            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }
}
