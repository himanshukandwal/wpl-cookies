package me.hxkandwal.spring_mongo.biz.manager;

import java.util.List;

import me.hxkandwal.spring_mongo.common.dto.Event;

public interface EventServiceManager {

	public Event createEvent(Event event);
	
	public List<Event> getEvents();
	
	public Event getEvent(String id);
	
	public Event updateEvent(Event event);
	
	public void deleteEvent(Event event);
}
