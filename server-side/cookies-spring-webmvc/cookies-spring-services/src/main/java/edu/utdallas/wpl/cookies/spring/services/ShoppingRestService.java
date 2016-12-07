package edu.utdallas.wpl.cookies.spring.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import edu.utdallas.wpl.cookies.spring.common.dto.ShoppingInfo;
import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;

public interface ShoppingRestService {
	
	ResponseEntity<List<ShoppingInfo>> getShoppingInfo();
	
	ResponseEntity<ShoppingInfo> addToShopping(ShoppingInfo shoppingInfo);
	
	ResponseEntity<List<ShoppingInfo>> checkOutItems(List<TransactionInfo> transactionInfoList);
	
}
