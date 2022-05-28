package fs3.dal.user;

import fs3.be.User;

public interface SubUserDAO<T extends User> {
    void set(User user) throws Exception;

    void create(T t) throws Exception;

    void update(T t) throws Exception;
}
