import controllers.interfaces.IBrandController;
import controllers.interfaces.IDeviceController;
import controllers.interfaces.IUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController userController;
    private final IDeviceController deviceController;
    private final IBrandController brandController;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IUserController userController, IDeviceController deviceController, IBrandController brandController) {
        this.userController = userController;
        this.deviceController = deviceController;
        this.brandController = brandController;
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
                    case 7: getAllBrandsMenu(); break;
                    case 8: getBrandByIdMenu(); break;
                    case 9: createBrandMenu(); break;
                    default: return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Input must be an integer.");
                scanner.next();
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
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

        String response = userController.createUser(name, surname, email, password, address, phone);
        System.out.println(response);
    }

    private void getUserByIdMenu() {
        System.out.println("Enter user id: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(userController.getUserById(id));
    }

    private void getAllUsersMenu() {
        System.out.println(userController.getAllUsers());
    }

    private void createDeviceMenu() {
        System.out.println("Enter device name: ");
        String name = scanner.nextLine();
        System.out.println("Enter device description: ");
        String description = scanner.nextLine();
        System.out.println("Enter device category ID: ");
        int categoryId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter device brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter device price: ");
        double price = scanner.nextDouble();
        System.out.println("Enter device stock quantity: ");
        int stockQuantity = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter device release date: ");
        String releaseDate = scanner.nextLine();
        System.out.println("Enter device specifications: ");
        String specifications = scanner.nextLine();

        String response = deviceController.createDevice(name, description, categoryId, brand, price, stockQuantity, releaseDate, specifications);
        System.out.println(response);
    }

    private void getDeviceByIdMenu() {
        System.out.println("Enter device ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(deviceController.getDeviceById(id));
    }

    private void getAllDeviceMenu() {
        System.out.println(deviceController.getAllDevices());
    }

    // Brand Management Menus
    private void createBrandMenu() {
        System.out.println("Enter brand name: ");
        String name = scanner.nextLine();
        System.out.println("Enter brand description: ");
        String description = scanner.nextLine();

        String response = brandController.createBrand(name, description);
        System.out.println(response);
    }

    private void getBrandByIdMenu() {
        System.out.println("Enter brand ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println(brandController.getBrandById(id));
    }

    private void getAllBrandsMenu() {
        System.out.println(brandController.getAllBrands());
    }

    private void mainMenu() {
        System.out.println("\nWelcome to My Application");
        System.out.println("Select an option:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by ID");
        System.out.println("3. Create new user");
        System.out.println("4. Get all devices");
        System.out.println("5. Get device by ID");
        System.out.println("6. Create new device");
        System.out.println("7. Get all brands");
        System.out.println("8. Get brand by ID");
        System.out.println("9. Create new brand");
        System.out.println("0. Exit");
        System.out.print("Enter option: ");
    }
}
