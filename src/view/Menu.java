package view;

import models.Concert;
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
		eventService.addEvent(new Concert(1, "Evergrey", "Progressive Metal", "Mjeriet",
				"Lund", "Sweden", "2026-11-11", "First class progressive metal from Gothenburg", true,
				"","Architects of A New Wave tour", SeatingType.STANDING ));

		printMenu();
	}

	private void printMenu(){
		IO.println("\n ---- MUSIC EVENT TRACKER ----");
		IO.println("[1.] SHOW ALL EVENTS");
	}

}
