package models;

public class EventModel {
	private int id;
	private String artist;
	private String genre;
	private String place;
	private String date;
	private String description;
	private int rating;
	private boolean isFinished;

	/**
	 * Constructor for
	 * @param id
	 * @param artist
	 * @param genre
	 * @param place
	 * @param date
	 * @param description
	 */
	public EventModel(int id, String artist, String genre, String place, String date, String description) {
		this.id = id;
		this.artist = artist;
		this.genre = genre;
		this.place = place;
		this.date = date;
		this.description = description;
		this.rating = 0;
		this.isFinished = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getartist() {
		return artist;
	}

	public void setartist(String artist) {
		this.artist = artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean finished) {
		isFinished = finished;
	}
}
