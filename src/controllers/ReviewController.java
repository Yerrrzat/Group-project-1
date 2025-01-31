package controllers;

import controllers.interfaces.IReviewController;
import models.Review;
import repositories.interfaces.IReviewRepository;

import java.util.List;

public class ReviewController implements IReviewController {
    private final IReviewRepository repo;

    public ReviewController(IReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createReview(int user_id, int device_id, int rating, String comment) {
        Review review = new Review(user_id, device_id, rating, comment);
        boolean created = repo.createReview(review);
        return (created ? "Review created" : "Review creation failed");

    }

    @Override
    public String getReviewById(int id) {
        Review review = repo.getReviewById(id);
        return (review == null) ? "Review not found" : review.toString();
    }

    @Override
    public String getAllReviews() {
        List<Review> reviews = repo.getAllReviews();
        return reviews.toString();
    }
}
