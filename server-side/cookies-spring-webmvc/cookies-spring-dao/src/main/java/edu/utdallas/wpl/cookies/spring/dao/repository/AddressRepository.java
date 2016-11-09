package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.Address;

@Repository
public class AddressRepository extends GenericDAORepositoryImpl<Address, Integer> {

	public AddressRepository() {
		super(Address.class);
	}

}
