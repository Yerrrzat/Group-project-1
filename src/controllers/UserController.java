package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;

import java.util.List;

public class UserController  implements IUserController {
    private final IUserRepository repo;
    public UserController(IUserRepository repo) {

        this.repo=repo;
    }


    @Override
    public String createUser(String name,String surname,String email,String password, String address, String phone) {
        User user=new User(name,surname,email,password,address,phone);

        boolean created = repo.createUser(user);
        return (created?"User created":"User creation failed");
    }

    @Override
    public String getUserById(int id) {
            User user=repo.getUserById(id);
            return (user==null) ? "User not found" : user.toString();

    }

    @Override
    public String getAllUsers() {
        List<User> users=repo.getAllUsers();
        StringBuilder response = new StringBuilder();
        for (User user:users) {
            response.append(user.toString());
        }
        return response.toString();

    }
}
