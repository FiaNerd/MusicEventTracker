package models;

import interfaces.Reviewable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Concert event, extending the EventModel superclass.
 * Adds unique attributes specific to concerts, such as support acts, tour names,
 * and seating arrangement types with enums.
 */
public class Concert extends EventModel implements Reviewable {
	private String supportAct;
	private String tourName;
	private SeatingType seatingType;
	private List<Review> reviews = new ArrayList<>();
	/**
	 * Constructs a new Concert instance.
	 * Uses super() to initialize inherited fields from EventModel.
	 *
	 * @param id          Unique identifier for the event
	 * @param artist      The main artist or performing act
	 * @param genre       Music genre
	 * @param venue       Venue name
	 * @param city        City where the concert takes place
	 * @param country     Country where the concert takes place
	 * @param date        Date of the concert
	 * @param description Brief description of the event
	 * @param isOutdoor   True if the concert is outdoors, false otherwise
	 * @param supportAct  The opening act or support artist
	 * @param tourName    The name of the tour
	 * @param seatingType The seating arrangement (STANDING, SEATED, or BOOTH)
	 */
	public Concert(int id, String artist, String genre, String venue, String city, String country, String date, String description,
	               boolean isOutdoor, String supportAct, String tourName, SeatingType seatingType) {
		super(id, artist, genre, venue, city, country, date, description, isOutdoor);

		this.supportAct = supportAct;
		this.tourName = tourName;
		this.seatingType = seatingType;
	}

	/**
	 * Gets the support act for this concert.
	 * @return the support act name
	 */
	public String getSupportAct() {
		return supportAct;
	}

	/**
	 * Sets the support act for this concert.
	 * @param supportAct the support act name to set
	 */
	public void setSupportAct(String supportAct) {
		this.supportAct = supportAct;
	}

	/**
	 * Gets the tour name for this concert.
	 * @return the tour name
	 */
	public String getTourName() {
		return tourName;
	}

	/**
	 * Sets the tour name for this concert.
	 * @param tourName the tour name to set
	 */
	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	/**
	 * Gets the seating arrangement type for this concert.
	 * @return the SeatingType enum value
	 */
	public SeatingType getSeatingType() {
		return seatingType;
	}

	/**
	 * Sets the seating arrangement type for this concert.
	 * @param seatingType the SeatingType to set
	 */
	public void setSeatingType(SeatingType seatingType) {
		this.seatingType = seatingType;
	}


	//---- OVERRIDE METHODS ----
	/**
	 * Returns a detailed description of the concert.
	 * Extends the base event description with concert-specific information.
	 *
	 * @return formatted concert description as a String
	 */
	@Override
	public String getDescription() {
		return super.getDescription() +
				"\n  Support act: " + supportAct +
				"\n  Tour name: " + tourName +
				"\n  Seating type: " + seatingType;
	}

	/**
	 * Returns a formatted text representation of the concert.
	 * Builds upon the EventModel toString() output by adding concert-specific fields.
	 *
	 * @return formatted concert information as a String
	 */
	@Override
	public String toString() {
		return super.toString().replace("EventModel", "Concert") +
				"\n  Support: " + supportAct +
				"\n  TourName: " + tourName +
				"\n  Seating type: " + seatingType +
				"\n  Average rating: " + getAverageRating();
	}

	/**
	 * Adds a review to the list.
	 *
	 * @param review the review to add
	 */
	@Override
	public void addReview(Review review) {
		reviews.add(review);
	}

	/**
	 * Gets the list of reviews.
	 *
	 * @return the list of reviews
	 */
	@Override
	public List<Review> getReviews() {
		return reviews;
	}
}