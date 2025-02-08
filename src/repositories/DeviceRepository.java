package repositories;

import data.interfaces.IDB;
import models.Device;
import repositories.interfaces.IDeviceRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeviceRepository implements IDeviceRepository {
    private final IDB db;
    private List<Device> devices;

    public DeviceRepository(IDB db) {
        this.db = db;

    }

    @Override
    public boolean createDevice(Device device) {
        Connection conn=null;
        try{
            conn=db.getConnection();
            String sql="INSERT INTO devices(name,description,category_id,brand,price,stock_quantity,release_date,specifications) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st= conn.prepareStatement(sql);

            st.setString(1, device.getName());
            st.setString(2, device.getDescription());
            st.setInt(3,device.getCategory_id());
            st.setString(4, device.getBrand());
            st.setDouble(5, device.getPrice());
            st.setInt(6, device.getStock_quantity());
            st.setString(7, device.getRelease_date());
            st.setString(8, device.getSpecifications());

            st.execute();
            return true;
        }catch (SQLException e){
            System.out.println("sql error" + e.getMessage());

        }
        return false;
    }

    @Override
    public Device getDeviceById(int id) {
       Connection conn=null;
       try{
           conn=db.getConnection();
           String sql="SELECT * FROM devices WHERE id=?";
           PreparedStatement st=conn.prepareStatement(sql);

           st.setInt(1,id);
           ResultSet rs=st.executeQuery();
           if(rs.next()){
               return new Device(rs.getInt("id"),
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
       }catch (SQLException e){
           System.out.println("sql error" + e.getMessage());
       }
       return null;
    }

    @Override
    public List<Device> getAllDevices() {
        Connection conn=null;
        try {
            conn=db.getConnection();
            String sql="SELECT * FROM devices";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            List<Device> devices=new ArrayList<>();
            while(rs.next()){
                Device device = new Device(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getInt("category_id"),
                        rs.getString("brand"),
                        rs.getDouble("price"),
                        rs.getInt("stock_quantity"),
                        rs.getString("release_date"),
                        rs.getString("specifications"));
                devices.add(device);
            }
            return devices;
        }catch (SQLException e){
            System.out.println("sql error" + e.getMessage());
        }
        return null;
    }
    @Override
    public double getDevicePriceById(int id) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT price FROM devices WHERE id=?";
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

}



