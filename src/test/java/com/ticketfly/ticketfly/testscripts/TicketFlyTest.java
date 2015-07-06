/**
 * @author Kalaiselvan Ulaganathan
 * @Date 06/15/2015
 * @Version 1.0
 * @Description This is the test script to test the module Schedule webinar
 **/
package com.ticketfly.ticketfly.testscripts;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ticketfly.ticketfly.frameworklibrary.FrameworkMethods;
import com.ticketfly.ticketfly.functionlibrary.SearchAndCartTickets;

public class TicketFlyTest extends DriverTest {
	final static Logger Log = Logger.getLogger(TicketFlyTest.class.getName());
	FrameworkMethods frmWrk=new FrameworkMethods();

	/**
	 * First test method. It asserts if the expected results not returned.
	 * @throws Exception
	 */
	@Test
	public void searchAndCartATicket() throws Exception {
		Log.info("Test case searchAndCartATicket execution starts now.");
		SearchAndCartTickets eventTicket=new SearchAndCartTickets(driver);
		//Create a new webinar schedule
		Assert.assertTrue(eventTicket.searchAndAddTicket());
		//View the webinar schedule & check if the details provided above are captured properly
		//Assert.assertEquals(objNewWebinar.verifyWebinarDetails(mapTestData),"success","Webinar details not matched correctly");
		Log.info("Test case searchAndCartATicket execution completed.");
	}


}
