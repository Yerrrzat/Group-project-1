package controllers.interfaces;

import models.User;

public interface IUserController {
    String createUser(String name, String surname, String email, String password,String address,String phone);
    String getUserById(int id);
    String getAllUsers();



}
