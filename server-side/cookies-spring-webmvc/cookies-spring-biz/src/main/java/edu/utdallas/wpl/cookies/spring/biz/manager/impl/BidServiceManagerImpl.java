package edu.utdallas.wpl.cookies.spring.biz.manager.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.utdallas.wpl.cookies.spring.biz.manager.BidServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.PublishedBids;
import edu.utdallas.wpl.cookies.spring.dao.orm.PublishedBidsEntity;
import edu.utdallas.wpl.cookies.spring.dao.repository.PublishedBidsRepository;

@Service
public class BidServiceManagerImpl implements BidServiceManager{
	
	@Autowired
	private Mapper mapper;

	@Autowired
	private PublishedBidsRepository publishedBidsRepository;
	
	@Override
	public PublishedBids addBidRequest(PublishedBids publishedBids) {
		return mapper.map(publishedBidsRepository.save(mapper.map(publishedBids, PublishedBidsEntity.class)), PublishedBids.class);
	}

	@Override
	public List<PublishedBids> getBidRequests(Integer userId) {
		List<PublishedBidsEntity> pubBidList = publishedBidsRepository.getAllBidRequestes(userId);
		List<PublishedBids> publishedBidsList = null;
		
		if (pubBidList != null) {
			publishedBidsList = new ArrayList<>();
			for (PublishedBidsEntity publishedBidsEntity : pubBidList) {
				PublishedBids publishedBids = mapper.map(publishedBidsEntity, PublishedBids.class);
				publishedBidsList.add(publishedBids);
			}
		}

		return publishedBidsList;
	}

	
	@Override
	public List<PublishedBids> getBidRequests() {
		List<PublishedBidsEntity> pubBidList = publishedBidsRepository.getAll();
		List<PublishedBids> publishedBidsList = null;
		
		if (pubBidList != null) {
			publishedBidsList = new ArrayList<>();
			for (PublishedBidsEntity publishedBidsEntity : pubBidList) {
				PublishedBids publishedBids = mapper.map(publishedBidsEntity, PublishedBids.class);
				publishedBidsList.add(publishedBids);
			}
		}

		return publishedBidsList;
	}
	@Override
	public void deleteBidRequest(Integer bidId) {
		PublishedBidsEntity publishedBidsEntity = publishedBidsRepository.get(bidId);
		publishedBidsRepository.delete(publishedBidsEntity);
	}

	@Override
	public List<PublishedBids> getlatestBids(Long timeStamp) {
		List<PublishedBidsEntity> publishedBidsEntityList = publishedBidsRepository.getAllActiveBids(timeStamp);
		List<PublishedBids> publishedBidsList = null;
		
		if (publishedBidsEntityList != null) {
			publishedBidsList = new ArrayList<>();
			for (PublishedBidsEntity publishedBidsEntity : publishedBidsEntityList) {
				PublishedBids publishedBids = mapper.map(publishedBidsEntity, PublishedBids.class);
				publishedBidsList.add(publishedBids);
			}
		}

		return publishedBidsList;
	}

	@Override
	public PublishedBids getBidRequest(Integer bidId) {
		PublishedBidsEntity publishedBidsEntity =publishedBidsRepository.get(bidId);
		PublishedBids publishedBids = mapper.map(publishedBidsEntity, PublishedBids.class);
		
		return publishedBids;
	}

	@Override
	public void updateBids(PublishedBids publishedBids) {
		publishedBidsRepository.update(mapper.map(publishedBids, PublishedBidsEntity.class));
	}

}
