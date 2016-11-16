package edu.utdallas.wpl.cookies.spring.dao.orm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
@Entity(name = "transaction_info")
public class TransactionInfoEntity {
	@Id
    @Basic(optional = false)
	@Column(name = "TRAN_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TRANSACTION_INFO_SEQUENCE")
	@SequenceGenerator(name = "TRANSACTION_INFO_SEQUENCE", sequenceName = "TRANSACTION_INFO_SEQUENCE")
	private Integer tranId;
	@Column(name="BID_ID", length = 38)
	private Integer bidId;
	@Column(name="BIDPOSTER_ID", length = 38)
	private Integer bidPosterId;
	@Column(name="BIDRECEIVER_ID", length = 38)
	private Integer bidReceiverId;
	@Column(name="BID_PRICE", length = 20)
	private String bidPrice;
	public Integer getTranId() {
		return tranId;
	}
	public void setTranId(Integer tranId) {
		this.tranId = tranId;
	}
	public Integer getBidId() {
		return bidId;
	}
	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}
	public Integer getBidPosterId() {
		return bidPosterId;
	}
	public void setBidPosterId(Integer bidPosterId) {
		this.bidPosterId = bidPosterId;
	}
	public Integer getBidReceiverId() {
		return bidReceiverId;
	}
	public void setBidReceiverId(Integer bidReceiverId) {
		this.bidReceiverId = bidReceiverId;
	}
	public String getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(String bidPrice) {
		this.bidPrice = bidPrice;
	}

}
