package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;

@Repository
public class UserInformationRepository extends GenericDAORepositoryImpl<UserInformationEntity, Integer> {

	public UserInformationRepository() {
		super(UserInformationEntity.class);
	}

}
