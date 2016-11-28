package edu.utdallas.wpl.cookies.spring.dao.integration;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class TestMain {

	public static void main(String[] args) {
		/*SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd/MM/yyyy hh:mm:ss aa");
	    String date=	dateFormat.format(new Date(1480287976419L));
	
		try {
			
			
			Date modifiedDate=dateFormat.parse(date);
			System.out.println("Modified date "+modifiedDate);
		
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
		Date date = new Date(1480287976419L);   // given date
		Calendar calendar = GregorianCalendar.getInstance(); // creates a new calendar instance
		calendar.setTime(date);   // assigns calendar to given date 
	System.out.println(calendar.get(Calendar.HOUR_OF_DAY)); // gets hour in 24h format
	System.out.println(calendar.get(Calendar.HOUR));        // gets hour in 12h format
	System.out.println(calendar.get(Calendar.MONTH)); 
	System.out.println(calendar.get(Calendar.AM_PM));
	
	calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)-12);
	
	Date dateCa =calendar.getTime();
	System.out.println(dateCa);
		
		
		
		
	}

}
