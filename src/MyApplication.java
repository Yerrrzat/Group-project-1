import controllers.interfaces.IBrandController;
import controllers.interfaces.IDeviceController;
import controllers.interfaces.IUserController;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController userController;
    private final IDeviceController deviceController;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IUserController userController, IDeviceController deviceController, IBrandController controllerBrand) {
        this.userController = userController;
        this.deviceController = deviceController;

    }

    public void start() {
        while (true) {
            mainMenu();
            try{
                int option = scanner.nextInt();
                switch (option) {
                    case 1:getAllUsersMenu();break;
                    case 2:getUserByIdMenu();break;
                    case 3:createUserMenu();break;
                    case 4:getAllDeviceMenu();break;
                    case 5:getDeviceByIdMenu();break;
                    case 6:createDeviceMenu();break;



                    default: return;
                }

            }catch(InputMismatchException e) {
                System.out.println("Input must be an integer");

            }catch(Exception e) {
                System.out.println(e.getMessage());
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
        System.out.println("Enter user id");
        int id= scanner.nextInt();
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
        int category_id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter device brand: ");
        String brand = scanner.nextLine();
        System.out.println("Enter device price: ");
        double price = Double.parseDouble(scanner.nextLine());
        System.out.println("Enter device stock quantity: ");
        int stock_quantity = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter device release date: ");
        String release_date = scanner.nextLine();
        System.out.println("Enter device specification: ");
        String specifications= scanner.nextLine();

        String response = deviceController.createDevice(name,description,category_id,brand,price,stock_quantity,release_date,specifications);

        System.out.println(response);

    }
    private void getDeviceByIdMenu() {
        System.out.println("Enter user id");
        int id= scanner.nextInt();
        String response = userController.getUserById(id);
        System.out.println(response);

    }
    private void getAllDeviceMenu() {
        String response = deviceController.getAllDevices();
        System.out.println(response);

    }
    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select an option (1-3):");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by id");
        System.out.println("3. Create new user");
        System.out.println("4. Get all devices");
        System.out.println("5. Get device by id");
        System.out.println("6. Create new device");
        System.out.println("0. Exit");
        System.out.print("Enter option (1-24): ");
    }
}
