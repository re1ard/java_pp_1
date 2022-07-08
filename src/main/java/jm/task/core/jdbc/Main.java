package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService service = new UserServiceImpl();
        service.createUsersTable();
        service.saveUser("Анатолий", "Васерман", (byte) 67);
        service.saveUser("Глад", "Валакас", (byte) 54);
        service.saveUser("Дмитрий", "Носов", (byte) 228);
        service.saveUser("Владимиро", "Путен", (byte) 69);
        for(User user: service.getAllUsers()) {
            System.out.println(user);
        }
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
