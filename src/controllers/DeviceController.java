package controllers;

import controllers.interfaces.IDeviceController;
import models.Device;
import repositories.interfaces.IDeviceRepository;

import java.util.List;

public class    DeviceController implements IDeviceController {
    private final IDeviceRepository deviceRepository;
    public DeviceController(IDeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }


    @Override
    public String createDevice(String name, String description, int category_id, String brand, double price, int stock_quantity, String release_date, String specifications) {
        Device device = new Device(name,description,category_id,brand,price,stock_quantity,release_date,specifications);

        boolean created = deviceRepository.createDevice(device);
        return (created ? "Device created" : "Device creation failed");

    }

    @Override
    public String getDeviceById(int id) {
        Device device = deviceRepository.getDeviceById(id);
        return (device == null) ? "Device not found" : device.toString();

    }

    @Override
    public String getAllDevices() {
        List<Device> devices = deviceRepository.getAllDevices();

        return devices.stream()
                .sorted((d1, d2) -> Double.compare(d1.getPrice(), d2.getPrice())) // Sorting by price
                .map(Device::toString)
                .reduce("", (a, b) -> a + b);
    }



    @Override
    public double getDevicePriceById(int deviceId) {
        return deviceRepository.getDevicePriceById(deviceId);
    }

}
