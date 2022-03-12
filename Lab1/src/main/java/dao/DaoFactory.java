package dao;

import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    public static IQuestionsDao createQuestionDao (Connection connection) throws SQLException {
        return new QuestionsDao(connection);
    }
    public static IPackDao createPackDao (Connection connection) throws SQLException {
        return new PackDao(connection);
    }
    public static IUserDao createUserDao (Connection connection) throws SQLException {
        return new UserDao(connection);
    }

}
