package fs3.dal.citizen;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import fs3.be.Citizen;
import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class CitizenDAO {
    private final PersonalInformationDAO personalInformationDAO = new PersonalInformationDAO();
    private final GeneralInformationDAO generalInformationDAO = new GeneralInformationDAO();
    private final HealthConditionDAO healthConditionDAO = new HealthConditionDAO();
    private final FunctionalAbilityDAO functionalAbilityDAO = new FunctionalAbilityDAO();

    private ExecutorService subExecutor;

    private String tableName = "Citizens";
    private String[] columns = {"id", "isTemplate"};

    private String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String insert = "INSERT INTO " + tableName + " " + "VALUES (?)";
    private String readAllInstances = "SELECT * FROM " + tableName + " WHERE " + columns[1] + " = 0";
    private String readAllTemplates = "SELECT * FROM " + tableName + " WHERE " + columns[1] + " = 1";
    private String delete = "DELETE FROM " + tableName + " WHERE " + columns[0] + " = ?";

    public List<CitizenTemplate> readAllCitizenTemplates() throws Exception {
        return readAll(readAllTemplates).stream().map(c -> (CitizenTemplate) c).collect(Collectors.toList());
    }

    public List<CitizenInstance> readAllCitizenInstances() throws Exception {
        return readAll(readAllInstances).stream().map(c -> (CitizenInstance) c).collect(Collectors.toList());
    }

    public Citizen read(int id) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(select);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return constructCitizen(rs);
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        return null;
    }

    private List<Citizen> readAll(String query) throws Exception {
        List<Citizen> citizens = new ArrayList<>();

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                citizens.add(constructCitizen(rs));
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }

        return citizens;
    }

    public Citizen create(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            ps.setBoolean(1, CitizenTemplate.class.equals(citizen.getClass()));
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                citizen.setId(rs.getInt(1));
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        subExecutor = Executors.newFixedThreadPool(4);
        List<Future<Exception>> futures = new ArrayList<>();
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                personalInformationDAO.create(citizen);
            }
        }));
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                generalInformationDAO.create(citizen);
            }
        }));
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                healthConditionDAO.create(citizen);
            }
        }));
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                functionalAbilityDAO.create(citizen);
            }
        }));

        subExecutor.shutdown();
        for (Future<Exception> future : futures) {
            if (future.get() != null) {
                throw future.get();
            }
        }

        return citizen;
    }

    public void update(Citizen citizen) throws Exception {
        subExecutor = Executors.newFixedThreadPool(4);
        List<Future<Exception>> futures = new ArrayList<>();
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                personalInformationDAO.update(citizen);
            }
        }));
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                generalInformationDAO.update(citizen);
            }
        }));
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                healthConditionDAO.update(citizen);
            }
        }));
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                functionalAbilityDAO.update(citizen);
            }
        }));

        subExecutor.shutdown();
        for (Future<Exception> future : futures) {
            if (future.get() != null) {
                throw future.get();
            }
        }
    }

    public void delete(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(delete);
            ps.setInt(1, citizen.getId());
            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    private Citizen constructCitizen(ResultSet rs) throws Exception {
        Citizen citizen = CitizenFactory.createCitizen(rs.getBoolean(columns[1]));
        int citizenId = rs.getInt(columns[0]);
        citizen.setId(citizenId);

        subExecutor = Executors.newFixedThreadPool(4);
        List<Future<Exception>> futures = new ArrayList<>();
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                citizen.setPersonalInformation(personalInformationDAO.read(citizen));
            }
        }));
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                citizen.setGeneralInformation(generalInformationDAO.read(citizen));
            }
        }));
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                citizen.setHealthConditions(healthConditionDAO.read(citizen));
            }
        }));
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                citizen.setFunctionalAbilities(functionalAbilityDAO.read(citizen));
            }
        }));

        subExecutor.shutdown();
        for (Future<Exception> future : futures) {
            if (future.get() != null) {
                throw future.get();
            }
        }

        return citizen;
    }
}
