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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.utdallas.wpl.cookies.spring.biz.manager.AddressServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.Address;
import edu.utdallas.wpl.cookies.spring.services.AddressRestService;

@Controller 
@RequestMapping("/api")
public  class AddressRestServiceImpl implements AddressRestService {

    private static final Logger LOG = LoggerFactory.getLogger(AddressRestService.class);

	@Autowired
	private AddressServiceManager addressServiceManager;

	@Override
	@RequestMapping(value = "/address", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Address> createAddress(@RequestBody Address address, HttpServletRequest request) {
		Address persistedAddress = addressServiceManager.createAddress(address);

		LOG.info("created address with id : " + persistedAddress.getId());
		
		if (persistedAddress.getId() == null)
			return new ResponseEntity<Address>(HttpStatus.BAD_REQUEST);

		return ResponseEntity.ok(persistedAddress);
	}

	@Override
	
	@RequestMapping(value = "/addresses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<Address>> getAddresses() {
		return ResponseEntity.ok(addressServiceManager.getAddresses());
	}

	@Override
	@RequestMapping(value = "/address/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Address> getAddress(@PathVariable Integer id) {
		return ResponseEntity.ok(addressServiceManager.getAddress(id));
	}

	@Override
	@RequestMapping(value = "/address", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<Address> updateAddress(@RequestBody Address address) {
		if (null == address.getId())
			return new ResponseEntity<Address>(HttpStatus.BAD_REQUEST);
		
		if (addressServiceManager.getAddress(address.getId()) == null)
			return new ResponseEntity<Address>(HttpStatus.NOT_FOUND);
		
		addressServiceManager.updateAddress(address);
		
		LOG.info("updated address with id : " + address.getId());
		
		return ResponseEntity.ok(address);
	}

	@Override
	@RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<String> deleteAddress(@PathVariable Integer id) {
		if (null == id)
			return ResponseEntity.badRequest().body("address id is empty");
		
		if (addressServiceManager.getAddress(id) == null)
			return ResponseEntity.badRequest().body("address not found");
		
		addressServiceManager.deleteAddress(addressServiceManager.getAddress(id));
		
		LOG.info("deleted address with id : " + id);
		
		return ResponseEntity.ok("success");
	}
    
}