package fs3.dal;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> get(int id) throws Exception;

    List<T> getAll() throws Exception;

    void save(T t) throws Exception;

    void update(T t, String[] params) throws Exception;

    void delete(T t) throws Exception;
}
