package dao;

import entities.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDao implements IQuestionsDao{
    private final String SELECT_QUESTIONS = "SELECT questions.question";
    private Connection connection;
    private final static String COLUMN_ID = "id";
    private final static String COLUMN_QUESTIONTEXT = "question";
    private final static String COLUMN_PACKID = "pack_id";
    private final static String COLUMN_PACKORDER = "pack_order";
    private Statement statement;



    public QuestionsDao(final Connection connection) throws SQLException{
        this.connection = connection;
        this.statement = connection.createStatement();
    }

    private Question getQuestion(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong(COLUMN_ID);
        String questionText = resultSet.getString(COLUMN_QUESTIONTEXT);
        long packID = resultSet.getLong(COLUMN_PACKID);
        long packOrder = resultSet.getLong(COLUMN_PACKORDER);

        return new Question(id, questionText, packID, packOrder);
    }

    @Override
    public List<Question> findAll() {
        String query = "SELECT * FROM questions";
        List<Question> questionList = new ArrayList<Question>();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Question question = getQuestion(resultSet);
                questionList.add(question);
            }

            resultSet.close();
        } catch (
        SQLException e) {
            e.printStackTrace();
        }

        return questionList;
    }



    @Override
    public Question findById(long id){
        String query = "SELECT * FROM questions WHERE questions.id=" + id;

        Question question = new Question();

        try {
            ResultSet resultSet = statement.executeQuery(query);

            resultSet.next();
            question = getQuestion(resultSet);

            resultSet.close();
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return question;

    };

    @Override
    public long save(Question question) {
        String query = "INSERT INTO questions (question, pack_id, pack_order) VALUES ('";
        query += question.getQuestionText()+"',"
                +question.getPackId()+","+question.getPackOrder()+") RETURNING id" ;

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
    public void update(long id, Question question) {
        String query = "UPDATE questions SET question = '"+question.getQuestionText()
                +"', pack_id = "+question.getPackId()+", pack_order = "+question.getPackOrder()
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
        String query = "DELETE FROM questions WHERE id="+id;

        try {
            statement.executeUpdate(query);
        } catch (
                SQLException e) {
            e.printStackTrace();
        }

        return;
    };

}








