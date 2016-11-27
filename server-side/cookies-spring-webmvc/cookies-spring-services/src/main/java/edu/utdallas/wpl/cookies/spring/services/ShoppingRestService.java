package edu.utdallas.wpl.cookies.spring.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import edu.utdallas.wpl.cookies.spring.common.dto.ShoppingInfo;

public interface ShoppingRestService {
	ResponseEntity<List<ShoppingInfo>> getShoppingInfo();
	ResponseEntity<ShoppingInfo> addToShopping(ShoppingInfo shoppingInfo,HttpServletRequest request);
	
	
}
