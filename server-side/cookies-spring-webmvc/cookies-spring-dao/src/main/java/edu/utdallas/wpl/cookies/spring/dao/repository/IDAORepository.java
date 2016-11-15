package edu.utdallas.wpl.cookies.spring.dao.repository;

import java.io.Serializable;
import java.util.List;

public interface IDAORepository<T, PK extends Serializable> {

    T save(T newInstance);
    
    T get(PK id);

    List<T> getAll();

    void update(T transientObject);

    void delete(T persistentObject);

    Integer count();

    List<T> findByExample(T example);
    
}
