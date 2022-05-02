package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.PersonalInformation;
import fs3.dal.ConnectionManager;
import fs3.dal.ConnectionManagerPool;
import fs3.dal.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CitizenDAO implements DAO<Citizen> {
    private final DAO<PersonalInformation> personalInformationDAO = new PersonalInformationDAO();

    String tableName = "Citizens";
    String[] columns = {"id", "name"};

    String read = "SELECT * FROM" + tableName + " WHERE " + columns[0] + " = ?";
    String readAll = "SELECT * FROM " + tableName;
    String create = "INSERT INTO " + tableName + " (" + columns[0] + ", " + columns[1] + ") VALUES (?, ?)";
    String update = "UPDATE " + tableName + " SET " + columns[0] + " = ?, " + columns[1] + " = ? WHERE " + columns[0] + " = ?";
    String delete = "DELETE FROM " + tableName + " WHERE " + columns[0] + " = ?";

    @Override
    public Optional<Citizen> read(int id) {
        return Optional.empty();
    }

    @Override
    public List<Citizen> readAll() throws Exception {
        List<Citizen> citizens = new ArrayList<>();

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(readAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                citizens.add(constructObject(rs).get());
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return citizens;
    }

    @Override
    public Optional<Citizen> create(Citizen citizen) {
        return Optional.empty();
    }

    @Override
    public void update(Citizen citizen) {

    }

    @Override
    public void delete(Citizen citizen) {

    }

    public Optional<Citizen> constructObject(ResultSet rs) throws Exception {
        Citizen citizen = new Citizen();
        citizen.setId(rs.getInt(columns[0]));
        citizen.setPersonalInformation(personalInformationDAO.read(rs.getInt(columns[0])).get());
        return Optional.of(citizen);
    }
}
