package tektravel.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import web.controller.WebActions;

public class Management_MyTrips extends WebActions{
	WebDriver driver;
	
	public By tabMyTrips = By.xpath(".//li[@id='submyTrip']//a");
	private By requestName_MyTrips = By.xpath("(.//div[@id='travel-grid']//td[@class='trip-name'])[1]"); 
	
	public Management_MyTrips(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	
	public boolean tabMyTripsDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(tabMyTrips).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	//Select tab My Trips
	public void selectTabMyTrips() {
		waitForElementClickable(5,tabMyTrips);
		driver.findElement(tabMyTrips).click();
	}
	
	//Select request name in tab My Trips
	public void selectRequestName_tabMyTrips() {
		waitForElementClickable(5,requestName_MyTrips);
		driver.findElement(requestName_MyTrips).click();
		sleep(5);
	}
}
