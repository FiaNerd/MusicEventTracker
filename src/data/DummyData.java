package data;

import models.ClubGig;
import models.Concert;
import models.Festival;
import models.SeatingType;
import models.Review;
import services.EventService;

/**
 * Provides sample events and reviews for testing the application.
 */
public class DummyData {

	/**
	 * @param eventService the service used to add events to the application
	 */
	public static void loadData(EventService eventService) {

		// Create a concert and add sample reviews
		Concert evergrey = new Concert(
				1, "Evergrey", "Progressive Metal", "Mejeriet",
				"Lund", "Sweden", "2026-11-11",
				"First class progressive metal from Gothenburg",
				false, "", "Architects of A New Wave tour",
				SeatingType.STANDING
		);

		evergrey.addReview(new Review(5, "Magical performance! Tom S. was on fire."));
		evergrey.addReview(new Review(3, "Great sound at Mejeriet, but a bit crowded."));
		evergrey.addReview(new Review(2, "Too little space for the band. Too crowded."));
		evergrey.addReview(new Review(1, "Too much light on stage and the sound was not great."));

		eventService.addEvent(evergrey);

		// Create a festival and add sample reviews
		Festival hollywoodUndead = new Festival(
				2, "Hollywood Undead", "Hiphop, rap, metal",
				"Brutal Assault", "Jaromer", "Czech Republic",
				"2026-08-08",
				"Hiphopper that loves metal, doing a mix between hiphop and metal.",
				true, false, 4
		);

		hollywoodUndead.addReview(new Review(5, "Best festival atmosphere ever!"));
		hollywoodUndead.addReview(new Review(4, "A bit muddy, but an incredible show."));

		eventService.addEvent(hollywoodUndead);

		// Create a club gig and add sample reviews
		ClubGig theHaunted = new ClubGig(
				3, "The Haunted", "Death Metal", "Mejeriet",
				"Lund", "Sweden", "2026-11-21",
				"Swedish old death metal group", false,
				18, 150, "19:00-01:00"
		);

		theHaunted.addReview(new Review(5, "Pure brutal energy in a small garage! Love it."));
		theHaunted.addReview(new Review(4, "Loud, sweaty and perfect."));

		eventService.addEvent(theHaunted);
	}
}