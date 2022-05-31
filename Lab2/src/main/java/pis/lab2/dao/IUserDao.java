package pis.lab2.dao;

import pis.lab2.entities.User;

import java.sql.SQLException;
import java.util.List;

public interface IUserDao {

    List<User> findAll();
    User findById(long id);

    User findByUsername(String username);

    boolean checkUsernameOccupation(String username);

    long save(User user);
    void update(long id, User user);
    void delete(long id);
    void closeConnection();
}
