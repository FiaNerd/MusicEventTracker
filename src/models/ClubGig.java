package models;

/**
 * Represents a club-based music event, extending the generic EventModel.
 * A ClubGig typically has additional rules and characteristics such as
 * age restrictions, entry fees, and specific opening hours.

 *  * This class demonstrates specialization through inheritance and
 * overrides methods to provide club-specific behavior.
 */
public class ClubGig extends EventModel {

	private int ageLimit;        // Minimum age required to enter the club
	private double entryFee;     // Entry price for the club event
	private String openingHours; // Opening hours for the club night (e.g., "22:00–03:00")

	/**
	 * Constructs a new ClubGig with both general event details and
	 * club-specific attributes.
	 *
	 * @param id            Unique identifier for the event
	 * @param artist        Main performing artist or DJ
	 * @param genre         Music genre played at the event
	 * @param place         Venue or club name
	 * @param country       Country where the event takes place
	 * @param date          Date of the event
	 * @param description   Brief description of the event
	 * @param isOutdoor     True if the event is outdoors, false otherwise
	 * @param ageLimit      Minimum age required to attend the event
	 * @param entryFee      Entry fee in SEK
	 * @param openingHours  Opening hours for the club event
	 */
	public ClubGig(int id, String artist, String genre, String place, String country, String date,
	               String description, boolean isOutdoor,
	               int ageLimit, double entryFee, String openingHours) {

		// Pass shared event data to the superclass constructor
		super(id, artist, genre, place, country, date, description, isOutdoor);

		this.ageLimit = ageLimit;
		this.entryFee = entryFee;
		this.openingHours = openingHours;
	}

	// --- GETTERS & SETTERS ---

	/** @return the minimum age required to enter the club */
	public int getAgeLimit() {
		return ageLimit;
	}

	/** @param ageLimit sets the minimum age required to enter the club */
	public void setAgeLimit(int ageLimit) {
		this.ageLimit = ageLimit;
	}

	/** @return the entry fee in SEK */
	public double getEntryFee() {
		return entryFee;
	}

	/** @param entryFee sets the entry fee in SEK */
	public void setEntryFee(double entryFee) {
		this.entryFee = entryFee;
	}

	/** @return the opening hours for the club event */
	public String getOpeningHours() {
		return openingHours;
	}

	/** @param openingHours sets the opening hours for the club event */
	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}


	//---- OVERIDE METHODS ----ss
	/**
	 * Returns a detailed description of the club gig, including base event details,
	 * age limit, entry fee, and opening hours.
	 * Overrides EventModel.getDescription() to add club-specific information.
	 *
	 * @return formatted club gig description string
	 */
	@Override
	public String getDescription(){
		return super.getDescription() +
				"\nAge limit: " + ageLimit +
				"\nEntry fee: " + entryFee + " SEK" +
				"\nOpening hours: " + openingHours;
	}

	/**
	 * Returns a short summary of the club event.
	 * This override ensures that ClubGig objects display club-specific
	 * information when printed, supporting polymorphic behavior.
	 *
	 * @return formatted club gig summary
	 */
	@Override
	public String toString() {
		return super.toString().replace("EventModel", "ClubGig") +
				"\n  ageLimit: " + ageLimit +
				"\n  entryFee: " + entryFee +
				"\n  openingHours: " + openingHours +
				"\n  averageRating: " + getAverageRating();
	}
}
