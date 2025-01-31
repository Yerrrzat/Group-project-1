package controllers.interfaces;

public interface IReviewController {
    String createBrand(String name, String description);
    String getBrandById(int id);
    String getAllBrands();

    String createReview(int userId, int deviceId, String comment, int rating);
}
