package fs3.dal.citizen;

import fs3.be.Citizen;
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
    String tableName = "Citizens";
    String[] columns = {"id", "name"};

    String selectById = "SELECT * FROM" + tableName + " WHERE " + columns[0] + " = ?";
    String selectAll = "SELECT * FROM " + tableName;
    String insert = "INSERT INTO " + tableName + " (" + columns[0] + ", " + columns[1] + ") VALUES (?, ?)";
    String update = "UPDATE " + tableName + " SET " + columns[0] + " = ?, " + columns[1] + " = ? WHERE " + columns[0] + " = ?";
    String delete = "DELETE FROM " + tableName + " WHERE " + columns[0] + " = ?";

    @Override
    public Optional<Citizen> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Citizen> getAll() throws Exception {
        List<Citizen> citizens = new ArrayList<>();

        ConnectionManager cm = ConnectionManagerPool.getInstance().getConnectionManager();
        try (Connection con = cm.getConnection()) {
            PreparedStatement ps = con.prepareStatement(selectAll);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                citizens.add(constructObject(rs).get());
            }
        }
        ConnectionManagerPool.getInstance().returnConnectionManager(cm);

        return citizens;
    }

    @Override
    public void save(Citizen citizen) {

    }

    @Override
    public void update(Citizen citizen, String[] params) {

    }

    @Override
    public void delete(Citizen citizen) {

    }

    public Optional<Citizen> constructObject(ResultSet rs) throws Exception {
        Citizen citizen = new Citizen();
        citizen.setId(rs.getInt(columns[0]));
        citizen.setName(rs.getString(columns[1]));
        return Optional.of(citizen);
    }
}
