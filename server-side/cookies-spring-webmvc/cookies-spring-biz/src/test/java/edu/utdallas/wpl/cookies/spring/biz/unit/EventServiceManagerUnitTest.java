package edu.utdallas.wpl.cookies.spring.biz.unit;

import static edu.utdallas.wpl.cookies.spring.common.dto.builders.Events.CREATE_EVENT;
import static edu.utdallas.wpl.cookies.spring.common.dto.builders.Events.PERSISTED_EVENT_1;
import static edu.utdallas.wpl.cookies.spring.common.dto.builders.Events.PERSISTED_EVENT_2;
import static edu.utdallas.wpl.cookies.spring.common.dto.builders.Events.PERSISTED_EVENT_3;
import static edu.utdallas.wpl.cookies.spring.dao.orm.builders.EventModels.PERSISTED_EVENT_MODELS;
import static edu.utdallas.wpl.cookies.spring.dao.orm.builders.EventModels.PERSISTED_EVENT_MODEL_1;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.utdallas.wpl.cookies.spring.biz.manager.EventServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.Event;
import edu.utdallas.wpl.cookies.spring.dao.orm.EventModel;
import edu.utdallas.wpl.cookies.spring.dao.repository.EventRepository;


/**
 * A sophisticated test-bed for business level mock testing. This includes,
 * dozer mapping tests and application-level flow mock tests.
 * 
 * @author Heman
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testBusinessContext.xml" })
public class EventServiceManagerUnitTest {

	@InjectMocks
	@Autowired
	private EventServiceManager eventServiceManager;

	@Mock
	private EventRepository eventRepository;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void a_testCreateEvent() {
		when(eventRepository.save(any(EventModel.class))).thenReturn(PERSISTED_EVENT_MODEL_1);

		Event event = eventServiceManager.createEvent(CREATE_EVENT);

		assertNotNull(event);
		assertNotNull(event.getId());

		assertEquals(PERSISTED_EVENT_MODEL_1.getId(), event.getId());
		assertEquals(PERSISTED_EVENT_MODEL_1.getEventName(), event.getEventName());
		assertEquals(PERSISTED_EVENT_MODEL_1.getEventLocation(), event.getEventLocation());
		assertEquals(PERSISTED_EVENT_MODEL_1.getEventDate(), event.getEventDate());
	}

	@Test
	public void b_testUpdateEvent() {
		when(eventRepository.save(any(EventModel.class))).thenReturn(PERSISTED_EVENT_MODEL_1);

		Event event = eventServiceManager.updateEvent(CREATE_EVENT);

		assertNotNull(event);
		assertNotNull(event.getId());

		assertEquals(PERSISTED_EVENT_MODEL_1.getId(), event.getId());
		assertEquals(PERSISTED_EVENT_MODEL_1.getEventName(), event.getEventName());
		assertEquals(PERSISTED_EVENT_MODEL_1.getEventLocation(), event.getEventLocation());
		assertEquals(PERSISTED_EVENT_MODEL_1.getEventDate(), event.getEventDate());
	}

	@Test
	public void c_testGetEvent() {
		when(eventRepository.findOne(anyString())).thenReturn(PERSISTED_EVENT_MODEL_1);

		Event event = eventServiceManager.getEvent("");

		assertNotNull(event);
		assertNotNull(event.getId());

		assertEquals(PERSISTED_EVENT_MODEL_1.getId(), event.getId());
		assertEquals(PERSISTED_EVENT_MODEL_1.getEventName(), event.getEventName());
		assertEquals(PERSISTED_EVENT_MODEL_1.getEventLocation(), event.getEventLocation());
		assertEquals(PERSISTED_EVENT_MODEL_1.getEventDate(), event.getEventDate());
	}

	@Test
	public void d_testGetEvents() {
		when(eventRepository.findAll()).thenReturn(PERSISTED_EVENT_MODELS);

		List<Event> events = eventServiceManager.getEvents();
		
		assertNotNull(events);
		assertThat(events.size(), equalTo(PERSISTED_EVENT_MODELS.size()));
	}

	@Test
	public void e_testDeleteEvent() {
		doNothing().when(eventRepository).delete(any(EventModel.class));

		eventServiceManager.deleteEvent(PERSISTED_EVENT_1);
		eventServiceManager.deleteEvent(PERSISTED_EVENT_2);
		eventServiceManager.deleteEvent(PERSISTED_EVENT_3);
	}

}