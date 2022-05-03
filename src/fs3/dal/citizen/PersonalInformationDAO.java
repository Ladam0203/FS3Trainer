package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.PersonalInformation;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonalInformationDAO {
    private String tableName = "PersonalInformations";
    private String[] columns = {"citizenId", "name"};

    private String read = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String readAll = "SELECT * FROM " + tableName;
    private String create = "INSERT INTO " + tableName + " VALUES (?, ?)";
    private String update = "UPDATE " + tableName + " SET " + columns[1] + " = ? WHERE " + columns[0] + " = ?";

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
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return personalInformation;
    }

    public void update(Citizen citizen) throws Exception {
        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(update);
            ps.setString(1, citizen.getPersonalInformation().getName());

            ps.setInt(2, citizen.getId());

            ps.executeUpdate();
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);
    }

    public PersonalInformation constructObject(ResultSet rs) throws Exception {
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setName(rs.getString(columns[1]));
        return personalInformation;
    }
}
