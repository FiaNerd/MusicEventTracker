import data.DummyData;
import services.EventService;
import views.Menu;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		EventService eventService = new EventService();
		Scanner input = new Scanner(System.in);

		DummyData.loadData(eventService);

		Menu menu = new Menu(eventService, input);
		menu.start();
	}
}
