package pis.lab2.dao;

import pis.lab2.entities.Pack;
import pis.lab2.entities.Question;

import java.sql.SQLException;
import java.util.List;

public interface IQuestionsDao {

    List<Question> findAll();
    Question findById(long id);
    long save(Question question);
    void update(long id, Question question);
    void delete(long id);
    void closeConnection();

    List<Question> findByPackId(Long packId);

    void deleteByPackId(Long packId);

    long getPackOrder(long packId);
}
