package dao;

import entities.Pack;
import entities.Question;

import java.util.List;

public interface IPackDao {

    List<Pack> findAll();
    Pack findById(long id);
    long save(Pack pack);
    void update(long id, Pack pack);
    void updateTime(long id);
    void delete(long id);
}
