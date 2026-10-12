package utils;

import java.util.Scanner;

public class MenuUtils {
	public static void pressEnterToContinue(Scanner input){
		System.out.println("Press [ Enter ] to return to the menu...");
		input.nextLine();
	}
}
