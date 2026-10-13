package utils;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputValidation {


	public static int getNumberInRange(Scanner input, int min, int max, String prompt) {
		int choice;

		do {
			System.out.print(prompt);
			String userInput = input.nextLine().trim();

			if (userInput.isEmpty()) {
				System.out.println("[!] Please enter a menu option.");
				choice = min - 1;
				continue;
			}

			try {
				choice = Integer.parseInt(userInput);

				if (choice < min || choice > max) {
					System.out.println(
							"[!] Invalid choice. Choose between "
									+ min + "-" + max + "."
					);
				}

			} catch (NumberFormatException ex) {
				System.out.println("[!] Invalid input. Please enter a number.");
				choice = min - 1;
			}

		} while (choice < min || choice > max);

		return choice;
	}


	public static boolean getYesNoInput(
			Scanner input, String prompt, Boolean currentValue) {

		while (true) {
			System.out.print(prompt);
			String answer = input.nextLine().trim();

			if (answer.isEmpty() && currentValue != null) {
				return currentValue;
			} else if (answer.equalsIgnoreCase("yes")) {
				return true;
			} else if (answer.equalsIgnoreCase("no")) {
				return false;
			} else {
				System.out.println("Please enter Yes or No.");
			}
		}
	}
	public static String getDateInput(Scanner input, String prompt, String currentDate) {
		while (true) {
			System.out.print(prompt);
			String dateInput = input.nextLine().trim();

			if (dateInput.isEmpty() && currentDate != null) {
				return currentDate;
			}

			try {
				LocalDate date = LocalDate.parse(dateInput);

				if (!date.toString().equals(dateInput)) {
					System.out.println("Invalid date format. Use yyyy-MM-dd.");
					continue;
				}

				return dateInput;

			} catch (DateTimeParseException e) {
				System.out.println("Invalid date. Use yyyy-MM-dd.");
			}
		}
	}
}
