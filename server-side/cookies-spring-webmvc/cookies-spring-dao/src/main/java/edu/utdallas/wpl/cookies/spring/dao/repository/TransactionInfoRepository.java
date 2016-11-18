package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.TransactionInfoEntity;

@Repository
public class TransactionInfoRepository extends GenericDAORepositoryImpl<TransactionInfoEntity, Integer> {

	public TransactionInfoRepository() {
		super(TransactionInfoEntity.class);
	}

}
