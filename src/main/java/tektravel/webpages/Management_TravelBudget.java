package tektravel.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import web.controller.WebActions;

public class Management_TravelBudget extends WebActions{
	WebDriver driver;
	
	public By tabTravelBudget = By.xpath(".//li[@id='reportTravelBudget']//a");
	
	public Management_TravelBudget(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public boolean tabTravelBudgetDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(tabTravelBudget).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	//Select tab Travel Budget
	public void selectTabCostProjection() {
		waitForElementClickable(5,tabTravelBudget);
		clickByJavaScript(tabTravelBudget);
		sleep(2);
	}
	
	
	
}
