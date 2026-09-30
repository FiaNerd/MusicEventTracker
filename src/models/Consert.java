package models;

public class Consert extends EventModel {
	private boolean isOutdoor;
	private String supportAct;

	public Consert(int id, String artist, String genre, String place, String date, String description, boolean isOutdoor, String supportAct) {
		super(id, artist, genre, place, date, description);
		this.isOutdoor = isOutdoor;
		this.supportAct = supportAct;
	}

	public boolean isOutdoor() {
		return isOutdoor;
	}

	public void setOutdoor(boolean outdoor) {
		isOutdoor = outdoor;
	}

	public String getSupportAct() {
		return supportAct;
	}

	public void setSupportAct(String supportAct) {
		this.supportAct = supportAct;
	}
}
