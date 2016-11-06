package me.hxkandwal.spring_mongo.services.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Optional;

import me.hxkandwal.spring_mongo.biz.manager.EventServiceManager;
import me.hxkandwal.spring_mongo.common.dto.Event;
import me.hxkandwal.spring_mongo.services.EventRestService;
import me.hxkandwal.spring_mongo.services.proxy.location.LocationLookupProxyService;

@Controller
@RequestMapping("/api")
public  class EventRestServiceImpl implements EventRestService {

    private static final Logger LOG = LoggerFactory.getLogger(EventRestService.class);

	@Autowired
	private EventServiceManager eventServiceManager;

	@Autowired
    private LocationLookupProxyService locationLookupProxyService;

	@Override
	@RequestMapping(value = "/event", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Event> createEvent(@RequestBody Event event, HttpServletRequest request) {
		Optional<String> optionalLocation;

		if ((optionalLocation = getLocation(request.getRemoteAddr())).isPresent())
			event.setEventLocation(optionalLocation.get());

		Event persistedEvent = eventServiceManager.createEvent(event);

		if (persistedEvent.getId() == null)
			return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);

		return ResponseEntity.ok(persistedEvent);
	}

	@Override
	@RequestMapping(value = "/events", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Event>> getEvents() {
		return ResponseEntity.ok(eventServiceManager.getEvents());
	}

	@Override
	@RequestMapping(value = "/event/{eventId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Event> getEvent(@PathVariable String eventId) {
		return ResponseEntity.ok(eventServiceManager.getEvent(eventId));
	}

	@Override
	@RequestMapping(value = "/event", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Event> updateEvent(@RequestBody Event event) {
		if (null == event.getId() || event.getId().isEmpty())
			return new ResponseEntity<Event>(HttpStatus.BAD_REQUEST);
		
		else if (eventServiceManager.getEvent(event.getId()) == null)
			return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
		
		else {
			Event persistedEvent = eventServiceManager.updateEvent(event);
			return ResponseEntity.ok(persistedEvent);
		}
	}

	@Override
	@RequestMapping(value = "/event/{eventId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteEvent(@PathVariable String eventId) {
		if (null == eventId || eventId.isEmpty())
			return ResponseEntity.badRequest().body("event id is empty");
		
		else if (eventServiceManager.getEvent(eventId) == null)
			return ResponseEntity.badRequest().body("event not found");
		else 
			eventServiceManager.deleteEvent(eventServiceManager.getEvent(eventId));
		
		return ResponseEntity.ok("success");
	}
	
    private Optional<String> getLocation(String ipAddress) {
        Optional<String> optionalLocation = Optional.absent();

        try {
            optionalLocation = locationLookupProxyService.lookupLocation(ipAddress);
        } catch (Exception e) {
            LOG.error("Failed to lookup location information. Will try again later.", e);
        }

        return optionalLocation;
    }
    
}