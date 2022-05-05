package fs3.dal.citizen;

public class FunctionalAbilityDAO {
    private final String tableName = "FunctionalAbilities";
    private final String[] columns = {"citizenId", "currentLimitationLevel", "expectedLimitationLevel", "professionalNote", "performance", "citizenRequest", "followUpDate", "observationNote"};

    private final String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
}
