package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.FunctionalAbilityData;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.enums.FunctionalAbility;
import fs3.enums.LimitationLevel;
import fs3.enums.PerceivedLimitationLevel;
import fs3.enums.Performance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class FunctionalAbilityDAO {
    private final String tableName = "FunctionalAbilities";
    private final String[] columns = {"citizenId", "functionalAbility", "currentLimitationLevel", "expectedLimitationLevel", "professionalNote", "performance", "perceivedLimitationLevel", "citizenRequest", "followUpDate", "observationNote"};

    private final String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";

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

    private void setObject(HashMap<FunctionalAbility, FunctionalAbilityData> functionalAbilities, ResultSet rs) throws Exception {
        while (rs.next()) {
            functionalAbilities.put(FunctionalAbility.fromString(rs.getString(columns[1])), constructFunctionalAbilityData(rs));
        }
    }

    private FunctionalAbilityData constructFunctionalAbilityData(ResultSet rs) throws SQLException {
        FunctionalAbilityData functionalAbilityData = new FunctionalAbilityData();

        functionalAbilityData.setCurrentLimitationLevel(LimitationLevel.fromInt(rs.getInt(columns[2])));
        functionalAbilityData.setExpectedLimitationLevel(LimitationLevel.fromInt(rs.getInt(columns[3])));
        functionalAbilityData.setProfessionalNote(rs.getString(columns[4]));
        functionalAbilityData.setPerformance(Performance.fromString(rs.getString(columns[5])));
        functionalAbilityData.setPerceivedLimitationLevel(PerceivedLimitationLevel.fromString(rs.getString(columns[6])));
        functionalAbilityData.setCitizenRequest(rs.getString(columns[7]));
        functionalAbilityData.setFollowUpDate(rs.getDate(columns[8]).toLocalDate());
        functionalAbilityData.setObservationNote(rs.getString(columns[9]));

        return functionalAbilityData;
    }
}
