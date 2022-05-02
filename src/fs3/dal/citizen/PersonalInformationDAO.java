package fs3.dal.citizen;

import fs3.be.Citizen;
import fs3.be.PersonalInformation;
import fs3.dal.DAO;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class PersonalInformationDAO implements DAO<PersonalInformation> {

    @Override
    public Optional<PersonalInformation> read(int id) {
        return Optional.empty();
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
    public Optional<Citizen> constructObject(ResultSet rs) throws Exception {
        return Optional.empty();
    }
}
