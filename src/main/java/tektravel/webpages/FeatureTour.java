package tektravel.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import web.controller.WebActions;

public class FeatureTour extends WebActions{
	WebDriver driver;

	private By popUp1 = By.xpath(".//div[@id='step-0']//button[@class='close']");
	private By popUp2 = By.xpath(".//div[@id='step-1']//button[@class='close']");
	
	public FeatureTour(WebDriver driver) {
		super(driver);
	}
	
	//Close pop-up Features Tour - Get Started
	public void closePopUpFeaturesTourGetStarted() {
		if (isElementPresent(popUp1)== true)
		{
			clickByJavaScript(popUp1);
		}
		
	}
	
	//Close pop-up Features Tour - Travel Request
	public void closePopUpFeaturesTourTravelRequest() {
		if (isElementPresent(popUp2)== true)
		{
			clickByJavaScript(popUp2);
		}

	}
}
