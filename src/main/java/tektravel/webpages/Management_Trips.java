package tektravel.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import web.controller.WebActions;

public class Management_Trips extends WebActions{
	WebDriver driver;

	public By tabTrips = By.xpath(".//li[@id='tripsDashboard']//a[@href='/Dashboard/Trips']");
	
	private By tabNewRequest = By.xpath(".//a[@id='newRequestTab']");
	private By tabProcessing = By.xpath(".//a[@id='processingTab']");
	private By tabPendingApproval = By.xpath("//a[@id='pendingTab']");
	private By tabNewTrip = By.xpath(".//a[@id='newTripTap']");
	private By tabArrangementComplete = By.xpath(".//a[@id='sendEmailTap']");
	private By tabOnGoing = By.xpath(".//a[@id='onGoingTap']");
	private By tabFinished = By.xpath(".//a[@id='finishedTap']");
	private By tabCompleted = By.xpath(".//a[@id='completedTap']");
	private By tabExpired = By.xpath(".//a[@id='expiredTap']");
	private By tabRejected = By.xpath(".//a[@id='rejectedTap']");
	private By tabCanceled = By.xpath(".//a[@id='canceledTap']");

	private By valueRequest = By.xpath("(.//div[@id='new_Detail']//tr)[2]");
	private By valueTable = By.xpath(".//*[@id=\"new-grid\"]/div[2]");
	
	private By iconEdit_NewRequest = By.xpath("(.//div[@id='new_Detail']//td[@class='text-center']//i)[1]");
	private By iconEdit_Processing = By.xpath("(.//div[@id='processing_Detail']//td[@class='text-center']//i)[1]");
	
	private By requestName_Finished = By.xpath("(.//div[@id='finished-grid']//td[@class='trip-name'])[1]");
	private By requestName_NewTrip = By.xpath("(.//div[@id='newTrip-grid']//td[@class='trip-name'])[1]");
	private By requestName_NewRequest = By.xpath("(.//div[@id='new-grid']//td[@class='trip-name'])[1]");
	private By requestName_Processing  = By.xpath("(.//div[@id='processing-grid']//td[@class='trip-name'])[1]");
	private By requestName_PendingApproval = By.xpath("(.//div[@id='pending-grid']//td[@class='trip-name'])[1]");
	private By requestName_ArrangementComplete  = By.xpath("(.//div[@id='sendEmail-grid']//td[@class='trip-name'])[1]");
	private By requestName_OnGoing  = By.xpath("(.//div[@id='onGoing-grid']//td[@class='trip-name'])[1]");
	private By requestName_Expired  = By.xpath("(.//div[@id='expired-grid']//td[@class='trip-name'])[1]");
	private By requestName_Rejected  = By.xpath("");
	private By requestName_Completed  = By.xpath("(.//div[@id='completed-grid']//td[@class='trip-name'])[1]");
	private By requestName_Canceled  = By.xpath("(.//div[@id='canceled-grid']//td[@class='trip-name'])[1]");

	public Management_Trips(WebDriver driver){
		super(driver);
		this.driver = driver;			
	}
	
	public boolean tabTripsDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(tabTrips).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean tabProcessingDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(tabProcessing).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	//Select Menu TRIPS
	public void selectMenuTrips() {
		waitForElementClickable(5,tabTrips);
		clickByJavaScript(tabTrips);
		sleep(2);
	}
	
	//Select Tab New Request
	public void selectTabNewRequest() {
		waitForElementClickable(5,tabNewRequest);
		clickByJavaScript(tabNewRequest);
	}

	//Select Tab Processing
	public void selectTabProcessing() {
		waitForElementClickable(10,tabProcessing);
		clickByJavaScript(tabProcessing);
	}
	
	//Select Tab PendingApproval
	public void selectTabPendingApproval() {
		waitForElementClickable(5,tabPendingApproval);
		clickByJavaScript(tabPendingApproval);
	}
	
	//Select Tab NewTrip
	public void selectTabNewTrip() {
		waitForElementClickable(5,tabNewTrip);
		clickByJavaScript(tabNewTrip);
	}
	
	//Select Tab Arrangement Complete
	public void selectTabArrangementComplete() {
		waitForElementClickable(5,tabArrangementComplete);
		clickByJavaScript(tabArrangementComplete);
	}
	
	//Select Tab On Going
	public void selectTabOnGoing() {
		waitForElementClickable(5,tabOnGoing);
		clickByJavaScript(tabOnGoing);
	}
	
	//Select Tab Finished
	public void selectTabFinished() {
		waitForElementClickable(5,tabFinished);
		clickByJavaScript(tabFinished);
	}
	
	//Select Tab Completed
	public void selectTabCompleted() {
		waitForElementClickable(5,tabCompleted);
		clickByJavaScript(tabCompleted);
	}
	
	//Select Tab Expired
	public void selectTabExpired() {
		waitForElementClickable(5,tabExpired);
		clickByJavaScript(tabExpired);
	}
	
	//Select Tab Rejected 
	public void selectTabRejected() {
		waitForElementClickable(5,tabRejected);
		clickByJavaScript(tabRejected);
	}
	
	//Select Tab Canceled 
	public void selectTabCanceled() {
		waitForElementClickable(5,tabCanceled);
		clickByJavaScript(tabCanceled);
	}
	
	//Select Request Name in tab Finished
	public void selectRequestName_tabFinished() {
		waitForElementClickable(15,requestName_Finished);
		driver.findElement(requestName_Finished).click();
		sleep(5);
	}
	
	//Select Request Name in tab New Trip
	public void selectRequestName_tabNewTrip() {
		waitForElementClickable(5,requestName_NewTrip);
		driver.findElement(requestName_NewTrip).click();
		sleep(5);
	}
	
	//Select Request Name in tab New Request
	public void selectRequestName_tabNewRequest() {
		waitForElementClickable(5,requestName_NewRequest);
		driver.findElement(requestName_NewRequest).click();
		sleep(5);
	}
	
	//Select Request Name in tab Processing
	public void selectRequestName_tabProcessing() {
		waitForElementClickable(5,requestName_Processing);
		driver.findElement(requestName_Processing).click();
		sleep(5);
	}
	
	//Select Request Name in tab Arrangement Complete 
	public void selectRequestName_tabArrangementComplete() {
		waitForElementClickable(5,requestName_ArrangementComplete);
		driver.findElement(requestName_ArrangementComplete).click();
		sleep(5);
	}
	
	//Select Request Name in tab On Going
	public void selectRequestName_tabOnGoing() {
		waitForElementClickable(5,requestName_OnGoing);
		driver.findElement(requestName_OnGoing).click();
		sleep(5);
	}
	
	//Select Request Name in tab Pending Approval
	public void selectRequestName_tabPendingApproval() {
		waitForElementClickable(5,requestName_PendingApproval);
		driver.findElement(requestName_PendingApproval).click();
		sleep(5);
	}
	
	//Select Request Name in tab Expired
	public void selectRequestName_tabExpired() {
		waitForElementClickable(5,requestName_Expired);
		driver.findElement(requestName_Expired).click();
		sleep(5);
	}
	
	//Select Request Name in tab Rejected
	public void selectRequestName_tabRejected() {
		
	}
	
	//Get Value Info first request in trip table
	public String getInfoRequest() throws InterruptedException {
		waitForElementVisible(10, valueRequest);
		String infoRequest = driver.findElement(valueRequest).getText();
		return infoRequest;
	}
	
	//Get Value Info all requests in trip table
	public String getInfoTable() throws InterruptedException {
		waitForElementVisible(10, valueTable);
		String infoTabNewRequest = driver.findElement(valueTable).getText();
		return infoTabNewRequest;
	}
	
	//Click on icon Edit in tab New Request
	public void clickIconEdit_NewRequest() throws InterruptedException {
		waitForElementVisible(10, iconEdit_NewRequest);
		driver.findElement(iconEdit_NewRequest).click();
		sleep(3);
	}
	
	//Click on icon Edit in tab Processing
	public void clickIconEdit_Processing() throws InterruptedException {
		waitForElementVisible(10, iconEdit_Processing);
		driver.findElement(iconEdit_Processing).click();
		sleep(3);
	}
	
	
}
