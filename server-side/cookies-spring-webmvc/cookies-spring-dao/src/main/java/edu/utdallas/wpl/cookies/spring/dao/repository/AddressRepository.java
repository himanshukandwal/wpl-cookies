package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;

@Repository
public class AddressRepository extends GenericDAORepositoryImpl<AddressEntity, Integer> {

	public AddressRepository() {
		super(AddressEntity.class);
	}

}
