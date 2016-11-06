package me.hxkandwal.spring_mongo.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import me.hxkandwal.spring_mongo.common.dto.Event;

public interface EventRestService {

	ResponseEntity<Event> createEvent(Event event,  HttpServletRequest request);

	ResponseEntity<List<Event>> getEvents();
	
	ResponseEntity<Event> getEvent(String eventId);
	
	ResponseEntity<Event> updateEvent(Event event);
	
	ResponseEntity<String> deleteEvent(String eventId);
	
}
