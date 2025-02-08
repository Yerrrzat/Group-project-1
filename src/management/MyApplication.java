package management;

import controllers.interfaces.*;
import management.DeviceMenu;
import management.OrderMenu;
import management.ReviewMenu;
import management.UserMenu;
import management.EmployeeMenu;

import java.util.Scanner;

public class MyApplication {
    private final Scanner scanner = new Scanner(System.in);
    private final UserMenu userMenu;
    private final DeviceMenu deviceMenu;
    private final OrderMenu orderMenu;
    private final ReviewMenu reviewMenu;
    private final EmployeeMenu employeeMenu;

    public MyApplication(IUserController userController, IDeviceController deviceController,
                         IBrandController brandController, ICategoryController categoryController,
                         IOrderController orderController, IOrderItemController orderItemController,
                         IReviewController reviewController) {
        this.userMenu = new UserMenu(userController, scanner);
        this.deviceMenu = new DeviceMenu(deviceController, scanner);
        this.orderMenu = new OrderMenu(orderController, scanner);
        this.reviewMenu = new ReviewMenu(reviewController, scanner, -1);
        this.employeeMenu = new EmployeeMenu(userController, deviceController, reviewController, orderController, scanner);
    }

    public void start() {
        while (true) {
            mainMenu();
            int option = getUserInput();

            switch (option) {
                case 1 -> userMenu.showMenu();
                case 2 -> employeeMenu.showMenu();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void mainMenu() {
        System.out.println("\nWelcome to Device Store 'The Algorithm Avengers'");
        System.out.println("1. For Users");
        System.out.println("2. For Employees");
        System.out.println("0. Exit");
        System.out.print("Enter option: ");
    }

    private int getUserInput() {
        while (true) {
            if (scanner.hasNextInt()) {
                int option = scanner.nextInt();
                scanner.nextLine();
                return option;
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private record EmployeeMenu(IUserController userController, IDeviceController deviceController,
                                IReviewController reviewController, IOrderController orderController, Scanner scanner) {
        public void showMenu() {
        }
    }
}