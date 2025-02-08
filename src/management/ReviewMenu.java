package management;

import controllers.interfaces.IReviewController;
import java.util.Scanner;

public class ReviewMenu {
    private final IReviewController reviewController;
    private final Scanner scanner;
    private int currentUserId;

    public ReviewMenu(IReviewController reviewController, Scanner scanner, int currentUserId) {
        this.reviewController = reviewController;
        this.scanner = scanner;
        this.currentUserId = currentUserId;
    }

    public void showReviewMenu() {
        while (true) {
            System.out.println("\nReview Menu");
            System.out.println("1. Leave a review");
            System.out.println("2. See all reviews");
            System.out.println("0. Go back");
            System.out.print("Enter option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createReviewMenu();
                    break;
                case 2:
                    System.out.println(reviewController.getAllReviews());
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private void createReviewMenu() {
        if (currentUserId == -1) {
            System.out.println("You must be logged in to leave a review.");
            return;
        }

        System.out.print("Enter device ID: ");
        int deviceId = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter rating (1-5): ");
        int rating = Integer.parseInt(scanner.nextLine());
        System.out.print("Share your comments: ");
        String comment = scanner.nextLine();

        String response = reviewController.createReview(currentUserId, deviceId, rating, comment);
        System.out.println(response);
    }
}
