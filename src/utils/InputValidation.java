package utils;

import java.util.Scanner;

public class InputValidation {

		public static int getNumberInRange(Scanner input, int min, int max) {

			int choice;

			do {
				System.out.print(
						"\n Choose a number (" + min + "-" + max + "): ");

				String userInput = input.nextLine().trim();

				try {
					choice = Integer.parseInt(userInput);

					if (choice < min || choice > max) {
						System.out.println(
								"\n[!] Invalid choice. Choose between "
										+ min + "-" + max + ". " );
					}

				} catch (NumberFormatException ex) {
					System.out.println(
							"\n[!] Invalid input. Please enter a number. ");
					choice = min - 1;
				}

			} while (choice < min || choice > max);

			return choice;
		}
}
