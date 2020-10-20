package org.example.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Repository {

    private static final Logger LOG = LoggerFactory.getLogger(Repository.class);

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "12345678";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/weather_db";

    private Connection connection;

    public void printWeatherInfo() {
        String sql = "SELECT * FROM weather_table ORDER BY id DESC LIMIT 1";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
            LOG.info("Got connection to DB for getting data.");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next())
                System.out.println("Welcome to the weather forecast!" +
                        "\nToday is: " + resultSet.getString(2) +
                        "\nTime now: " + resultSet.getString(3) +
                        "\nTemperature: " + resultSet.getString(4));
            LOG.info("The weather was printed from DB.");
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        }

    }

    public void insertWeatherInfo(String date, String time, String temperature) {
        String sql = "INSERT INTO weather_table (date, time, temperature) VALUES (?, ?, ?)";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(CONNECTION_URL, USER, PASSWORD);
            LOG.info("Got connection to DB for insert data.");
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, date);
            preparedStatement.setString(2, time);
            preparedStatement.setString(3, temperature);
            preparedStatement.executeUpdate();
            LOG.info("The weather info was added to the DB.");
            LOG.info("Transferred data to DB: {} {} {}", date, time, temperature);
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            LOG.error(e.getMessage(), e);
        }
    }
}