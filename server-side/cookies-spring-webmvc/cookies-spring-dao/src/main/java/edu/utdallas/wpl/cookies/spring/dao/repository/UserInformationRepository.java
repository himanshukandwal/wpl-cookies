package edu.utdallas.wpl.cookies.spring.dao.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;

@Repository
public class UserInformationRepository extends GenericDAORepositoryImpl<UserInformationEntity, Integer> {

	@Autowired
	private SessionFactory sessionFactory;

	public UserInformationRepository() {
		super(UserInformationEntity.class);
	}

	@SuppressWarnings("unchecked")
	@Cacheable(cacheNames = "cookiecache")
	public UserInformationEntity getUserInformationByEmail(String email) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(UserInformationEntity.class);
		criteria.add(Restrictions.eq("email", email));

		List<UserInformationEntity> informations = (List<UserInformationEntity>) criteria.list();
		
		if (informations == null || informations.isEmpty())
			return null;
		else
			return informations.get(0);
	}
}
