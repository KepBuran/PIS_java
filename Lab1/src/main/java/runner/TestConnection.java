package runner;

import connection.ConnectionPool;
import dao.DaoFactory;
import dao.IPackDao;
import dao.IQuestionsDao;
import dao.IUserDao;
import entities.Pack;
import entities.Question;
import entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestConnection {
    public static void main(String args[]) {

        System.out.println("\n");
        testQuestionDao();
        System.out.println("\n\n");
        testPackDao();
        System.out.println("\n\n");
        testUserDao();
        System.out.println("\n\n");
        testConnectionPool();


    }


    private static void testQuestionDao(){
        try {
            System.out.println("______Test of QuestionDao______");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection1 = connectionPool.getConnection();

            IQuestionsDao questionDao = DaoFactory.createQuestionDao(connection1);

            System.out.println("\t*Finding question by id 2*");
            System.out.println(questionDao.findById(2).toString());

            System.out.println("\n\t*Inserting new question*");
            Question question1 = new Question("Temp question", 1, 4);
            long new_id = questionDao.save(question1);
            printAll(questionDao.findAll());

            System.out.println("\n\t*Updating question*");
            question1.setQuestionText("Temp question updated");
            questionDao.update(new_id , question1);
            printAll(questionDao.findAll());

            System.out.println("\n\t*Deleting question*");
            questionDao.delete(new_id);
            printAll(questionDao.findAll());


            connectionPool.releaseConnection(connection1);
            System.out.println("__________________________");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void testPackDao(){
        try {
            System.out.println("______Test of PackDao______");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection1 = connectionPool.getConnection();

            IPackDao packDao = DaoFactory.createPackDao(connection1);

            System.out.println("\t*Finding pack by id 1*");
            System.out.println(packDao.findById(1).toString());

            System.out.println("\n\t*Inserting new pack*");
            Pack pack1 = new Pack("Temp pack", 1);
            long new_id = packDao.save(pack1);
            printAll(packDao.findAll());

            System.out.println("\n\t*Updating pack*");
            pack1.setName("Temp pack updated");
            packDao.update(new_id , pack1);
            printAll(packDao.findAll());

            System.out.println("\n\t*Deleting pack*");
            packDao.delete(new_id);
            printAll(packDao.findAll());


            connectionPool.releaseConnection(connection1);
            System.out.println("____________________________");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void testUserDao(){
        try {
            System.out.println("______Test of UserDao______");
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            Connection connection1 = connectionPool.getConnection();

            IUserDao userDao = DaoFactory.createUserDao(connection1);

            System.out.println("\t*Finding user by id 1*");
            System.out.println(userDao.findById(1).toString());

            System.out.println("\n\t*Inserting new user*");
            User user1 = new User("TempUsername", "TempPassword");
            long new_id = userDao.save(user1);
            printAll(userDao.findAll());

            System.out.println("\n\t*Updating pack*");
            user1.setPasswordHash("TempPasswordUpdated");
            userDao.update(new_id , user1);
            printAll(userDao.findAll());

            System.out.println("\n\t*Deleting user*");
            userDao.delete(new_id);
            printAll(userDao.findAll());

            connectionPool.releaseConnection(connection1);
            System.out.println("____________________________");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }

    private static void testConnectionPool(){
        System.out.println("______Test of UserDao______");
        try {
            List<Connection> connectionList = new ArrayList<>();
            ConnectionPool connectionPool = ConnectionPool.getInstance();

            System.out.print("Initial amount of coonections in pool: ");
            System.out.println(connectionPool.amountOfFreeConnections());

            System.out.println("  *Getting 100 connections from the pool*");

            for (int i = 0; i < 100; i++)
                connectionList.add(connectionPool.getConnection());

            System.out.print("Amount of free connections in pool: ");
            System.out.println(connectionPool.amountOfFreeConnections());

            System.out.println("  *Free all 100 connections from the pool*");

            for (int i = 99; i > -1; i--) {
                connectionPool.releaseConnection(connectionList.get(i));
                connectionList.remove(i);
            }

            System.out.print("Amount of free connections in pool: ");
            System.out.println(connectionPool.amountOfFreeConnections());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("____________________________");
    }

    private static void printAll(List questionList){
        questionList.forEach((question) -> {
            System.out.println(question);
        });
        return;
    }


}


//Система "Что? Где? Когда?". Система состоит из Знатоков и Судьи.
//        Знатоки отвечают на Вопросы разного типа (с варинтами ответа и без). Судья
//        принимает ответы от Знатоков, опередляет правильность ответа, и
//        защитывает очко команде знатоков (в случае правильного ответа) или
//        команде противников (если Время вышло, или ответ не правильный). Судья
//        также может при запросе от знатоков давать Подсказки разного типа.
//        Сервисы: Время - засекает время между началом и концом вопроса в
//        соответствии с Конфигурацией. Подсказки: • может выводить вероятность
//        правильного ответа (при выборе из нескольких вариантов), • либо убрать
//        несколько неправильных вариантов (при выборе из нескольких вариантов), •
//        либо дать текстовую подсказку (заранее прикреплена к вопросу) • либо дать
//        дополнительное время, и т.п. Статистика - отображает статистику после
//        окончания игры. Формат определяется Конфигурацией Конфигурация -
//        управляет настройками системы: время, количество игроков, тип подсказок,
//        количество вопросов, и т.п.
