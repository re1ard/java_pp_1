package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {

    private String sql_create_table = "CREATE TABLE IF NOT EXISTS `users` ( " +
            "`id` BIGINT NOT NULL AUTO_INCREMENT , " +
            "`name` VARCHAR(256) NOT NULL , " +
            "`last_name` VARCHAR(256) NOT NULL , " +
            "`age` INT NOT NULL , PRIMARY KEY (`id`)" +
            ") ENGINE = InnoDB;";
    private String sql_drop_table = "DROP TABLE IF EXISTS `users`;";
    private String sql_clearup_table = "TRUNCATE `users`;";
    private String sql_add_user = "INSERT INTO `users` (`id`, `name`, `last_name`, `age`) VALUES (NULL, '%s', '%s', '%d')";
    private String sql_remove_user_of_id = "DELETE FROM `users` WHERE `users`.`id` = %d;";
    private String sql_get_all_users = "SELECT `id`,`name`,`last_name`,`age` FROM `users`";
    private Util db_utils;

    public UserDaoJDBCImpl() {
        db_utils = new Util();
    }

    public void createUsersTable() {
        try (Connection con = db_utils.getConnection()) {
            con.createStatement().executeQuery(sql_create_table);
        } catch (SQLException sql_e) { sql_e.printStackTrace(); }
    }

    public void dropUsersTable() {
        try (Connection con = db_utils.getConnection()) {
            con.createStatement().executeQuery(sql_drop_table);
        } catch (SQLException sql_e) { sql_e.printStackTrace(); }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection con = db_utils.getConnection()) {
            int count = con.createStatement().executeUpdate(String.format(sql_add_user, name, lastName, Integer.valueOf(age)));
            if (count > 0) {
                System.out.println(String.format("User с именем – %s добавлен в базу данных", name));
            } else {
                System.out.println(String.format("User с именем – %s НЕ БЫЛ добавлен в базу данных", name));
            }
        } catch (SQLException sql_e) { sql_e.printStackTrace(); }
    }

    public void removeUserById(long id) {
        try (Connection con = db_utils.getConnection()) {
            con.createStatement().executeUpdate(String.format(sql_remove_user_of_id, id));
        } catch (SQLException sql_e) { sql_e.printStackTrace(); }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection con = db_utils.getConnection()) {
            ResultSet result = con.createStatement().executeQuery(sql_get_all_users);
            while (result.next()) {
                users.add(new User(result.getString(1), result.getString(2), result.getByte(3)));
            }
        } catch (SQLException sql_e) { sql_e.printStackTrace(); }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection con = db_utils.getConnection()) {
            con.createStatement().executeQuery(sql_clearup_table);
        } catch (SQLException sql_e) { sql_e.printStackTrace(); }
    }
}
