package repositories;

import data.interfaces.IDB;
import models.Review;
import repositories.interfaces.IReviewRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements IReviewRepository {
    private final IDB db;

    public ReviewRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createReview(Review review) {
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement("INSERT INTO Reviews (user_id, device_id, rating, comment) VALUES (?, ?, ?, ?)")) {
            st.setInt(1, review.getUserId());
            st.setInt(2, review.getDeviceId());
            st.setInt(3, review.getRating());
            st.setString(4, review.getComment());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Review getReviewById(int id) {
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement("SELECT * FROM Reviews WHERE id = ?")) {
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Review(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("device_id"), rs.getInt("rating"), rs.getString("comment"));
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        try (Connection con = db.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM Reviews")) {
            while (rs.next()) {
                reviews.add(new Review(rs.getInt("id"), rs.getInt("user_id"), rs.getInt("device_id"), rs.getInt("rating"), rs.getString("comment")));
            }
        } catch (SQLException e) {
            return null;
        }
        return reviews;
    }
}
