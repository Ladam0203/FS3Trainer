package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.PersonalInformation;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.dal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class PersonalInformationDAO implements DAO<PersonalInformation> {
    private String tableName = "PersonalInformations";
    private String[] columns = {"CitizenID", "FirstName", "LastName"};

    private String read = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String readAll = "SELECT * FROM " + tableName;
    private String create = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
    private String update = "UPDATE " + tableName + " SET " + columns[1] + " = ?, " + columns[2] + " = ? WHERE " + columns[0] + " = ?";

    @Override
    public Optional<PersonalInformation> read(int citizenId) throws Exception {
        PersonalInformation personalInformation;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(read);
            ps.setInt(1, citizenId);
            ResultSet rs = ps.executeQuery();

            personalInformation = constructObject(rs).get();
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return Optional.of(personalInformation);
    }

    @Override
    public List<PersonalInformation> readAll() throws Exception {
        return null;
    }

    @Override
    public Optional<PersonalInformation> create(PersonalInformation personalInformation) {
        return Optional.empty();
    }

    @Override
    public void update(PersonalInformation personalInformation) throws Exception {

    }

    @Override
    public void delete(PersonalInformation personalInformation) throws Exception {

    }

    @Override
    public Optional<PersonalInformation> constructObject(ResultSet rs) throws Exception {
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setFirstName(rs.getString(2));
        personalInformation.setLastName(rs.getString(3));
        return Optional.of(personalInformation);
    }
}
