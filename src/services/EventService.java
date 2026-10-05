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
	}
