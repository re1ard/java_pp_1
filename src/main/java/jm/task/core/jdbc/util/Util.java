package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД

    public Connection getConnection() throws SQLException{
        return getConnection("192.168.3.3", 3306, "java_pp_1", "java", "java2281488");
    }
    public Connection getConnection(String host, int port, String db, String user, String password) throws SQLException {
        String ConnectUrl = String.format("jdbc:mysql://%s:%d/%s", host, port, db);
        return DriverManager.getConnection(ConnectUrl, user, password);
    }
}
