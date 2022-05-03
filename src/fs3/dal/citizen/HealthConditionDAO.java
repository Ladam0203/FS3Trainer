package fs3.dal.citizen;

import fs3.be.HealthConditionData;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.dal.DAO;
import fs3.enums.HealthCondition;
import fs3.util.ExpectedLevelParser;
import fs3.util.HealthConditionParser;
import fs3.util.HealthConditionStateParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class HealthConditionDAO {
    private String tableName = "HealthConditions";
    private String[] columns = {"citizenId", "healthCondition", "healthConditionState", "professionalNote", "currentAssesment", "expectedLevel", "followUpDate", "observationNote"};
    String select = "SELECT * FROM " + tableName + " WHERE citizenId = ?";

    public HashMap<HealthCondition, HealthConditionData> read(int citizenId) throws Exception {
        HashMap<HealthCondition, HealthConditionData> healthConditionData;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, citizenId);
            ResultSet rs = ps.executeQuery();
            healthConditionData = constructObject(rs);
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return healthConditionData;
    }

    public HashMap<HealthCondition, HealthConditionData> constructObject(ResultSet rs) throws Exception {
        HashMap<HealthCondition, HealthConditionData> healthConditions = new HashMap<>();
        while (rs.next()) {
            healthConditions.put(HealthConditionParser.StringToHealthCondition(rs.getString(columns[1])), constructHealthConditionData(rs));
        }
        return healthConditions;
    }

    private HealthConditionData constructHealthConditionData(ResultSet rs) throws Exception {
        HealthConditionData healthConditionData = new HealthConditionData();
        healthConditionData.setHealthConditionState(HealthConditionStateParser.StringToHealthCondition(rs.getString(columns[2])));
        healthConditionData.setProfessionalNote(rs.getString(columns[3]));
        healthConditionData.setCurrentAssessment(rs.getString(columns[4]));
        healthConditionData.setExpectedLevel(ExpectedLevelParser.StringToExpectedLevel(rs.getString(columns[5])));
        healthConditionData.setFollowUpDate(rs.getDate(columns[6]).toLocalDate());
        healthConditionData.setObservationNote(rs.getString(columns[7]));
        return healthConditionData;
    }
}

