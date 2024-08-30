package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Sasha", "Troyanov", (byte) 18);
        userService.saveUser("Lasha", "Troyanov", (byte) 18);
        userService.saveUser("Zasha", "Troyanov", (byte) 18);
        userService.saveUser("Pasha", "Troyanov", (byte) 18);
        List<User> list = userService.getAllUsers();
        list.forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
