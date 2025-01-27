package repositories;

import data.interfaces.IDB;
import models.Brand;
import repositories.interfaces.IBrandRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrandRepository implements IBrandRepository {
    private final IDB db;

    public BrandRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createBrand(Brand brand) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "INSERT INTO brands(name, description, country, established_date) VALUES(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, brand.getName());
            st.setString(2, brand.getDescription());
            st.setString(3, brand.getCountry());
            st.setString(4, brand.getEstablishedDate());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Brand getBrandById(int id) {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM brands WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Brand(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("country"),
                        rs.getString("established_date")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Brand> getAllBrands() {
        Connection conn = null;
        try {
            conn = db.getConnection();
            String sql = "SELECT * FROM brands";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Brand> brands = new ArrayList<>();
            while (rs.next()) {
                Brand brand = new Brand(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("country"),
                        rs.getString("established_date")
                );
                brands.add(brand);
            }
            return brands;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
}
