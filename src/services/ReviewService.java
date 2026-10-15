
package services;

import interfaces.Reviewable;
import models.EventModel;
import models.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewService {

	public List<EventModel> getEventsWithReviewsByArtist(
			List<EventModel> events, String artist) {

		List<EventModel> matchingEvents = new ArrayList<>();

		for (EventModel event : events) {
			if (event.getArtist().equalsIgnoreCase(artist)
					&& !event.getReviews().isEmpty()) {
				matchingEvents.add(event);
			}
		}

		return matchingEvents;
	}


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
	}

	public int getTotalReviews(List<? extends Reviewable> items) {
		int total = 0;

		for (Reviewable item : items) {
			total += item.getReviews().size();
		}

		return total;
	}
}
