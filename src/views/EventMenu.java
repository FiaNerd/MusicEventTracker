package views;

import models.ClubGig;
import models.Concert;
import models.Festival;
import models.SeatingType;
import services.EventService;
import utils.InputValidation;
import utils.MenuUtils;
import models.EventModel;
import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class EventMenu {
	private EventService eventService;
	private Scanner input;

	public EventMenu(EventService eventService, Scanner input) {
		this.eventService = eventService;
		this.input = input;
	}

	public void start() {
		int choice = 0;

		do {
			printMenu();

				choice = InputValidation.getNumberInRange(input, 1, 6,"\nChoose an option (1-6): ");

			try {
				switch (choice) {
					case 1:
						showAllaEvents();
						MenuUtils.pressEnterToContinue(input);
						break;
					case 2:
						searchEvent();
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
			} catch (CancellationException ex) {
				System.out.println("\nAction cancelled. Returning to event menu...");
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

			System.out.println("--------------------------------------------------");
		}
	}


	private void searchEvent() {
		System.out.println("\n==========================");
		System.out.println("       SEARCH EVENT");
		System.out.println("==========================");

		MenuUtils.printCancelInfo();

		while (true) {
			System.out.print("Search for artist / band, venue or city: ");

			String searchTerm = MenuUtils.cancelInput(input);

			if (searchTerm.isEmpty()) {
				System.out.println("Search term cannot be empty. Please try again.\n");
				continue;
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

			if (found) {
				MenuUtils.pressEnterToContinue(input);
				return;
			}

			System.out.println("No events found matching: \"" + searchTerm + "\"");
			System.out.println("Please try another search term, or type ESC to return.");

			System.out.println();
		}
	}




	private void addEvent() {
		System.out.println("\n==========================");
		System.out.println("          ADD EVENT");
		System.out.println("==========================");
		System.out.println("\n What Event do you wanna add:");
		System.out.println("[1] CONCERT");
		System.out.println("[2] FESIVAL");
		System.out.println("[3] CLUB GIG");
		System.out.println("[4] BACK TO EVENT");

		try {

			int numberEvent = InputValidation.getNumberInRange(input, 1, 4,  "\nChoose an option (1-4): ");

				if (numberEvent == 4) {
					return;
				}

			MenuUtils.printCancelInfo();

			String artist = InputValidation.getInput(input, "Enter artis: ", true);

			String genre = InputValidation.getInput(input, "Enter genre: ", true);

			String venue = InputValidation.getInput(input, "Enter venue: ", true);

			String city = InputValidation.getInput(input, "Enter city: ", true);

			String country = InputValidation.getInput(input, "Enter country: ", true);

			String date = InputValidation.getRequiredDateInput(input, "Enter event date (yyyy-MM-dd): ", true);

			String description = InputValidation.getInput(input, "Enter description (optional): ", false);

			boolean isOutdoor  = InputValidation.getYesNoInput(input, "Is it outdoor? (Yes/No): ", null,true);

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

					System.out.print("\nChoose an option (1-4): ");
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
							System.out.println("Invalid choice. Defaulting to Standing.\n");
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
							input, "Is camping included? (Yes/No): ", null, true);

					Festival festival = new Festival(
							artist, genre, venue, city, country, date,
							description, isOutdoor, camping, numberOfDays
					);

					eventService.addEvent(festival);
					System.out.println("\nFestival added successfully!");
					break;

				case 3: // ClubGig
					Integer ageLimit = null;

					while (true) {
						String ageLimitInput = InputValidation.getInput(
								input, "Enter age limit (0-100) (optional): ", false
						);

						if (ageLimitInput.isBlank()) {
							break;
						}

						try {
							int value = Integer.parseInt(ageLimitInput);

							if (value >= 0 && value <= 100) {
								ageLimit = value;
								break;
							}

							System.out.println("Please enter a number between 0 and 100.\n");

						} catch (NumberFormatException e) {
							System.out.println("Please enter numbers only (0-100).\n");
						}
					}

					Double price;

					while (true) {
						String priceInput = InputValidation.getInput(
								input, "Enter ticket price (e.g. 150.50) (optional): ", false
						);

						if (priceInput.isBlank()) {
							price = null;
							break;
						}

						try {
							price = Double.parseDouble(priceInput);

							if (price >= 0) {
								break;
							}

							System.out.println("Ticket price cannot be negative.\n");

						} catch (NumberFormatException ex) {
							System.out.println("Please enter a valid price, e.g. 150.50.\n");
						}
					}

					String setTime = InputValidation.getOptionalTimeRange(
							input, "Enter set times (e.g. 19:00-01:00) (optional): ", false
					);

					if (setTime.isBlank()) {
						setTime = null;
					}

					ClubGig clubGig = new ClubGig(
							artist, genre, venue, city, country, date,
							description, isOutdoor, ageLimit, price, setTime
					);

					eventService.addEvent(clubGig);
					System.out.println("\nClub Gig added successfully!");
					break;
				default:
					System.out.println("Invalid event type choice.\n");
			}

		} catch (NumberFormatException ex) {
			System.out.println("Not a valid number.\n");
		} catch (IllegalArgumentException ex) {
			System.out.println("Could not add event:\n");
			System.out.println(ex.getMessage());
		}
	}


	private void updateEvent() {
		System.out.println("\n==========================");
		System.out.println("       UPDATE EVENT");
		System.out.println("==========================");

		for (var item : eventService.getAllEvents()) {
			System.out.println("  ID " + item.getId() + ": "
					+ item.getArtist() + " @ " + item.getVenue()
					+ " (" + item.getDate() + ")");
		}

		MenuUtils.printCancelInfo();

		int id = -1;
		EventModel event = null;

		// Find an existing event
		while (event == null) {
			System.out.print("\nEnter event ID to update: ");

			try {
				String userInput = MenuUtils.cancelInput(input);
				id = Integer.parseInt(userInput);

				event = eventService.getEventById(id);

				if (event == null) {
					System.out.println(
							"No event found with ID " + id
									+ ". Please try again.\n"
					);
				}

			} catch (NumberFormatException e) {
				System.out.println("Invalid ID. Please enter a number.\n");
			}
		}

		System.out.println("\nEvent found - " + event.getArtist()
				+ "!\nEnter new details (press Enter to keep current).");

		// Artist
		System.out.print("\nEnter new artist (current: "
				+ event.getArtist() + "): ");
		String artist = MenuUtils.cancelInput(input);

		if (!artist.isEmpty()) {
			event.setArtist(artist);
		}

		// Genre
		System.out.print("Enter new genre (current: "
				+ event.getGenre() + "): ");
		String genre = MenuUtils.cancelInput(input);

		if (!genre.isEmpty()) {
			event.setGenre(genre);
		}

		// Venue
		System.out.print("Enter new venue (current: "
				+ event.getVenue() + "): ");
		String venue = MenuUtils.cancelInput(input);

		if (!venue.isEmpty()) {
			event.setVenue(venue);
		}

		// City
		System.out.print("Enter new city (current: "
				+ event.getCity() + "): ");
		String city = MenuUtils.cancelInput(input);

		if (!city.isEmpty()) {
			event.setCity(city);
		}

		// Country
		System.out.print("Enter new country (current: "
				+ event.getCountry() + "): ");
		String country = MenuUtils.cancelInput(input);

		if (!country.isEmpty()) {
			event.setCountry(country);
		}

		// Date
		String date = InputValidation.getDateInput(
				input,
				"Enter new date (current: " + event.getDate() + "): ",
				event.getDate()
		);
		event.setDate(date);

		// Description
		System.out.print("Enter new description (current: "
				+ event.getBaseDescription() + "): ");
		String description = MenuUtils.cancelInput(input);

		if (!description.isEmpty()) {
			event.setDescription(description);
		}

		// Outdoor
		boolean isOutdoor = InputValidation.getYesNoInput(
				input,
				"Is it outdoors? (Yes/No, current: "
						+ (event.isOutdoor() ? "Yes" : "No") + "): ",
				event.isOutdoor(),
				true
		);
		event.setOutdoor(isOutdoor);

		// Concert
		if (event instanceof Concert) {
			Concert concert = (Concert) event;

			System.out.print("Enter new support act (current: "
					+ concert.getSupportAct() + "): ");
			String supportAct = MenuUtils.cancelInput(input);

			if (!supportAct.isEmpty()) {
				concert.setSupportAct(supportAct);
			}

			System.out.print("Enter new tour name (current: "
					+ concert.getTourName() + "): ");
			String tourName = MenuUtils.cancelInput(input);

			if (!tourName.isEmpty()) {
				concert.setTourName(tourName);
			}

			while (true) {
				System.out.print(
						"Enter seating type STANDING/SITTING/BOOTH "
								+ "(current: " + concert.getSeatingType() + "): "
				);

				String seatingInput = MenuUtils.cancelInput(input);

				if (seatingInput.isEmpty()) {
					break;
				}

				boolean valid = false;

				for (SeatingType type : SeatingType.values()) {
					if (type.name().equalsIgnoreCase(seatingInput)) {
						concert.setSeatingType(type);
						valid = true;
						break;
					}
				}

				if (valid) {
					break;
				}

				System.out.println(
						"Invalid seating type. Choose STANDING, SITTING or BOOTH.\n"
				);
			}

			// Festival
		} else if (event instanceof Festival) {
			Festival festival = (Festival) event;

			while (true) {
				System.out.print("Enter new number of days (current: "
						+ festival.getNumberOfDays() + "): ");

				String daysStr = MenuUtils.cancelInput(input);

				if (daysStr.isEmpty()) {
					break;
				}

				try {
					int days = Integer.parseInt(daysStr);

					if (days > 0) {
						festival.setNumberOfDays(days);
						break;
					}

					System.out.println("Number of days must be greater than 0.\n");

				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid whole number.\n");
				}
			}

			boolean campingIncluded = InputValidation.getYesNoInput(
					input,
					"Is camping included? (Yes/No, current: "
							+ (festival.isCampingIncluded() ? "Yes" : "No") + "): ",
					festival.isCampingIncluded(),
					true
			);

			festival.setCampingIncluded(campingIncluded);

			// Club gig
		} else if (event instanceof ClubGig) {
			ClubGig clubGig = (ClubGig) event;

			while (true) {
				System.out.print("Enter new age limit (current: "
						+ clubGig.getAgeLimit() + "): ");

				String ageStr = MenuUtils.cancelInput(input);

				if (ageStr.isEmpty()) {
					break;
				}

				try {
					int age = Integer.parseInt(ageStr);

					if (age >= 0 && age <= 100) {
						clubGig.setAgeLimit(age);
						break;
					}

					System.out.println("Age limit must be between 0 and 100.\n");

				} catch (NumberFormatException e) {
					System.out.println("Please enter a valid whole number.\n");
				}
			}

			while (true) {
				System.out.print("Enter new ticket price (current: "
						+ clubGig.getEntryFee() + "): ");

				String priceStr = MenuUtils.cancelInput(input);

				if (priceStr.isEmpty()) {
					break;
				}

				try {
					double price = Double.parseDouble(priceStr);

					if (Double.isFinite(price) && price >= 0) {
						clubGig.setEntryFee(price);
						break;
					}

					System.out.println("Ticket price cannot be negative.\n");

				} catch (NumberFormatException e) {
					System.out.println(
							"Please enter a valid price, e.g. 150.50.\n"
					);
				}
			}

			System.out.print("Enter new set times (current: "
					+ clubGig.getOpeningHours() + "): ");
			String setTimes = MenuUtils.cancelInput(input);

			if (!setTimes.isEmpty()) {
				clubGig.setOpeningHours(setTimes);
			}
		}

		// Save changes
		eventService.updateEvent(id, event);

		System.out.println("\nEvent updated successfully!");
	}


	private void deleteEvent() {
		System.out.println("\n==========================");
		System.out.println("         DELETE EVENT");
		System.out.println("==========================");

		MenuUtils.printCancelInfo();

		for (var event : eventService.getAllEvents()) {
			System.out.println("  ID " + event.getId() + ": "
					+ event.getArtist() + " @ " + event.getVenue()
					+ " (" + event.getDate() + ")");
		}


		System.out.print("\n Enter event ID to delete: ");

		try {
			int deleteById = Integer.parseInt(MenuUtils.cancelInput(input));

			eventService.deleteEvent(deleteById);
			System.out.println("Event with ID " + deleteById + " deleted successfully!");

		} catch (NumberFormatException ex) {
			System.out.println("Invalid input. Event ID must be a number.");
		} catch (IllegalArgumentException ex) {
			System.out.println(ex.getMessage());
		}
	}
	}

