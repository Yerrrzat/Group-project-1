package repositories.interfaces;


import java.util.List;
import java.util.Map;

public interface IReviewRepository {
    boolean createReview(Review review);

    boolean createReview(Object review);

    boolean createReview(Map<String, Object> reviewData);

    List<Map<String, Object>> getAllReviews();
    <Review> Review getReviewById(int id);
}
