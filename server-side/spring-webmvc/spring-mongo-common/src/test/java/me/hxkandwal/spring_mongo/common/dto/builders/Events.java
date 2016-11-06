package me.hxkandwal.spring_mongo.common.dto.builders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.hxkandwal.spring_mongo.common.dto.Event;

public class Events {
		
	public static final Event CREATE_EVENT = new EventBuilder().withEventName("test-create-event").withEventDate(new Date()).build();
	
	public static final Event CREATE_EVENT_WITH_LOCATION = new EventBuilder().withEventName("test-create-event-with-location").withEventLocation("US").withEventDate(new Date()).build();
	
	public static final Event PERSISTED_EVENT_1 = new EventBuilder().withId("1").withEventName("test-persisted-event-1").withEventLocation("US").withEventDate(new Date()).build();
	
	public static final Event PERSISTED_EVENT_2 = new EventBuilder().withId("2").withEventName("test-persisted-event-2").withEventLocation("IN").withEventDate(new Date()).build();
	
	public static final Event PERSISTED_EVENT_3 = new EventBuilder().withId("2").withEventName("test-persisted-event-3").withEventLocation("CA").withEventDate(new Date()).build();
	
	public static Event copy(Event srcEvent) {
		return new EventBuilder()
				.withId(srcEvent.getId())
				.withEventName(srcEvent.getEventName())
				.withEventLocation(srcEvent.getEventLocation())
				.withEventDate(srcEvent.getEventDate())
				.build();
	}
	
	@SuppressWarnings("serial")
	public static final List<Event> PERSISTED_EVENT_LIST = new ArrayList<Event>() {
		{
			add(PERSISTED_EVENT_1);
			add(PERSISTED_EVENT_2);
			add(PERSISTED_EVENT_3);
		}
	};
}
