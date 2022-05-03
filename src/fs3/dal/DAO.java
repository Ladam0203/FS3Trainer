package fs3.dal;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {


    T read(int id) throws Exception;

    List<T> readAll() throws Exception;

    T create(T t);

    void update(T t) throws Exception;


    void delete(T t) throws Exception;
}
