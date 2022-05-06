package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.HealthConditionData;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.enums.ExpectedLevel;
import fs3.enums.HealthCondition;
import fs3.enums.HealthConditionState;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class HealthConditionDAO {
    private final String tableName = "HealthConditions";
    private final String[] columns = {"citizenId", "healthCondition", "healthConditionState", "professionalNote", "currentAssesment", "expectedLevel", "followUpDate", "observationNote"};

    String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    String selectHealthCondition = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?" + " AND " + columns[1] + " = ?";
    String update = "UPDATE " + tableName + " SET " + columns[2] + " = ?, " + columns[3] + " = ?, " + columns[4] + " = ?, " + columns[5] + " = ?, " + columns[6] + " = ?, " + columns[7] + " = ? WHERE " + columns[0] + " = ? AND " + columns[1] + " = ?";
    String insertHealthConditonData = "INSERT INTO " + tableName + " (" + String.join(", ", columns) + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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
            PreparedStatement preparedStatementUpdate = con.prepareStatement(update);
            PreparedStatement preparedStatementInsert = con.prepareStatement(insertHealthConditonData);
            for (Map.Entry<HealthCondition, HealthConditionData> entry : healthConditions.entrySet()) {
                PreparedStatement preparedStatementSelectHealthCondition = con.prepareStatement(selectHealthCondition);
                preparedStatementSelectHealthCondition.setInt(1, citizen.getId());
                preparedStatementSelectHealthCondition.setString(2, entry.getKey().toString());
                ResultSet rs = preparedStatementSelectHealthCondition.executeQuery();
                HealthConditionData healthConditionData = entry.getValue();
                if (rs.next()) {
                    preparedStatementUpdate.setString(1, healthConditionData.getHealthConditionState().toString());
                    preparedStatementUpdate.setString(2, healthConditionData.getProfessionalNote());
                    preparedStatementUpdate.setString(3, healthConditionData.getCurrentAssessment());
                    preparedStatementUpdate.setString(4, healthConditionData.getExpectedLevel().toString());
                    preparedStatementUpdate.setDate(5, java.sql.Date.valueOf(healthConditionData.getFollowUpDate()));
                    preparedStatementUpdate.setString(6, healthConditionData.getObservationNote());

                    preparedStatementUpdate.setInt(7, citizen.getId());
                    preparedStatementUpdate.setString(8, entry.getKey().toString());

                    preparedStatementUpdate.addBatch();
                }
                else { //responsible for creating new rows
                    preparedStatementInsert.setInt(1, citizen.getId());
                    preparedStatementInsert.setString(2, healthConditionData.toString());
                    preparedStatementInsert.setString(3, healthConditionData.getHealthConditionState().toString());
                    preparedStatementInsert.setString(4, healthConditionData.getProfessionalNote());
                    preparedStatementInsert.setString(5, healthConditionData.getCurrentAssessment());
                    preparedStatementInsert.setString(6, healthConditionData.getExpectedLevel().toString());
                    preparedStatementInsert.setDate(7, java.sql.Date.valueOf(healthConditionData.getFollowUpDate()));
                    preparedStatementInsert.setString(8, healthConditionData.getObservationNote());
                }
            }
            preparedStatementUpdate.executeBatch();
            preparedStatementInsert.executeBatch();
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);
    }

    public HashMap<HealthCondition, HealthConditionData> constructObject(ResultSet rs) throws Exception {
        HashMap<HealthCondition, HealthConditionData> healthConditions = new HashMap<>();
        while (rs.next()) {
            healthConditions.put(HealthCondition.fromString(rs.getString(columns[1])), constructHealthConditionData(rs));
        }
        return healthConditions;
    }

    private HealthConditionData constructHealthConditionData(ResultSet rs) throws Exception {
        HealthConditionData healthConditionData = new HealthConditionData();
        healthConditionData.setHealthConditionState(HealthConditionState.fromString(rs.getString(columns[2])));
        healthConditionData.setProfessionalNote(rs.getString(columns[3]));
        healthConditionData.setCurrentAssessment(rs.getString(columns[4]));
        healthConditionData.setExpectedLevel(ExpectedLevel.fromString(rs.getString(columns[5])));
        healthConditionData.setFollowUpDate(rs.getDate(columns[6]).toLocalDate());
        healthConditionData.setObservationNote(rs.getString(columns[7]));
        return healthConditionData;
    }
}

