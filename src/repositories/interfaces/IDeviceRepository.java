package repositories.interfaces;

import models.Device;

import java.util.List;

public interface IDeviceRepository {
    boolean createDevice(Device device);
    Device getDeviceById(int id);
    List<Device> getAllDevices();

    double getDevicePriceById(int id);
}
