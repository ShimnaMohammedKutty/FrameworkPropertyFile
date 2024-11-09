package Demofiles;

import java.util.Date;

public class TimeStamp {

	public String time() {
		
		
		return "shimna"+new Date().toString().replace(" ", "_").replace(":", "_")+"@gmail.com";
		
		
		/*Date date=new Date();
		System.out.println(date);
		
		String datestring=date.toString();  //Convert date into string
		System.out.println(datestring); 
		
		String nospaceadate=datestring.replace(" ", "_");  //replace space(" ") with underscore(_)
		System.out.println(nospaceadate);
		
		String timeStamp=nospaceadate.replace(":", "_");
		System.out.println(timeStamp);
		
		String finalEmail="shimna"+timeStamp+"@gmail.com";
		System.out.println(finalEmail);*/
		
		
		

	}

}
