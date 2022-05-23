package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.HealthConditionData;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.enums.ExpectedLevel;
import fs3.enums.HealthCondition;
import fs3.enums.HealthConditionState;

import java.sql.*;
import java.util.*;

public class HealthConditionDAO {
    private final String tableName = "HealthConditions";
    private final String[] columns = {"citizenId", "healthCondition", "healthConditionState", "professionalNote", "currentAssessment", "expectedLevel", "followUpDate", "observationNote"};

    String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    String selectHealthCondition = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?" + " AND " + columns[1] + " = ?";
    String update = "UPDATE " + tableName + " SET " + columns[2] + " = ?, " + columns[3] + " = ?, " + columns[4] + " = ?, " + columns[5] + " = ?, " + columns[6] + " = ?, " + columns[7] + " = ? WHERE " + columns[0] + " = ? AND " + columns[1] + " = ?";
    String insertHealthConditonData = "INSERT INTO " + tableName + "  VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    public HashMap<HealthCondition, HealthConditionData> read(Citizen citizen) throws Exception {
        HashMap<HealthCondition, HealthConditionData> healthConditionData;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, citizen.getId());
            ResultSet rs = ps.executeQuery();
            healthConditionData = constructObject(rs);
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }

        return healthConditionData;
    }

    public void create(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(insertHealthConditonData);
            for (Map.Entry<HealthCondition, HealthConditionData> entry : citizen.getHealthConditions().entrySet()) {
                HealthConditionData healthConditionData = entry.getValue();
                ps.setInt(1, citizen.getId());
                ps.setString(2, entry.getKey().toString());
                ps.setString(3, healthConditionData.getHealthConditionState().toString());
                if (healthConditionData.getHealthConditionState() == HealthConditionState.INACTIVE) {
                    ps.setNull(4, java.sql.Types.NVARCHAR);
                    ps.setNull(5, java.sql.Types.NVARCHAR);
                    ps.setNull(6, java.sql.Types.NVARCHAR);
                    ps.setNull(7, Types.DATE);
                    ps.setNull(8, Types.NVARCHAR);
                } else { //we can trust the GUI that f the state is not inactive, all additional data is filled
                    ps.setString(4, healthConditionData.getProfessionalNote());
                    ps.setString(5, healthConditionData.getCurrentAssessment());
                    ps.setString(6, healthConditionData.getExpectedLevel().toString());
                    ps.setDate(7, java.sql.Date.valueOf(healthConditionData.getFollowUpDate()));
                    ps.setString(8, healthConditionData.getObservationNote());
                }
                ps.addBatch();
            }
            ps.executeBatch();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public void update(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            HashMap<HealthCondition, HealthConditionData> healthConditions = citizen.getHealthConditions();

            PreparedStatement psUpdate = con.prepareStatement(update);
            PreparedStatement psInsert = con.prepareStatement(insertHealthConditonData);

            for (Map.Entry<HealthCondition, HealthConditionData> entry : healthConditions.entrySet()) {

                PreparedStatement preparedStatementSelectHealthCondition = con.prepareStatement(selectHealthCondition);
                preparedStatementSelectHealthCondition.setInt(1, citizen.getId());
                preparedStatementSelectHealthCondition.setString(2, entry.getKey().toString());

                ResultSet rs = preparedStatementSelectHealthCondition.executeQuery();
                HealthConditionData healthConditionData = entry.getValue();

                if (rs.next()) {
                    psUpdate.setInt(7, citizen.getId());
                    psUpdate.setString(8, entry.getKey().toString());
                    psUpdate.setString(1, healthConditionData.getHealthConditionState().toString());
                    if (healthConditionData.getHealthConditionState() == HealthConditionState.INACTIVE) {
                        psUpdate.setNull(2, java.sql.Types.NVARCHAR);
                        psUpdate.setNull(3, java.sql.Types.NVARCHAR);
                        psUpdate.setNull(4, java.sql.Types.NVARCHAR);
                        psUpdate.setNull(5, Types.DATE);
                        psUpdate.setNull(6, Types.NVARCHAR);
                    } else { //we can trust the GUI that f the state is not inactive, all additional data is filled
                        psUpdate.setString(2, healthConditionData.getProfessionalNote());
                        psUpdate.setString(3, healthConditionData.getCurrentAssessment());
                        psUpdate.setString(4, healthConditionData.getExpectedLevel().toString());
                        psUpdate.setDate(5, java.sql.Date.valueOf(healthConditionData.getFollowUpDate()));
                        psUpdate.setString(6, healthConditionData.getObservationNote());
                    }

                    psUpdate.addBatch();
                }
                else { //responsible for creating new rows
                    psInsert.setInt(1, citizen.getId());
                    psInsert.setString(2, entry.getKey().toString());
                    psInsert.setString(3, healthConditionData.getHealthConditionState().toString());
                    if (healthConditionData.getHealthConditionState() == HealthConditionState.INACTIVE) {
                        psInsert.setNull(4, java.sql.Types.NVARCHAR);
                        psInsert.setNull(5, java.sql.Types.NVARCHAR);
                        psInsert.setNull(6, java.sql.Types.NVARCHAR);
                        psInsert.setNull(7, Types.DATE);
                        psInsert.setNull(8, Types.NVARCHAR);
                    } else { //we can trust the GUI that f the state is not inactive, all additional data is filled
                        psInsert.setString(4, healthConditionData.getProfessionalNote());
                        psInsert.setString(5, healthConditionData.getCurrentAssessment());
                        psInsert.setString(6, healthConditionData.getExpectedLevel().toString());
                        psInsert.setDate(7, java.sql.Date.valueOf(healthConditionData.getFollowUpDate()));
                        psInsert.setString(8, healthConditionData.getObservationNote());
                    }
                    psInsert.addBatch();
                }
            }
            psUpdate.executeBatch();
            psInsert.executeBatch();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
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
        if (healthConditionData.getHealthConditionState() == HealthConditionState.ACTIVE) {
            healthConditionData.setProfessionalNote(rs.getString(columns[3]));
            healthConditionData.setCurrentAssessment(rs.getString(columns[4]));
            healthConditionData.setExpectedLevel(ExpectedLevel.fromString(rs.getString(columns[5])));
            healthConditionData.setFollowUpDate(rs.getDate(columns[6]).toLocalDate());
            healthConditionData.setObservationNote(rs.getString(columns[7]));
        }
        return healthConditionData;
    }
}

