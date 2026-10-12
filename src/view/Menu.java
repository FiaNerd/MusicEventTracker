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

			try {
				choice = InputValidation.getNumberInRange(input, 1, 3,"Choose an option (1-3): ");

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

			} catch (NumberFormatException ex) {
				System.out.println("You need to put in a number between 1-3. Try again");
				choice = 0; // 0 so the loop will continue
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