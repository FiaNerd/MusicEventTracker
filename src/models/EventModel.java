package models;

public class EventModel {
	private int id;
	private String band;
	private String genre;
	private String place;
	private String date;
	private String description;
	private int rating;
	private boolean isFinished;

	public EventModel(int id, String band, String genre, String place, String date, String description, int rating, boolean isFinished) {
		this.id = id;
		this.band = band;
		this.genre = genre;
		this.place = place;
		this.date = date;
		this.description = description;
		this.rating = rating;
		this.isFinished = isFinished;
	}
}
