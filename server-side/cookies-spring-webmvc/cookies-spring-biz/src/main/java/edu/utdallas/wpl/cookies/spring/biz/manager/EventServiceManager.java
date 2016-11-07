package edu.utdallas.wpl.cookies.spring.biz.manager;

import java.util.List;

import edu.utdallas.wpl.cookies.spring.common.dto.Event;

public interface EventServiceManager {

	public Event createEvent(Event event);
	
	public List<Event> getEvents();
	
	public Event getEvent(String id);
	
	public Event updateEvent(Event event);
	
	public void deleteEvent(Event event);
}
