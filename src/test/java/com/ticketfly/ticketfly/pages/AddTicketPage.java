/**
 * @author Kalaiselvan Ulaganathan
 * @Date 07/05/2015
 * @Version 1.0
 * @Description This class contains page objects for booking tickets from the search results
 **/

package com.ticketfly.ticketfly.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddTicketPage {
	private WebDriver driver = null;
	private WebDriverWait webWait = null;
	final static Logger Log = Logger.getLogger(AddTicketPage.class.getName());

	// To identify the first product add button
	private By plusButtonLocator = By
			.cssSelector("div#productsDiv div.product.product-0 button.plus");
	// To identify the maximum no.of tickets allowed
	private By maxQtyLocator = By
			.cssSelector("div#productsDiv div.product.product-0 input.qty");
	// To Identify the GetTickets button in the get ticket page
	private By getTicketButtonLocator = By.id("bestSeats");

	/**
	 * Constructor for the AddTicket page
	 * 
	 * @param driver
	 */
	public AddTicketPage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * To check if the current page is add page
	 * 
	 * @return true if the title matches
	 */
	public Boolean verifyAddTicketPage() {
		webWait = new WebDriverWait(driver, 10);
		try {
			webWait.until(ExpectedConditions.titleIs("Search | Ticketfly"));
			String pageTitle = driver.getTitle();
			Log.info("Page Title is: " + pageTitle);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * To get the max.quantity of tickets allowed for booking
	 * This may vary for different events
	 * @param No parameter
	 * @return maxQty - Maximum allowed number of tickets
	 */
	public int getMaxQuantity() {
		WebElement maxQuantity = driver.findElement(maxQtyLocator);
		int maxQty = Integer.parseInt(maxQuantity.getAttribute("max"));
		Log.info("Max Quantity of tickets available: " + maxQty);
		return maxQty;
	}

	/**
	 * To add the number of tickets required for the user
	 * @param eventID
	 * @return ticketsRequired - added ticket quantity
	 */
	public int clickTicketQuantity(int ticketsRequired) {
		WebElement addTicketQuantity = driver.findElement(plusButtonLocator);
		//Click the plus button for the required quantity
		for (int i = 1; i <= ticketsRequired; i++) {
			addTicketQuantity.click();
		}
		Log.info("No of tickets added: " + ticketsRequired);
		return ticketsRequired;
	}

	/**
	 * To add tickets to the cart by clicking GET TICKETS button
	 * @param eventID
	 * @return DeliveryPage - Instance of DeliveryPage
	 */
	public DeliveryPage clickGetTickets() {
		webWait = new WebDriverWait(driver, 10);
		webWait.until(ExpectedConditions
				.elementToBeClickable(getTicketButtonLocator));
		WebElement getTicket = driver.findElement(getTicketButtonLocator);
		try {
			getTicket.click();
		} catch (Exception e) {
			Log.info("Inside Catch Exception");
			int elementPosition = getTicket.getLocation().getY();
			String js = String.format("window.scroll(0, %s)", elementPosition);
			((JavascriptExecutor) driver).executeScript(js);
			getTicket.click();
		}
		Log.info("Adding tickets to the cart");
		return new DeliveryPage(driver);
	}

}
