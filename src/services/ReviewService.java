
package services;

import models.EventModel;
import models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewService {

	private List<Review> reviews = new ArrayList<>();


	public void addReview(EventModel event, Review review) {
		if (event == null) {
			throw new IllegalArgumentException("Event can't be null.");
		}

		if (review == null) {
			throw new IllegalArgumentException("Review can't be null.");
		}

		if (review.getRating() < 1 || review.getRating() > 5) {
			throw new IllegalArgumentException(
					"Rating must be between 1 and 5."
			);
		}

		event.addReview(review);
		reviews.add(review);
	}
}
