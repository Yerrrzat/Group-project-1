package controllers.interfaces;

public interface IReviewController {
    String createReview(int user_id, int device_id, int rating, String comment);
    String getReviewById(int id);
    String getAllReviews();
}
