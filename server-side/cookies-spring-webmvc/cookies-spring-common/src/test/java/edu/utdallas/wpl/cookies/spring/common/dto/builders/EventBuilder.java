package edu.utdallas.wpl.cookies.spring.common.dto.builders;

import java.util.Date;

import edu.utdallas.wpl.cookies.spring.common.dto.Event;

public class EventBuilder {

	private Event event;

	public EventBuilder() {
		event = new Event();
	}

	public EventBuilder withId(String id) {
		event.setId(id);
		return this;
	}
	
	public EventBuilder withEventName(String eventName) {
		event.setEventName(eventName);
		return this;
	}

	public EventBuilder withEventLocation(String eventLocation) {
		event.setEventLocation(eventLocation);
		return this;
	}

	public EventBuilder withEventDate(Date eventDate) {
		event.setEventDate(eventDate);
		return this;
	}
	
	public Event build() {
		return event;
	}

}