package utils;

import models.EventModel;
import java.util.ArrayList;
import java.util.List;

/**
 *  Validates event data and collects any validation errors.
 */
public class EventValidator {

	public static List<String> validate(EventModel event) {

		List<String> errors = new ArrayList<>();

		if (event.getArtist() == null || event.getArtist().isBlank()) {
			errors.add("Artist is required\n");
		}

		if (event.getGenre() == null || event.getGenre().isBlank()) {
			errors.add("Genre is required\n");
		}

		if (event.getVenue() == null || event.getVenue().isBlank()) {
			errors.add("Venue is required\n");
		}

		if (event.getDescription() != null
				&& event.getDescription().length() > 500) {
			errors.add("Description must be at most 500 characters\n");
		}

		return errors;
	}
}
