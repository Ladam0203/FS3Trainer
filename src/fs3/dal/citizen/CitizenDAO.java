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

    ExecutorService subExecutor = Executors.newFixedThreadPool(4);
    ExecutorService executor = Executors.newCachedThreadPool();

    String tableName = "Citizens";
    String[] columns = {"id"};

    String readAll = "SELECT * FROM " + tableName;

    public List<Citizen> readAll() throws Exception {
        long start = System.currentTimeMillis();

        List<Citizen> citizens = new ArrayList<>();

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(readAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                executeAsync(new ExceptionCallable() {
                    @Override
                    void doTask() throws Exception {
                        citizens.add(constructCitizen(rs));
                    }
                });
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }

        long end = System.currentTimeMillis();
        System.out.println("CitizenDAO.readAll() took " + (end - start) + " ms");

        return citizens;
    }

    public void update(Citizen citizen) throws Exception {
        subExecutor = Executors.newFixedThreadPool(4);
        List<Future<Exception>> futures = new ArrayList<>();
        futures.add(executor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                personalInformationDAO.update(citizen);
            }
        }));
        futures.add(executor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                generalInformationDAO.update(citizen);
            }
        }));
        futures.add(executor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                healthConditionDAO.update(citizen);
            }
        }));
        futures.add(executor.submit(new ExceptionCallable() {
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

    private Citizen constructCitizen(ResultSet rs) throws Exception {
        Citizen citizen = new Citizen();
        int citizenId = rs.getInt(columns[0]);
        citizen.setId(citizenId);

        subExecutor = Executors.newFixedThreadPool(4);
        List<Future<Exception>> futures = new ArrayList<>();
        futures.add(executor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                citizen.setPersonalInformation(personalInformationDAO.read(citizen));
            }
        }));
        futures.add(executor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                citizen.setGeneralInformation(generalInformationDAO.read(citizen));
            }
        }));
        futures.add(executor.submit(new ExceptionCallable() {
            @Override
            void doTask() throws Exception {
                citizen.setHealthConditions(healthConditionDAO.read(citizen));
            }
        }));
        futures.add(executor.submit(new ExceptionCallable() {
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

    /*
    * Submits task to executor service and throws the exception upwards if it happened, not really async because it will wait to finish...
     */
    public void executeAsync(ExceptionCallable callable) throws Exception {
        Future<Exception> exception = executor.submit(callable);
        if (exception.get() != null) {
            throw exception.get();
        }
    }
}
