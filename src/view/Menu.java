package view;

import models.*;
import services.EventService;
import utils.InputValidation;

import java.util.Scanner;

public class Menu {
	private EventService eventService;
	private Scanner input;
	private EventMenu eventMenu;
	private ReviewMenu reviewMenu;

	public Menu(EventService eventService, Scanner input) {
		this.eventService = eventService;
		this.input = input;

		this.eventMenu = new EventMenu(eventService, input);
		this.reviewMenu = new ReviewMenu(eventService, input);
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
					default:
						System.out.println("Not valid input. only number between 1-3");
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