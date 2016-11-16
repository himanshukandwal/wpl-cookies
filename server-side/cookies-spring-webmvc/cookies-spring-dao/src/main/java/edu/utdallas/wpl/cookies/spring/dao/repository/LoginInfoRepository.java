package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.LoginInfoEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.LoginPk;

@Repository
public class LoginInfoRepository  extends GenericDAORepositoryImpl<LoginInfoEntity, LoginPk> {

	public LoginInfoRepository() {
		super(LoginInfoEntity.class);
		
	}

}
