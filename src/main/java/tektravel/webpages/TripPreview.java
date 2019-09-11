package tektravel.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TripPreview extends TravelRequest{
	WebDriver driver;
	String note, line, assignedTC;
	
	private By txtTravellerRibbon = By.xpath(".//li[@id='ribbon-info']//span[@class='brand-primary']");
	private By popoverInfo = By.xpath(".//div[@id='popover-traveler']");
	private By btnShowMore = By.xpath(".//a[@id='show-more']");
	private By btnShowLess = By.xpath(".//a[@id='show-less']");
	
	private By btnCompleteTrip = By.xpath(".//button[@data-bind='click: updateTripCompleted']");
	private By btnCancelTrip = By.xpath(".//button[@data-bind='click: updateTripCancel']");
	private By btnSendArrangements = By.xpath(".//button[@id='btnSendMail']");
	
	private By iconEditConfidential = By.xpath(".//div[@class='info-text']//div[@class='request-box']//i[@class='icon icon-pencil']");
	private By confidentialNote = By.xpath(".//div[@class='info-text']//div[@class='request-box']//h3");
	private By txtConfidentialNote = By.xpath(".//div[@class='info-text']//div[@class='request-box']//textarea[@id='trip-note']");
	private By btnSaveConfidentialNote = By.xpath(".//div[@class='info-text']//div[@class='request-box']//button[@class='btn btn-primary']");
	private By btnCancelConfidentialNote = By.xpath(".//div[@class='info-text']//div[@class='request-box']//button[@class='btn btn-default']");
	
	private By txtConfidentialNoteVisible = By.xpath(".//div[@class='info-text']//div[@data-bind='visible:IsVisibleEditTripNote,text: TripNote']");
	private By txtPassportExpiryDateVisible = By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table mrt10']//tr[1]//td[4]");
	private By txtPassportIssuingCountryVisible = By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table mrt10']//tr[1]//td[5]");
	
	private By txtVisaExpiryDateVisible = By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table']//tr[1]//td[5]");
	private By txtVisaEntriesVisible = By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table']//tr[1]//td[6]");
	private By txtVisaTypeVisible = By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table']//tr[1]//td[7]");
	
	private By btnAddVisa = By.xpath(".//button[@id='btnAddVisa']");
	
	private By txtAssignmentTripVisible = By.xpath(".//b[@id='assignedTCFullName']");
	private By iconEditAssignmentTrip = By.xpath(".//i[@data-bind='visible: isTravelCoordinator, events: { click: ShowAssignedTCDialog }']");
	
	private By tabHistoryLog = By.xpath(".//a[@href=\"#history\"]");

	public TripPreview(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}
	
	public void clickTravelerRibbonName(){
		waitForElementClickable(5,txtTravellerRibbon);
		driver.findElement(txtTravellerRibbon).click();
		sleep(2);
	}
	
	public void clickIconEditAssignmentTrip(){
		waitForElementClickable(5,iconEditAssignmentTrip);
		driver.findElement(iconEditAssignmentTrip).click();
		sleep(2);
	}
	
	public void clickbtnCompleteTrip() {
		waitForElementClickable(5,btnCompleteTrip);
		driver.findElement(btnCompleteTrip).click();
	}
	
	public void clickbtnCancelTrip() {
		waitForElementClickable(5,btnCancelTrip);
		driver.findElement(btnCancelTrip).click();
	}
	
	public void clickbtnSendArrangements() {
		waitForElementClickable(5,btnSendArrangements);
		driver.findElement(btnSendArrangements).click();
		sleep(2);
	}
	
	public void clickbtnShowMore() {
		waitForElementClickable(5,btnShowMore);
		driver.findElement(btnShowMore).click();
		sleep(2);
	}
	
	public void clickbtnShowLess() {
		waitForElementClickable(5,btnShowLess);
		driver.findElement(btnShowLess).click();
		sleep(2);
	}
	
	public void clickIconEditConfidentials() {
		waitForElementClickable(5,iconEditConfidential);
		driver.findElement(iconEditConfidential).click();
		driver.findElement(txtConfidentialNote).clear();
		sleep(2);
	}
	
	public void inputConfidentialsNote(String _Note) {
		note = _Note;
		waitForElementClickable(5,txtConfidentialNote);
		driver.findElement(txtConfidentialNote).sendKeys(_Note);
		sleep(1);
	}
	
	public void clickbtnSaveConfidentials() {
		waitForElementClickable(5,btnSaveConfidentialNote);
		driver.findElement(btnSaveConfidentialNote).click();
		sleep(1);
	}
	
	public void clickbtnCancelConfidentials() {
		waitForElementClickable(5,btnCancelConfidentialNote);
		driver.findElement(btnCancelConfidentialNote).click();
		sleep(1);
	}
	
	public boolean iconEditConfidentialsNoteDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(iconEditConfidential).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean confidentialsNoteDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(confidentialNote).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
	
	}
	
	public boolean travellerRibbonDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(popoverInfo).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean showMoreDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(btnShowMore).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean showLessDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(btnShowLess).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean morePassportDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table mrt10']//tr[2]//td[4]")).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean moreVisaDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table']//tr[2]//td[5]")).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean assignmentTripFieldDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(txtAssignmentTripVisible).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean iconEditassignmentTripDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(iconEditAssignmentTrip).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean isPassportDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(iconEditAssignmentTrip).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	//Select tab 'History' in Trip Preview
	public void selectTabHistoryLog() {
		waitForElementClickable(5,tabHistoryLog);
		driver.findElement(tabHistoryLog).click();
		sleep(1);
	}
	
	//View History Log
	public String viewHistoryLog() throws InterruptedException {
		clickByJavaScript(By.xpath("(.//a[@class='k-icon k-i-expand'])[1]"));
		sleep(2);
		String infoNewAssign = driver.findElement(By.xpath(".//tr[@class='k-detail-row']//td[3]")).getText();
		return infoNewAssign;		
	}
	
	//Select Assigned TC for trip
	public void selectAssignedTC(String _assignedTC) {
		assignedTC = _assignedTC;
		clickByJavaScript(By.xpath(".//div[@id='tc-assigned-modal']//span[@class='k-icon k-i-arrow-s']"));
		sleep(1);
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_assignedTC+"'])[2]"));
		sleep(2);
	}
	
	//Remove  Assigned TC for trip
	public void removeAssignedTC() {
		clickByJavaScript(By.xpath(".//div[@id='tc-assigned-modal']//span[@class='k-icon k-i-arrow-s']"));
		sleep(2);
		clickOnElement(driver.findElement(By.xpath("(.//div[@class='k-list-optionlabel' and .//text()= 'Select...'])[1]")));		
		sleep(2);
	}
	
	//Get Info Passport Expiry Date in Trip Preview 
	public String getInfoPassportExpiryDate(String _line) throws InterruptedException {
		waitForElementVisible(10, txtPassportExpiryDateVisible);
		line = _line;
		String infoPassportExpiryDate = driver.findElement(By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table mrt10']//tr["+_line+"]//td[4]")).getText();
		return infoPassportExpiryDate;
	}
	
	//Get Info Passport Issuing Country in Trip Preview
	public String getInfoPassportIssuingCountry(String _line) throws InterruptedException {
		waitForElementVisible(10, txtPassportIssuingCountryVisible);
		line = _line;
		String infoPassportIssuingCountry = driver.findElement(By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table mrt10']//tr["+_line+"]//td[5]")).getText();
		return infoPassportIssuingCountry;
	}
	
	//Get Info Confidential Note in Trip Preview
	public String getInfoConfidentialNote() throws InterruptedException {
		waitForElementVisible(10, txtConfidentialNoteVisible);
		String infoConfidentialNote = driver.findElement(txtConfidentialNoteVisible).getText();
		return infoConfidentialNote;
	}
	
	//Get Info Visa Expiry Date in Trip Preview
	public String getInfoVisaExpiryDate(String _line) throws InterruptedException {
		waitForElementVisible(10, txtVisaExpiryDateVisible );
		line = _line;
		String infoVisaExpiryDate = driver.findElement(By.xpath("(.//div[@class='box-content'])[3]//table[@class='table tek-table']//tr["+_line+"]//td[5]")).getText();
		return infoVisaExpiryDate;
	}
	
	//Get Info Visa Entries in Trip Preview
	public String getInfoVisaEntries(String _line) throws InterruptedException {
		waitForElementVisible(10, txtVisaEntriesVisible );
		line = _line;
		String infoVisaEntries = driver.findElement(By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table']//tr["+_line+"]//td[6]")).getText();
		return infoVisaEntries;
	}
	
	//Get Info Visa Type in Trip Preview
	public String getInfoVisaType(String _line) throws InterruptedException {
		waitForElementVisible(10, txtVisaTypeVisible );
		String infoVisaType = driver.findElement(By.xpath("(//div[@class='box-content'])[3]//table[@class='table tek-table']//tr["+_line+"]//td[7]")).getText();
		return infoVisaType;
	}
	
	//Get Info Assignment Trip in Trip Preview
	public String getInfoAssignmentTrip() throws InterruptedException {
		waitForElementVisible(10, txtAssignmentTripVisible);
		String infoAssignmentTrip = driver.findElement(txtAssignmentTripVisible).getText();
		return infoAssignmentTrip;
	}
	
	//Click on button 'Add Visa' in Trip Preview
	public void clickbtnAddVisa() {
		waitForElementClickable(5,btnAddVisa);
		driver.findElement(btnAddVisa).click();
		sleep(2);
	}
	
}
	

