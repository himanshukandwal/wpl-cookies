package edu.utdallas.wpl.cookies.spring.biz.manager;

import java.util.List;

import edu.utdallas.wpl.cookies.spring.common.dto.PublishedBids;

public interface BidServiceManager {

	
	public PublishedBids addBidRequest(PublishedBids publishedBids);
	
	public List<PublishedBids> getBidRequests(Integer userId);
	public void deleteBidRequest(Integer bidId);
	public List<PublishedBids> getBidRequests();
	public List<PublishedBids> getlatestBids(Long timeStamp);

}
