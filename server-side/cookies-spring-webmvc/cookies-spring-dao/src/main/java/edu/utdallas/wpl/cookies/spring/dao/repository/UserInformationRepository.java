package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.UserInfomation;

@Repository
public class UserInformationRepository extends GenericDAORepositoryImpl<UserInfomation, Integer> {

	public UserInformationRepository() {
		super(UserInfomation.class);
	}

}
