	package services;

	import models.EventModel;

	import java.util.ArrayList;
	import java.util.List;

	public class EventService {
		private List<EventModel> events = new ArrayList<>();

		public void addEvent(EventModel event){
			if(event == null){
				throw new IllegalArgumentException("Event can`t be null. Try again. ");
			}
				events.add(event);
		}

		public List<EventModel> getAllEvents(){
			return events;
		}

		public EventModel getEventById(int id){
			for(EventModel event : events){
				if(event.getId() == id){
					return event;
				}
			}
				return null;
		}

		public void updateEvent(int id, EventModel updateEvent){
			if(updateEvent == null){
				throw new IllegalArgumentException("Update event can't be null");
			}

			EventModel existingEvent = getEventById(id);

			if(existingEvent == null){
				throw new IllegalArgumentException("No event found by id: " + id);
			}

			int eventId = events.indexOf(existingEvent);

			events.set(eventId, updateEvent);
		}

		public void deleteEvent(int id){
			EventModel existingEvent = getEventById(id);

			if(existingEvent == null){
				throw new IllegalArgumentException("No event found by id: " + id);
			}

			events.remove(existingEvent);
		}
	}
