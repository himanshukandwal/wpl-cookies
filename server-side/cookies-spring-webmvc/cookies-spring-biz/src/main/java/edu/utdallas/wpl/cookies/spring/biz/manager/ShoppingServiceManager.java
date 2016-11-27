package edu.utdallas.wpl.cookies.spring.biz.manager;

import java.util.List;

import edu.utdallas.wpl.cookies.spring.common.dto.ShoppingInfo;

public interface ShoppingServiceManager {

	

	public ShoppingInfo addShoppingInfo(ShoppingInfo shoppingInfo);

	public List<ShoppingInfo> getShoppingInfo();
	
}
