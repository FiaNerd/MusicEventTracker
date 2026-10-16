package utils;

import java.util.Scanner;
import java.util.concurrent.CancellationException;

public class MenuUtils {
	public static void pressEnterToContinue(Scanner input){
		System.out.println("Press [ Enter ] to return to the menu...");
		input.nextLine();
	}


	public static String cancelInput(Scanner input) {
		String value = input.nextLine().trim();

		if (value.equalsIgnoreCase("ESC")) {
			throw new CancellationException();
		}

		return value;
	}

	public static void printCancelInfo() {
		System.out.println("\n(Type: ESC and press Enter to return to event menu)\n");
	}
}
