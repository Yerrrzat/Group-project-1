package repositories;

import data.interfaces.IDB;
import models.Device;
import repositories.interfaces.IDeviceRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DeviceRepository implements IDeviceRepository {
    private final IDB db;

    public DeviceRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createDevice(Device device) {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO devices(name, description, category_id, brand, price, stock_quantity, release_date, specifications) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, device.getName());
            st.setString(2, device.getDescription());
            st.setInt(3, device.getCategory_id());
            st.setString(4, device.getBrand());
            st.setDouble(5, device.getPrice());
            st.setInt(6, device.getStock_quantity());
            st.setString(7, device.getRelease_date());
            st.setString(8, device.getSpecifications());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Device getDeviceById(int id) {
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM devices WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Device(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("release_date"),
                        rs.getString("specifications")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Device> getAllDevices() {
        List<Device> devices = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM devices")) {

            while (rs.next()) {
                devices.add(new Device(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("release_date"),
                        rs.getString("specifications")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return devices;
    }

    @Override
    public double getDevicePriceById(int id) {
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT price FROM devices WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return rs.getDouble("price");
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return -1;
    }


    public List<Device> getDevicesSortedByPrice() {
        List<Device> devices = getAllDevices();
        return devices.stream()
                .sorted(Comparator.comparingDouble(Device::getPrice))
                .collect(Collectors.toList());
    }
}