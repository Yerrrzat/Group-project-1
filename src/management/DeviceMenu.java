package management;

import controllers.interfaces.IDeviceController;
import java.util.Scanner;

public class DeviceMenu {
    private final IDeviceController deviceController;
    private final Scanner scanner;

    public DeviceMenu(IDeviceController deviceController, Scanner scanner) {
        this.deviceController = deviceController;
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nDevice Menu");
            System.out.println("1. See all devices");
            System.out.println("2. Get device by ID");
            System.out.println("0. Go back");
            System.out.print("Enter option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(deviceController.getAllDevices());
                    break;
                case 2:
                    getDeviceById();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void getDeviceById() {
        System.out.print("Enter device ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(deviceController.getDeviceById(id));
    }
}
