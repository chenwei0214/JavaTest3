package com.hand.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtils {

    private static String USERNAME;

    private static String PASSWORD;

    private static String URL;

    private static String DRIVER;


    static {
        Properties properties = new Properties();
        InputStream in = DBUtils.class.getClassLoader()
                .getResourceAsStream("db.properties");
        try {
            properties.load(in);
            DRIVER = properties.getProperty("jdbc.driver");
            URL = properties.getProperty("jdbc.url");
            USERNAME = properties.getProperty("jdbc.username");
            PASSWORD = properties.getProperty("jdbc.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
