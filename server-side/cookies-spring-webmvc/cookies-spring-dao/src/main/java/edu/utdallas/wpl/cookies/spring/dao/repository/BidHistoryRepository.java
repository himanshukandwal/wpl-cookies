package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.BidHistoryEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.BidHistoryEntityPk;

@Repository
public class BidHistoryRepository  extends GenericDAORepositoryImpl<BidHistoryEntity, BidHistoryEntityPk> {

	public BidHistoryRepository() {
		super(BidHistoryEntity.class);
	}

}
