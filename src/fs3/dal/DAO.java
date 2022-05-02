package fs3.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import fs3.be.Citizen;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {


    Optional<T> read(int id);

    List<T> readAll() throws Exception;

    Optional<T> create(T t);

    void update(T t) throws Exception;


    void delete(T t) throws Exception;

    Optional<Citizen> constructObject(ResultSet rs) throws Exception;
}
