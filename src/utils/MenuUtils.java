package utils;

import java.util.Scanner;
import java.util.concurrent.CancellationException;

/**
 * Provides utility methods for menu navigation and input cancellation.
 */
public class MenuUtils {

	/**
	 * Pauses the program until the user presses Enter.
	 *
	 * @param input the Scanner used to read console input
	 */
	public static void pressEnterToContinue(Scanner input){
		System.out.println("Press [ Enter ] to return to the menu...");
		input.nextLine();
	}

	/**
	 * Reads user input and cancels the current operation if the user enters ESC.
	 *
	 * @param input the Scanner used to read console input
	 * @return the trimmed input entered by the user
	 * @throws CancellationException if the user enters ESC
	 */
	public static String cancelInput(Scanner input) {
		String value = input.nextLine().trim();

		if (value.equalsIgnoreCase("ESC")) {
			throw new CancellationException();
		}

		return value;
	}

	/**
	 * Informs the user how to cancel the current operation.
	 */
	public static void printCancelInfo() {
		System.out.println("\n(Type: ESC and press Enter to return to event menu)\n");
	}
}
