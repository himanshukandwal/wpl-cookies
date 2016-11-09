package edu.utdallas.wpl.cookies.spring.dao.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

/**
 * 
 * @author Himanshu Kandwal, Anirudha KV, Srinivas
 *
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings({ "unchecked" })
public class GenericDAORepositoryImpl<T, PK extends Serializable>  implements IDAORepository<T, PK> {
	
	@Autowired
    protected HibernateTemplate hibernateTemplate;

    protected Class<T> type;

    public GenericDAORepositoryImpl(Class<T> type) {
        this.type = type;
    }

	@Override
    public PK save(T newInstance) {
        return (PK) hibernateTemplate.save(newInstance);
    }

    @Override
    public T get(PK id) {
    	return (T) hibernateTemplate.get(type, id);
    }

    @Override
    public void update(T transientObject) {
    	hibernateTemplate.update(transientObject);
    }

    @Override
    public void delete(T persistentObject) {
    	hibernateTemplate.delete(persistentObject);
    }
    
	@Override
    public List<T> getAll() {
        return (List<T>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(type));
    }
    
    @Override
    public List<T> findByExample(T example) {
        return (List<T>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(type)
        		.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
        		.add(Example.create(example)));
    }

    @Override
    public Integer count() {
        return (Integer) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(type).setProjection(Projections.rowCount())).size();
    }

}
