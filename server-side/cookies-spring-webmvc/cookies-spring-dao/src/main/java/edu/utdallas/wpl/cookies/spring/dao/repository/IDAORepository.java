package edu.utdallas.wpl.cookies.spring.dao.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.REQUIRED)
public interface IDAORepository<T, PK extends Serializable> {

	@Transactional(readOnly = false)
    PK save(T newInstance);

    @Transactional(readOnly = true)
    T get(PK id);
    
    @Transactional(readOnly = true)
    List<T> getAll();

    @Transactional(readOnly = false)
    void update(T transientObject);

    @Transactional(readOnly = false)
    void delete(T persistentObject);

    Integer count();

    List<T> findByExample(T example);
    
}
