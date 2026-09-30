package models;

/**
 * Represents the different seating or venue arrangement types available for concerts
 * and other events (e.g., standing floors, reserved seats, or booth standing and seating).
 */
public enum SeatingType {
	/** Standing area, typically close to the stage. */
	STANDING,

	/** Reserved seated areas in the venue. */
	SEATED,

	/** A combination of both standing and seated areas. */
	MIXED
}