package management;

import controllers.interfaces.IOrderController;
import controllers.interfaces.IUserController;
import java.util.Scanner;

public class UserMenu {
    private final IUserController userController;
    private final Scanner scanner;
    private int currentUserId = -1;

    public UserMenu(IUserController userController, Scanner scanner) {
        this.userController = userController;
        this.scanner = scanner;
    }

    public UserMenu(Scanner scanner, IUserController userController, IOrderController orderController, IUserController userController1, Scanner scanner1) {

        this.userController = userController1;
        this.scanner = scanner1;
    }

    public void showMenu() {
        System.out.println("\n1. Create Account");
        System.out.println("2. Log In");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            createUserMenu();
        } else if (choice == 2) {
            logIn();
        }
    }

    private void createUserMenu() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        if (!Validator.isValidEmail(email)) {
            System.out.println("Invalid email format!");
            return;
        }
        System.out.print("Enter user password: ");
        String password = scanner.nextLine();
        if (!Validator.isValidPassword(password)) {
            System.out.println("Password must be at least 8 characters, include letters and numbers.");
            return;
        }
        System.out.print("Enter user address: ");
        String address = scanner.nextLine();
        System.out.print("Enter user phone number: ");
        String phone = scanner.nextLine();

        String response = userController.createUser(name, surname, email, password, address, phone);
        System.out.println(response);
    }

    private void logIn() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (!Validator.isValidEmail(email)) {
            System.out.println("Invalid email format!");
            return;
        }

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (!Validator.isValidPassword(password)) {
            System.out.println("Invalid password!");
            return;
        }

        int userId = userController.getUserIdByEmail(email);
        if (userId == -1 || !userController.validateUser(email, password)) {
            System.out.println("Invalid credentials!");
            return;
        }

        currentUserId = userId;
        System.out.println("Login successful! Your user ID: " + currentUserId);
    }
}
