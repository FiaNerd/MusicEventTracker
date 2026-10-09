package models;

import interfaces.Reviewable;

import java.time.LocalDateTime;

/**
 * Represents a single review for an event.
 * Stores rating, written comment, and the timestamp when the review was created.
 * Used by EventModel to calculate average ratings and display user feedback.
 */
public class Review {

	private int rating;                 // Numeric rating given by the reviewer (e.g., 1–5)
	private String comment;             // Written feedback describing the experience
	private LocalDateTime createdAt = LocalDateTime.now(); // Timestamp when the review was created

	/**
	 *
	 * @param rating    Rating for the event - 1-5
	 * @param comment   Comment about the event
	 */

	public Review(int rating, String comment) {
		this.rating = rating;
		this.comment = comment;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
}
