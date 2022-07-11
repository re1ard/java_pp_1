package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    //UserDao db = new UserDaoJDBCImpl();
    UserDao db = new UserDaoHibernateImpl();
    public void createUsersTable() {
        System.out.println("createUsersTable");
        db.createUsersTable();
    }

    public void dropUsersTable() {
        System.out.println("dropUsersTable");
        db.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        System.out.println("saveUser");
        db.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        System.out.println("removeUserById");
        db.removeUserById(id);
    }

    public List<User> getAllUsers() {
        System.out.println("getAllUsers");
        return db.getAllUsers();
    }
    public void cleanUsersTable() {
        System.out.println("cleanUsersTable");
        db.cleanUsersTable();
    }
}
