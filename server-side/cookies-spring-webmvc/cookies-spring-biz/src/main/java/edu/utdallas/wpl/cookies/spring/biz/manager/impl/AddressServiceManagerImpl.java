package edu.utdallas.wpl.cookies.spring.biz.manager.impl;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.utdallas.wpl.cookies.spring.biz.manager.AddressServiceManager;
import edu.utdallas.wpl.cookies.spring.biz.manager.utils.DozerHelper;
import edu.utdallas.wpl.cookies.spring.common.dto.Address;
import edu.utdallas.wpl.cookies.spring.dao.orm.AddressEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.AddressRepository;

@Service
public class AddressServiceManagerImpl implements AddressServiceManager {

	public static final Logger LOG = LoggerFactory.getLogger(AddressServiceManagerImpl.class);

	@Autowired
	private Mapper mapper;

	@Autowired
	private AddressRepository addressRepository;

	@Override
	public List<Address> getAddresses() {
		Iterable<AddressEntity> addressEntityIterable = addressRepository.getAll();
		return DozerHelper.map(mapper, addressEntityIterable, Address.class);
	}

	@Override
	public Address createAddress(Address address) {
		return mapper.map(addressRepository.save(mapper.map(address, AddressEntity.class)), Address.class);
	}

	@Override
	public Address getAddress(Integer id) {
		return mapper.map(addressRepository.get(id), Address.class);
	}

	@Override
	public Address updateAddress(Address address) {
		return mapper.map(addressRepository.save(mapper.map(address, AddressEntity.class)), Address.class);
	}

	@Override
	public void deleteAddress(Address address) {
		addressRepository.delete(mapper.map(address, AddressEntity.class));
	}

	@Override
	public void deleteAddress(Integer id) {
		addressRepository.delete(addressRepository.get(id));
	}

}
