package fs3.dal.citizen;

import fs3.be.PersonalInformation;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.dal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class PersonalInformationDAO {
    private String tableName = "PersonalInformations";
    private String[] columns = {"citizenId", "name"};

    private String read = "SELECT * FROM " + tableName + " WHERE " + columns[0] + " = ?";
    private String readAll = "SELECT * FROM " + tableName;
    private String create = "INSERT INTO " + tableName + " VALUES (?, ?)";
    private String update = "UPDATE " + tableName + " SET " + columns[1] + " = ? WHERE " + columns[0] + " = ?";

    public PersonalInformation read(int citizenId) throws Exception {
        PersonalInformation personalInformation = null;

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(read);
            ps.setInt(1, citizenId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                personalInformation = constructObject(rs);
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return personalInformation;
    }

    public PersonalInformation constructObject(ResultSet rs) throws Exception {
        PersonalInformation personalInformation = new PersonalInformation();
        personalInformation.setName(rs.getString(columns[1]));
        return personalInformation;
    }
}
