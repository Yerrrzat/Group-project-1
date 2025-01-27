package controllers.interfaces;

import models.Device;


public interface IDeviceController {
    String createDevice(String name, String description, int category_id, String brand, double price, int stock_quantity, String release_date, String specifications);

    Device getDeviceById(int id);

   String getAllDevices();

    String createDevice(Device device);
}
