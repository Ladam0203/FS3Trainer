package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.CitizenInstance;
import fs3.be.CitizenTemplate;
import fs3.be.School;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.dal.school.SchoolDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public class CitizenDAO {
    private final PersonalInformationDAO personalInformationDAO = new PersonalInformationDAO();
    private final GeneralInformationDAO generalInformationDAO = new GeneralInformationDAO();
    private final HealthConditionDAO healthConditionDAO = new HealthConditionDAO();
    private final FunctionalAbilityDAO functionalAbilityDAO = new FunctionalAbilityDAO();
    private final MedicineListDAO medicineListDAO = new MedicineListDAO();

    private final String tableName = "Citizens";
    private final String[] columns = {"id", "isTemplate", "schoolId"};

    private final String select = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private final String insert = "INSERT INTO " + tableName + " " + "VALUES (?, ?)";
    private final String readAllInstances = "SELECT * FROM " + tableName + " WHERE " + columns[1] + " = 0";
    private final String readAllInstancesFrom = "SELECT * FROM " + tableName + " WHERE " + columns[1] + " = 0 AND " + columns[2] + " = ?";
    private final String readAllTemplates = "SELECT * FROM " + tableName + " WHERE " + columns[1] + " = 1";
    private final String readAllTemplatesFrom = "SELECT * FROM " + tableName + " WHERE " + columns[1] + " = 1 AND " + columns[2] + " = ?";
    private final String delete = "DELETE FROM " + tableName + " WHERE " + columns[0] + " = ?";

    private final SchoolDAO schoolDAO = new SchoolDAO();

    private ExecutorService subExecutor;

    public List<CitizenTemplate> readAllCitizenTemplatesFrom(School school) throws Exception {
        return readAllFrom(readAllTemplatesFrom, school).stream().map(c -> (CitizenTemplate) c).collect(Collectors.toList());
    }

    public List<CitizenInstance> readAllCitizenInstancesFrom(School school) throws Exception {
        return readAllFrom(readAllInstancesFrom, school).stream().map(c -> (CitizenInstance) c).collect(Collectors.toList());
    }

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

    private List<Citizen> readAllFrom(String query, School school) throws Exception {
        List<Citizen> citizens = new ArrayList<>();

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, school.getId());
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
            if (citizen.getSchool() != null) {
                ps.setInt(2, citizen.getSchool().getId());
            } else {
                ps.setNull(2, Types.INTEGER);
            }
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                citizen.setId(rs.getInt(1));
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
        subExecutor = Executors.newFixedThreadPool(5);
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
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                medicineListDAO.create(citizen);
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

    //doesn't take into account the school as it is never updated on a citizen
    public void update(Citizen citizen) throws Exception {
        subExecutor = Executors.newFixedThreadPool(5);
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
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                medicineListDAO.update(citizen);
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
        citizen.setId(rs.getInt(columns[0]));
        citizen.setSchool(schoolDAO.read(rs.getInt(columns[2])));

        subExecutor = Executors.newFixedThreadPool(5);
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
        futures.add(subExecutor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                citizen.setMedicineList(medicineListDAO.read(citizen));
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
