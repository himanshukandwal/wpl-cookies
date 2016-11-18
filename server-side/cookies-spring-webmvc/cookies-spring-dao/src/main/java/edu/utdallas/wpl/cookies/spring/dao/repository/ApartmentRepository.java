package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.ApartmentEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.LoginPk;

@Repository
public class ApartmentRepository extends GenericDAORepositoryImpl<ApartmentEntity, Integer> {

	public ApartmentRepository() {
		super(ApartmentEntity.class);
	}

}
