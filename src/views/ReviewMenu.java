package views;

import models.EventModel;
import models.Review;
import services.EventService;
import services.ReviewService;
import utils.InputValidation;
import utils.MenuUtils;

import java.util.Scanner;

public class ReviewMenu {
	private EventService eventService;
	private ReviewService reviewService;
	private Scanner input;

	public ReviewMenu(EventService eventService, ReviewService reviewService, Scanner input) {
		this.eventService = eventService;
		this.reviewService = reviewService;
		this.input = input;
	}

	public void start() {
		int choice;

		do {
			System.out.println("=====================================");
			System.out.println("               REVIEW");
			System.out.println("=====================================");
			System.out.println("[1] Show reviews for an artist");
			System.out.println("[2] Add review to event - (by ID)");
			System.out.println("[3] Back to main menu");
			System.out.println("=====================================");

				choice = InputValidation.getNumberInRange(input, 1, 3,"\nChoose an option (1-3): " );

				switch (choice) {
					case 1:
						showReviewsByArtist();
						break;
					case 2:
						addReviewToEvent();
						break;
					case 3:
						break;
				}
		} while (choice != 3);
	}

	private void showReviewsByArtist() {
		System.out.println("\n ================================");
		System.out.println("      SHOW REVIEWS OF ARTIST ");
		System.out.println(" ==================================");
		System.out.print("Enter artist name: ");
		String searchArtist = input.nextLine().trim();

			if (searchArtist.isEmpty()) {
				return;
			}

			var reviews = reviewService.getReviewsByArtist(
					eventService.getAllEvents(), searchArtist);

			if (reviews.isEmpty()) {
				System.out.println("No reviews found for " + searchArtist + ".");
				return;
			}

			for (var review : reviews) {
				System.out.println("Rating: " + review.getRating() + "/5");
				System.out.println("Comment: \"" + review.getComment() + "\"");
				System.out.println("Date: " + review.getCreatedAt().toLocalDate());
				System.out.println("------------------------------------------");
			}

		MenuUtils.pressEnterToContinue(input);
	}


	private void addReviewToEvent() {

		System.out.println("\n===============================");
		System.out.println("      ADD REVIEW TO EVENT ");
		System.out.println("================================");

		System.out.println("Available events:");

		for (var event : eventService.getAllEvents()) {
			System.out.println("  ID " + event.getId() + ": "
					+ event.getArtist() + " @ " + event.getVenue()
					+ " (" + event.getDate() + ")");
		}

		System.out.println("--------------------------------------------------");

		var selectedEvent = (EventModel) null;

		while (selectedEvent == null) {

			int id = InputValidation.getNumberInRange(
					input, 1, Integer.MAX_VALUE, "\nEnter Event ID to review: ");

			selectedEvent = eventService.getEventById(id);

			if (selectedEvent == null) {
				System.out.println(
						"No event found with ID " + id + ". Please try again.");
			}
		}
		System.out.println("Selected: " + selectedEvent.getArtist()
				+ " at " + selectedEvent.getVenue());

		// Get rating
		int rating = InputValidation.getNumberInRange(input, 1, 5, "Enter rating between (1- 5): ");

		// Get optional comment
		System.out.print("Enter comment (optional): ");
		String comment = input.nextLine().trim();


		String reviewComment = comment.isEmpty() ? "No comment" : comment;

		Review review = new Review(rating, reviewComment);
		reviewService.addReview(selectedEvent, review);

		System.out.println("Review added successfully!");

	}
}