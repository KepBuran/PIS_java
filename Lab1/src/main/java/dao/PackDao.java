package dao;

import entities.Pack;
import entities.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PackDao implements IPackDao{
    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_NAME = "name";
    private final static String COLUMN_CREATEDON = "created_on";
    private final static String COLUMN_LASTUPDATE = "last_update";
    private final static String COLUMN_USERID= "user_id";
    private Statement statement;


    public PackDao(final Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Pack getPack(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String name = resultSet.getString(COLUMN_NAME);
        Timestamp createdOn = resultSet.getTimestamp(COLUMN_CREATEDON);
        Timestamp lastUpdate = resultSet.getTimestamp(COLUMN_LASTUPDATE);
        long userID = resultSet.getLong(COLUMN_USERID);

        return new Pack(id, name, createdOn, lastUpdate, userID);
    }

    @Override
    public List<Pack> findAll() {
        String query = "SELECT * FROM packs";
        List<Pack> packList = new ArrayList<Pack>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Pack question = getPack(resultSet);
                packList.add(question);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return packList;
    }



    @Override
    public Pack findById(long id){
        String query = "SELECT * FROM packs WHERE packs.id=" + id;

        Pack pack = new Pack();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            pack = getPack(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return pack;

    };

    @Override
    public long save(Pack pack) {
        String query = "INSERT INTO packs (name, created_on, last_update, user_id) VALUES ('";
        query += pack.getName()+"', now(), now(),"
                +pack.getUserId()+") RETURNING id" ;

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
    public void update(long id, Pack pack) {
        String query = "UPDATE packs SET name = '"+pack.getName()
                +"', last_update = now()"
                +" WHERE id="+id;

        try {
            statement.executeUpdate(query);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };


    @Override
    public void updateTime(long id){
        String query = "UPDATE packs SET last_update = now() WHERE id="+id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM packs WHERE id="+id;

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };


}
