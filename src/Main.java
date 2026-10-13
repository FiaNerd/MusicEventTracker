import data.DummyData;
import services.EventService;
import services.ReviewService;
import views.Menu;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		EventService eventService = new EventService();
		ReviewService reviewService = new ReviewService();
		Scanner input = new Scanner(System.in);

		DummyData.loadData(eventService);

		Menu menu = new Menu(eventService, reviewService, input);
		menu.start();
	}
}
