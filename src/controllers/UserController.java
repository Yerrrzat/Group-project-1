package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createUser(String name, String surname, String email, String password, String address, String phone) {
        User user = new User(name, surname, email, password, address, phone);
        boolean created = repo.createUser(user);
        return (created ? "User created" : "User creation failed");
    }

    @Override
    public String getUserById(int id) {
        User user = repo.getUserById(id);
        return (user == null) ? "User not found" : user.toString();
    }

    @Override
    public String getAllUsers() {
        List<User> users = repo.getAllUsers();
        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String deleteUser(int id) {
        boolean deleted = repo.deleteUser(id);
        return deleted ? "User deleted successfully" : "User deletion failed";
    }

    @Override
    public String updateUser(int id, String name, String surname) {
        boolean updated = repo.updateUser(id, name, surname);
        return updated ? "User updated successfully" : "User update failed";
    }

    @Override
    public boolean validateUser(String email, String password) {
        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            return false; // Invalid input
        }

        User user = repo.getUserByEmail(email);

        if (user == null) {
            return false; // User does not exist
        }

        return user.getPassword().equals(password);
    }

    @Override
    public int getUserIdByEmail(String email) {
        User user = repo.getUserByEmail(email);
        return (user != null) ? user.getId() : -1;
    }

    @Override
    public String getUserRoleById(int userId) {
        return repo.getUserRoleById(userId);
    }
}
