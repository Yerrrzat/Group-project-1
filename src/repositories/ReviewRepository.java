package repositories;

import data.interfaces.IDB;
import models.Review; // Убедитесь, что класс Review импортирован
import repositories.interfaces.IReviewRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements IReviewRepository { // Убрали дженерик
    private final IDB db;

    public ReviewRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createReview(Review review) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO Reviews (user_id, device_id, rating, comment) VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, review.getUserId()); // Используем геттеры
            st.setInt(2, review.getDeviceId());
            st.setInt(3, review.getRating());
            st.setString(4, review.getComment());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM Reviews";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("device_id"),
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getString("created_at") // Добавьте это поле, если оно есть в таблице
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public Review getReviewById(int id) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM Reviews WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Review(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("device_id"),
                        rs.getInt("rating"),
                        rs.getString("comment"),
                        rs.getString("created_at") // Добавьте это поле, если оно есть в таблице
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}