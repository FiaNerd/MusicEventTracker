import data.DummyData;
import services.EventService;
import services.ReviewService;
import views.Menu;
import java.util.Scanner;

/**
 * Starts the Music Event Tracker application.
 */
public class Main {

	/**
	 * Initializes the services, loads sample data and starts the main menu.
	 *
	 * @param args command-line arguments
	 */
	public static void main(String[] args) {
		EventService eventService = new EventService();
		ReviewService reviewService = new ReviewService();
		Scanner input = new Scanner(System.in);

		// Load sample events into the application.
		DummyData.loadData(eventService);

		// Create and start the main menu.
		Menu menu = new Menu(eventService, reviewService, input);
		menu.start();

		// Close the scanner when the application exits.
		input.close();
	}
}