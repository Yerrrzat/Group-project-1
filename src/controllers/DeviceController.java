package controllers;

import controllers.interfaces.IDeviceController;
import models.Device;

import java.util.List;

public class DeviceController implements IDeviceController {
    private final IDeviceController deviceController;
    public DeviceController(IDeviceController deviceController) {
        this.deviceController = deviceController;
    }


    @Override
    public String createDevice(String name, String description, int category_id, String brand, double price, int stock_quantity, String release_date, String specifications) {
        Device device = new Device(name,description,category_id,brand,price,stock_quantity,release_date,specifications);

        boolean created = deviceController.createDevice(device);
        return (created ? "Device created" : "Device creation failed");

    }

    @Override
    public String getDeviceById(int id) {
        Device device = deviceController.getDeviceById(id);
        return (device == null) ? "Device not found" : device.toString();

    }

    @Override
    public String getAllDevices() {
        List<Device> devices = deviceController.getAllDevices();
        StringBuilder sb = new StringBuilder();
        for (Device device : devices) {
            sb.append(device.toString());

        }
        return sb.toString();
    }
}
