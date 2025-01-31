package controllers.interfaces;

public interface IReviewController {
    String createReview(int userId, int deviceId, int rating, String comment);
    String getReviewById(int id);
    String getAllReviews();
}
