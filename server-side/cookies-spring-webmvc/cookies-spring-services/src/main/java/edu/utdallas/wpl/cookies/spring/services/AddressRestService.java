package edu.utdallas.wpl.cookies.spring.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import edu.utdallas.wpl.cookies.spring.common.dto.Address;

public interface AddressRestService {

	ResponseEntity<Address> createAddress(Address address,  HttpServletRequest request);

	ResponseEntity<List<Address>> getAddresses();
	
	ResponseEntity<Address> getAddress(Integer id);
	
	ResponseEntity<Address> updateAddress(Address address);
	
	ResponseEntity<String> deleteAddress(Integer id);
	
	
	
}
