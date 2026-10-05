package interfaces;

import models.Review;

import java.util.List;

/**
 * Represents an interface for entities that can be reviewed.
 * Implementing classes must manage a collection of Review objects
 * and support adding and retrieving reviews.
 */
public interface Reviewable {

	/**
	 * Adds a new review to the object's collection of reviews.
	 *
	 * @param review the review object to add
	 */
	void addReview(Review review);

	/**
	 * Retrieves the list of reviews associated with this object.
	 *
	 * @return a list of Review objects
	 */
	List<Review> getReviews();

	/**
	 * Calculates and returns the average rating based on all stored reviews.
	 * Uses a default method implementation available to all implementing classes.
	 *
	 * @return the average rating as a double, or 0.0 if there are no reviews
	 */
	default double getAverageRating() {
		List<Review> reviews = getReviews();

		if (reviews == null || reviews.isEmpty()) {
			return 0.0;
		}

		int sum = 0;

		for (Review review : reviews) {
			sum += review.getRating();
		}

		return (double) sum / reviews.size();
	}
}