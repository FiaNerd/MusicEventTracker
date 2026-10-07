package models;

import interfaces.Reviewable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Festival event, extending the EventModel superclass.
 * Adds unique attributes specific to festivals, such as camping availability.
 */
public class Festival extends EventModel implements Reviewable {
	private boolean campingIncluded;
	private int numberOfDays;
	private List<Review> reviews = new ArrayList<>();

	/**
	 * Constructs a new Festival instance.
	 * Uses super() to initialize inherited fields from EventModel.
	 *
	 * @param id              Unique identifier for the event
	 * @param artist          The main artist or performing act
	 * @param genre           Music genre
	 * @param venue           Venue name
	 * @param city            City where the event takes place
	 * @param country         Country where the festival takes place
	 * @param date            Date of the festival
	 * @param description     Brief description of the event
	 * @param isOutdoor       True if the festival is outdoors, false otherwise
	 * @param campingIncluded True if camping is included in the ticket, false otherwise
	 * @param numberOfDays   Number of days the festival lastst
	 */
	public Festival(int id, String artist, String genre, String venue, String city, String country, String date,
	                String description, boolean isOutdoor, boolean campingIncluded, int numberOfDays) {
		super(id, artist, genre, venue, city, country, date, description, isOutdoor);
		this.campingIncluded = campingIncluded;
		this.numberOfDays = numberOfDays;
	}


	/**
	 * Checks if camping is included for this festival.
	 * @return true if camping is included, false otherwise
	 */
	public boolean isCampingIncluded() {
		return campingIncluded;
	}


	/**
	 * Sets whether camping is included for this festival.
	 * @param campingIncluded boolean value for camping availability
	 */
	public void setCampingIncluded(boolean campingIncluded) {
		this.campingIncluded = campingIncluded;
	}

	/**
	 * Gets the number of days the festival lasts.
	 * @return the number of days
	 */
	public int getNumberOfDays(){
		return numberOfDays;
	}

	/**
	 * Sets the number of days the festival lasts.
	 * @param numberOfDays the number of days to set
	 */
	public void setNumberOfDays(int numberOfDays){
		this.numberOfDays = numberOfDays;
	}


	//---- OVERRIDE METHOD ----
	/**
	 * Returns a detailed description of the festival, including base event details,
	 * whether camping is included, and the duration in days.
	 * Overrides EventModel.getDescription() to add festival-specific information.
	 *
	 * @return formatted festival description string
	 */
	@Override
	public String getDescription() {
		return super.getDescription() +
				"\nCamping included: " + (campingIncluded ? "Yes" : "No") +
				"\nNumber of days: " + numberOfDays;
	}

	/**
	 * Returns a formatted text representation of the festival object.
	 * Overrides EventModel.toString() to include festival-specific attributes
	 * like camping status and number of days.
	 *
	 * @return formatted festival information as a String
	 */
	@Override
	public String toString() {
		double averageRating = getAverageRating();

		String ratingText;

		if (averageRating == 0.0) {
			ratingText = "No ratings yet";
		} else {
			ratingText = String.format("%.1f", averageRating);
		}

		return super.toString().replace("EventModel", "Festival") +
				"\n  Camping included: " + (campingIncluded ? "Yes" : "No") +
				"\n  Number of Days: " + numberOfDays +
				"\n  Average rating: " + ratingText;
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
