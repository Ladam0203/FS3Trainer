package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CitizenDAO {
    private final PersonalInformationDAO personalInformationDAO = new PersonalInformationDAO();
    private final GeneralInformationDAO generalInformationDAO = new GeneralInformationDAO();
    private final HealthConditionDAO healthConditionDAO = new HealthConditionDAO();

    String tableName = "Citizens";
    String[] columns = {"id"};

    String read = "SELECT * FROM" + tableName + " WHERE " + columns[0] + " = ?";
    String readAll = "SELECT * FROM " + tableName;
    String create = "INSERT INTO " + tableName + " DEFAULT VALUES";
    String delete = "DELETE FROM " + tableName + " WHERE " + columns[0] + " = ?";

    public Citizen read(int id) {
        return null;
    }

    public List<Citizen> readAll() throws Exception {
        List<Citizen> citizens = new ArrayList<>();

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(readAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                citizens.add(constructObject(rs));
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return citizens;
    }

    public void update(Citizen citizen) throws Exception {
        personalInformationDAO.update(citizen);
        generalInformationDAO.update(citizen);
        healthConditionDAO.update(citizen);
    }

    private Citizen constructObject(ResultSet rs) throws Exception {
        Citizen citizen = new Citizen();
        int citizenId = rs.getInt(columns[0]);
        citizen.setId(citizenId);
        citizen.setPersonalInformation(personalInformationDAO.read(citizen));
        citizen.setGeneralInformation(generalInformationDAO.read(citizen));
        citizen.setHealthConditions(healthConditionDAO.read(citizen));
        return citizen;
    }
}
