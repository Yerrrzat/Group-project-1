package repositories.interfaces;

import models.User;
import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();
    boolean deleteUser(int id);
    boolean updateUser(int id, String name, String surname);
    User getUserByEmail(String email);
    int getUserIdByEmail(String email);
    String getUserRoleById(int userId);
}
