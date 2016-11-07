package edu.utdallas.wpl.cookies.spring.biz.integration;

import static edu.utdallas.wpl.cookies.spring.common.dto.builders.Events.CREATE_EVENT;
import static edu.utdallas.wpl.cookies.spring.common.dto.builders.Events.CREATE_EVENT_WITH_LOCATION;
import static edu.utdallas.wpl.cookies.spring.common.dto.builders.Events.copy;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.utdallas.wpl.cookies.spring.biz.manager.EventServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.Event;
import edu.utdallas.wpl.cookies.spring.dao.integration.AbstractEmbeddedMongoIntegrationTest;

/**
 * A sophisticated test-bed for business level testing. This includes, dozer mapping tests and application-level flow tests.
 * 
 * @author Heman
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testBusinessContext.xml" })
public class EventServiceManagerIntegrationTest extends AbstractEmbeddedMongoIntegrationTest {

	@Autowired
	private EventServiceManager eventServiceManager;
	
	@Test
	public void a_testCreateEvent() {
		Event event = eventServiceManager.createEvent(CREATE_EVENT);
		
		assertNull(event.getEventLocation());
		assertNotNull(event);
		assertNotNull(event.getId());
		
		assertEquals(CREATE_EVENT.getEventName(), event.getEventName());
		assertEquals(CREATE_EVENT.getEventDate(), event.getEventDate());
		
		// for future handles (update event scenarios)
		CREATE_EVENT.setId(event.getId());
	}
	
	@Test
	public void a_testCreateEventWithLocation() {
		Event event = eventServiceManager.createEvent(CREATE_EVENT_WITH_LOCATION);
		
		assertNotNull(event);
		assertNotNull(event.getEventLocation());
		assertNotNull(event.getId());
		
		assertEquals(CREATE_EVENT_WITH_LOCATION.getEventName(), event.getEventName());
		assertEquals(CREATE_EVENT_WITH_LOCATION.getEventLocation(), event.getEventLocation());
		assertEquals(CREATE_EVENT_WITH_LOCATION.getEventDate(), event.getEventDate());
		
		// for future handles (update event scenarios)
		CREATE_EVENT_WITH_LOCATION.setId(event.getId());
	}
	
	@Test
	public void b_testUpdateEvent() {
		Event createEventCopy = copy(CREATE_EVENT);
		
		createEventCopy.setEventName(CREATE_EVENT.getEventName() + "-updated");
		
		Event event = eventServiceManager.updateEvent(createEventCopy);
		
		assertNotNull(event);
		assertNull(event.getEventLocation());
		assertNotNull(event.getId());
		
		assertNotEquals(CREATE_EVENT.getEventName(), event.getEventName());
		assertEquals(createEventCopy.getEventName(), event.getEventName());
		assertThat(event.getEventName(), containsString("-updated"));
		
		assertEquals(createEventCopy.getEventDate(), event.getEventDate());
		
		// for future handles (find event scenarios)
		CREATE_EVENT.setEventName(event.getEventName());
	}
	
	@Test
	public void b_testUpdateEventWithLocation() {
		Event createEventCopy = copy(CREATE_EVENT_WITH_LOCATION);
		
		createEventCopy.setEventName(CREATE_EVENT_WITH_LOCATION.getEventName() + "-updated");
		
		Event event = eventServiceManager.updateEvent(createEventCopy);
		
		assertNotNull(event);
		assertNotNull(event.getEventLocation());
		assertNotNull(event.getId());
		
		assertNotEquals(CREATE_EVENT_WITH_LOCATION.getEventName(), event.getEventName());
		assertEquals(createEventCopy.getEventName(), event.getEventName());
		assertThat(event.getEventName(), containsString("-updated"));
		
		assertEquals(createEventCopy.getEventLocation(), event.getEventLocation());
		assertEquals(createEventCopy.getEventDate(), event.getEventDate());
		
		// for future handles (find event scenarios)
		CREATE_EVENT_WITH_LOCATION.setEventName(event.getEventName());
	}
	
	@Test
	public void c_testGetEvent() {
		Event event = eventServiceManager.getEvent(CREATE_EVENT.getId());
		
		assertNotNull(event);
		assertEquals(CREATE_EVENT.getEventName(), event.getEventName());
		assertThat(event.getEventName(), containsString("-updated"));
		assertEquals(CREATE_EVENT.getEventDate(), event.getEventDate());
	}
	
	@Test
	public void d_testGetEvents() {
		List<Event> events = eventServiceManager.getEvents();
		
		assertNotNull(events);
		assertThat(events.size(), equalTo(2));
	}
			
	@Test
	public void e_testDeleteEvent() {
		eventServiceManager.deleteEvent(CREATE_EVENT);
		
		assertThat(eventServiceManager.getEvents().size(), equalTo(1));
	}
	
	@Test
	public void e_testDeleteEventWithLocation() {
		eventServiceManager.deleteEvent(CREATE_EVENT_WITH_LOCATION);
		
		assertThat(eventServiceManager.getEvents().size(), equalTo(0));
	}
	
}