package tektravel.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utils.TestManager;
import web.controller.WebActions;

public class Management_TravelData extends WebActions{
	WebDriver driver;
	
	private By tabTravelData = By.xpath(".//li[@id='reportTravelData']");
	
	public Management_TravelData(WebDriver driver) {
		super(driver);
		this.driver = driver;	
	}
	
	public boolean tabTravelDataDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(tabTravelData).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	//Select Menu TRIPS
	public void selectMenuTravelData() {
		waitForElementClickable(5,tabTravelData);
		clickByJavaScript(tabTravelData);
		sleep(2);
	}
	
	
}
