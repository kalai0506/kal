/**
 * @author Kalaiselvan Ulaganathan
 * @Date 07/05/2015
 * @Version 1.0
 * @Description This class contains page objects related to the Ticket search results
 **/

package com.ticketfly.ticketfly.pages;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultsPage {
		private WebDriver driver=null;
		final static Logger Log = Logger.getLogger(SearchResultsPage.class.getName());
		
		//To locate the GET TICKET buttons in the Ticket search results
		private By getTicketsButtonsLocator=By.linkText("Get Tickets");
		private WebDriverWait webWait=null;

		//constructor for Search Results page
		public SearchResultsPage(WebDriver driver){
			this.driver=driver;
		}
		
		/**
		 * To check if the current page is Search Results page
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
		 * To select the first event displayed in the search results by clicking on a
		 * GET TICKETS button
		 * @param eventID
		 * @return AddTicketPage
		 */
		public AddTicketPage selectAnEvent(){
			//Find the list of GET TICKETS button 
			ArrayList<WebElement> eventInHomePage=(ArrayList<WebElement>) driver.findElements(getTicketsButtonsLocator);
			//Click the 1st GET TICKETS button 
			eventInHomePage.get(0).click();
			Log.info("1st Get Ticket button clicked in search results");
			return new AddTicketPage(driver);
		}
		
}
