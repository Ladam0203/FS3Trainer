package fs3.dal.citizen;

import fs3.be.GeneralInformation;
import fs3.be.PersonalInformation;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.dal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class GeneralInformationDAO {
    private String tableName = "GeneralInformations";
    private String[] columns = {"citizenId", "coping", "motivation", "resources", "roles", "habits", "education", "jobs", "lifeStory", "healthInformation", "equipmentAids", "homeLayout", "network"};

    private String read = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String readAll = "SELECT * FROM " + tableName;

    public GeneralInformation read(int citizenId) throws Exception {
        GeneralInformation generalInformation = null;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(read);
            ps.setInt(1, citizenId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                generalInformation = constructObject(rs);
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return generalInformation;
    }

    public GeneralInformation constructObject(ResultSet rs) throws Exception {
        GeneralInformation generalInformation = new GeneralInformation();
        generalInformation.setCoping(rs.getString("coping"));
        generalInformation.setMotivation(rs.getString("motivation"));
        generalInformation.setResources(rs.getString("resources"));
        generalInformation.setRoles(rs.getString("roles"));
        generalInformation.setHabits(rs.getString("habits"));
        generalInformation.setEducation(rs.getString("education"));
        generalInformation.setJobs(rs.getString("jobs"));
        generalInformation.setLifeStory(rs.getString("lifeStory"));
        generalInformation.setHealthInformation(rs.getString("healthInformation"));
        generalInformation.setEquipmnentAids(rs.getString("equipmentAids"));
        generalInformation.setHomeLayout(rs.getString("homeLayout"));
        generalInformation.setNetwork(rs.getString("network"));
        return generalInformation;
    }
}
