package management;
import controllers.interfaces.IOrderController;
import java.util.Scanner;

public class OrderMenu {
    private final IOrderController orderController;
    private final Scanner scanner;

    public OrderMenu(IOrderController orderController, Scanner scanner) {
        this.orderController = orderController;
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nOrder Menu");
            System.out.println("1. Make an order");
            System.out.println("2. Get full order description");
            System.out.println("0. Go back");
            System.out.print("Enter option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> makeOrder();
                case 2 -> getFullOrderDescription();
                case 0 -> { return; }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void makeOrder() {
        System.out.print("Enter device ID: ");
        int deviceId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter order date (YYYY-MM-DD HH:MM:SS): ");
        String orderDate = scanner.nextLine();
        System.out.print("Enter order status: ");
        String status = scanner.nextLine();
        System.out.print("Enter total price: ");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine();

        String response = orderController.createOrder(deviceId, orderDate, status, totalPrice);
        System.out.println(response);
    }

    private void getFullOrderDescription() {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();
        System.out.println(orderController.getFullOrderDescription(orderId));
    }
}
