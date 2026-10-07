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

		// Dummy data
		eventService.addEvent(new Concert(1, "Evergrey", "Progressive Metal", "Mejeriet",
				"Lund", "Sweden", "2026-11-11", "First class progressive metal from Gothenburg", true,
				"","Architects of A New Wave tour", SeatingType.STANDING ));

		eventService.addEvent(new Festival(2, "Hollywood Undead", "Hiphop, rap, metal", "Brutal Assault", "Jaromer",
				"Czech Republic", "2026-08-08", "Hiphopper that loves metal, doing a mix between hiphop and metal.",
				true, false, 4));

		eventService.addEvent(new ClubGig(3, "The Haunted", "Death Metal", "Garage", "Höganäs", "Sweden", "2026-11-21",
				"Swedish old detah metal group", false, 18, 150, "19:00-01:00"));

		// TODO: Do a check so the user only puts in numbbers in the festival numbers of days
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

	// TODO: If the user input does chose andything else for standing, sitting or booth, then it will print out a error.


}
