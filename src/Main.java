import controllers.*;
import controllers.interfaces.*;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.*;
import repositories.interfaces.*;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "123456789", "devices_store");

        IUserRepository userRepo = new UserRepository(db);
        IUserController userController = new UserController(userRepo);

        IDeviceRepository deviceRepo = new DeviceRepository(db);
        IDeviceController deviceController = new DeviceController(deviceRepo);

        IBrandRepository brandRepo = new BrandRepository(db);
        IBrandController brandController = new BrandController(brandRepo);

        ICategoryRepository categoryRepo = new CategoryRepository(db);
        ICategoryController categoryController = new CategoryController(categoryRepo);

        IOrderRepository orderRepo = new OrderRepository(db);
        IOrderController orderController = new OrderController(orderRepo);

        IOrderItemRepository orderItemRepo = new OrderItemRepository(db);
        IOrderItemController orderItemController = new OrderItemController(orderItemRepo);

        IReturnRepository returnRepo = new ReturnRepository (db) {};
        IReturnController returnController = new ReturnController(returnRepo);

        IReviewRepository reviewRepo = new ReviewRepository(db) {};
        IReviewController reviewController = new ReviewController(reviewRepo);

        MyApplication app = new MyApplication(userController, deviceController, brandController, categoryController, orderController, orderItemController, returnController, reviewController
        );

        app.start();

        db.close();
    }
}