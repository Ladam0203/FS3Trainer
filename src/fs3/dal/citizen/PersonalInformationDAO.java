package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.PersonalInformation;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonalInformationDAO {
    private final String tableName = "PersonalInformations";
    private final String[] columns = {"citizenId", "name", "age"};

    private final String read = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private final String create = "INSERT INTO " + tableName + " VALUES (?, ?, ?)";
    private final String update = "UPDATE " + tableName + " SET " + columns[1] + " = ?, " + columns[2] + " = ? WHERE " + columns[0] + " = ?";

    public PersonalInformation read(Citizen citizen) throws Exception {
        PersonalInformation personalInformation = null;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(read);
            ps.setInt(1, citizen.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                personalInformation = constructObject(rs);
            }
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }

        return personalInformation;
    }

    public void create(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(create);
            ps.setInt(1, citizen.getId());
            ps.setString(2, citizen.getPersonalInformation().getName());
            ps.setInt(3, citizen.getPersonalInformation().getAge());

            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public void update(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, citizen.getPersonalInformation().getName());
            ps.setInt(2, citizen.getPersonalInformation().getAge());

            ps.setInt(3, citizen.getId());

            ps.executeUpdate();
        } finally {
            ConnectionManagerPool.getInstance().returnConnectionManager(cm);
        }
    }

    public PersonalInformation constructObject(ResultSet rs) throws Exception {
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setName(rs.getString(columns[1]));
        personalInformation.setAge(rs.getInt(columns[2]));
        return personalInformation;
    }
}
