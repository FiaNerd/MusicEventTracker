package models;
/**
 * Represents a Festival event, extending the EventModel superclass.
 * Adds unique attributes specific to festivals, such as camping availability.
 */
public class Festival extends EventModel {
	private boolean campingIncluded;
	private int numbersOfDays;

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
	 * @param numbersOfDays   Number of days the festival lastst
	 */
	public Festival(int id, String artist, String genre, String venue, String city, String country, String date,
	                String description, boolean isOutdoor, boolean campingIncluded, int numbersOfDays) {
		super(id, artist, genre, venue, city, country, date, description, isOutdoor);
		this.campingIncluded = campingIncluded;
		this.numbersOfDays = numbersOfDays;
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
	public int getNumbersOfDays(){
		return numbersOfDays;
	}

	/**
	 * Sets the number of days the festival lasts.
	 * @param numbersOfDays the number of days to set
	 */
	public void setNumbersOfDays(int numbersOfDays){
		this.numbersOfDays = numbersOfDays;
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
				"\nNumber of days: " + numbersOfDays;
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
		return super.toString().replace("EventModel", "Festival") +
				"\n  campingIncluded: " + campingIncluded +
				"\n  numbersOfDays: " + numbersOfDays;
	}
}
