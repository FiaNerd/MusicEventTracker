package view;

import models.ClubGig;
import models.Concert;
import models.Festival;
import models.SeatingType;
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

		// Dummy data
		eventService.addEvent(new Concert(1, "Evergrey", "Progressive Metal", "Mejeriet",
				"Lund", "Sweden", "2026-11-11", "First class progressive metal from Gothenburg", true,
				"","Architects of A New Wave tour", SeatingType.STANDING ));

		eventService.addEvent(new Festival(2, "Hollywood Undead", "Hiphop, rap, metal", "Brutal Assault", "Jaromer",
				"Czech Republic", "2026-08-08", "Hiphopper that loves metal, doing a mix between hiphop and metal.",
				true, false, 4));

		eventService.addEvent(new ClubGig(3, "The Haunted", "Death Metal", "Garage", "Höganäs", "Sweden", "2026-11-21",
				"Swedish old detah metal group", false, 18, 150, "19:00-01:00"));

		// TODO: Do a check so the user only puts in numbers in the festival numbers of days

		do {
			printMenu();
			System.out.print("Välj ett alternativ: ");

			try {
				choice = Integer.parseInt(input.nextLine());

				switch (choice) {
					case 1:
						System.out.println("Show alla events");
						showAllaEvents();
						break;
					case 2:
						System.out.println("Search event");
						searchEvent();
						break;
					case 3:
						System.out.println("Add Event");
						addEvent();
						break;
					case 4:
						System.out.println("Update Event");
						updateEvent();
						break;
					case 6:
						System.out.println("Exit application. Welcome back soon!");
						break;
					default:
						System.out.println("Not valid input. only number between 1-6");
				}

			} catch (NumberFormatException ex) {
				System.out.println("You need to put in a number between 1-6. Try again");
				choice = 0; // 0 so the loop will continue
			}
		} while(choice != 6); // until the user press 6, the loop will quit
	}

	private void printMenu(){
		System.out.println("\n ---- MUSIC EVENT TRACKER ----");
		System.out.println("[1] SHOW ALL EVENTS");
		System.out.println("[2] SEARCH EVENT");
		System.out.println("[3] ADD EVENT");
		System.out.println("[4] UPDATE EVENT");
		System.out.println("[5] DELETE EVENT");
		System.out.println("[6] EXIT");
	}

	// TODO: If the user input does chose anything else for standing, sitting or booth, then it will print out a error.

	// TODO: See if its time for making count how many events it is in the list total
	private void showAllaEvents(){
		System.out.println("---- ALL EVENTS ----");

		if(eventService.getAllEvents().isEmpty()){
			System.out.println("No events at the moment");
		}

		for(var event: eventService.getAllEvents()){
			System.out.println(event);
			System.out.println("------------------------");
		}
	}

	private void searchEvent(){
		System.out.println("\n ---- SEARCH EVENT BY ID ----");
		System.out.println("\n Enter event Id: ");

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
		System.out.println("\n---- ADD EVENT ----");
		System.out.println("What Event do you wanna add:");
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
		System.out.println("\n---- UPDATE EVENT ----");
		System.out.print("Enter event ID to update: ");

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

			System.out.println("Event updated successfully!");

		} catch (NumberFormatException ex) {
			System.out.println("Invalid number format. Please try again.");
		}
	}
}
