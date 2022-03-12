package dao;

import entities.Pack;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements IUserDao{
    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_USERNAME = "username";
    private final static String COLUMN_PASSWORDHASH = "password_hash";
    private Statement statement;



    public UserDao(final Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String username = resultSet.getString(COLUMN_USERNAME);
        String passwordHash = resultSet.getString(COLUMN_PASSWORDHASH);

        return new User(id, username, passwordHash);
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM users";
        List<User> userList = new ArrayList();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return userList;
    }



    @Override
    public User findById(long id){
       String query = "SELECT * FROM users WHERE users.id=" + id;

        User user = new User();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            user = getUser(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return user;

    };

    @Override
    public long save(User user) {
        String query = "INSERT INTO users (username, password_hash) VALUES ('";
        query += user.getUsername()+"', '"
                +user.getPasswordHash()+"') RETURNING id" ;

        long id = 0;

        try {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                id = resultSet.getLong("id");
            }


            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return id;
    };

    @Override
    public void update(long id, User user) {
        String query = "UPDATE users SET username = '"+user.getUsername()
                +"', password_hash = '"+user.getPasswordHash()+"'"
                +" WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };


    public void delete(long id) {
        String query = "DELETE FROM users WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };



}
