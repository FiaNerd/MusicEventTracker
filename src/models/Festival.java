package models;

public class Festival extends EventModel {
	private boolean campingIncluded;
	private int numbersOfDays;

	public Festival(int id, String artist, String genre, String place, String country, String date,
	                String description, boolean isOutdoor, boolean campingIncluded, int numbersOfDays) {
		super(id, artist, genre, place, country, date, description, isOutdoor);
		this.campingIncluded = campingIncluded;
		this.numbersOfDays = numbersOfDays;
	}

	public boolean isCampingIncluded() {
		return campingIncluded;
	}

	public void setCampingIncluded(boolean campingIncluded) {
		this.campingIncluded = campingIncluded;
	}

	public int getNumbersOfDays(){
		return numbersOfDays;
	}

	public void setNumbersOfDays(int numbersOfDays){
		this.numbersOfDays = numbersOfDays;
	}
}