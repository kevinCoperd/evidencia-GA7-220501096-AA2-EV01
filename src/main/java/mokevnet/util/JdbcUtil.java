package com.mokevnet.proyecto.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class JdbcUtil {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try (InputStream in = JdbcUtil.class.getResourceAsStream("/application.properties")) {
            Properties p = new Properties();
            p.load(in);
            url = p.getProperty("db.url");
            user = p.getProperty("db.user");
            password = p.getProperty("db.password");
            driver = p.getProperty("db.driver");
            Class.forName(driver);
        } catch (Exception e) {
            throw new ExceptionInInitializerError("Error configurando conexión: " + e.getMessage());
        }
    }

    private JdbcUtil() {}

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
