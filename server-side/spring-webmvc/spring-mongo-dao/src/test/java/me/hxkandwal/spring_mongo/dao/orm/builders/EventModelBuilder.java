package me.hxkandwal.spring_mongo.dao.orm.builders;

import java.util.Date;

import me.hxkandwal.spring_mongo.dao.orm.EventModel;

public class EventModelBuilder {
	
	private EventModel eventModel;

	public EventModelBuilder() {
			eventModel=new EventModel();
	}
	
	public  EventModelBuilder withId(String id) {
		eventModel.setId(id);
		return this;
	}
	
	public  EventModelBuilder withEventName(String eventName) {
		eventModel.setEventName(eventName);
		return this;
	}

	public  EventModelBuilder withEventLocation(String eventLocation) {
		eventModel.setEventLocation(eventLocation);
		return this;
	}
	
	public  EventModelBuilder withEventDate(Date eventDate) {
		eventModel.setEventDate(eventDate);
		return this;
	}
	
	public EventModel build() {
		return eventModel;
	}
	
}
