import services.EventService;
import view.Menu;
import java.util.Scanner;

public class Main{
	public static void main(String[] args) {
		EventService eventService = new EventService();

		Scanner scanner = new Scanner(System.in);

		Menu menu = new Menu(eventService, scanner);

		menu.start();
	}
}
