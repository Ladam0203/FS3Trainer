package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.HealthConditionData;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.enums.HealthCondition;
import fs3.util.ExpectedLevelParser;
import fs3.util.HealthConditionParser;
import fs3.util.HealthConditionStateParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class HealthConditionDAO {
    private final String tableName = "HealthConditions";
    private final String[] columns = {"citizenId", "healthCondition", "healthConditionState", "professionalNote", "currentAssesment", "expectedLevel", "followUpDate", "observationNote"};

    String select = "SELECT * FROM " + tableName + " WHERE citizenId = ?";
    String update = "UPDATE " + tableName + " SET " + columns[2] + " = ?, " + columns[3] + " = ?, " + columns[4] + " = ?, " + columns[5] + " = ?, " + columns[6] + " = ?, " + columns[7] + " = ? WHERE " + columns[0] + " = ? AND " + columns[1] + " = ?";

    public HashMap<HealthCondition, HealthConditionData> read(Citizen citizen) throws Exception {
        HashMap<HealthCondition, HealthConditionData> healthConditionData;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, citizen.getId());
            ResultSet rs = ps.executeQuery();
            healthConditionData = constructObject(rs);
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return healthConditionData;
    }

    public void update(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            HashMap<HealthCondition, HealthConditionData> healthConditions = citizen.getHealthConditions();
            PreparedStatement preparedStatement = con.prepareStatement(update);
            for (Map.Entry<HealthCondition, HealthConditionData> entry : healthConditions.entrySet()) {
                HealthConditionData healthConditionData = entry.getValue();
                preparedStatement.setString(1, healthConditionData.getHealthConditionState().toString());
                preparedStatement.setString(2, healthConditionData.getProfessionalNote());
                preparedStatement.setString(3, healthConditionData.getCurrentAssessment());
                preparedStatement.setString(4, healthConditionData.getExpectedLevel().toString());
                preparedStatement.setDate(5, java.sql.Date.valueOf(healthConditionData.getFollowUpDate()));
                preparedStatement.setString(6, healthConditionData.getObservationNote());

                preparedStatement.setInt(7, citizen.getId());
                preparedStatement.setString(8, entry.getKey().toString());

                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);
    }

    public HashMap<HealthCondition, HealthConditionData> constructObject(ResultSet rs) throws Exception {
        HashMap<HealthCondition, HealthConditionData> healthConditions = new HashMap<>();
        while (rs.next()) {
            healthConditions.put(HealthConditionParser.parseString(rs.getString(columns[1])), constructHealthConditionData(rs));
        }
        return healthConditions;
    }

    private HealthConditionData constructHealthConditionData(ResultSet rs) throws Exception {
        HealthConditionData healthConditionData = new HealthConditionData();
        healthConditionData.setHealthConditionState(HealthConditionStateParser.parseString(rs.getString(columns[2])));
        healthConditionData.setProfessionalNote(rs.getString(columns[3]));
        healthConditionData.setCurrentAssessment(rs.getString(columns[4]));
        healthConditionData.setExpectedLevel(ExpectedLevelParser.parseString(rs.getString(columns[5])));
        healthConditionData.setFollowUpDate(rs.getDate(columns[6]).toLocalDate());
        healthConditionData.setObservationNote(rs.getString(columns[7]));
        return healthConditionData;
    }
}

