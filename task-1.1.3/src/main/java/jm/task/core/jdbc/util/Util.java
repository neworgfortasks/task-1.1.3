package jm.task.core.jdbc.util;

import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.Connection;
import java.io.FileInputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    public static Connection getConnection() {
        String dbURL = null;
        String dbUsername = null;
        String dbPassword = null;

        FileInputStream fis;
        Properties properties = new Properties();
        Connection connection = null;

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            properties.load(fis);

            dbURL = properties.getProperty("db.url");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(dbURL, dbUsername, dbPassword);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
