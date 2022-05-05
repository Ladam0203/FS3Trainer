package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.FunctionalAbilityData;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.enums.FunctionalAbility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;

public class FunctionalAbilityDAO {
    private final String tableName = "FunctionalAbilities";
    private final String[] columns = {"citizenId", "currentLimitationLevel", "expectedLimitationLevel", "professionalNote", "performance", "citizenRequest", "followUpDate", "observationNote"};

    private final String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";

    public HashMap<FunctionalAbility, FunctionalAbilityData> read(Citizen citizen) throws Exception {
        HashMap<FunctionalAbility, FunctionalAbilityData> functionalAblities = new HashMap<>();

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            //TODO
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return functionalAblities;
    }

    private HashMap<FunctionalAbility, FunctionalAbilityData> constructObject(ResultSet rs) throws Exception {
        HashMap<FunctionalAbility, FunctionalAbilityData> functionalAblities = new HashMap<>();
        while (rs.next()) {
            //TODO
        }
        return null;
    }

    private FunctionalAbilityData constructFunctionalAbilityData(ResultSet rs) {
        return null;
    }
}
