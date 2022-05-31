package pis.lab2.connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String DATASOURCE_NAME = "jdbc/Lab2";
    private static DataSource ds = null;

    private static ConnectionFactory instance;

    private ConnectionFactory() throws NamingException {
        Context initContext = null;

        initContext = new InitialContext();
        Context envContext = (Context)initContext.lookup("java:/comp/env");
        this.ds = (DataSource) envContext.lookup(DATASOURCE_NAME);


    }

    public static ConnectionFactory getInstance() throws SQLException, NamingException {
        if (instance == null) {
            instance = new ConnectionFactory();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        Connection conn = ds.getConnection();
        return conn;
    }
}
