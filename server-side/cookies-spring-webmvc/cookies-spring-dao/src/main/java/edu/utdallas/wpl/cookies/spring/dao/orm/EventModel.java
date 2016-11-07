package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "events")
public class EventModel {

	@Id
	private String id;
	
	@Field
	private String eventName;
	
	@Field
	private String eventLocation;
	
	@Field
	private Date eventDate;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getEventName() {
		return eventName;
	}
	
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	
	public String getEventLocation() {
		return eventLocation;
	}
	
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
	public Date getEventDate() {
		return eventDate;
	}
	
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	@Override
	public String toString() {
		return "Event [id=" + id + ", eventName=" + eventName + ", eventLocation=" + eventLocation
				+ ", eventDate=" + eventDate + "]";
	}
	
}