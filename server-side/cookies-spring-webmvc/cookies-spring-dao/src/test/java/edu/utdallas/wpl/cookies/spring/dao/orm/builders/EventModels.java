package edu.utdallas.wpl.cookies.spring.dao.orm.builders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.utdallas.wpl.cookies.spring.dao.orm.EventModel;

public class EventModels {

	public static final EventModel EVENT_MODEL_1 = new EventModelBuilder()
			.withEventName("sample-event-1")
			.withEventLocation("US")
			.withEventDate(new Date())
			.build();

	public static final EventModel EVENT_MODEL_2 = new EventModelBuilder()
			.withEventName("sample-event-2")
			.withEventLocation("IN")
			.withEventDate(new Date())
			.build();

	public static final EventModel EVENT_MODEL_3 = new EventModelBuilder()
			.withEventName("sample-event-3")
			.withEventLocation("UK")
			.withEventDate(new Date())
			.build();
	
	public static final EventModel PERSISTED_EVENT_MODEL_1 = new EventModelBuilder()
			.withId("abc-123")
			.withEventName("sample-persisted-event-1")
			.withEventLocation("US")
			.withEventDate(new Date())
			.build();

	public static final EventModel PERSISTED_EVENT_MODEL_2 = new EventModelBuilder()
			.withId("abc-456")
			.withEventName("sample-persisted-event-2")
			.withEventLocation("US")
			.withEventDate(new Date())
			.build();
	
	public static EventModel copy(EventModel srcEventModel) {
		return new EventModelBuilder()
				.withEventName(srcEventModel.getEventName())
				.withEventLocation(srcEventModel.getEventLocation())
				.withEventDate(srcEventModel.getEventDate())
				.build();
	}

	@SuppressWarnings("serial")
	public static final List<EventModel> EVENT_MODELS = new ArrayList<EventModel>() {
		{
			add(EVENT_MODEL_1);
			add(EVENT_MODEL_2);
			add(EVENT_MODEL_3);
		}
	};
	
	@SuppressWarnings("serial")
	public static final List<EventModel> PERSISTED_EVENT_MODELS = new ArrayList<EventModel>() {
		{
			add(PERSISTED_EVENT_MODEL_1);
			add(PERSISTED_EVENT_MODEL_2);
		}
	};

}
