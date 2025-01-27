import controllers.interfaces.IUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController controller;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IUserController controller) {
        this.controller = controller;

    }
    public void start() {
        while (true) {
            mainMenu();
            try{
                int option = scanner.nextInt();
                switch (option) {
                    case 1:getAllUsersMenu();break;
                    case 2:getUserByIdMenu();break;
                    case 3:createUserMenu();break;
                    default: return;
                }

            }catch(InputMismatchException e) {
                System.out.println("Input must be an integer");

            }catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void createUserMenu() {
        System.out.println("Enter user name: ");
        String name = scanner.nextLine();
        System.out.println("Enter user surname: ");
        String surname = scanner.nextLine();
        System.out.println("Enter user email: ");
        String email = scanner.nextLine();
        System.out.println("Enter user password: ");
        String password = scanner.nextLine();
        System.out.println("Enter user address: ");
        String address = scanner.nextLine();
        System.out.println("Enter user phone number: ");
        String phone = scanner.nextLine();

        String response = controller.createUser(name, surname, email, password, address, phone);
        System.out.println(response);

    }
    private void getUserByIdMenu() {
        System.out.println("Enter user id");
        int id= scanner.nextInt();
        String response = controller.getUserById(id);
        System.out.println(response);

    }
    private void getAllUsersMenu() {
        String response = controller.getAllUsers();
        System.out.println(response);

    }
    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select an option (1-3):");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create new user");
        System.out.println("0. Exit");
        System.out.print("Enter option (1-3): ");
    }
}
