package controllers.interfaces;

import models.Device;

public interface IDeviceController {
    String createDevice(String name, String description, int category_id, String brand, double price, int stock_quantity, String release_date, String specifications);
    String getDeviceById(int id);
    String getAllDevices();


    double getDevicePriceById(int deviceId);
}
