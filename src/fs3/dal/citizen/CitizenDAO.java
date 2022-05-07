package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CitizenDAO {
    private final PersonalInformationDAO personalInformationDAO = new PersonalInformationDAO();
    private final GeneralInformationDAO generalInformationDAO = new GeneralInformationDAO();
    private final HealthConditionDAO healthConditionDAO = new HealthConditionDAO();
    private final FunctionalAbilityDAO functionalAbilityDAO = new FunctionalAbilityDAO();

    ExecutorService executor = Executors.newCachedThreadPool();

    String tableName = "Citizens";
    String[] columns = {"id"};

    String readAll = "SELECT * FROM " + tableName;

    public List<Citizen> readAll() throws Exception {
        List<Citizen> citizens = new ArrayList<>();

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(readAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Future<Exception> future = executor.submit(() -> {
                    try {
                        citizens.add(constructObject(rs));
                    }
                    catch (Exception e) {
                        return e;
                    }
                    return null;
                });
                if (future.get() != null) {
                    throw future.get();
                }
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        return citizens;
    }

    public void update(Citizen citizen) throws Exception {
        personalInformationDAO.update(citizen);
        generalInformationDAO.update(citizen);
        healthConditionDAO.update(citizen);
        functionalAbilityDAO.update(citizen);
    }

    private Citizen constructObject(ResultSet rs) throws Exception {
        Citizen citizen = new Citizen();
        int citizenId = rs.getInt(columns[0]);
        citizen.setId(citizenId);
        Future<Exception> future = executor.submit(() -> {
            try {
                citizen.setPersonalInformation(personalInformationDAO.read(citizen));
            }
            catch (Exception e) {
                return e;
            }
            return null;
        });
        Future<Exception> future1 = executor.submit(() -> {
            try {
                citizen.setGeneralInformation(generalInformationDAO.read(citizen));
            }
            catch (Exception e) {
                return e;
            }
            return null;
        });
        Future<Exception> future2 = executor.submit(() -> {
            try {
                citizen.setHealthConditions(healthConditionDAO.read(citizen));
            }
            catch (Exception e) {
                return e;
            }
            return null;
        });
        Future<Exception> future3 = executor.submit(() -> {
            try {
                citizen.setFunctionalAbilities(functionalAbilityDAO.read(citizen));
            }
            catch (Exception e) {
                return e;
            }
            return null;
        });
        if (future.get() != null || future1.get() != null || future2.get() != null || future3.get() != null) {
            throw new Exception("Error while constructing citizen object");
        }

        return citizen;
    }
}
