package me.hxkandwal.spring_mongo.biz.manager.impl;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hxkandwal.spring_mongo.biz.manager.EventServiceManager;
import me.hxkandwal.spring_mongo.biz.manager.utils.DozerHelper;
import me.hxkandwal.spring_mongo.common.dto.Event;
import me.hxkandwal.spring_mongo.dao.orm.EventModel;
import me.hxkandwal.spring_mongo.dao.repository.EventRepository;

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
