package fs3.dal;

import fs3.be.Citizen;

import java.util.List;
import java.util.Optional;

public class CitizenDAO implements DAO<Citizen>{
    String tableName = "Citizen";
    String[] columns = {"name"};

    @Override
    public Optional<Citizen> get(int id) {
        return Optional.empty();
    }

    @Override
    public List<Citizen> getAll() {
        return null;
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
}
