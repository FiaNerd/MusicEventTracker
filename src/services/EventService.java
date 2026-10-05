package services;

import models.EventModel;
import models.Review;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class responsible for managing events.
 * Handles the business logic and full CRUD operations (Create, Read, Update, Delete).
 */
public class EventService {

	/** In-memory storage list containing all registered events. */
	private List<EventModel> events = new ArrayList<>();

	// ---- EVENT ----

	/**
	 * Adds a new event to the event list.
	 * Validates that the event is not null.
	 *
	 * @param event the EventModel object to add
	 * @throws IllegalArgumentException if the provided event is null
	 */
	public void addEvent(EventModel event) {
		if (event == null) {
			throw new IllegalArgumentException("Event can't be null.");
		}
		events.add(event);
	}

	/**
	 * Retrieves a list of all registered events.
	 *
	 * @return a List containing all events
	 */
	public List<EventModel> getAllEvents() {
		return events;
	}

	/**
	 * Finds and returns an event by its unique ID.
	 *
	 * @param id the unique ID of the event to find
	 * @return the EventModel if found, or null if no match exists
	 */
	public EventModel getEventById(int id) {
		for (EventModel event : events) {
			if (event.getId() == id) {
				return event;
			}
		}
		return null;
	}

	/**
	 * Updates an existing event based on its ID with new event data.
	 * Validates that the update data is not null and that the event exists.
	 *
	 * @param id the ID of the event to update
	 * @param updateEvent the new EventModel data to replace the old one
	 * @throws IllegalArgumentException if updateEvent is null or if no event with the given ID exists
	 */
	public void updateEvent(int id, EventModel updateEvent) {
		if (updateEvent == null) {
			throw new IllegalArgumentException("Update event can't be null");
		}

		EventModel existingEvent = getEventById(id);

		if (existingEvent == null) {
			throw new IllegalArgumentException("No event found by id: " + id);
		}

		int eventId = events.indexOf(existingEvent);
		events.set(eventId, updateEvent);
	}

	/**
	 * Deletes an event from the list based on its unique ID.
	 * Validates that the event exists before attempting removal.
	 *
	 * @param id the ID of the event to delete
	 * @throws IllegalArgumentException if no event with the given ID exists
	 */
	public void deleteEvent(int id) {
		EventModel existingEvent = getEventById(id);

		if (existingEvent == null) {
			throw new IllegalArgumentException("No event found by id: " + id);
		}

		events.remove(existingEvent);
	}

	//---- REVIEW ----
	// TODO: add Review functionality for adding, fetching, updating, and deleting reviews.
}