package views;

import services.EventService;
import services.ReviewService;
import utils.InputValidation;

import java.util.Scanner;

public class Menu {
	private EventService eventService;
	private ReviewService reviewService;
	private Scanner input;
	private EventMenu eventMenu;
	private ReviewMenu reviewMenu;

	public Menu(EventService eventService, ReviewService reviewService, Scanner input) {
		this.eventService = eventService;
		this.reviewService = reviewService;
		this.input = input;

		this.eventMenu = new EventMenu(eventService, input);
		this.reviewMenu = new ReviewMenu(eventService, reviewService, input);
	}

	public void start() {
		int choice = 0;

		do {
			printMenu();

				choice = InputValidation.getNumberInRange(input, 1, 3,"\nChoose an option (1-3): ");

				switch (choice) {
					case 1:
						eventMenu.start();
						break;
					case 2:
						reviewMenu.start();
						break;
					case 3:
						System.out.println("Exit application. Welcome back soon!");
						break;
				}
		} while (choice != 3);
	}


	private void printMenu() {
		System.out.println("\n==========================");
		System.out.println("       Welcome to");
		System.out.println("   MUSIC EVENT TRACKER   ");
		System.out.println("==========================");
		System.out.println(" [1] EVENTS");
		System.out.println(" [2] REVIEW");
		System.out.println(" [3] EXIT");
		System.out.println("==========================");
	}
}