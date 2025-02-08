package management;

import controllers.interfaces.IDeviceController;
import java.util.Scanner;

public class DeviceMenu {
    private final IDeviceController deviceController;
    private final Scanner scanner = new Scanner(System.in);

    public DeviceMenu(IDeviceController deviceController) {
        this.deviceController = deviceController;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nDevice Management Menu:");
            System.out.println("1. Get all devices");
            System.out.println("2. Get device by ID");
            System.out.println("3. Create new device");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1 -> getAllDevices();
                case 2 -> getDeviceById();
                case 3 -> createDevice();
                case 4 -> {
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void getAllDevices() {
        String response = deviceController.getAllDevices();
        System.out.println(response);
    }

    private void getDeviceById() {
        System.out.print("Enter device ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        String response = deviceController.getDeviceById(id);
        System.out.println(response);
    }

    private void createDevice() {
        System.out.print("Enter device name: ");
        String name = scanner.nextLine();
        System.out.print("Enter device description: ");
        String description = scanner.nextLine();
        System.out.print("Enter category ID: ");
        int categoryId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter brand: ");
        String brand = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter stock quantity: ");
        int stockQuantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter release date (YYYY-MM-DD): ");
        String releaseDate = scanner.nextLine();
        System.out.print("Enter specifications: ");
        String specifications = scanner.nextLine();

        String response = deviceController.createDevice(name, description, categoryId, brand, price, stockQuantity, releaseDate, specifications);
        System.out.println(response);
    }
}