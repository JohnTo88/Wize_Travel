package tektravel.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import web.controller.WebActions;

public class Management_MyRequests extends WebActions{
	WebDriver driver;
	String status;

	public By tabMyRequests = By.xpath(".//li[@id='submyRequest']//a");
	
	private By iconCancel = By.xpath("(.//div[@id='projection-grid']//i[@class='icon icon-cancel-circle'])[1]");
	private By iconEdit = By.xpath("(.//div[@id='projection-management']//i[@class='icon icon-pencil'])[1]");
	
	private By btnFilter = By.xpath(".//button[@data-bind='click: filter']");
	
	public Management_MyRequests(WebDriver driver){
		super(driver);
		this.driver = driver;
	}
	
	public boolean tabMyRequestsDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(tabMyRequests).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	//Select tab My Requests
	public void selectTabMyRequests() {
		waitForElementClickable(5,tabMyRequests);
		driver.findElement(tabMyRequests).click();
		sleep(1);
	}
	
	//Click on icon Cancel Request
	public void clickIconCancelRequest() {
		waitForElementClickable(5,iconCancel);
		driver.findElement(iconCancel).click();
		sleep(1);
	}
	
	//Click on icon Edit Request
	public void clickIconEditRequest() {
		waitForElementClickable(5,iconEdit);
		driver.findElement(iconEdit).click();
		sleep(1);
	}
	
	//Click on button Filter
	public void clickButtonFilter() {
		driver.findElement(btnFilter).click();
	}
	
	public boolean buttonFilterDisplayed()
	{
		try {
			waitForElementVisible(10, btnFilter);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (isElementPresent(btnFilter))
		{
			return true;
		}
		else
		{
			return false;
		}
		
	}
	
	//Filter request by Status in My Requests 
	public void selectStatus(String _status) {
		status = _status;
		clickByJavaScript(By.xpath(".//*[@id='projection-management']/div[1]/div[6]/span[1]/span[1]/span[2]/span[1]"));
		
		sleep(2);
		
		clickOnElement(driver.findElement(By.xpath(".//li[@data-offset-index = '"+_status+"']")));		
	}
	
	public void confirmUpdatedStatus() {
		
	}
	
}
