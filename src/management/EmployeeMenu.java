package management;

import controllers.interfaces.IUserController;
import java.util.Scanner;

public class EmployeeMenu {
    private final IUserController userController;
    private final Scanner scanner = new Scanner(System.in);

    public EmployeeMenu(IUserController userController) {
        this.userController = userController;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nUser Management Menu:");
            System.out.println("1. Get all users");
            System.out.println("2. Get user by ID");
            System.out.println("3. Create new user");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> getAllUsers();
                case 2 -> getUserById();
                case 3 -> createUser();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void getAllUsers() {
        String response = userController.getAllUsers();
        System.out.println(response);
    }

    private void getUserById() {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String response = userController.getUserById(id);
        System.out.println(response);
    }

    private void createUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        System.out.print("Enter user password: ");
        String password = scanner.nextLine();
        System.out.print("Enter user address: ");
        String address = scanner.nextLine();
        System.out.print("Enter user phone number: ");
        String phone = scanner.nextLine();

        String response = userController.createUser(name, surname, email, password, address, phone);
        System.out.println(response);
    }
}