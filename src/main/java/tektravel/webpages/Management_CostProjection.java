package tektravel.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import web.controller.WebActions;

public class Management_CostProjection extends WebActions{
	WebDriver driver;
	
	public By tabCostProjection = By.xpath(".//div[@class='main-menu active']//a[@href='/Dashboard/Index']");
	
	private By btnFilter = By.xpath(".//button[@data-bind='click: filter']");
	private By projectionName = By.xpath("(.//td[@class='trip-name']//a)[1]");
	
	private By btnApprove = By.xpath(".//button[@data-bind='visible: showBudgetOwnerButtons, click: approve']");
	private By btnReject = By.xpath(".//button[@data-bind='visible: showBudgetOwnerButtons, click: reject']");
	private By btnBack = By.xpath(".//button[@id='btnBack']");
	
	public Management_CostProjection(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean tabCostProjectionDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(tabCostProjection).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}

	//Select tab Cost Projection
	public void selectTabCostProjection() {
		waitForElementClickable(10,tabCostProjection);
		clickByJavaScript(tabCostProjection);
		sleep(2);
	}
	
	//Click on button 'Filter'
	public void clickbtnFilter() {
		waitForElementClickable(10,btnFilter);
		clickByJavaScript(btnFilter);
		sleep(3);
	}
	
	public boolean buttonFilterDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(btnFilter).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean btnApproveDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(btnApprove).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean btnRejectDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(btnReject).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	//Filter request by Status in Cost Projection
	public void filterRequestStatus(String _status) {
		sleep(3);
		clickByJavaScript(By.xpath(".//*[@id='projection-management']/div[1]/div[6]/span[1]/span[1]/span[2]/span[1]"));
		sleep(1);
		clickOnElement(driver.findElement(By.xpath(".//li[@data-offset-index = '"+_status+"']")));		
	}
	
	//Select Projection Name
	public void selectProjectionName() {
		clickByJavaScript(projectionName);
		sleep(3);
	}
	
	//Click on button Approve
	public void clickbtnApprove() {
		waitForElementClickable(10,btnApprove);
		clickByJavaScript(btnApprove);
		sleep(1);
	}
	
	//Click on button Reject 
	public void clickbtnReject() {
		waitForElementClickable(10,btnReject);
		driver.findElement(btnReject).click();
		sleep(1);
	}
	
	//Click on button Back
	public void clickbtnBack() {
		driver.findElement(btnBack).click();
	}
	
	
}
