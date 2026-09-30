package models;

public class EventModel {
		private int id;
		private String artist;
		private String genre;
		private String place;
		private String country;
		private String date;
		private String description;

		// setting fields as standard instead of sending it to the constructor
		private int rating = 0;
		private boolean isFinished = false;
		private boolean isOutdoor;
		private String review = "No review yet";

		public EventModel(int id, String artist, String genre, String place, String country, String date,
		                  String description, boolean isOutdoor) {
			this.id = id;
			this.artist = artist;
			this.genre = genre;
			this.place = place;
			this.country = country;
			this.date = date;
			this.description = description;
			this.isOutdoor = isOutdoor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public boolean isOutdoor() {
		return isOutdoor;
	}

	public void setOutdoor(boolean outdoor) {
		isOutdoor = outdoor;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
}
