package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    SessionFactory SF = null;
    public UserDaoHibernateImpl() {
        SF = new Util().getFactory();
    }


    @Override
    public void createUsersTable() {
        try (Session session = SF.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("CREATE TABLE IF NOT EXISTS `users` ( " +
                    "`id` BIGINT NOT NULL AUTO_INCREMENT , " +
                    "`name` VARCHAR(256) NOT NULL , " +
                    "`last_name` VARCHAR(256) NOT NULL , " +
                    "`age` INT NOT NULL , PRIMARY KEY (`id`)" +
                    ") ENGINE = InnoDB;");
            session.getTransaction().commit();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = SF.getCurrentSession()) {
            session.beginTransaction();
            session.createNativeQuery("DROP TABLE IF EXISTS `users`;");
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = SF.getCurrentSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
            System.out.println(String.format("User с именем – %s добавлен в базу данных", name));
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = SF.getCurrentSession()) {
            session.beginTransaction();
            session.remove(session.get(User.class, id = id));
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = null;
        try (Session session = SF.getCurrentSession()) {
            session.beginTransaction();
            users = session.createQuery("SELECT i FROM User i", User.class).getResultList();
            session.getTransaction().commit();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = SF.getCurrentSession()) {
            session.beginTransaction();
            //session.createQuery("SELECT i FROM User i", User.class).getResultList().stream().forEach(session :: remove);
            session.createQuery("delete User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
