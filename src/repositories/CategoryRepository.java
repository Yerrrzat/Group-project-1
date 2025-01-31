package repositories;

import data.interfaces.IDB;
import models.Category;
import repositories.interfaces.ICategoryRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements ICategoryRepository {
    private final IDB db;

    public CategoryRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createCategory(Category category) {
        try (Connection conn = db.getConnection()) {
            String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
            PreparedStatement st = conn.prepareStatement(sql);

            st.setString(1, category.getName());
            st.setString(2, category.getDescription());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Category getCategoryById(int id) {
        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM categories WHERE id = ?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();

        try (Connection conn = db.getConnection()) {
            String sql = "SELECT * FROM categories";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Category category = new Category(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("description")
                );
                categories.add(category);
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }

        return categories;
    }
}
