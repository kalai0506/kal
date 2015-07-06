/**
 * @author Kalaiselvan Ulaganathan
 * @Date 06/15/2015
 * @Version 1.0
 * @Description This class contains page objects related to the Delivery page of the application
 **/

package com.ticketfly.ticketfly.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DeliveryPage {
		private WebDriver driver=null;
		private WebDriverWait webWait=null;
		final static Logger Log = Logger.getLogger(DeliveryPage.class.getName());

		//To locate the Number of tickets available in the cart
		private By ticketQtyLocator=By.xpath(".//*[@id='wrapper']/section[2]/form/div/div/section[1]/div/div[3]");

		/**
		 * Constructor for the Deliver Page
		 * @param driver
		 */
		public DeliveryPage(WebDriver driver){
			this.driver=driver;
		}
		/**
		 * To check if the current page is Home Page of the application
		 * @return true if the title matches
		 */
		public Boolean verifySearchResultPage(){
			webWait=new WebDriverWait(driver,10);
			try{
				webWait.until(ExpectedConditions.titleIs("Search | Ticketfly"));
				String pageTitle=driver.getTitle();
				Log.info("Page Title is: "+pageTitle);
				return true;
			}catch(Exception e){
				return false;
			}
		}
		
		/**
		 * To get the Ticket Quantity for the event selected in the cart
		 * @param eventID
		 */
		public String getTicketQty(){
			String txtQuantity=null;
			WebElement getTicket=driver.findElement(ticketQtyLocator);
			txtQuantity=getTicket.getText();
			Log.info("Quantity text in delivery page: "+txtQuantity);		
			return txtQuantity; 
		}
		
		

		
}
