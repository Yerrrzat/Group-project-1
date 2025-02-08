import controllers.interfaces.IOrderController;
import utils.Validator;
import java.util.Scanner;

public class OrderMenu {
    private final IOrderController orderController;
    private final Scanner scanner;
    private int currentUserId;

    public OrderMenu(IOrderController orderController, Scanner scanner, int currentUserId) {
        this.orderController = orderController;
        this.scanner = scanner;
        this.currentUserId = currentUserId;
    }

    public void showOrderMenu() {
        if (currentUserId == -1) {
            System.out.println("You must be logged in to access this menu.");
            return;
        }

        while (true) {
            System.out.println("\nOrder Menu");
            System.out.println("1. Make an order");
            System.out.println("2. Get full order description");
            System.out.println("0. Go back");
            System.out.print("Enter option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    makeOrder();
                    break;
                case 2:
                    getFullOrderDescription();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void makeOrder() {
        System.out.print("Enter device ID to purchase: ");
        String input = scanner.nextLine();
        if (!Validator.isValidInteger(input)) {
            System.out.println("Invalid input! Device ID must be a number.");
            return;
        }

        int deviceId = Integer.parseInt(input);
        System.out.print("Enter order date (YYYY-MM-DD HH:MM:SS): ");
        String orderDate = scanner.nextLine();
        System.out.print("Enter order status: ");
        String status = scanner.nextLine();

        System.out.print("Enter order total price: ");
        String priceInput = scanner.nextLine();
        if (!Validator.isValidDouble(priceInput)) {
            System.out.println("Invalid price format!");
            return;
        }
        double totalPrice = Double.parseDouble(priceInput);

        System.out.print("Confirm purchase (yes/no): ");
        String confirm = scanner.nextLine();
        if (!confirm.equalsIgnoreCase("yes")) {
            System.out.println("Purchase canceled.");
            return;
        }

        String response = orderController.createOrder(currentUserId, orderDate, status, totalPrice);
        System.out.println(response);
    }

    private void getFullOrderDescription() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.println(orderController.getFullOrderDescription(orderId));
    }
}
