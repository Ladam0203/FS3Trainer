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

public class HealthConditionDAO implements DAO<HashMap<HealthCondition, HealthConditionData>> {
    private String tableName = "HealthConditions";
    private String[] columns = {"citizenId", "healthCondition", "healthConditionState", "professionalNote", "currentAssesment", "expectedLevel", "followUpDate", "observationNote"};
    String select = "SELECT * FROM " + tableName + " WHERE citizenId = ?";

    @Override
    public Optional<HashMap<HealthCondition, HealthConditionData>> read(int citizenId) throws Exception {
        HashMap<HealthCondition, HealthConditionData> healthConditionData = null;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, citizenId);
            ResultSet rs = ps.executeQuery();
            healthConditionData = constructObject(rs).get();
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return Optional.of(healthConditionData);
    }

    @Override
    public List<HashMap<HealthCondition, HealthConditionData>> readAll() throws Exception {
        return null;
    }

    @Override
    public Optional<HashMap<HealthCondition, HealthConditionData>> create(HashMap<HealthCondition, HealthConditionData> healthConditionHealthConditionDataHashMap) {
        return Optional.empty();
    }

    @Override
    public void update(HashMap<HealthCondition, HealthConditionData> healthConditionHealthConditionDataHashMap) throws Exception {

    }

    @Override
    public void delete(HashMap<HealthCondition, HealthConditionData> healthConditionHealthConditionDataHashMap) throws Exception {

    }

    @Override
    public Optional<HashMap<HealthCondition, HealthConditionData>> constructObject(ResultSet rs) throws Exception {
        HashMap<HealthCondition, HealthConditionData> healthConditions = new HashMap<>();
        while (rs.next()) {
            healthConditions.put(HealthConditionParser.StringToHealthCondition(rs.getString(columns[1])), constructHealthConditionData(rs));
        }
        return Optional.of(healthConditions);
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

