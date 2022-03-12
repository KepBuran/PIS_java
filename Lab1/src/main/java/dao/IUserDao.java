package dao;

import entities.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();
    User findById(long id);
    long save(User user);
    void update(long id, User user);
    void delete(long id);
}
