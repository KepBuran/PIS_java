package pis.lab2.services;

import pis.lab2.dao.DaoFactory;
import pis.lab2.dao.IPackDao;
import pis.lab2.dao.IQuestionsDao;
import pis.lab2.entities.Pack;
import pis.lab2.entities.Question;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

public class QuestionService {


    public List<Question> findQuestionsByPackId(Long packId) {
        IQuestionsDao questionsDao = DaoFactory.createQuestionDao();
        List<Question> questions = questionsDao.findByPackId(packId);
        questionsDao.closeConnection();

        return questions;

    }

    public void create(String question, String answer, long packId) {
        IQuestionsDao questionsDao = DaoFactory.createQuestionDao();
        long packOrder = questionsDao.getPackOrder(packId);
        questionsDao.save(new Question(question, answer, packId, packOrder));
        questionsDao.closeConnection();

        IPackDao packDao = DaoFactory.createPackDao();
        packDao.updateTime(packId);
        packDao.closeConnection();
    }

    public void deleteQuestion(Long questionId) {
        IQuestionsDao questionsDao = DaoFactory.createQuestionDao();
        questionsDao.delete(questionId);
        questionsDao.closeConnection();

    }
}
