package edu.utdallas.wpl.cookies.spring.biz.manager.impl;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.utdallas.wpl.cookies.spring.biz.manager.UserInformationServiceManager;
import edu.utdallas.wpl.cookies.spring.biz.manager.utils.DozerHelper;
import edu.utdallas.wpl.cookies.spring.common.dto.UserInformation;
import edu.utdallas.wpl.cookies.spring.dao.orm.UserInformationEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.UserInformationRepository;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

@Service
public class UserInformationServiceManagerImpl implements UserInformationServiceManager {

	public static final Logger LOG = LoggerFactory.getLogger(UserInformationServiceManagerImpl.class);

	@Autowired
	private Mapper mapper;
	
	@Autowired
	private CacheManager cacheManager;

	@Autowired
	private UserInformationRepository userInformationRepository;

	@Override
	public List<UserInformation> getUserInformations() {
		Iterable<UserInformationEntity> userInformationEntityIterable = userInformationRepository.getAll();
		return DozerHelper.map(mapper, userInformationEntityIterable, UserInformation.class);
	}

	@Override
	public UserInformation createUserInformation(UserInformation userInformation) {
		return mapper.map(userInformationRepository.save(mapper.map(userInformation, UserInformationEntity.class)),
				UserInformation.class);
	}

	@Override
	public UserInformation getUserInformation(Integer id) {
		return mapper.map(userInformationRepository.get(id), UserInformation.class);
	}

	@Override
	public UserInformation updateUserInformation(UserInformation userInformation) {
		userInformationRepository.update(mapper.map(userInformation, UserInformationEntity.class));
		return mapper.map(userInformationRepository.get(userInformation.getId()), UserInformation.class);
	}

	@Override
	public void deleteUserInformation(UserInformation userInformation) {
		userInformationRepository.delete(mapper.map(userInformation, UserInformationEntity.class));
	}

	@Override
	public void deleteUserInformation(Integer id) {
		userInformationRepository.delete(userInformationRepository.get(id));
	}

	@Override
	public UserInformation getUserInformationByEmail(String email) {
		UserInformationEntity informationEntity = null;
		
		if (cacheManager.getCache("cookiecache").get(email) != null) {
			System.out.println("from cache>>>>>>>>>>" + cacheManager.getCache("cookiecache").get(email));
			
			Element element = cacheManager.getCache("cookiecache").get(email);
			informationEntity = (UserInformationEntity) element.getValue();
		} else {
			System.out.println("not from cache");
			informationEntity = userInformationRepository.getUserInformationByEmail(email);
		}
		return (informationEntity == null) ? null : mapper.map(informationEntity, UserInformation.class);
	}

}
