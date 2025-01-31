package repositories.interfaces;

import entities.Review;

import java.util.List;

public interface IReviewRepository {
    boolean createReview(Review review);
    List<Review> getAllReviews();
    Review getReviewById(int id);
}
