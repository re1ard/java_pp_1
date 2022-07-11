package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.hibernate.cfg.Configuration;

import javax.persistence.Entity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


public class Util {
    // реализуйте настройку соеденения с БД

    public Connection getConnection() throws SQLException{
        return getConnection("192.168.3.3", 3306, "java_pp_1", "java", "java2281488");
    }
    public Connection getConnection(String host, int port, String db, String user, String password) throws SQLException {
        String ConnectUrl = String.format("jdbc:mysql://%s:%d/%s", host, port, db);
        return DriverManager.getConnection(ConnectUrl, user, password);
    }

    public SessionFactory getFactory() {
        return new Configuration().addAnnotatedClass(User.class).buildSessionFactory();
    }
}
