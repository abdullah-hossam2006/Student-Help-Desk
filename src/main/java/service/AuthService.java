package service;

import model.Admin;
import model.Student;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private final Map<String, User> users = new HashMap<>();

    public AuthService() {
        users.put("admin", new Admin("admin", "123"));
    }

    public synchronized String register(String role, String username, String password) {
        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            return "ERROR|Please fill all fields";
        }

        if (users.containsKey(username)) {
            return "ERROR|Username already exists";
        }

        if (role.equalsIgnoreCase("ADMIN")) {
            users.put(username, new Admin(username, password));
        } else {
            users.put(username, new Student(username, password));
        }

        return "SUCCESS|Account created successfully";
    }

    public synchronized String login(String username, String password) {
        if (username == null || password == null || username.isBlank() || password.isBlank()) {
            return "ERROR|Please fill all fields";
        }

        User user = users.get(username);

        if (user == null || !user.getPassword().equals(password)) {
            return "ERROR|Invalid username or password";
        }

        return "SUCCESS|" + user.getRole();
    }
}