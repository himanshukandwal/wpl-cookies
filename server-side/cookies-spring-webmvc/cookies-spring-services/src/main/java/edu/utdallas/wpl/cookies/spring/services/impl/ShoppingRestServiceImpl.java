package edu.utdallas.wpl.cookies.spring.services.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.utdallas.wpl.cookies.spring.biz.manager.ShoppingServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.ShoppingInfo;
import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;
import edu.utdallas.wpl.cookies.spring.common.enums.BidStatus;
import edu.utdallas.wpl.cookies.spring.services.ShoppingRestService;

@Controller
@RequestMapping("/api")
public class ShoppingRestServiceImpl implements ShoppingRestService {
	 private static final Logger LOG = LoggerFactory.getLogger(ShoppingRestServiceImpl.class);
	 
	 @Autowired
	 private ShoppingServiceManager ShoppingServiceManager;

	@Override
	
	@RequestMapping(value = "/getShoppingInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShoppingInfo>> getShoppingInfo() {
	
		List<ShoppingInfo> shoppingInfosList = ShoppingServiceManager.getShoppingInfo();
		if (shoppingInfosList != null) {
			LOG.info(" The number of bid requests for user  :" + shoppingInfosList.size());
			return ResponseEntity.ok(shoppingInfosList);
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@Override
	@RequestMapping(value = "/saveShoppingInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingInfo> addToShopping(@RequestBody ShoppingInfo shoppingInfoReq, HttpServletRequest request) {
		System.out.println("inside shopping>>>>"+shoppingInfoReq.getPrice());
		

		ShoppingInfo shoppingInfo = ShoppingServiceManager.addShoppingInfo(shoppingInfoReq);

		LOG.info(" created user with id :" + shoppingInfo.getShoppingId());

		return ResponseEntity.ok(shoppingInfo);

	}
	
	

}
