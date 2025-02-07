package controllers.interfaces;

public interface IUserController {
    String createUser(String name, String surname, String email, String password, String address, String phone);
    String getUserById(int id);
    String getAllUsers();
    String deleteUser(int id);
    String updateUser(int id, String name, String surname);
    boolean validateUser(String email, String password);
    int getUserIdByEmail(String email);
    String getUserRoleById(int userId);
}
