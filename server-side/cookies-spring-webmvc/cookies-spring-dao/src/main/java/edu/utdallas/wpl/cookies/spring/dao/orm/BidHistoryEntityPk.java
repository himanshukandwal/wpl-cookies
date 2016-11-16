package edu.utdallas.wpl.cookies.spring.dao.orm;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class BidHistoryEntityPk implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "USER_ID")
	private UserInformationEntity user;
	
	@OneToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "BID_ID")
	private PublishedBidsEntity bid;

	public BidHistoryEntityPk() {}
	
	public BidHistoryEntityPk(UserInformationEntity user, PublishedBidsEntity bid) {
		super();
		this.user = user;
		this.bid = bid;
	}

}
