import controllers.DeviceController;
import controllers.UserController;
import controllers.interfaces.IDeviceController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.DeviceRepository;
import repositories.UserRepository;
import repositories.interfaces.IDeviceRepository;
import repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432","postgres","Kundyz2007","postgres");
        IUserRepository repo=new UserRepository(db);
        IUserController controller=new UserController(repo);

        IDeviceRepository repoDevice=new DeviceRepository(db);
        IDeviceController controllerDevice=new DeviceController(repoDevice);
        
        MyApplication app=new MyApplication(controller,controllerDevice);

        app.start();
        db.close();

    }
}