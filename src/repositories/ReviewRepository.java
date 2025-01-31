package repositories;

import data.interfaces.IDB;
import repositories.interfaces.IReviewRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class ReviewRepository implements IReviewRepository {
    private final IDB db;

    public ReviewRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createReview(Map<String, Object> reviewData) {
        try (Connection con = db.getConnection()) {
            String sql = "INSERT INTO Reviews (user_id, device_id, rating, comment) VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, (Integer) reviewData.get("user_id"));
            st.setInt(2, (Integer) reviewData.get("device_id"));
            st.setInt(3, (Integer) reviewData.get("rating"));
            st.setString(4, (String) reviewData.get("comment"));
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Map<String, Object>> getAllReviews() {
        List<Map<String, Object>> reviews = new ArrayList<>();
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM Reviews";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Map<String, Object> review = new HashMap<>();
                review.put("id", rs.getInt("id"));
                review.put("user_id", rs.getInt("user_id"));
                review.put("device_id", rs.getInt("device_id"));
                review.put("rating", rs.getInt("rating"));
                review.put("comment", rs.getString("comment"));
                review.put("created_at", rs.getString("created_at"));
                reviews.add(review);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reviews;
    }

    @Override
    public Map<String, Object> getReviewById(int id) {
        try (Connection con = db.getConnection()) {
            String sql = "SELECT * FROM Reviews WHERE id = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Map<String, Object> review = new HashMap<>();
                review.put("id", rs.getInt("id"));
                review.put("user_id", rs.getInt("user_id"));
                review.put("device_id", rs.getInt("device_id"));
                review.put("rating", rs.getInt("rating"));
                review.put("comment", rs.getString("comment"));
                review.put("created_at", rs.getString("created_at"));
                return review;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}