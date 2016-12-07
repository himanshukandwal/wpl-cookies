package edu.utdallas.wpl.cookies.spring.dao.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.PublishedBidsEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.TransactionInfoEntity;

@Repository
public class TransactionInfoRepository extends GenericDAORepositoryImpl<TransactionInfoEntity, Integer> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private PublishedBidsRepository publishedBidsRepository;
	
	public TransactionInfoRepository() {
		super(TransactionInfoEntity.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<TransactionInfoEntity> getAllTransactionsByBidId(Integer bidId ) {
		
		PublishedBidsEntity publishedBidsEntity =publishedBidsRepository.get(bidId);
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(TransactionInfoEntity.class);
		criteria.add(Restrictions.eq("bid", publishedBidsEntity));
		
		List<TransactionInfoEntity> informations = (List<TransactionInfoEntity>) criteria.list();
		if (informations == null || informations.isEmpty())
			return null;
		else
			return informations;
	}
	
}
