package edu.utdallas.wpl.cookies.spring.biz.manager;

import java.util.List;

import edu.utdallas.wpl.cookies.spring.common.dto.Address;

public interface AddressServiceManager {

	public Address createAddress(Address address);
	
	public List<Address> getAddresses();
	
	public Address getAddress(Integer id);
	
	public void updateAddress(Address address);
	
	public void deleteAddress(Address address);
	
	public void deleteAddress(Integer id);
}
