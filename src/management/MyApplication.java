package management;

import controllers.interfaces.*;
import strategies.*;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class MyApplication {
    private final IUserController userController;
    private final IDeviceController deviceController;
    private final IBrandController brandController;
    private final ICategoryController categoryController;
    private final IOrderController orderController;
    private final IOrderItemController orderItemController;
    private final IReviewController reviewController;
    private final Scanner scanner = new Scanner(System.in);


    private static final String ADMIN_PASSWORD = "0123456789";
    private int currentUserId = -1;


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
                case 1:
                    userSection();
                    break;
                case 2:
                    employeeSection();
                    break;
                default:
                    return;
            }
        }
    }

    private void mainMenu() {
        System.out.println("\nWelcome to Device Store 'The Algorithm Avengers' ");
        System.out.println("1. Customer");
        System.out.println("2. Admin");
        System.out.println("0. Exit");
        System.out.print("Enter option: ");
    }

    private void createUserMenu() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine();
        System.out.print("Enter user surname: ");
        String surname = scanner.nextLine();
        System.out.print("Enter user email: ");
        String email = scanner.nextLine();
        if (!Validator.isValidEmail(email)) {
            System.out.println(" Invalid email format!");
            return;
        }
        System.out.print("Enter password (Password must be at least 8 characters, include letters and numbers): ");
        String password = scanner.nextLine();
        if (!Validator.isValidPassword(password)) {
            System.out.println(" Password must be at least 8 characters, include letters and numbers.");
            return;
        }
        System.out.print("Enter user address: ");
        String address = scanner.nextLine();
        System.out.print("Enter user phone number: ");
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
            if (!Validator.isValidEmail(email)) {
                System.out.println(" Invalid email format!");
                return;
            }

            System.out.print("Enter password (Password must be at least 8 characters, include letters and numbers): ");
            String password = scanner.nextLine();
            if (!Validator.isValidPassword(password)) {
                System.out.println(" Invalid password!");
                return;
            }

            int userId = userController.getUserIdByEmail(email);
            if (userId == -1 || !userController.validateUser(email, password)) {
                System.out.println(" Invalid credentials!");
                return;
            }

            currentUserId = userId;
            System.out.println(" Login successful! Your user ID: " + currentUserId);
            userPurchaseMenu();
        }
    }

    private void createReviewMenu(int deviceId) {
        if (currentUserId == -1) {
            System.out.println("You must be logged in to leave a review.");
            return;
        }

        System.out.println("Enter device id : ");
        int device_id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter rating (1-5): ");
        int rating = Integer.parseInt(scanner.nextLine());
        System.out.println("Share with comments: ");
        String comment = scanner.nextLine();

        String response = reviewController.createReview(currentUserId, device_id, rating, comment);
        System.out.println(response);
    }

    private void userPurchaseMenu() {
        if (currentUserId == -1) {
            System.out.println("You must be logged in to access this menu.");
            return;
        }

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

    private void makeOrder() {
        if (currentUserId == -1) {
            System.out.println("You must be logged in to place an order.");
            return;
        }

        System.out.print("Enter device ID to purchase: ");
        String input = scanner.nextLine();
        if (!Validator.isValidInteger(input)) {
            System.out.println("Invalid input! Device ID must be a number.");
            return;
        }

        int deviceId = Integer.parseInt(input);
        double devicePrice = deviceController.getDevicePriceById(deviceId);
        if (devicePrice == -1) {
            System.out.println("Invalid device ID. Please try again.");
            return;
        }

        System.out.println("Choose payment method:");
        System.out.println("1. Cash");
        System.out.println("2. Credit Card");
        System.out.println("3. PayPal");
        System.out.print("Enter option: ");
        int paymentChoice = scanner.nextInt();
        scanner.nextLine();

        PaymentContext paymentContext = new PaymentContext();
        Map<Integer, Supplier<PaymentStrategy>> paymentStrategies = Map.of(
                1, CashPayment::new,
                2, CreditCardPayment::new,
                3, PayPalPayment::new
        );

        paymentContext.setPaymentStrategy(paymentStrategies.getOrDefault(paymentChoice, () -> {
            System.out.println("Invalid choice! Order canceled.");
            return null;
        }).get());

        System.out.print("Confirm purchase (yes/no): ");
        String confirm = scanner.nextLine();
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Purchase canceled.");
            return;
        }

        String response = orderController.createOrder(currentUserId, "2025-02-08 08:20:00", "Pending", devicePrice);
        System.out.println(response);

        paymentContext.executePayment(devicePrice);

        System.out.println("Purchase successful! Thank you.");

        System.out.print("Would you like to leave a review for this product? (yes/no): ");
        String reviewResponse = scanner.nextLine();

        if (reviewResponse.equalsIgnoreCase("yes")) {
            createReviewMenu(deviceId);
        } else {
            System.out.println("Thank you for your purchase!");
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


    private void getFullOrderDescriptionMenu() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.println(orderController.getFullOrderDescription(orderId));
    }

    private void getAllUsersMenu() {
        String response = userController.getAllUsers();
        System.out.println(response);
    }


    private void employeeSection() {
        System.out.print("Enter company password: ");
        String password = scanner.nextLine();
        if (!password.equals(ADMIN_PASSWORD)) {
            System.out.println("Incorrect password.");
            return;
        }

        while (true) {
            System.out.println("\nAdmin Panel");
            System.out.println("1. Get all users");
            System.out.println("2. Get user by ID");
            System.out.println("3. Get all devices");
            System.out.println("4. Get device by ID");
            System.out.println("5. Get all reviews");
            System.out.println("6. Get all orders");
            System.out.println("7. Delete user");
            System.out.println("8. Upgrade user info");
            System.out.println("9. Get full order description");
            System.out.println("0. Exit");
            System.out.print("Enter option: ");
            int option = scanner.nextInt();
            scanner.nextLine();


            Map<Integer, Runnable> adminActions = Map.of(
                    1, () -> System.out.println(userController.getAllUsers()),
                    2, this::getUserByIdMenu,
                    3, () -> System.out.println(deviceController.getAllDevices()),
                    4, this::getDeviceByIdMenu,
                    5, () -> System.out.println(reviewController.getAllReviews()),
                    6, () -> System.out.println(orderController.getAllOrders()),
                    7, this::deleteUserMenu,
                    8, this::updateUserMenu,
                    9, this::getFullOrderDescriptionMenu,
                    0, () -> {
                        System.out.println("Exiting Employee Panel...");
                        return;
                    } // Case 0: Exit
            );


            adminActions.getOrDefault(option, () -> System.out.println("Invalid option. Try again.")).run();

            if (option == 0) {
                return;
            }
        }
    }
}
