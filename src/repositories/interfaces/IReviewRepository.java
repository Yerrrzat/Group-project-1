package repositories.interfaces;


import java.util.List;

public interface IReviewRepository {
    boolean createReview(Review review);

    boolean createReview(Object review);

    List<Review> getAllReviews();
    <Review> Review getReviewById(int id);
}
