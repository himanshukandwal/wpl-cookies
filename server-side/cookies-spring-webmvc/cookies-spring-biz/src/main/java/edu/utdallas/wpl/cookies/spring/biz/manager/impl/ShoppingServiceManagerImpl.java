package edu.utdallas.wpl.cookies.spring.biz.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.utdallas.wpl.cookies.spring.biz.manager.ShoppingServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.ShoppingInfo;
import edu.utdallas.wpl.cookies.spring.dao.orm.ShoppingInfoEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.ShoppingRepository;
@Service
public class ShoppingServiceManagerImpl implements ShoppingServiceManager {
	@Autowired
	ShoppingRepository shoppingRepository;
	@Autowired
	private Mapper mapper;
	
	
	
	@Override
	public ShoppingInfo addShoppingInfo(ShoppingInfo shoppingInfo) {
		
		return mapper.map(shoppingRepository.save(mapper.map(shoppingInfo, ShoppingInfoEntity.class)),
				ShoppingInfo.class);
		
		
	}

	@Override
	public List<ShoppingInfo> getShoppingInfo() {

		List<ShoppingInfoEntity> shoppingInfoEntityList = shoppingRepository.getAll();

		List<ShoppingInfo> shoppingInfoList = null;
		if (shoppingInfoEntityList != null) {
			shoppingInfoList = new ArrayList<>();
			for (ShoppingInfoEntity shoppingEntity : shoppingInfoEntityList) {
				ShoppingInfo shoppingInfo = mapper.map(shoppingEntity, ShoppingInfo.class);
				shoppingInfoList.add(shoppingInfo);
			}
		}

		return shoppingInfoList;
	}

}
