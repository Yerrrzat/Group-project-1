package repositories;

import data.interfaces.IDB;
import models.Review;
import repositories.interfaces.IReviewRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements IReviewRepository {
    private final IDB db;
    private List<Review> reviews;
    private Review review;

    public ReviewRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createReview(Review review) {
        try (Connection con = db.getConnection();
             PreparedStatement st = con.prepareStatement("INSERT INTO Reviews (user_id, device_id, rating, comment) VALUES (?, ?, ?, ?)")) {
            st.setInt(1, review.getUser_id());
            st.setInt(2, review.getDevice_id());
            st.setInt(3, review.getRating());
            st.setString(4, review.getComment());
            st.execute();
            return true;

        } catch (SQLException e) {
            System.out.println("sql error" + e.getMessage());
        }
        return false;
    }

    @Override
    public Review getReviewById(int id) {
        Connection conn = null;
        try {
            Connection con = db.getConnection();
            String sql = "SELECT * FROM reviews WHERE id=?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Review(rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("device_id"),
                        rs.getInt("rating"),
                        rs.getString("comment"));

            }
        } catch (SQLException e) {
            System.out.println("sql error" + e.getMessage());
        }
        return null;

    }

    @Override
    public List<Review> getAllReviews() {
        List<Review> reviews = new ArrayList<>();
        try (Connection conn = db.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM reviews")) {

            while (rs.next()) {
                reviews.add(new Review(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("device_id"),
                        rs.getInt("rating"),
                        rs.getString("comment")
                ));
            }
            return reviews;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

}

