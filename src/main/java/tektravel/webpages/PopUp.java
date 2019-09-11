package tektravel.webpages;

import java.awt.AWTException;
import java.awt.Robot;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.Mouse;

import Utils.TienIch;
import web.controller.WebActions;

public class PopUp extends WebActions{
	WebDriver driver;
	
	private By txtComment1 = By.xpath(".//div[@class='modal-dialog']//textarea");
	private By confirmSubmit = By.xpath(".//div[@class='modal-dialog']//button[@data-bind='click: confirm']");
	
	private By txtComment2 = By.xpath(".//div[@id='trip-approval-modal']//textarea[@id='commentext']");
	private By sendApprove = By.xpath(".//div[@id='trip-approval-modal']//button[@class='btn btn-primary']");

	private By txtComment3 = By.xpath(".//div[@id='trip-confirm-modal']//textarea[@id='commentext']");
	private By btnUpdateTrip = By.xpath(".//div[@id='trip-confirm-modal']//button[@class='btn btn-primary']");
	
	private By btnConfirmYes = By.xpath(".//div[@id='popupWindow']//button[@class='confirm_yes btn btn-primary']");
	private By btnConfirmCancel = By.xpath(".//div[@id='popupWindow']//button[@class='confirm_no btn btn-default']");
	
	private By txtComment4 = By.xpath(".//div[@id='projection-confirm-modal']//textarea[@id='commentext']");
	private By btnUpdateApprove = By.xpath(".//div[@class='modal-dialog']//button[@data-bind='visible: isApproveAction, click: confirmApprove']");
	private By btnUpdateReject = By.xpath(".//div[@class='modal-dialog']//button[@data-bind='invisible: isApproveAction, click: confirmReject']");
	
	private By btnYes = By.xpath(".//button[@class='confirm_yes btn btn-primary']");
	private By btnCancel = By.xpath(".//button[@class='confirm_no btn btn-default']");
	
	private By btnSendArrangements = By.xpath(".//button[@data-bind='visible:isFinanceRole,click: Sendemail']");
	private By btnCancelSend = By.xpath(".//div[@id='send-email-modal']//button[@class='btn btn-default']");
	
	private By btnSignOutYes = By.xpath(".//div[@id='logout-confirm-modal']//input[@class='btn btn-primary']");
	private By btnSignOutNo = By.xpath("//div[@id='logout-confirm-modal']//input[@class='btn btn-default close-request-modal']");
	
	private By btnSubmitPassport = By.xpath(".//div[@id='add-passport-visa-modal']//button[@class='btn btn-primary']");
	private By btnCancelPassport = By.xpath(".//div[@id='add-passport-visa-modal']//button[@class='btn btn-default']");
	
	private By btnSaveAssigned = By.xpath(".//div[@id='tc-assigned-modal']//button[@class='btn btn-primary']");
	private By btnCancelAssigned = By.xpath(".//div[@id='tc-assigned-modal']//button[@class='btn btn-default']");

	public PopUp(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	//Pop-up Travel Request Confirmation
	public void travelRequestConfirmation() throws InterruptedException {
		waitForElementPresent(10, txtComment1);
		sleep(2);
		driver.findElement(txtComment1).sendKeys( "Automation_" + TienIch.taoRandomSo(10));
		driver.findElement(confirmSubmit).click();
	}

	//Pop-up Send Approval Confirmation
	public void sendApprovalConfirmation() throws InterruptedException {
		waitForElementClickable(10, txtComment2);
		driver.findElement(txtComment2).sendKeys( "Automation_" + TienIch.taoRandomSo(10));
		sleep(2);
		driver.findElement(sendApprove).click();
	}
	
	//Pop-up Trip Confirmation
	public void tripConfirmation() throws InterruptedException {
		waitForElementPresent(10, txtComment3);
		driver.findElement(txtComment3).sendKeys( "Automation_" + TienIch.taoRandomSo(10));
		sleep(2);
		driver.findElement(btnUpdateTrip).click();
	}
	
	//Select Option Yes/No in pop-up Confirm Cancellation
	public void confirmCancellationYes() {
		try {
			waitForElementPresent(10, btnConfirmYes);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (isElementPresent(btnConfirmYes))
		{
			clickByJavaScript(btnConfirmYes);
		}
	}
	
	public void confirmCancellationNo() {
		try {
			waitForElementPresent(10, btnConfirmCancel);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if (isElementPresent(btnConfirmCancel))
		{
			clickByJavaScript(btnConfirmCancel);
		}
	}
	
	//Pop-up Cost Projection Confirmation - Approve Request
	public void costProjectionConfirmationApprove() throws InterruptedException {
		waitForElementClickable(10,txtComment4);
		driver.findElement(txtComment4).sendKeys( "Automation_" + TienIch.taoRandomSo(10));
		sleep(2);
		driver.findElement(btnUpdateApprove).click();
	}
	
	//Pop-up Cost Projection Confirmation - Reject Request
	public void costProjectionConfirmationReject() throws InterruptedException {
		waitForElementClickable(10,txtComment4);
		driver.findElement(txtComment4).sendKeys( "Automation_" + TienIch.taoRandomSo(10));
		sleep(2);
		driver.findElement(btnUpdateReject).click();
	}
	
	//Pop-up Compelete Trip Confirmation
	public void completeTripComfirmationYes() {
		waitForElementClickable(10,btnYes);
		driver.findElement(btnYes).click();
	}
	
	public void completeTripComfirmationNo() {
		waitForElementClickable(10,btnCancel);
		driver.findElement(btnCancel).click();
	}
	
	//Pop-up Cancel Trip Confirmation
	public void cancelTripComfirmationYes() {
		waitForElementClickable(10,btnYes);
		driver.findElement(btnYes).click();
	}
	
	public void cancelTripComfirmationNo() {
		waitForElementClickable(10,btnCancel);
		driver.findElement(btnCancel).click();
	}
	
	//Pop-up Send Arrangements
	public void sendArrangements() {
		waitForElementClickable(10,btnSendArrangements);
		driver.findElement(btnSendArrangements).click();
	}
	
	public void cancelSendArrangements() {
		waitForElementClickable(10,btnCancelSend);
		driver.findElement(btnCancelSend).click();
	}	
	//Pop-up Confirmation Sign Out
	public void signOutConfirmationYes(){
		waitForElementClickable(10,btnSignOutYes);
		driver.findElement(btnSignOutYes).click();
		sleep(2);
	}

	public void signOutConfirmationNo(){
		waitForElementClickable(10,btnSignOutNo);
		driver.findElement(btnSignOutNo).click();
	}
	
	//Pop-up Add Passport & Visa in Trip Preview
	public void addPassportVisaSubmit() {
		waitForElementClickable(10,btnSubmitPassport);
		driver.findElement(btnSubmitPassport).click();
		sleep(10);
	}
	
	public void addPassportVisaCancel() {
		waitForElementClickable(10,btnCancelPassport);
		driver.findElement(btnCancelPassport).click();
		sleep(2);
	}
	
	//Pop-up Assigning Travel Coordinator in Trip Preview
	public void saveAssignment() {
		waitForElementClickable(10,btnSaveAssigned);
		driver.findElement(btnSaveAssigned).click();
		sleep(2);
	}
	
	public void cancelAssignment() {
		waitForElementClickable(10,btnCancelAssigned);
		driver.findElement(btnCancelAssigned).click();
		sleep(2);
	}
	
	//Pop-up Browser
	public void popUpBrowser() throws AWTException {
		Robot r = new Robot();
		r.mouseMove(631, 154);
		r.mousePress(Mouse.LEFT);
		r.mouseRelease(Mouse.LEFT);
		
		sleep(2);
		
		r.mouseMove(927, 165);
		r.mousePress(Mouse.LEFT);
		r.mouseRelease(Mouse.LEFT);
	}
		
}
