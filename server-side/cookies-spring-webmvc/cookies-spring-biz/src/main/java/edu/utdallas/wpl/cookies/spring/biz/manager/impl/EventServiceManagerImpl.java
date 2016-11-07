package edu.utdallas.wpl.cookies.spring.biz.manager.impl;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.utdallas.wpl.cookies.spring.biz.manager.EventServiceManager;
import edu.utdallas.wpl.cookies.spring.biz.manager.utils.DozerHelper;
import edu.utdallas.wpl.cookies.spring.common.dto.Event;
import edu.utdallas.wpl.cookies.spring.dao.orm.EventModel;
import edu.utdallas.wpl.cookies.spring.dao.repository.EventRepository;

/**
 * @author Heman
 */
@Service
public class EventServiceManagerImpl implements EventServiceManager {

	public static final Logger LOG = LoggerFactory.getLogger(EventServiceManagerImpl.class);

	@Autowired
	private Mapper mapper;

	@Autowired
	private EventRepository eventRepository;

	@Override
	public List<Event> getEvents() {
		Iterable<EventModel> eventModelIterable = eventRepository.findAll();
		return DozerHelper.map(mapper, eventModelIterable, Event.class);
	}

	@Override
	public Event createEvent(Event event) {
		return mapper.map(eventRepository.save(mapper.map(event, EventModel.class)), Event.class);
	}

	@Override
	public Event getEvent(String id) {
		return mapper.map(eventRepository.findOne(id), Event.class);
	}

	@Override
	public Event updateEvent(Event event) {
		return mapper.map(eventRepository.save(mapper.map(event, EventModel.class)), Event.class);
	}

	@Override
	public void deleteEvent(Event event) {
		eventRepository.delete(mapper.map(event, EventModel.class));
	}

}
