package models;

/**
 * Represents a generic music event, acting as the base class (superclass)
 * for all specific event types such as Concerts and Festivals.
 * Handles core attributes and default values shared across all events.
 */
public class EventModel {
	private int id;
	private String artist;
	private String genre;
	private String venue;
	private String city;
	private String country;
	private String date;
	private String description;

	// Default values initialized directly instead of requiring them in the constructor
	private boolean isFinished = false;
	private boolean isOutdoor;

	/**
	 * Constructs a new EventModel with all required core details.
	 *
	 * @param id          Unique identifier for the event
	 * @param artist      The main artist or performing act
	 * @param genre       Music genre
	 * @param venue       Venue name
	 * @param city        City where the event takes place
	 * @param country     Country where the event takes place
	 * @param date        Date of the event
	 * @param description Brief description of the event
	 * @param isOutdoor   True if the event is outdoors, false if indoors
	 */
	public EventModel(int id, String artist, String genre, String venue, String city, String country, String date,
	                  String description, boolean isOutdoor) {
		this.id = id;
		this.artist = artist;
		this.genre = genre;
		this.venue = venue;
		this.city = city;
		this.country = country;
		this.date = date;
		this.description = description;
		this.isOutdoor = isOutdoor;
	}

	/**
	 * Gets the unique identifier of the event.
	 * @return the event id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique identifier of the event.
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the performing artist.
	 * @return the artist name
	 */
	public String getArtist() {
		return artist;
	}

	/**
	 * Sets the performing artist.
	 * @param artist the artist name to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}

	/**
	 * Gets the music genre of the event.
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * Sets the music genre of the event.
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getVenue(){ return venue; }

	public void setVenue(String venue) { this.venue = venue; }

	/**
	 * Gets the venue or city where the event takes place.
	 * @return the place name
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the venue or city where the event takes place.
	 * @param city the place to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the country where the event takes place.
	 * @return the country name
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country where the event takes place.
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Gets the date of the event.
	 * @return the date string
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date of the event.
	 * @param date the date string to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets a brief description of the event.
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets a brief description of the event.
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Checks if the event has already taken place.
	 * @return true if finished, false otherwise
	 */
	public boolean isFinished() {
		return isFinished;
	}

	/**
	 * Sets the finished status of the event.
	 * @param finished boolean value for event completion
	 */
	public void setFinished(boolean finished) {
		isFinished = finished;
	}

	/**
	 * Checks if the event is held outdoors.
	 * @return true if outdoor, false if indoor
	 */
	public boolean isOutdoor() {
		return isOutdoor;
	}

	/**
	 * Sets whether the event is held outdoors.
	 * @param outdoor boolean value for outdoor status
	 */
	public void setOutdoor(boolean outdoor) {
		isOutdoor = outdoor;
	}


	//---- OVERRIDE METHOD ----
	/**
	 * Returns a formatted text representation of the event.
	 * Overrides Object.toString() and serves as a base implementation
	 * that can be extended and reused by subclasses.
	 *
	 * @return formatted event information as a String
	 */
	@Override
	public String toString() {
		return "EventModel" +
				"\n  Id: " + id +
				"\n  Artist: " + artist +
				"\n  Genre: " + genre +
				"\n  Venue: " + venue +
				"\n  City: " + city +
				"\n  Country: " + country +
				"\n  Date: " + date +
				"\n  Outdoor: " + (isOutdoor ? "Yes" : "No") +
				"\n  Event is finished: " + (isFinished ? "Yes" : "No");
	}
}