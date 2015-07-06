/**
 * @author Kalaiselvan Ulaganathan
 * @Date 06/15/2015
 * @Version 1.0
 * @Description This class contains page objects related to the Home/Main page of the application
 **/

package com.ticketfly.ticketfly.pages;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
		private WebDriver driver=null;
		private WebDriverWait webWait=null;
		final static Logger Log = Logger.getLogger(HomePage.class.getName());

		//Choose the center event in the scrolling events at top
		private By eventInHomePageLocator=By.xpath(".//*[@id='post-16']/div[1]/div/div[3]/div/div[2]/div/h3/a");
		//To locate the search event text box
		private By eventIDLocator=By.id("q");
		//To locate the search event Find button
		private By findEventsButtonLocator=By.id("header-search-btn");
		
		/**
		 * constructor for home page
		 * @param driver
		 */
		public HomePage(WebDriver driver){
			this.driver=driver;
		}
		
		/**
		 * To check if the current page is Home Page of the application
		 * @return true if the title matches
		 */
		public Boolean verifyHomePage(){
			webWait=new WebDriverWait(driver,10);
			try{
				webWait.until(ExpectedConditions.titleIs("Ticketfly | Buy Tickets"));
				String pageTitle=driver.getTitle();
				Log.info("Page Title is: "+pageTitle);
				return true;
			}catch(Exception e){
				return false;
			}
		}
		
		/**
		 * To get an Event name/ID from the home page
		 * @return eventID
		 */
		public String getEventID(){
			String eventID=null;
			webWait=new WebDriverWait(driver,30);
			webWait.until(ExpectedConditions.visibilityOfElementLocated(eventInHomePageLocator));
			WebElement eventInHomePage=driver.findElement(eventInHomePageLocator);
			eventID=eventInHomePage.getText();
			Log.info("Event ID retrieved: "+eventID);		
			return eventID;
		}
		
		/**
		 * To enter Event name/ID in the find event search box
		 * @param eventID
		 */
		public void enterEventID(String eventID){
			Log.info("Event ID: "+eventID);
			webWait=new WebDriverWait(driver,10);
			webWait.until(ExpectedConditions.presenceOfElementLocated(eventIDLocator));
			WebElement txtEmailID=driver.findElement(eventIDLocator);
			txtEmailID.clear();
			txtEmailID.sendKeys(eventID);
		}
		
		/**
		 * To click the Find events button in the application Home Page
		 */
		public SearchResultsPage clickFindEventsButton(){
			webWait=new WebDriverWait(driver,10);
			webWait.until(ExpectedConditions.presenceOfElementLocated(findEventsButtonLocator));
			WebElement btnFindEvents=driver.findElement(findEventsButtonLocator);
			btnFindEvents.click();
			return new SearchResultsPage(driver);
		}
}
