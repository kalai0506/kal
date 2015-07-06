/**
 * @author Kalaiselvan Ulaganathan
 * @Date 07/05/2015
 * @Version 1.0
 * @Description This class contains business functionality of search tickets
 **/
package com.ticketfly.ticketfly.functionlibrary;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.ticketfly.ticketfly.pages.AddTicketPage;
import com.ticketfly.ticketfly.pages.DeliveryPage;
import com.ticketfly.ticketfly.pages.HomePage;
import com.ticketfly.ticketfly.pages.SearchResultsPage;

public class SearchAndCartTickets {
	final static Logger Log = Logger.getLogger(SearchAndCartTickets.class.getName());
	private WebDriver driver = null;

	/**
	 * Constructor for SearchTickets class
	 * @param driver WebDriver Instance
	 */
	public SearchAndCartTickets(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * This method searches for a ticket by taking an event name from
	 * ticketfly.com home page
	 * @param No Parameters
	 * @return True or False
	 */
	public Boolean searchAndAddTicket() {
		Boolean retValue = false;
		try {
			HomePage homePage = new HomePage(driver);
			// Verify if home page is displayed
			if (homePage.verifyHomePage()) {
				// get an event displayed in the home page
				String eventID = homePage.getEventID();
				//Verify if the event name is too large, if it is large
				//truncate it to make it a valid search string as a 
				//workaround for the search issue exists in the application
				if (eventID.endsWith("...")) {
					eventID = eventID.replaceAll("\\s[a-zA-Z0-9]*...$", "");
				}
				Log.info("Event ID: " + eventID);

				//Enter the event name and click find button
				homePage.enterEventID(eventID);
				SearchResultsPage searchResult = homePage
						.clickFindEventsButton();
				//Click 1st Get Ticket button from the search results
				AddTicketPage addTicket = searchResult.selectAnEvent();
				//Get the max allowed ticket to generate random number accordingly
				int maxAllowedQty = addTicket.getMaxQuantity();
				//Get a random number between 1 and maximum number of tickets allowed
				int randNum = this.randInt(1, maxAllowedQty);
				//Add tickets to the cart 
				int qtyAdded = addTicket.clickTicketQuantity(randNum);
				DeliveryPage cart = addTicket.clickGetTickets();
				//Verify the quantity added into the cart
				String qtyFromCart = cart.getTicketQty();
				int qty = this.getQuantityInCart(qtyFromCart);
				Log.info("Quantity parsed from Cart: " + qty);
				if (qty == qtyAdded) {
					Log.info("Quantities are matched between cart and form: "
							+ qty);
					return true;
				} else {
					return false;
				}

			} else {
				Log.info("Home Page not loaded properly");
				retValue = false;
			}
			return retValue;
		} catch (Exception e) {
			Log.error("Exception in Search Tickets:", e);
			retValue = false;
			return retValue;
		}
	}

	/**
	 * This Method is used to parse the event ticket quantity available in the cart
	 * @param qtyFromCart
	 * @return qtyParsed - Quantity parsed from the cart
	 */
	public int getQuantityInCart(String qtyFromCart) {
		int qtyParsed = 0;
		String pattern = "(^[0-9]+)";
		// Create a Pattern object
		Pattern r = Pattern.compile(pattern);

		// Now create matcher object.
		Matcher m = r.matcher(qtyFromCart);
		while (m.find()) {
			qtyParsed = Integer.parseInt(m.group(1));
		}
		return qtyParsed;
	}

	/**
	 * Returns a psuedo-random number between min and max, inclusive. The
	 * difference between min and max can be at most
	 * @param min
	 *            Minimum value
	 * @param max
	 *            Maximum value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 */
	public int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

}
