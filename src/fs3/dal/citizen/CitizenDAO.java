package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.GeneralInformation;
import fs3.be.HealthConditionData;
import fs3.be.PersonalInformation;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.dal.DAO;
import fs3.enums.HealthCondition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class CitizenDAO implements DAO<Citizen> {
    private final PersonalInformationDAO personalInformationDAO = new PersonalInformationDAO();
    private final GeneralInformationDAO generalInformationDAO = new GeneralInformationDAO();
    private final HealthConditionDAO healthConditionDAO = new HealthConditionDAO();

    String tableName = "Citizens";
    String[] columns = {"id"};

    String read = "SELECT * FROM" + tableName + " WHERE " + columns[0] + " = ?";
    String readAll = "SELECT * FROM " + tableName;
    String create = "INSERT INTO " + tableName + " DEFAULT VALUES";
    String delete = "DELETE FROM " + tableName + " WHERE " + columns[0] + " = ?";

    @Override
    public Citizen read(int id) {
        return null;
    }

    @Override
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

    @Override
    public Citizen create(Citizen citizen) {
        return null;
    }

    @Override
    public void update(Citizen citizen) {

    }

    @Override
    public void delete(Citizen citizen) {

    }

    private Citizen constructObject(ResultSet rs) throws Exception {
        Citizen citizen = new Citizen();
        int citizenId = rs.getInt(columns[0]);
        citizen.setId(citizenId);
        citizen.setPersonalInformation(personalInformationDAO.read(citizenId));
        citizen.setGeneralInformation(generalInformationDAO.read(citizenId));
        citizen.setHealthConditions(healthConditionDAO.read(citizenId));
        return citizen;
    }
}
