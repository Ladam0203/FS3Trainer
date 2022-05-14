package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.GeneralInformation;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class GeneralInformationDAO {
    private String tableName = "GeneralInformations";
    private String[] columns = {"citizenId", "coping", "motivation", "resources", "roles", "habits", "educationAndJobs", "lifeStory", "healthInformation", "equipmentAids", "homeLayout", "network"};

    private String read = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String readAll = "SELECT * FROM " + tableName;
    private String update = "UPDATE " + tableName + " SET " + columns[1] + " = ?, " + columns[2] + " = ?, " + columns[3] + " = ?, " + columns[4] + " = ?, " + columns[5] + " = ?, " + columns[6] + " = ?, " + columns[7] + " = ?, " + columns[8] + " = ?, " + columns[9] + " = ?, " + columns[10] + " = ?, " + columns[11] + " = ? WHERE " + columns[0] + " = ?";
    private String insert = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public GeneralInformation read(Citizen citizen) throws Exception {
        GeneralInformation generalInformation = null;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(read);
            ps.setInt(1, citizen.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                generalInformation = constructObject(rs);
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }

        return generalInformation;
    }

    public void create(Citizen citizen) throws Exception {
        if (citizen.getGeneralInformation() == null) {
            return;
        }
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(insert);
            ps.setInt(1, citizen.getId());
            ps.setString(2, citizen.getGeneralInformation().getCoping());
            ps.setString(3, citizen.getGeneralInformation().getMotivation());
            ps.setString(4, citizen.getGeneralInformation().getResources());
            ps.setString(5, citizen.getGeneralInformation().getRoles());
            ps.setString(6, citizen.getGeneralInformation().getHabits());
            ps.setString(7, citizen.getGeneralInformation().getEducationAndJobs());
            ps.setString(8, citizen.getGeneralInformation().getLifeStory());
            ps.setString(9, citizen.getGeneralInformation().getHealthInformation());
            ps.setString(10, citizen.getGeneralInformation().getEquipmentAids());
            ps.setString(11, citizen.getGeneralInformation().getHomeLayout());
            ps.setString(12, citizen.getGeneralInformation().getNetwork());

            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public void update(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, citizen.getGeneralInformation().getCoping() == null ? "" : citizen.getGeneralInformation().getCoping());
            ps.setString(2, citizen.getGeneralInformation().getMotivation() == null ? "" : citizen.getGeneralInformation().getMotivation());
            ps.setString(3, citizen.getGeneralInformation().getResources() == null ? "" : citizen.getGeneralInformation().getResources());
            ps.setString(4, citizen.getGeneralInformation().getRoles() == null ? "" : citizen.getGeneralInformation().getRoles());
            ps.setString(5, citizen.getGeneralInformation().getHabits() == null ? "" : citizen.getGeneralInformation().getHabits());
            ps.setString(6, citizen.getGeneralInformation().getEducationAndJobs() == null ? "" : citizen.getGeneralInformation().getEducationAndJobs());
            ps.setString(7, citizen.getGeneralInformation().getLifeStory() == null ? "" : citizen.getGeneralInformation().getLifeStory());
            ps.setString(8, citizen.getGeneralInformation().getHealthInformation() == null ? "" : citizen.getGeneralInformation().getHealthInformation());
            ps.setString(9, citizen.getGeneralInformation().getEquipmentAids() == null ? "" : citizen.getGeneralInformation().getEquipmentAids());
            ps.setString(10, citizen.getGeneralInformation().getHomeLayout() == null ? "" : citizen.getGeneralInformation().getHomeLayout());
            ps.setString(11, citizen.getGeneralInformation().getNetwork() == null ? "" : citizen.getGeneralInformation().getNetwork());

            ps.setInt(12, citizen.getId());

            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public GeneralInformation constructObject(ResultSet rs) throws Exception {
        GeneralInformation generalInformation = new GeneralInformation();
        generalInformation.setCoping(rs.getString("coping"));
        generalInformation.setMotivation(rs.getString("motivation"));
        generalInformation.setResources(rs.getString("resources"));
        generalInformation.setRoles(rs.getString("roles"));
        generalInformation.setHabits(rs.getString("habits"));
        generalInformation.setEducationAndJobs(rs.getString("educationAndJobs"));
        generalInformation.setLifeStory(rs.getString("lifeStory"));
        generalInformation.setHealthInformation(rs.getString("healthInformation"));
        generalInformation.setEquipmentAids(rs.getString("equipmentAids"));
        generalInformation.setHomeLayout(rs.getString("homeLayout"));
        generalInformation.setNetwork(rs.getString("network"));
        return generalInformation;
    }
}
