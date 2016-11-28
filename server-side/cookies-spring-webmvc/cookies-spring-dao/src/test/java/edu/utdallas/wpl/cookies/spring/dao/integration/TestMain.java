package edu.utdallas.wpl.cookies.spring.dao.integration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestMain {

	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss aa");
	    String date=	dateFormat.format(new Date(1480287976419L));
	
		try {
			
			
			Date modifiedDate=dateFormat.parse(date);
			System.out.println("Modified date "+modifiedDate);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
