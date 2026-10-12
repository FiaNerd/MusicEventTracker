package view;

import models.Review;
import services.EventService;
import utils.MenuUtils;

import java.util.Scanner;

public class ReviewMenu {
	private EventService eventService;
	private Scanner input;

	public ReviewMenu(EventService eventService, Scanner input) {
		this.eventService = eventService;
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
			System.out.print("Choose option (1-3): ");

			try {
				choice = Integer.parseInt(input.nextLine());

				switch (choice) {
					case 1:
						showReviewsByArtist();
						MenuUtils.pressEnterToContinue(input);
						break;
					case 2:
						addReviewToEvent();
						break;
					case 3:
						break;
					default:
						System.out.println("\nInvalid choice. Choose between 1-3. \n");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Invalid input. Please enter a number.");
				choice = 0;
			}
		} while (choice != 3);
	}

	private void showReviewsByArtist() {
		System.out.println("\n ================================");
		System.out.println("      SHOW REVIEWS BY ARTIST ");
		System.out.println(" ==================================");
		System.out.print("Enter artist name: ");
		String searchArtist = input.nextLine().trim();

		if (searchArtist.isEmpty()) {
			System.out.println("Artist name cannot be empty.");
			return;
		}

		boolean foundEvent = false;
		boolean foundReview = false;

		for (var event : eventService.getAllEvents()) {
			if (event.getArtist().equalsIgnoreCase(searchArtist)) {
				foundEvent = true;
				System.out.println("\n Event: " + event.getClass().getSimpleName() +
						" at " + event.getVenue() + " (" + event.getDate() + ")");

				var reviews = event.getReviews();

				if (reviews.isEmpty()) {
					System.out.println("No reviews yet for this event.");
				} else {
					foundReview = true;

					for (var review : reviews) {
						System.out.println("    Rating: " + review.getRating() + "/5");
						System.out.println("    Comment: \"" + review.getComment() + "\"");
						System.out.println("    Date: " + review.getCreatedAt().toLocalDate());
						System.out.println("--------------------------------------------------");
					}
				}
			}
		}

		if (!foundEvent) {
			System.out.println("\n No events found for artist: " + searchArtist);
		} else if (!foundReview) {
			System.out.println("Events were found for " + searchArtist + ", but none of them have any reviews yet.");
		}
	}

	private void addReviewToEvent() {

		System.out.println("\n===============================");
		System.out.println("      ADD REVIEW TO EVENT ");
		System.out.println("================================ ");

		System.out.println("Available events:");
		for (var event : eventService.getAllEvents()) {
			System.out.println("  ID " + event.getId() + ": " + event.getArtist() +
					" @ " + event.getVenue() + " (" + event.getDate() + ")");
		}
		System.out.println("--------------------------------------------------");

		System.out.print("Enter Event ID to review: ");
		try {
			int id = Integer.parseInt(input.nextLine());
			var event = eventService.getEventById(id);

			if (event == null) {
				System.out.println("No event found with ID " + id);
				return;
			}

			System.out.println("Selected: " + event.getArtist() + " at " + event.getVenue());

			System.out.print("Enter rating (1 to 5): ");
			int rating = Integer.parseInt(input.nextLine());

			if (rating < 1 || rating > 5) {
				System.out.println("Invalid rating! Must be 1-5.");
				return;
			}

			System.out.print("Enter comment: ");
			String comment = input.nextLine().trim();

			event.addReview(new Review(rating, comment.isEmpty() ? "No comment" : comment));
			System.out.println("Review added successfully!");

		} catch (NumberFormatException ex) {
			System.out.println("Invalid input! ID and rating must be numbers.");
		}
	}
}
