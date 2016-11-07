package edu.utdallas.wpl.cookies.spring.dao.repository;
import org.springframework.data.repository.CrudRepository;

import edu.utdallas.wpl.cookies.spring.dao.orm.EventModel;

/**
 * A simple CRUD event repository. The implementation of this interface is provided by SimpleMongoRepository.java 
 * 
 * @author Heman
 *
 */
public interface EventRepository extends CrudRepository<EventModel, String> {
	
}