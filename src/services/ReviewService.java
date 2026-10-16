
package services;

import interfaces.Reviewable;
import models.EventModel;
import models.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles business logic related to event reviews,
 * including searching, adding and counting reviews.
 */
public class ReviewService {

	/**
	 * Finds events by artist name that have at least one review.
	 *
	 * @param events the list of events to search
	 * @param artist the artist name to search for
	 * @return a list of matching events with reviews,
	 *         or an empty list if the input is invalid
	 */
	public List<EventModel> getEventsWithReviewsByArtist(
			List<EventModel> events, String artist) {

		// Return an empty list if the search input is invalid.
		if (events == null || artist == null || artist.isBlank()) {
			return new ArrayList<>();
		}

		List<EventModel> matchingEvents = new ArrayList<>();

		for (EventModel event : events) {
			// Match artist names regardless of letter case
			// and exclude events without reviews.
			if (event != null
					&& event.getArtist() != null
					&& event.getArtist().equalsIgnoreCase(artist)
					&& !event.getReviews().isEmpty()) {
				matchingEvents.add(event);
			}
		}

		return matchingEvents;
	}

	/**
	 * Validates and adds a review to an event.
	 *
	 * @param event the event receiving the review
	 * @param review the review to add
	 * @throws IllegalArgumentException if the event or review is null,
	 *         or if the rating is outside the range 1–5
	 */
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

	/**
	 * Counts all reviews belonging to a collection of reviewable objects.
	 *
	 * @param items the objects whose reviews should be counted
	 * @return the total number of reviews
	 */
	/**
	 * Counts all reviews belonging to a collection of reviewable objects.
	 *
	 * @param items the objects whose reviews should be counted
	 * @return the total number of reviews, or 0 if the list is null
	 */
	public int getTotalReviews(List<? extends Reviewable> items) {
		int total = 0;

		// Return 0 if no collection was provided.
		if (items == null) {
			return 0;
		}

		// Interface polymorphism: each item is accessed through Reviewable,
		// regardless of its concrete event type.
		for (Reviewable item : items) {
			total += item.getReviews().size();
		}

		return total;
	}
}
