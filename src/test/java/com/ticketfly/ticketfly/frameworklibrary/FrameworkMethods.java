/**
 * @author Kalaiselvan Ulaganathan
 * @version 1.0
 * @since 06/15/2015
 * @Description This class is used to provide re-usable framework level methods.
 */

package com.ticketfly.ticketfly.frameworklibrary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class FrameworkMethods {
	protected WebDriver driver=null;

	/**
	 * This method creates web driver instance based on the browser selected
	 * @param browser
	 * @param chromeDriverExe ChromeDriver.exe file location
	 * @param firefoxPath Firefox browser exe file location
	 * @return Web driver instance
	 * @throws Exception
	 */
	public WebDriver getWebDriver(String browser, String chromeDriverExe,String firefoxPath) throws Exception{
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", chromeDriverExe);
			driver=new ChromeDriver();
		}else if(browser.equalsIgnoreCase("htmlunit")){
			driver=new HtmlUnitDriver();
		}else {
			System.setProperty("webdriver.firefox.bin", firefoxPath);
			driver=new FirefoxDriver();
		}
		return driver;
	}
}
