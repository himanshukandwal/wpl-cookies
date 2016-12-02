package edu.utdallas.wpl.cookies.spring.dao.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * @author Himanshu Kandwal, Anirudha KV, Srinivas
 *
 * @param <T>
 * @param <PK>
 */
@Transactional(propagation = Propagation.REQUIRED)
@SuppressWarnings({ "unchecked" })
@Cacheable("cookiecache") 
public class GenericDAORepositoryImpl<T, PK extends Serializable>  implements IDAORepository<T, PK> {
	
	@Autowired
    protected HibernateTemplate hibernateTemplate;

    protected Class<T> type;

    public GenericDAORepositoryImpl(Class<T> type) {
        this.type = type;
    }

	@Override
	@Transactional
    public T save(T newInstance) {
		PK id = (PK) hibernateTemplate.save(newInstance);
        return (T) get(id);
    }

    @Override
    @Transactional (readOnly = true)
    public T get(PK id) {
    	return (T) hibernateTemplate.get(type, id);
    }

    @Override
    @Transactional
    public void update(T transientObject) {
    	hibernateTemplate.update(transientObject);
    }

    @Override
    @Transactional
    public void delete(T persistentObject) {
    	hibernateTemplate.delete(persistentObject);
    }
    
	@Override
	@Transactional (readOnly = true)
    public List<T> getAll() {
        return (List<T>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(type));
    }
    
    @Override
    @Transactional (readOnly = true)
    public List<T> findByExample(T example) {
        return (List<T>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(type)
        		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
        		.add(Example.create(example)));
    }

    @Override
    @Transactional (readOnly = true)
    public Integer count() {
        return (Integer) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(type).setProjection(Projections.rowCount())).size();
    }

}
