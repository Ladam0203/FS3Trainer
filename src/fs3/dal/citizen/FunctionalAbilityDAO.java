package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.FunctionalAbilityData;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.enums.FunctionalAbility;
import fs3.enums.LimitationLevel;
import fs3.enums.PerceivedLimitationLevel;
import fs3.enums.Performance;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class FunctionalAbilityDAO {
    private final String tableName = "FunctionalAbilities";
    private final String[] columns = {"citizenId", "functionalAbility", "currentLimitationLevel", "expectedLimitationLevel", "professionalNote", "performance", "perceivedLimitationLevel", "citizenRequest", "followUpDate", "observationNote"};

    private final String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private final String selectFunctionalAbility = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ? AND " + columns[1] + " = ?";
    private final String update = "UPDATE " + tableName + " SET " + columns[2] + " = ?, " + columns[3] + " = ?, " + columns[4] + " = ?, " + columns[5] + " = ?, " + columns[6] + " = ?, " + columns[7] + " = ?, " + columns[8] + " = ?, " + columns[9] + " = ?, " + " = ? WHERE " + columns[0] + " = ? AND " + columns[1] + " = ?";
    private final String insertFunctionalAbility = "INSERT INTO " + tableName + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public HashMap<FunctionalAbility, FunctionalAbilityData> read(Citizen citizen) throws Exception {
        HashMap<FunctionalAbility, FunctionalAbilityData> functionalAblities = new HashMap<>();

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, citizen.getId());
            ResultSet rs = ps.executeQuery();
            setObject(functionalAblities, rs);
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return functionalAblities;
    }

    public void update(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            HashMap<FunctionalAbility, FunctionalAbilityData> functionalAbilities = citizen.getFunctionalAbilities();

            PreparedStatement psUpdate = con.prepareStatement(update);
            PreparedStatement psInsert = con.prepareStatement(insertFunctionalAbility);

            for (Map.Entry<FunctionalAbility, FunctionalAbilityData> entry : functionalAbilities.entrySet()) {

                PreparedStatement psFunctionalAbility = con.prepareStatement(selectFunctionalAbility);
                psFunctionalAbility.setInt(1, citizen.getId());
                psFunctionalAbility.setString(2, entry.getKey().toString());

                ResultSet rs = psFunctionalAbility.executeQuery();
                FunctionalAbilityData functionalAbilityData = entry.getValue();

                if (rs.next()) {
                    psUpdate.setInt(1, functionalAbilityData.getCurrentLimitationLevel() == null ? null : functionalAbilityData.getCurrentLimitationLevel().getValue());
                    psUpdate.setInt(2, functionalAbilityData.getExpectedLimitationLevel() == null ? null : functionalAbilityData.getExpectedLimitationLevel().getValue());
                    psUpdate.setString(3, functionalAbilityData.getProfessionalNote());
                    psUpdate.setString(4, functionalAbilityData.getPerformance() == null ? null : functionalAbilityData.getPerformance().toString());
                    psUpdate.setString(5, functionalAbilityData.getPerceivedLimitationLevel() == null ? null : functionalAbilityData.getPerceivedLimitationLevel().toString());
                    psUpdate.setString(6, functionalAbilityData.getCitizenRequest());
                    psUpdate.setDate(7, functionalAbilityData.getFollowUpDate() == null ? null : java.sql.Date.valueOf(functionalAbilityData.getFollowUpDate()));
                    psUpdate.setString(8, functionalAbilityData.getObservationNote());

                    psUpdate.setInt(9, citizen.getId());
                    psUpdate.setString(10, entry.getKey().toString());

                    psUpdate.addBatch();
                }
                else {
                    psInsert.setInt(1, citizen.getId());
                    psInsert.setString(2, entry.getKey().toString());
                    psInsert.setInt(3, functionalAbilityData.getCurrentLimitationLevel().getValue());
                    if (functionalAbilityData.getExpectedLimitationLevel() == null) {
                        psInsert.setNull(4, Types.INTEGER);
                    }
                    else {
                        psInsert.setInt(4, functionalAbilityData.getExpectedLimitationLevel().getValue());
                    }
                    psInsert.setString(5, functionalAbilityData.getProfessionalNote());
                    psInsert.setString(6, functionalAbilityData.getPerformance() == null ? null : functionalAbilityData.getPerformance().toString());
                    psInsert.setString(7, functionalAbilityData.getPerceivedLimitationLevel() == null ? null : functionalAbilityData.getPerceivedLimitationLevel().toString());
                    psInsert.setString(8, functionalAbilityData.getCitizenRequest());
                    psInsert.setDate(9, functionalAbilityData.getFollowUpDate() == null ? null : java.sql.Date.valueOf(functionalAbilityData.getFollowUpDate()));
                    psInsert.setString(10, functionalAbilityData.getObservationNote());

                    psInsert.addBatch();
                }
                try {
                    psUpdate.executeBatch();
                } catch (SQLException e) {

                }
                try {
                    psInsert.executeBatch();
                } catch (SQLException e) {

                }
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);
    }

    private void setObject(HashMap<FunctionalAbility, FunctionalAbilityData> functionalAbilities, ResultSet rs) throws Exception {
        while (rs.next()) {
            functionalAbilities.put(FunctionalAbility.fromString(rs.getString(columns[1])), constructFunctionalAbilityData(rs));
        }
    }

    private FunctionalAbilityData constructFunctionalAbilityData(ResultSet rs) throws SQLException {
        FunctionalAbilityData functionalAbilityData = new FunctionalAbilityData();

        functionalAbilityData.setCurrentLimitationLevel(LimitationLevel.fromInt(rs.getInt(columns[2])));
        if (functionalAbilityData.getCurrentLimitationLevel() != LimitationLevel.NOT_RELEVANT) {
            functionalAbilityData.setExpectedLimitationLevel(LimitationLevel.fromInt(rs.getInt(columns[3])));
            functionalAbilityData.setProfessionalNote(rs.getString(columns[4]));
            functionalAbilityData.setPerformance(Performance.fromString(rs.getString(columns[5])));
            functionalAbilityData.setPerceivedLimitationLevel(PerceivedLimitationLevel.fromString(rs.getString(columns[6])));
            functionalAbilityData.setCitizenRequest(rs.getString(columns[7]));
            functionalAbilityData.setFollowUpDate(rs.getDate(columns[8]).toLocalDate());
            functionalAbilityData.setObservationNote(rs.getString(columns[9]));
        }
        return functionalAbilityData;
    }
}
