package models;

public class Consert extends EventModel {
	private String supportAct;
	private String tourName;
	private SeatingType seatingType;

	public Consert(int id, String artist, String genre, String place, String country, String date, String description,
	               boolean isOutdoor, String supportAct, String tourName, SeatingType seatingType) {
		super(id, artist, genre, place, country, date, description, isOutdoor);
		this.supportAct = supportAct;
		this.tourName = tourName;
		this.seatingType = seatingType;
	}

	public String getSupportAct() {
		return supportAct;
	}

	public void setSupportAct(String supportAct) {
		this.supportAct = supportAct;
	}

	public String getTourName() {
		return tourName;
	}

	public void setTourName(String tourName) {
		this.tourName = tourName;
	}

	public SeatingType getSeatingType() {
		return seatingType;
	}

	public void setSeatingType(SeatingType seatingType) {
		this.seatingType = seatingType;
	}
}
