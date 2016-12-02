package edu.utdallas.wpl.cookies.spring.dao.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.PublishedBidsEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;

@Repository
public class PublishedBidsRepository extends GenericDAORepositoryImpl<PublishedBidsEntity, Integer> {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserInformationRepository userInformationRepository;
	public PublishedBidsRepository() {
		super(PublishedBidsEntity.class);
	}

	@SuppressWarnings("unchecked")
	public List<PublishedBidsEntity> getAllBidRequestes(Integer userId ) {
		
		UserInformationEntity userInformationEntity =userInformationRepository.get(userId);
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PublishedBidsEntity.class);
		criteria.add(Restrictions.eq("owner", userInformationEntity));
		
		List<PublishedBidsEntity> informations = (List<PublishedBidsEntity>) criteria.list();
		if (informations == null || informations.isEmpty())
			return null;
		else
			return informations;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<PublishedBidsEntity> getAllActiveBids(Long timestamp) {
		
	

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(PublishedBidsEntity.class);
		criteria.add(Restrictions.gt("modifiedDate", timestamp.floatValue()));
		
		
		List<PublishedBidsEntity> informations = (List<PublishedBidsEntity>) criteria.list();
		if (informations == null || informations.isEmpty())
			return null;
		else
			return informations;
	}
	
	
}
