package controllers;
import controllers.interfaces.IReviewController;
import models.Review;
import repositories.interfaces.IReviewRepository;

import java.util.List;
import java.util.Map;

public class ReviewController implements IReviewController {
    @Override
    public String createBrand(String name, String description) {
        throw new UnsupportedOperationException("createBrand method is not implemented in ReviewController");
    }

    @Override
    public String getBrandById(int id) {
        throw new UnsupportedOperationException("getBrandById method is not implemented in ReviewController");
    }

    @Override
    public String getAllBrands() {
        throw new UnsupportedOperationException("getAllBrands method is not implemented in ReviewController");
    }
    private final IReviewRepository repo;

    public ReviewController(IReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createReview(int userId, int deviceId, String comment, int rating) {
        Review review = new Review(userId, deviceId, comment, rating);
        boolean created = repo.createReview(review);
        return created ? "Review created" : "Review creation failed";
    }


    public String getReviewById(int id) {
        Review review = repo.getReviewById(id);
        return (review == null) ? "Review not found" : review.toString();
    }

    public String getAllReviews() {
        List<Map<String, Object>> reviews = repo.getAllReviews();
        StringBuilder response = new StringBuilder();
        for (Map<String, Object> review : reviews) {
            response.append(review.toString()).append("\n");
        }
        return response.toString();
    }
}