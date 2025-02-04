import controllers.interfaces.*;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController userController;
    private final IDeviceController deviceController;
    private final IBrandController brandController;
    private final ICategoryController categoryController;
    private final IOrderController orderController;
    private final IOrderItemController orderItemController;
    private final IReviewController reviewController;
    private final Scanner scanner = new Scanner(System.in);

    private static final String EMPLOYEE_PASSWORD = "0123456789";

    public MyApplication(IUserController userController, IDeviceController deviceController, IBrandController brandController,
                         ICategoryController categoryController, IOrderController orderController, IOrderItemController orderItemController, IReviewController reviewController) {
        this.userController = userController;
        this.deviceController = deviceController;
        this.brandController = brandController;
        this.categoryController = categoryController;
        this.orderController = orderController;
        this.orderItemController = orderItemController;
        this.reviewController = reviewController;
    }

    public void start() {
        while (true) {
            mainMenu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: userSection(); break;
                case 2: employeeSection(); break;
                default: return;
            }
        }
    }

    private void mainMenu() {
        System.out.println("\nWelcome to Device Store 'The Algorithm Avengers' ");
        System.out.println("1. For Users");
        System.out.println("2. For Employees");
        System.out.println("0. Exit");
        System.out.print("Enter option: ");
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

        String response = userController.createUser(name, surname, email, password, address, phone);
        System.out.println(response);
    }


    private void userSection() {
        System.out.println("\n1. Create Account");
        System.out.println("2. Log In");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            createUserMenu();
        } else if (choice == 2) {
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (userController.validateUser(email, password)) {
                System.out.println("Login successful!");
                userPurchaseMenu();
            } else {
                System.out.println("Invalid credentials!");
            }
        }
    }
    private void createReviewMenu() {
        System.out.println("Enter user id : ");
        int user_id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter device id : ");
        int device_id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter rating: ");
        int rating = Integer.parseInt(scanner.nextLine());
        System.out.println("Share with comments: ");
        String comment = scanner.nextLine();


        String response = reviewController.createReview(user_id,device_id, rating,comment);

        System.out.println(response);
    }

    private void userPurchaseMenu() {
        while (true) {
            System.out.println("\n1. See all categories");
            System.out.println("2. See all brands");
            System.out.println("3. See all devices");
            System.out.println("4. Make an order");
            System.out.println("0. Go back");
            System.out.print("Enter option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(categoryController.getAllCategories());
                    break;
                case 2:
                    System.out.println(brandController.getAllBrands());
                    break;
                case 3:
                    System.out.println(deviceController.getAllDevices());
                    break;
                case 4:
                    makeOrder();
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void makeOrder() {
        System.out.print("Enter device ID to purchase: ");
        int deviceId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Confirm purchase (yes/no): ");
        String confirm = scanner.nextLine();

        if (confirm.equalsIgnoreCase("yes")) {
            // Get the current date and time
            LocalDateTime orderDate = LocalDateTime.now(); // Correct variable name

            // Assuming createOrder accepts (deviceId, orderDate, status, price)
            orderController.createOrder(deviceId, String.valueOf(orderDate), "Pending", 1099.99);

            System.out.println("Purchase successful! Thank you.");
            System.out.print("Would you like to leave a review? (yes/no): ");
            if (scanner.nextLine().equalsIgnoreCase("yes")) {
                createReviewMenu();
            }
        } else {
            System.out.println("Purchase canceled.");
        }
    }


    private void getDeviceByIdMenu() {
        System.out.println("Enter device id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String response = deviceController.getDeviceById(id);
        System.out.println(response);
    }
    private void getUserByIdMenu() {
        System.out.println("Enter user id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String response = userController.getUserById(id);
        System.out.println(response);
    }




    private void employeeSection() {
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
            System.out.println("8. Upgrade user info");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1: System.out.println(userController.getAllUsers()); break;
                case 2: getUserByIdMenu(); break;
                case 3: System.out.println(deviceController.getAllDevices()); break;
                case 4: getDeviceByIdMenu(); break;
                case 5: System.out.println(reviewController.getAllReviews()); break;
                case 6: System.out.println(orderController.getAllOrders()); break;
                case 7: deleteUserMenu(); break;
                case 8: updateUserMenu(); break;
                case 0: return;
            }
        }
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
}
