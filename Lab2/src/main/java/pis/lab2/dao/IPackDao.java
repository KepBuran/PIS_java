package pis.lab2.dao;

import pis.lab2.entities.Pack;

import java.util.List;

public interface IPackDao {

    List<Pack> findAll();
    Pack findById(long id);

    boolean checkPacknameOccupation(String packName, long userId);

    long save(Pack pack);

    boolean update(long id, String packName, Long userId);

    void updateTime(long id);
    void delete(long id);
    void closeConnection();
    List<Pack> findByUserId(Long userId);

    String getPackNameById(Long packId);
}
