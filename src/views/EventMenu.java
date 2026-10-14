package views;

import models.ClubGig;
import models.Concert;
import models.Festival;
import models.SeatingType;
import services.EventService;
import utils.InputValidation;
import utils.MenuUtils;
import interfaces.Reviewable;
import models.EventModel;
import java.util.Scanner;

public class EventMenu {
	private EventService eventService;
	private Scanner input;

	public EventMenu(EventService eventService, Scanner input) {
		this.eventService = eventService;
		this.input = input;
	}

	public void start() {
		int choice = 0;



		// TODO: Do a check so the user only puts in numbers in the festival numbers of days

		do {
			printMenu();

				choice = InputValidation.getNumberInRange(input, 1, 6,"\nChoose an option (1-6): ");

				switch (choice) {
					case 1:
						showAllaEvents();
						MenuUtils.pressEnterToContinue(input);
						break;
					case 2:
						searchEvent();
						MenuUtils.pressEnterToContinue(input);
						break;
					case 3:
						addEvent();
						break;
					case 4:
						updateEvent();
						break;
					case 5:
						deleteEvent();
						break;
					case 6:
						break;
				}

		} while (choice != 6); // until the user press 6, the loop will quit
	}


	private void printMenu() {
		System.out.println("\n===========================");
		System.out.println("       MUSIC EVENT");
		System.out.println("============================");
		System.out.println(" [1] SHOW ALL EVENTS");
		System.out.println(" [2] SEARCH EVENT");
		System.out.println(" [3] ADD EVENT");
		System.out.println(" [4] UPDATE EVENT");
		System.out.println(" [5] DELETE EVENT");
		System.out.println(" [6] BACK TO MAIN MENU");
		System.out.println("=============================");
	}



	// TODO: If the user input does chose anything else for standing, sitting or booth, then it will print out a error.
	// TODO: Connect everagerating to the events
	// TODO: See if its time for making count how many events it is in the list total

	private void showAllaEvents() {

		System.out.println("\n==========================");
		System.out.println("         ALL EVENTS");
		System.out.println("==========================");

		if (eventService.getAllEvents().isEmpty()) {
			System.out.println("No events at the moment");
			return;
		}

		for (EventModel event : eventService.getAllEvents()) {
			System.out.println(event);

			Reviewable reviewable = event;
			System.out.println(
					"Average rating: " + reviewable.getAverageRating()
			);

			System.out.println("--------------------------------------------------");
		}
	}


	private void searchEvent() {
		System.out.println("\n==========================");
		System.out.println("       SEARCH EVENT");
		System.out.println("==========================");
		System.out.print("Searrch for artist / band or venue or city: ");
		String searchTerm = input.nextLine().trim();

		if (searchTerm.isEmpty()) {
			System.out.println("Search term cannot be empty.");
			return;
		}

		boolean found = false;

		System.out.println("\n==========================");
		System.out.println("      SEARCH RESULT");
		System.out.println("==========================");

		for (var event : eventService.getAllEvents()) {
			if (event.getArtist().toLowerCase().contains(searchTerm.toLowerCase())
					|| event.getVenue().toLowerCase().contains(searchTerm.toLowerCase())
					|| event.getCity().toLowerCase().contains(searchTerm.toLowerCase())) {
				found = true;
				System.out.println(event);
				System.out.println("--------------------------------------------------");
			}
		}

		if (!found) {
			System.out.println("No events found matching: \"" + searchTerm + "\"");
		}
	}

	private void addEvent() {
		System.out.println("\n==========================");
		System.out.println("          ADD EVENT");
		System.out.println("==========================");
		System.out.println("\n What Event do you wanna add:");
		System.out.println("[1] Concert");
		System.out.println("[2] Festival");
		System.out.println("[3] Club gig");

		try {
			int numberEvent = InputValidation.getNumberInRange(input, 1, 3,  "Choose an option (1-3): ");

			// TODO: Check if you are going to skip ID or genera random id and only add by artis name?

			String artist = InputValidation.getRequiredInput(input, "Enter artis: ");

			String genre = InputValidation.getRequiredInput(input, "Enter genre: ");

			String venue = InputValidation.getRequiredInput(input, "Enter venue:  ");

			String city = InputValidation.getRequiredInput(input, "Enter city: ");

			String country = InputValidation.getRequiredInput(input, "Enter country:  ");

			String date = InputValidation.getRequiredDateInput(input, "Enter event date (yyyy-MM-dd): ");

			System.out.print("Enter description: ");
			String description = input.nextLine();

			boolean isOutdoor  = InputValidation.getYesNoInput(input, "Is it outdoor? (Yes/No): ", null);

			switch (numberEvent) {
				case 1: // Concert
					System.out.print("Enter support act: ");
					String supportAct = input.nextLine();

					System.out.print("Enter tour name: ");
					String tourName = input.nextLine();

					System.out.println("Choose seating type:");
					System.out.println("[1] Standing");
					System.out.println("[2] Sitting");
					System.out.println("[3] Booth");
					System.out.println("[Enter] Standing (default)");

					System.out.print("\nChoose an option (1-3): ");
					String seatingInput = input.nextLine().trim();

					SeatingType seatingType;

					switch (seatingInput) {
						case "2":
							seatingType = SeatingType.SEATED;
							break;
						case "3":
							seatingType = SeatingType.MIXED;
							break;
						case "":
						case "1":
							seatingType = SeatingType.STANDING;
							break;
						default:
							System.out.println("Invalid choice. Defaulting to Standing.");
							seatingType = SeatingType.STANDING;
							break;
					}


					Concert concert = new Concert( artist, genre, venue, city, country, date, description, isOutdoor, supportAct, tourName, seatingType);
					eventService.addEvent(concert);
					System.out.println("\nConcert added successfully!");
					break;

				case 2: // Festival
					int numberOfDays = InputValidation.getNumberInRange(input, 1, 10, "Enter number of days (1-10): "
					);

					boolean camping = InputValidation.getYesNoInput(
							input, "Is camping included? (Yes/No): ", null);

					Festival festival = new Festival(
							artist, genre, venue, city, country, date,
							description, isOutdoor, camping, numberOfDays
					);

					eventService.addEvent(festival);
					System.out.println("\nFestival added successfully!");
					break;


				case 3: // ClubGig
					int ageLimit = InputValidation.getNumberInRange(
							input, 0, 100, "Enter age limit (0-100): "
					);

					double ticketPrice;

					while (true) {
						System.out.print("Enter ticket price (e.g. 150.50): ");
						String priceInput = input.nextLine().trim();

						try {
							ticketPrice = Double.parseDouble(priceInput);
							if (ticketPrice >= 0) {
								break;
							}
							System.out.println("Ticket price cannot be negative.");
						} catch (NumberFormatException ex) {
							System.out.println("Please enter a valid price, e.g. 150.50.");
						}
					}

					String setTimes = InputValidation.getOptionalTimeRange(
							input, "Enter set times (e.g. 19:00-01:00): "
					);

					ClubGig clubGig = new ClubGig(
							artist, genre, venue, city, country, date,
							description, isOutdoor, ageLimit, ticketPrice, setTimes
					);

					eventService.addEvent(clubGig);
					System.out.println("Club Gig added successfully!");
					break;

				default:
					System.out.println("Invalid event type choice.");
			}

		} catch (NumberFormatException ex) {
			System.out.println("Not a valid number.");
		} catch (IllegalArgumentException ex) {
			System.out.println("Could not add event:");
			System.out.println(ex.getMessage());
		}
	}

	private void updateEvent() {
		System.out.println("\n==========================");
		System.out.println("       UPDATE EVENT");
		System.out.println("==========================");

		for (var event : eventService.getAllEvents()) {
			System.out.println("  ID " + event.getId() + ": "
					+ event.getArtist() + " @ " + event.getVenue()
					+ " (" + event.getDate() + ")");
		}


		System.out.print("\n Enter event ID to update: ");

		try {
			int id = Integer.parseInt(input.nextLine());
			var event = eventService.getEventById(id);

			if (event == null) {
				System.out.println("No event found with ID " + id);
				return;
			}

			System.out.println("Event found - " + event.getArtist() + "!\nEnter new details (press enter to keep current)");

			System.out.print("\nEnter new artist (current: " + event.getArtist() + "): ");
			String artist = input.nextLine();
			if (!artist.isEmpty()) event.setArtist(artist);

			System.out.print("Enter new genre (current: " + event.getGenre() + "): ");
			String genre = input.nextLine();
			if (!genre.isEmpty()) event.setGenre(genre);

			System.out.print("Enter new venue (current: " + event.getVenue() + "): ");
			String venue = input.nextLine();
			if (!venue.isEmpty()) {
				event.setVenue(venue);
			}

			System.out.print("Enter new city (current: " + event.getCity() + "): ");
			String city = input.nextLine();
			if (!city.isEmpty()) {
				event.setCity(city);
			}

			System.out.print("Enter new country (current: " + event.getCountry() + "): ");
			String country = input.nextLine();
			if (!country.isEmpty()) {
				event.setCountry(country);
			}

			String date = InputValidation.getDateInput(input, "Enter new date (current: " + event.getDate() + "): ",
					event.getDate()
			);

			event.setDate(date);

			System.out.print("\nEnter new description (current: " + event.getDescription() + "): ") ;
			String description = input.nextLine();

			if (!description.isEmpty()) {
				event.setDescription(description);
			}


			boolean isOutdoor = InputValidation.getYesNoInput(
					input,
					"Is it outdoors? (Yes/No, current: "
							+ (event.isOutdoor() ? "Yes" : "No") + "): ",
					event.isOutdoor()
			);

			event.setOutdoor(isOutdoor);

			if (event instanceof Concert) {
				Concert concert = (Concert) event;

				System.out.print("Enter new support act (current: " + concert.getSupportAct() + "): ");
				String supportAct = input.nextLine();

				if (!supportAct.isEmpty()) {
					concert.setSupportAct(supportAct);
				}

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

				System.out.print("Is camping included? Yses/No (current: " + festival.isCampingIncluded() + "): ");
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

			System.out.println("\nEvent updated successfully!");

		} catch (NumberFormatException ex) {
			System.out.println("Invalid number format. Please try again.");
		}
	}

	private void deleteEvent() {
		System.out.println("\n==========================");
		System.out.println("         DELETE EVENT");
		System.out.println("==========================");

		for (var event : eventService.getAllEvents()) {
			System.out.println("  ID " + event.getId() + ": "
					+ event.getArtist() + " @ " + event.getVenue()
					+ " (" + event.getDate() + ")");
		}


		System.out.print("\n Enter event ID to delete: ");

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
}
