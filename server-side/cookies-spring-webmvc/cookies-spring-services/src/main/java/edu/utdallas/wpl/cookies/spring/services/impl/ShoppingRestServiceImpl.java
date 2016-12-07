package edu.utdallas.wpl.cookies.spring.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.utdallas.wpl.cookies.spring.biz.manager.BidServiceManager;
import edu.utdallas.wpl.cookies.spring.biz.manager.ShoppingServiceManager;
import edu.utdallas.wpl.cookies.spring.common.dto.PublishedBids;
import edu.utdallas.wpl.cookies.spring.common.dto.ShoppingInfo;
import edu.utdallas.wpl.cookies.spring.common.dto.TransactionInfo;
import edu.utdallas.wpl.cookies.spring.services.ShoppingRestService;

@Controller
@RequestMapping("/api")
public class ShoppingRestServiceImpl implements ShoppingRestService {
	private static final Logger LOG = LoggerFactory.getLogger(ShoppingRestServiceImpl.class);

	@Autowired
	private ShoppingServiceManager ShoppingServiceManager;
	
	@Autowired
	private BidServiceManager bidServiceManager;

	@Override

	@RequestMapping(value = "/getShoppingInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShoppingInfo>> getShoppingInfo() {
		List<ShoppingInfo> shoppingInfosList = ShoppingServiceManager.getShoppingInfo();
		
		if (shoppingInfosList == null) 
			shoppingInfosList = new ArrayList<>();

		LOG.info(" The number of bid requests for user  :" + shoppingInfosList.size());
		return ResponseEntity.ok(shoppingInfosList);
	}

	@Override
	@RequestMapping(value = "/saveShoppingInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShoppingInfo> addToShopping(@RequestBody ShoppingInfo shoppingInfo) {
		System.out.println("inside shopping>>>>" + shoppingInfo.getPrice());

		ShoppingInfo persistedShoppingInfo = ShoppingServiceManager.addShoppingInfo(shoppingInfo);

		LOG.info(" created shopping summary with id :" + persistedShoppingInfo.getShoppingId());

		return ResponseEntity.ok(shoppingInfo);
	}

	@Override
	@RequestMapping(value = "/checkout", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShoppingInfo>> checkOutItems(@RequestBody List<TransactionInfo> transactionInfoList) {
		List<ShoppingInfo> shoppingInfoList = new ArrayList<>();
		
		for (TransactionInfo transactionInfo : transactionInfoList) {
			ShoppingInfo shoppingInfoReq = convertTransactionToshopping(transactionInfo);
			ShoppingInfo shoppingInfoRes=ShoppingServiceManager.addShoppingInfo(shoppingInfoReq);
			
			PublishedBids publishedBids=bidServiceManager.getBidRequest(shoppingInfoRes.getBidId());
			publishedBids.setActiveInd("N");
			bidServiceManager.updateBids(publishedBids);
			
			shoppingInfoList.add(shoppingInfoRes);
			LOG.info(" created user with id :" + shoppingInfoRes.getShoppingId());
		}

		for (TransactionInfo transactionInfo : transactionInfoList) {
			String bidPoster = transactionInfo.getBid().getOwner().getEmail();
			String bidReceiver = transactionInfo.getBidReceiver().getEmail();
			Integer bidId = transactionInfo.getBid().getBidId();
			System.out.println("Bid poster>"+bidPoster);
			System.out.println("Bid receiver>"+bidReceiver+" bid id "+bidId);
			
			sendMail("wpl.admn@gmail.com", "passwordWpl", bidPoster, bidReceiver);
		}

		return ResponseEntity.ok(shoppingInfoList);
	}

	private ShoppingInfo convertTransactionToshopping(TransactionInfo transactionInfo) {
		ShoppingInfo shoppingInfo = new ShoppingInfo();
		shoppingInfo.setTransactionInfo(transactionInfo);
		shoppingInfo.setBidId(transactionInfo.getBid().getBidId());
		shoppingInfo.setPrice(transactionInfo.getBidPrice());
		shoppingInfo.setQuantity(transactionInfo.getQuantity());
		return shoppingInfo;
	}

	
	private boolean sendMail(String from, String password, String bidPoster,String bidReciver) {
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.user", host);
		properties.put("mail.smtp.password", password);
		properties.put("mail.smtp.port", 587);
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(properties, null);
		MimeMessage mimeMessage = new MimeMessage(session);
		try {
			mimeMessage.setFrom(new InternetAddress(from));

			InternetAddress toAddress = new InternetAddress();
			toAddress.setAddress(bidPoster);
            mimeMessage.addRecipient(RecipientType.TO, toAddress);
			mimeMessage.setSubject("Your BID Receipt from Cookies");
			String messageIntro = "<div style=\"color:black;\"><br>Hi </br></div>";
			String messgeToBidPoster= "<div><body> <br>Thank you very much for purchasing the items through cookie bidding service.</br></body></div>";
			String messageSalutaion="<br></br><div><body> Thanks And Regards,<br> cookie admin</br></body></div>";
			mimeMessage.setContent(messageIntro+messgeToBidPoster+messageSalutaion, "text/html");
 
			Session session2 = Session.getDefaultInstance(properties, null);
			MimeMessage mimeMessage2 = new MimeMessage(session2);
			InternetAddress toAddress2 = new InternetAddress();
			toAddress2.setAddress(bidReciver);
			mimeMessage2.addRecipient(RecipientType.TO, toAddress2);
			mimeMessage2.setSubject("Congratulations!");
			
			
			sendMessageToBidAgents(session2, host, from,password,bidPoster,mimeMessage);
			String messageToBidReciver ="<div><body> <br> Congrats your Item has beeen purchased.Thank you very much for posting Item through cookie bidding service.</br></body></div>";
			mimeMessage2.setContent(messageIntro+messageToBidReciver+messageSalutaion, "text/html");
			sendMessageToBidAgents(session2,host,from, password,bidReciver, mimeMessage2);

			return true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	private static void sendMessageToBidAgents(Session session, String host, String from, String password, String bidPoster, MimeMessage mimeMessage) {
		try {
			Transport transport = session.getTransport("smtp");

			transport.connect(host, from, password);
			transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			transport.close();
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}