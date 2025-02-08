package management;

import java.util.Scanner;
import controllers.interfaces.*;

public class EmployeeMenu {
    private final IUserController userController;
    private final IDeviceController deviceController;
    private final IReviewController reviewController;
    private final IOrderController orderController;
    private final Scanner scanner;
    private static final String EMPLOYEE_PASSWORD = "0123456789";

    public EmployeeMenu(IUserController userController, IDeviceController deviceController,
                        IReviewController reviewController, IOrderController orderController, Scanner scanner) {
        this.userController = userController;
        this.deviceController = deviceController;
        this.reviewController = reviewController;
        this.orderController = orderController;
        this.scanner = scanner;
    }

    public void startEmployeeSection() {
        System.out.print("Enter company password: ");
        String password = scanner.nextLine();
        if (!password.equals(EMPLOYEE_PASSWORD)) {
            System.out.println("Incorrect password.");
            return;
        }

        while (true) {
            System.out.println("\nEmployee Panel");
            System.out.println("1. Get all users");
            System.out.println("2. Get user by ID");
            System.out.println("3. Get all devices");
            System.out.println("4. Get device by ID");
            System.out.println("5. Get all reviews");
            System.out.println("6. Get all orders");
            System.out.println("7. Delete user");
            System.out.println("8. Update user info");
            System.out.println("9. Get full order description");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> System.out.println(userController.getAllUsers());
                case 2 -> getUserByIdMenu();
                case 3 -> System.out.println(deviceController.getAllDevices());
                case 4 -> getDeviceByIdMenu();
                case 5 -> System.out.println(reviewController.getAllReviews());
                case 6 -> System.out.println(orderController.getAllOrders());
                case 7 -> deleteUserMenu();
                case 8 -> updateUserMenu();
                case 9 -> getFullOrderDescriptionMenu();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void getUserByIdMenu() {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(userController.getUserById(id));
    }

    private void getDeviceByIdMenu() {
        System.out.print("Enter device ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(deviceController.getDeviceById(id));
    }

    private void deleteUserMenu() {
        System.out.print("Enter user ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(userController.deleteUser(id));
    }

    private void updateUserMenu() {
        System.out.print("Enter user ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new surname: ");
        String surname = scanner.nextLine();
        System.out.println(userController.updateUser(id, name, surname));
    }

    private void getFullOrderDescriptionMenu() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.println(orderController.getFullOrderDescription(orderId));
    }
}
