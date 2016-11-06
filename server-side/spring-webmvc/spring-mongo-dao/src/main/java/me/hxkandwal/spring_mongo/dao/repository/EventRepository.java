package me.hxkandwal.spring_mongo.dao.repository;
import org.springframework.data.repository.CrudRepository;

import me.hxkandwal.spring_mongo.dao.orm.EventModel;

/**
 * A simple CRUD event repository. The implementation of this interface is provided by SimpleMongoRepository.java 
 * 
 * @author Heman
 *
 */
public interface EventRepository extends CrudRepository<EventModel, String> {
	
}