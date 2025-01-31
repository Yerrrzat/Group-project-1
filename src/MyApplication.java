import controllers.interfaces.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController userController;
    private final IDeviceController deviceController;
    private final IBrandController brandController;
    private final ICategoryController categoryController;
    private final IOrderController orderController;
    private final IOrderItemController orderItemController;
    private final IReviewController reviewcontroller;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IUserController userController, IDeviceController deviceController, IBrandController brandController,
                         ICategoryController categoryController, IOrderController orderController, IOrderItemController orderItemController, IReviewController reviewController) {
        this.userController = userController;
        this.deviceController = deviceController;
        this.brandController = brandController;
        this.categoryController = categoryController;
        this.orderController = orderController;
        this.orderItemController = orderItemController;
        this.reviewcontroller = reviewController;
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1: getAllUsersMenu(); break;
                    case 2: getUserByIdMenu(); break;
                    case 3: createUserMenu(); break;
                    case 4: getAllDeviceMenu(); break;
                    case 5: getDeviceByIdMenu(); break;
                    case 6: createDeviceMenu(); break;
                    case 7: getAllCategoriesMenu(); break;
                    case 8: getCategoryByIdMenu(); break;
                    case 9: createCategoryMenu(); break;
                    case 10: createOrderMenu(); break;
                    case 11: getOrderByIdMenu(); break;
                    case 12: getAllOrdersMenu(); break;
                    case 13: createOrderItemMenu(); break;
                    case 14: getOrderItemsByOrderIdMenu(); break;
                    case 15: getAllReviewMenu(); break;
                    case 16: getRevewByIdMenu(); break;
                    case 17: createReviewMenu(); break;

                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void getRevewByIdMenu() {
        System.out.println("Enter Review id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String response = reviewcontroller.getReviewById(id);
        System.out.println(response);
    }

    private void getAllReviewMenu() {
        String response = reviewcontroller.getAllReviews();
        System.out.println(response);
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


        String response = reviewcontroller.createReview(user_id,device_id, rating,comment);

        System.out.println(response);
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

    private void getUserByIdMenu() {
        System.out.println("Enter user id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String response = userController.getUserById(id);
        System.out.println(response);
    }

    private void getAllUsersMenu() {
        String response = userController.getAllUsers();
        System.out.println(response);
    }

    private void createDeviceMenu() {
        System.out.println("Enter device name: ");
        String name = scanner.nextLine();
        System.out.println("Enter device description: ");
        String description = scanner.nextLine();
        System.out.println("Enter device category_id: ");
        int categoryId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter device brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter device price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter device stock quantity: ");
        int stockQuantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter device release date: ");
        String releaseDate = scanner.nextLine();
        System.out.println("Enter device specification: ");
        String specifications = scanner.nextLine();

        String response = deviceController.createDevice(name, description, categoryId, brand, price, stockQuantity, releaseDate, specifications);
        System.out.println(response);
    }

    private void getDeviceByIdMenu() {
        System.out.println("Enter device id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String response = deviceController.getDeviceById(id);
        System.out.println(response);
    }

    private void getAllDeviceMenu() {
        String response = deviceController.getAllDevices();
        System.out.println(response);
    }


    private void createCategoryMenu() {
        System.out.println("Enter category name: ");
        String name = scanner.nextLine();
        System.out.println("Enter category description: ");
        String description = scanner.nextLine();

        String response = categoryController.createCategory(name, description);
        System.out.println(response);
    }

    private void getCategoryByIdMenu() {
        System.out.println("Enter category id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String response = categoryController.getCategoryById(id);
        System.out.println(response);
    }

    private void getAllCategoriesMenu() {
        String response = categoryController.getAllCategories();
        System.out.println(response);
    }

    private void createOrderMenu() {
        System.out.println("Enter user id: ");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter order status: ");
        String status = scanner.nextLine();

        String response = orderController.createOrder(userId, status);
        System.out.println(response);
    }

    private void getOrderByIdMenu() {
        System.out.println("Enter order id: ");
        int id = Integer.parseInt(scanner.nextLine());
        String response = orderController.getOrderById(id);
        System.out.println(response);
    }

    private void getAllOrdersMenu() {
        String response = orderController.getAllOrders();
        System.out.println(response);
    }

    private void createOrderItemMenu() {
        System.out.println("Enter order id: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter product id: ");
        int productId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter quantity: ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter price: ");
        int price = Integer.parseInt(scanner.nextLine());

        String response = orderItemController.createOrderItem(orderId, productId, quantity, price);
        System.out.println(response);
    }

    private void getOrderItemsByOrderIdMenu() {
        System.out.println("Enter order id: ");
        int orderId = Integer.parseInt(scanner.nextLine());
        String response = orderItemController.getOrderItemsByOrderId(orderId);
        System.out.println(response);
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select an option:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create new user");
        System.out.println("4. Get all devices");
        System.out.println("5. Get device by id");
        System.out.println("6. Create new device");
        System.out.println("7. Get all categories");
        System.out.println("8. Get category by id");
        System.out.println("9. Create new category");
        System.out.println("10. Create new order");
        System.out.println("11. Get order by id");
        System.out.println("12. Get all orders");
        System.out.println("13. Create new order item");
        System.out.println("14. Get order items by order id");
        System.out.println("15. Get all reviews");
        System.out.println("16. Get review by id");
        System.out.println("17. Create new review");
        System.out.println("0. Exit");
        System.out.print("Enter option: ");

    }
}