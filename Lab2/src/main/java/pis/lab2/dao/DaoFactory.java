package pis.lab2.dao;

import javax.naming.NamingException;
import java.sql.Connection;
import java.sql.SQLException;

public class DaoFactory {

    public static IQuestionsDao createQuestionDao () {
        return new QuestionsDao();
    }
    public static IPackDao createPackDao () {
        return new PackDao();
    }
    public static IUserDao createUserDao () {
        return new UserDao();
    }

}
