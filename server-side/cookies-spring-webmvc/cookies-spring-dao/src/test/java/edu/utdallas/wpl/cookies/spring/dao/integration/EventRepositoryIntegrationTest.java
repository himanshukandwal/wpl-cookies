package edu.utdallas.wpl.cookies.spring.dao.integration;

import static edu.utdallas.wpl.cookies.spring.dao.orm.builders.EventModels.EVENT_MODEL_1;
import static edu.utdallas.wpl.cookies.spring.dao.orm.builders.EventModels.EVENT_MODEL_2;
import static edu.utdallas.wpl.cookies.spring.dao.orm.builders.EventModels.copy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import edu.utdallas.wpl.cookies.spring.dao.orm.EventModel;
import edu.utdallas.wpl.cookies.spring.dao.repository.EventRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testDomainContext.xml" })
public class EventRepositoryIntegrationTest extends AbstractEmbeddedMongoIntegrationTest {
	
	@Autowired
	private EventRepository eventRepository;
    
	@Test
	public void testCreateEvent() {
		EventModel eventModel = eventRepository.save(EVENT_MODEL_1);

		assertNotNull(eventModel);
		assertNotNull(eventModel.getId());
		
		assertEquals(EVENT_MODEL_1.getEventName(), eventModel.getEventName());
		assertEquals(EVENT_MODEL_1.getEventDate(), eventModel.getEventDate());
		assertEquals(EVENT_MODEL_1.getEventLocation(), eventModel.getEventLocation());
	}
	
	@Test
	public void testUpdateEvent() {
		EventModel copyEventModel = copy(EVENT_MODEL_1);
		
		EventModel eventModel = eventRepository.save(copyEventModel);
		eventModel.setEventName("sample-event-1-updated");
		eventModel = eventRepository.save(eventModel);
		
		assertNotNull(eventModel);
		assertNotNull(eventModel.getId());
		
		assertNotEquals(EVENT_MODEL_1.getEventName(), eventModel.getEventName());
		assertEquals(EVENT_MODEL_1.getEventDate(), eventModel.getEventDate());
		assertEquals(EVENT_MODEL_1.getEventLocation(), eventModel.getEventLocation());
	}
	
	@Test
	public void testDeleteEvent() {
		EventModel eventModel = eventRepository.save(EVENT_MODEL_2);
		eventRepository.delete(eventModel.getId());
		
		assertFalse(eventRepository.exists(eventModel.getId()));
		assertNull(eventRepository.findOne(eventModel.getId()));
	}
	
}