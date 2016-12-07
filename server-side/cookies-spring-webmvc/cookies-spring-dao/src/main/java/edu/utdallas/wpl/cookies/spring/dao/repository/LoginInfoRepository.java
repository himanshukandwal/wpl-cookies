package edu.utdallas.wpl.cookies.spring.dao.repository;

import org.springframework.stereotype.Repository;

import edu.utdallas.wpl.cookies.spring.dao.orm.LoginInfoEntity;
import edu.utdallas.wpl.cookies.spring.dao.orm.LoginInfoEntityPk;

@Repository
public class LoginInfoRepository  extends GenericDAORepositoryImpl<LoginInfoEntity, LoginInfoEntityPk> {

	public LoginInfoRepository() {
		super(LoginInfoEntity.class);
	}

}
