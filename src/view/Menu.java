package view;

import models.*;
import services.EventService;

import java.util.Scanner;

public class Menu {
    private EventService eventService;
	private Scanner input;

	public Menu(EventService eventService, Scanner input) {
		this.eventService = eventService;
		this.input = input;
	}

	public void start(){
		int choice = 0;

		// Dummy data for event and reviews
		Concert evergrey = new Concert(1, "Evergrey", "Progressive Metal", "Mejeriet",
				"Lund", "Sweden", "2026-11-11", "First class progressive metal from Gothenburg", false,
				"", "Architects of A New Wave tour", SeatingType.STANDING);

		evergrey.addReview(new Review(5, "Magical performance! Tom S. was on fire."));
		evergrey.addReview(new Review(3, "Great sound at Mejeriet, but a bit crowded."));
		evergrey.addReview(new Review(2, "To litle place for the band. To crowded."));
		evergrey.addReview(new Review(1, "It was to much litght on the stage and the sound was not great."));

		eventService.addEvent(evergrey);

		Festival hollywoodUndead = new Festival(2, "Hollywood Undead", "Hiphop, rap, metal", "Brutal Assault", "Jaromer",
				"Czech Republic", "2026-08-08", "Hiphopper that loves metal, doing a mix between hiphop and metal.",
				true, false, 4);

		hollywoodUndead.addReview(new Review(5, "Best festival atmosphere ever!"));
		hollywoodUndead.addReview(new Review(4, "A bit muddy, but an incredible show."));
		eventService.addEvent(hollywoodUndead);

		ClubGig theHaunted = new ClubGig(3, "The Haunted", "Death Metal", "Garage", "Höganäs", "Sweden", "2026-11-21",
				"Swedish old death metal group", false, 18, 150, "19:00-01:00");

		theHaunted.addReview(new Review(5, "Pure brutal energy in a small garage! Love it."));
		theHaunted.addReview(new Review(4, "Loud, sweaty and perfect."));
		eventService.addEvent(theHaunted);


		// TODO: Do a check so the user only puts in numbers in the festival numbers of days

		do {
			printMenu();
			System.out.print("\n Enter your choice: ");

			try {
				choice = Integer.parseInt(input.nextLine());

				switch (choice) {
					case 1:
						System.out.println("\n ---- SHOW ALLA EVENTS ----");
						showAllaEvents();
						break;
					case 2:
						System.out.println("\n ---- SEARCH EVENT ----");
						searchEvent();
						break;
					case 3:
						System.out.println("\n ---- ADD EVENT ----");
						addEvent();
						break;
					case 4:
						System.out.println("\n ---- UPDATE EVENT ----");
						updateEvent();
						break;
					case 5:
						System.out.println("\n ---- DELETE EVENT ----");
						deleteEvent();
						break;
					case 6:
						System.out.println("\n ---- REVIEW ----");
						reviewCases();
						break;
					case 7:
						System.out.println("Exit application. Welcome back soon!");
						break;
					default:
						System.out.println("Not valid input. only number between 1-7");
				}

			} catch (NumberFormatException ex) {
				System.out.println("You need to put in a number between 1-7. Try again");
				choice = 0; // 0 so the loop will continue
			}
		} while(choice != 7); // until the user press 6, the loop will quit
	}



	private void printMenu(){
		System.out.println("\n ---- MUSIC EVENT TRACKER ----");
		System.out.println("[1] SHOW ALL EVENTS");
		System.out.println("[2] SEARCH EVENT");
		System.out.println("[3] ADD EVENT");
		System.out.println("[4] UPDATE EVENT");
		System.out.println("[5] DELETE EVENT");
		System.out.println("[6] REVIEWS");
		System.out.println("[7] EXIT");
	}

	// TODO: If the user input does chose anything else for standing, sitting or booth, then it will print out a error.
	// TODO: Connect everagerating to the events
	// TODO: See if its time for making count how many events it is in the list total
	private void showAllaEvents(){

		if(eventService.getAllEvents().isEmpty()){
			System.out.println("No events at the moment");
		}

		for(var event: eventService.getAllEvents()){
			System.out.println(event);
			System.out.println("------------------------");
		}
	}

	private void searchEvent(){
		System.out.println("Enter event Id: ");

		try {
			int id = Integer.parseInt(input.nextLine());

			var event = eventService.getEventById(id);

			if(event != null){
				System.out.println("\n Event found: ");
				System.out.println(event);
			}else{
				System.out.println("No event found based on id: " + id);
			}
		}catch(NumberFormatException ex){
			System.out.println("Inavlid input. Event id must be a number.");
		}
	}


	private void addEvent() {
		System.out.println("\n What Event do you wanna add:");
		System.out.println("[1] Concert");
		System.out.println("[2] Festival");
		System.out.println("[3] Club gig");
		System.out.print("Choose type (1-3): ");

		try {
			int numberEvent = Integer.parseInt(input.nextLine());

			System.out.print("Enter ID: ");
			int id = Integer.parseInt(input.nextLine());

			System.out.print("Enter artist: ");
			String artist = input.nextLine();

			System.out.print("Enter genre: ");
			String genre = input.nextLine();

			System.out.print("Enter venue: ");
			String venue = input.nextLine();

			System.out.print("Enter city: ");
			String city = input.nextLine();

			System.out.print("Enter country: ");
			String country = input.nextLine();

			System.out.print("Enter date (YYYY-MM-DD): ");
			String date = input.nextLine();

			System.out.print("Enter description: ");
			String description = input.nextLine();

			System.out.print("Is it outdoors? (true/false): ");
			boolean isOutdoor = Boolean.parseBoolean(input.nextLine());

			switch (numberEvent) {
				case 1: // Concert
					System.out.print("Enter support act: ");
					String supportAct = input.nextLine();

					System.out.print("Enter tour name: ");
					String tourName = input.nextLine();

					System.out.print("Enter seating type (STANDING, SITTING, BOOTH): ");
					String seatingInput = input.nextLine().trim();

					// Checking enum for seating type
					SeatingType seatingType = SeatingType.STANDING;
					for (SeatingType type : SeatingType.values()) {
						if (type.name().equalsIgnoreCase(seatingInput)) {
							seatingType = type;
							break;
						}
					}

					Concert concert = new Concert(id, artist, genre, venue, city, country, date, description, isOutdoor, supportAct, tourName, seatingType);
					eventService.addEvent(concert);
					System.out.println("Concert added successfully!");
					break;

				case 2: // Festival
					System.out.print("Enter number of days: ");
					int numberOfDays = Integer.parseInt(input.nextLine());

					// TODO: Fix so it is YES or NO isntead for true / false

					System.out.print("Is camping included? (true/false): ");
					boolean camping = Boolean.parseBoolean(input.nextLine());

					Festival festival = new Festival(id, artist, genre, venue, city, country, date, description, isOutdoor, camping, numberOfDays);
					eventService.addEvent(festival);
					System.out.println("Festival added successfully!");
					break;

				case 3: // ClubGig
					System.out.print("Enter age limit (e.g. 18): ");
					int ageLimit = Integer.parseInt(input.nextLine());

					System.out.print("Enter ticket price: ");
					double ticketPrice = Double.parseDouble(input.nextLine());

					System.out.print("Enter set times (e.g. 19:00-01:00): ");
					String setTimes = input.nextLine();

					ClubGig clubGig = new ClubGig(id, artist, genre, venue, city, country, date, description, isOutdoor, ageLimit, ticketPrice, setTimes);
					eventService.addEvent(clubGig);
					System.out.println("Club Gig added successfully!");
					break;

				default:
					System.out.println("Invalid event type choice.");
			}

		} catch (NumberFormatException ex){
			System.out.println("Not a valid number.");
		}
	}

	private void updateEvent() {
		System.out.print("\n Enter event ID to update: ");

		try {
			int id = Integer.parseInt(input.nextLine());
			var event = eventService.getEventById(id);

			if (event == null) {
				System.out.println("No event found with ID " + id);
				return;
			}

			System.out.println("Event found! Enter new details (leave blank to keep current):");

			System.out.print("Enter new artist (current: " + event.getArtist() + "): ");
			String artist = input.nextLine();
			if (!artist.isEmpty()) event.setArtist(artist);

			System.out.print("Enter new genre (current: " + event.getGenre() + "): ");
			String genre = input.nextLine();
			if (!genre.isEmpty()) event.setGenre(genre);

			System.out.print("Enter new venue (current: " + event.getVenue() + "): ");
			String venue = input.nextLine();
			if (!venue.isEmpty()) event.setVenue(venue);

			System.out.print("Enter new city (current: " + event.getCity() + "): ");
			String city = input.nextLine();
			if (!city.isEmpty()) event.setCity(city);

			System.out.print("Enter new country (current: " + event.getCountry() + "): ");
			String country = input.nextLine();
			if (!country.isEmpty()) event.setCountry(country);

			System.out.print("Enter new date (current: " + event.getDate() + "): ");
			String date = input.nextLine();
			if (!date.isEmpty()) event.setDate(date);

			System.out.print("Enter new description (current: " + event.getDescription() + "): ");
			String description = input.nextLine();
			if (!description.isEmpty()) event.setDescription(description);

			System.out.print("Is it outdoors? true/false (current: " + event.isOutdoor() + "): ");
			String outdoorStr = input.nextLine();
			if (!outdoorStr.isEmpty()) {
				event.setOutdoor(Boolean.parseBoolean(outdoorStr));
			}

			if (event instanceof Concert) {
				Concert concert = (Concert) event;

				System.out.print("Enter new support act (current: " + concert.getSupportAct() + "): ");
				String supportAct = input.nextLine();
				if (!supportAct.isEmpty()) concert.setSupportAct(supportAct);

				System.out.print("Enter new tour name (current: " + concert.getTourName() + "): ");
				String tourName = input.nextLine();
				if (!tourName.isEmpty()) concert.setTourName(tourName);

				System.out.print("Enter new seating type STANDING/SITTING/BOOTH (current: " + concert.getSeatingType() + "): ");
				String seatingInput = input.nextLine().trim();
				if (!seatingInput.isEmpty()) {
					for (SeatingType type : SeatingType.values()) {
						if (type.name().equalsIgnoreCase(seatingInput)) {
							concert.setSeatingType(type);
							break;
						}
					}
				}

			} else if (event instanceof Festival) {
				Festival festival = (Festival) event;

				System.out.print("Enter new number of days (current: " + festival.getNumberOfDays() + "): ");
				String daysStr = input.nextLine();
				if (!daysStr.isEmpty()) festival.setNumberOfDays(Integer.parseInt(daysStr));

				System.out.print("Is camping included? true/false (current: " + festival.isCampingIncluded() + "): ");
				String campingStr = input.nextLine();
				if (!campingStr.isEmpty()) festival.setCampingIncluded(Boolean.parseBoolean(campingStr));

			} else if (event instanceof ClubGig) {
				ClubGig clubGig = (ClubGig) event;

				System.out.print("Enter new age limit (current: " + clubGig.getAgeLimit() + "): ");
				String ageStr = input.nextLine();
				if (!ageStr.isEmpty()) clubGig.setAgeLimit(Integer.parseInt(ageStr));

				System.out.print("Enter new ticket price (current: " + clubGig.getEntryFee() + "): ");
				String priceStr = input.nextLine();
				if (!priceStr.isEmpty()) clubGig.setEntryFee(Double.parseDouble(priceStr));

				System.out.print("Enter new set times (current: " + clubGig.getOpeningHours() + "): ");
				String setTimes = input.nextLine();
				if (!setTimes.isEmpty()) clubGig.setOpeningHours(setTimes);
			}

			eventService.updateEvent(id, event);

			System.out.println("Event updated successfully!");

		} catch (NumberFormatException ex) {
			System.out.println("Invalid number format. Please try again.");
		}
	}

	private void deleteEvent() {
		System.out.print("Enter event ID to delete: ");

		try {
			int deleteById = Integer.parseInt(input.nextLine());

			eventService.deleteEvent(deleteById);
			System.out.println("Event with ID " + deleteById + " deleted successfully!");

		} catch (NumberFormatException ex) {
			System.out.println("Invalid input. Event ID must be a number.");
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}

	// ---- REVIEW ----

	private void reviewCases() {
		int choice;
		do {
			System.out.println("[1] Show reviews for an artist");
			System.out.println("[2] Add review to event - (by ID)");
			System.out.println("[3] Back to main menu");
			System.out.print("Choose option (1-3): ");

			try {
				choice = Integer.parseInt(input.nextLine());

				switch (choice) {
					case 1:
						System.out.println("SHOW REVIEWS");
						showReviewsByArtist();
						break;
					case 2:
						System.out.println("ADD REVIEW");
						addReviewToEvent();
						break;
					case 3:
						System.out.println("Returning to main menu...");
						break;
					default:
						System.out.println("Invalid choice. Choose between 1-3.");
				}
			} catch (NumberFormatException ex) {
				System.out.println("Invalid input. Please enter a number.");
				choice = 0;
			}
		} while (choice != 3);
	}

	private void showReviewsByArtist() {
		System.out.println("\n ---- SHOW REVIEWS BY ARTIST ----");
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
			System.out.println("No events found for artist: " + searchArtist);
		} else if (!foundReview) {
			System.out.println("Events were found for " + searchArtist + ", but none of them have any reviews yet.");
		}
	}

	private void addReviewToEvent() {
		System.out.println("\n ---- ADD REVIEW TO EVENT ----");
		System.out.print("Enter event ID to review: ");

		try {
			int id = Integer.parseInt(input.nextLine());

			var event = eventService.getEventById(id);

			if (event == null) {
				System.out.println("No event found with ID " + id);
				return;
			}

			System.out.println("Found event: " + event.getArtist() + " at " + event.getVenue());

			System.out.print("Enter rating (1 to 5): ");
			int rating = Integer.parseInt(input.nextLine());

			if (rating < 1 || rating > 5) {
				System.out.println("Invalid rating! Rating must be between 1 and 5. Review cancelled.");
				return;
			}

			System.out.print("Enter your review comment: ");
			String comment = input.nextLine().trim();

			if (comment.isEmpty()) {
				comment = "No comment provided.";
			}

			Review newReview = new Review(rating, comment);
			event.addReview(newReview);

			System.out.println("Review added successfully!");

		} catch (NumberFormatException ex) {
			System.out.println("Invalid input. Please enter a valid number for ID and rating.");
		}
	}
}
