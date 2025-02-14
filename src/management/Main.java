package management;

import controllers.*;
import controllers.interfaces.*;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.*;
import repositories.interfaces.*;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432","postgres","Kundyz2007","postgres");

        IUserRepository repo = new UserRepository(db);
        IUserController controller = new UserController(repo);

        IDeviceRepository repoDevice = new DeviceRepository(db);
        IDeviceController controllerDevice = new DeviceController(repoDevice);

        IBrandRepository repoBrand = new BrandRepository(db);
        IBrandController controllerBrand = new BrandController(repoBrand);

        ICategoryRepository repoCategory = new CategoryRepository(db);
        ICategoryController controllerCategory = new CategoryController(repoCategory);

        IOrderRepository orderRepo = new OrderRepository(db);
        IOrderController controllerOrder = new OrderController(orderRepo);

        IOrderItemRepository orderItemRepo = new OrderItemRepository(db);
        IOrderItemController controllerOrderItem = new OrderItemController(orderItemRepo);


        IReviewRepository reviewRepo = new ReviewRepository(db);
        IReviewController reviewController = new ReviewController(reviewRepo);




        MyApplication app = new MyApplication(controller, controllerDevice, controllerBrand, controllerCategory, controllerOrder, controllerOrderItem, reviewController);

        app.start();
        db.close();
    }
}
