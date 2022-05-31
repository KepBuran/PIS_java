package pis.lab2.dao;

import pis.lab2.connection.ConnectionFactory;
import pis.lab2.entities.Pack;

import javax.naming.NamingException;
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


    public PackDao() {
        try {
            this.connection = ConnectionFactory.getInstance().getConnection();
            this.statement = connection.createStatement();
        } catch (
            SQLException e) {
            e.printStackTrace();
        }
        catch (NamingException e) {
            e.printStackTrace();
        }
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
    public boolean checkPacknameOccupation(String packName, long userId) {
        String query = "SELECT * FROM packs WHERE "+COLUMN_NAME+"="+"'"+packName+"'"+" AND "+COLUMN_USERID+"="+userId;
        try {
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                resultSet.close();
                return true;
            }
            resultSet.close();
            return false;

        } catch (
                SQLException e) {
            e.printStackTrace();
        }


        return true;
    }
    @Override
    public long save(Pack pack) {
        if(checkPacknameOccupation(pack.getName(), pack.getUserId())) {
            //System.out.println("PACK WITH THIS NAME ALREADY EXISTS");
            return 0;
        }

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
    public boolean update(long id, String packName, Long userId) {
        if(checkPacknameOccupation(packName, userId)) {
            //System.out.println("PACK WITH THIS NAME ALREADY EXISTS");
            return false;
        }
        String query = "UPDATE packs SET name = '"+packName
                +"', last_update = now()"
                +" WHERE id="+id;

        try {
            statement.executeUpdate(query);

        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return true;
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

    @Override
    public void closeConnection(){
        try {
            statement.close();
            connection.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public List<Pack> findByUserId(Long userId) {
        String query = "SELECT * FROM packs WHERE "+COLUMN_USERID+"="+userId.toString()+" ORDER BY "+COLUMN_LASTUPDATE;
        List<Pack> packList = new ArrayList<Pack>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Pack pack = getPack(resultSet);
                packList.add(pack);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return packList;
    }

    @Override
    public String getPackNameById(Long packId) {
        String query = "SELECT * FROM packs WHERE "+COLUMN_ID+"="+packId;
        String packName = "";

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                packName = resultSet.getString(COLUMN_NAME);
            }

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return packName;
    }
}
