package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * The <code>ConnectorDB</code> class helps to get a connection to database.
 * @autor Eduard Ivanov
 * @since 2020-10-08
 */
public class ConnectorDB {

    /**
     * Create a connection to database using MySQL properties.
     * @throws SQLException if trouble with database connection.
     * @throws ClassNotFoundException if MySQL driver has not found.
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        ResourceBundle resource = ResourceBundle.getBundle("database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String password = resource.getString("db.password");
        String dbName = resource.getString("db.name");
        String driver = resource.getString("db.driver");
        Class.forName(driver);
        return DriverManager.getConnection(url + dbName, user, password);
    }
}

