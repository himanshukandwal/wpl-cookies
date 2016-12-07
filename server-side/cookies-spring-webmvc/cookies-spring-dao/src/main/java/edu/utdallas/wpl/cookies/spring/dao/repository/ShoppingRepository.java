package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.ShoppingInfoEntity;

@Repository
public class ShoppingRepository  extends GenericDAORepositoryImpl<ShoppingInfoEntity, Integer> {

	public ShoppingRepository() {
		super(ShoppingInfoEntity.class);
	}
	
}
