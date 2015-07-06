/**
 * @author Kalaiselvan Ulaganathan
 * @Date 06/15/2015
 * @Version 1.0
 * @Description This is the driver script for the test suite. Web driver instance and other resources are
 *              initiated here
 **/
package com.ticketfly.ticketfly.testscripts;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import com.ticketfly.ticketfly.frameworklibrary.FrameworkMethods;
import com.ticketfly.ticketfly.utility.XMLUtility;

public abstract class DriverTest {
	//Web driver Instance used in the test suite
	protected WebDriver driver; 
	//Low level logging using log4j
	final static Logger Log = Logger.getLogger(DriverTest.class.getName());
	protected static String testDataPath="";
	
	 /**
	 * To initiate the test suite by setting Environment variables, creating web driver instance and
	 * launching browser to start test execution. 
	 * @throws Exception - Handle any exception
	 */
	@BeforeSuite
	public void invokeBrowser() throws Exception {
		DOMConfigurator.configure("resources//log4j.xml");
		Log.info("Test suite execution starts now");
		//Extract the environment variables from Resources/envconfig.xml 
		XMLUtility xmlUtil=new XMLUtility();
		HashMap<String,String> envXMLVariables=xmlUtil.getXMLData();
		String browser=envXMLVariables.get("browser");
		String appURL=envXMLVariables.get("appURL");
		//Used only if the selected browser is chrome
		String chromeDriverPath=envXMLVariables.get("chromeDriverPath");
		//Used only if the selected browser is firefox
		String firefoxBrowserPath=envXMLVariables.get("firefoxBrowserPath");
		//Instantiate webdriver based on selected browser to test
		FrameworkMethods objFrmWrk=new FrameworkMethods();
		driver = objFrmWrk.getWebDriver(browser,chromeDriverPath,firefoxBrowserPath);
		//Implicit timeout setting for the test suite
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//launch the browser & open the app URL
		driver.get(appURL);
		//Maximize the browser window
		driver.manage().window().maximize();
	}
	    
	
	/**
	 * This method is used to gracefully exit the test execution by signing out of the 
	 * application and closing the browser instance.
	 * @throws Exception the exception
	 */
	@AfterSuite(alwaysRun = true)
	public void cleanUpWebDriver() {
        try {
    		Log.info("End of Test suite execution");
            if (this.driver != null) {
        		this.driver.close();
                this.driver.quit();
                this.driver = null;
            }

        } catch (Exception e) {
            // Driver maybe already closed. Ignore.
        }
    }
}
