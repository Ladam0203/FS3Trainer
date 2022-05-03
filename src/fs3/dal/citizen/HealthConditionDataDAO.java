package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.HealthConditionData;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.dal.DAO;
import fs3.enums.HealthCondition;
import fs3.enums.HealthConditionState;
import fs3.util.ExpectedLevelParser;
import fs3.util.HealthConditionStateParser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HealthConditionDataDAO {
    private String tableName = "HealthConditionData";
    private String[] columns = {"citizenId", "healthCondition", "healthConditionState", "professionalNote", "currentAssessment", "expectedLevel", "followUpDate", "observationNote"};
    String select = "SELECT * FROM " + tableName + " WHERE citizenId = ? AND healthCondition = ?";

    public Optional<HealthConditionData> read(int citizenId, HealthCondition healthCondition) throws Exception {
        HealthConditionData healthConditionData = null;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                healthConditionData = constructObject(rs);
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return Optional.of(healthConditionData);
    }

    private HealthConditionData constructObject(ResultSet rs) throws Exception {
        HealthConditionData healthConditionData = new HealthConditionData();
        healthConditionData.setHealthConditionState(HealthConditionStateParser.StringToHealthCondition(rs.getString("healthConditionState")));
        healthConditionData.setProfessionalNote(rs.getString("professionalNote"));
        healthConditionData.setCurrentAssessment(rs.getString("currentAssessment"));
        healthConditionData.setExpectedLevel(ExpectedLevelParser.StringToExpectedLevel(rs.getString("expectedLevel")));
        healthConditionData.setFollowUpDate(rs.getDate("followUpDate").toLocalDate());
        healthConditionData.setObservationNote(rs.getString("observationNote"));
        return healthConditionData;
    }
}

