import controllers.BrandController;
import controllers.DeviceController;
import controllers.UserController;
import controllers.interfaces.IBrandController;
import controllers.interfaces.IDeviceController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.BrandRepository;
import repositories.DeviceRepository;
import repositories.UserRepository;
import repositories.interfaces.IBrandRepository;
import repositories.interfaces.IDeviceRepository;
import repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432","postgres","123456789","devices_store");
        IUserRepository repo = new UserRepository(db);
        IUserController controller = new UserController(repo);

        IDeviceRepository repoDevice = new DeviceRepository(db);
        IDeviceController controllerDevice = new DeviceController(repoDevice);

        IBrandRepository repoBrand = new BrandRepository(db);
        IBrandController controllerBrand = new BrandController(repoBrand);

        MyApplication app = new MyApplication(controller, controllerDevice, controllerBrand);

        app.start();
        db.close();
    }
}
