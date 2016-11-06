package me.hxkandwal.spring_mongo.services.unit;

import static me.hxkandwal.spring_mongo.common.dto.builders.Events.CREATE_EVENT;
import static me.hxkandwal.spring_mongo.common.dto.builders.Events.PERSISTED_EVENT_1;
import static me.hxkandwal.spring_mongo.common.dto.builders.Events.PERSISTED_EVENT_LIST;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Optional;

import me.hxkandwal.spring_mongo.biz.manager.EventServiceManager;
import me.hxkandwal.spring_mongo.common.dto.Event;
import me.hxkandwal.spring_mongo.services.EventRestService;
import me.hxkandwal.spring_mongo.services.proxy.location.LocationLookupProxyService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:testApplicationContext.xml" })
public class EventRestServiceUnitTest {
	
	@InjectMocks
	@Autowired
	private EventRestService eventRestService;
	
	@Mock
	private EventServiceManager eventServiceManager;

	@Mock
	private LocationLookupProxyService locationLookupProxyService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testCreateEvent() throws Exception {
		when(eventServiceManager.createEvent(any(Event.class))).thenReturn(PERSISTED_EVENT_1);
        when(locationLookupProxyService.lookupLocation(anyString())).thenReturn(Optional.of("United States"));

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setRemoteAddr("localhost");
        
		ResponseEntity<Event> responseEventEntity = eventRestService.createEvent(CREATE_EVENT, mockHttpServletRequest);
		Event event = responseEventEntity.getBody();
		
		assertEquals(PERSISTED_EVENT_1.getId(), event.getId());
		assertEquals(PERSISTED_EVENT_1.getEventName(), event.getEventName());
		assertEquals(PERSISTED_EVENT_1.getEventLocation(), event.getEventLocation());
		assertEquals(PERSISTED_EVENT_1.getEventDate(), event.getEventDate());
	}

	@Test
	public void testGetEvents() {
		when(eventServiceManager.getEvents()).thenReturn(PERSISTED_EVENT_LIST);
		
		ResponseEntity<List<Event>> eventServiceResponse = eventRestService.getEvents();
		List<Event> events = eventServiceResponse.getBody();
		
		assertEquals(3, events.size());
	}

}