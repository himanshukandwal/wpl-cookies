package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.ApartmentEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.LoginPk;
import edu.utdallas.wpl.cookies.spring.dao.orm.PublishedBidsEntity;

@Repository
public class PublishedBidsRepository extends GenericDAORepositoryImpl<PublishedBidsEntity, Integer> {

	public PublishedBidsRepository() {
		super(PublishedBidsEntity.class);
	}

}
