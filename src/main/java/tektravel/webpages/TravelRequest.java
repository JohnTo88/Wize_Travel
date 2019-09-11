package tektravel.webpages;

import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Key;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import Utils.TienIch;
import web.controller.WebActions;

public class TravelRequest extends WebActions{
	WebDriver driver;
	String airPort, budgetType, title, issuingCountry, nationality, expiredDate, visaTo, startDate, entries, type, budgetOwner, budgetCategory;
	String origin, destination, firstName, middleName, lastName;

	private By menuTravelRequest = By.xpath(".//div[@class='row']//a[@href='/CostProjection/Index' and text()='Travel Request']");
	private By txtRequestName = By.xpath("//*[@id='projectionName']");
	
	public By datePickerDateOnSite = By.xpath(".//span[@aria-controls='start-date-1_dateview']");
	public By datePickerTripStartDate1 = By.xpath(".//*[@id=\"travel-trips\"]/div[1]/div[1]/div[3]/div[3]/span[1]/span[1]/span[1]/span[1]");
	public By datePickerEndDate1 = By.xpath(".//*[@id=\"travel-trips\"]/div[1]/div[1]/div[2]/div[4]/span[1]/span[1]/span[1]/span[1]/span[1]");
	public By datePickerTripEndDate1 = By.xpath(".//*[@id=\"travel-trips\"]/div[1]/div[1]/div[3]/div[4]/span[1]/span[1]/span[1]/span[1]");
	public By datePickerEndDate2 = By.xpath(".//*[@id=\"travel-trips\"]/div[1]/div[2]/div[2]/div[4]/span[1]/span[1]/span[1]/span[1]/span[1]");

	public By fieldDateOnSite = By.xpath(".//*[@id='start-date-1']");
	public By fieldEndDate = By.xpath(".//*[@id='travel-trips']/div[1]/div[1]/div[2]/div[4]/span[1]/span[1]/input[1]");
	public By fieldTripStartDate = By.xpath(".//input[@id='actual-startdate-1']");
	public By fieldTripEndDate = By.xpath(".//input[@name='actual-enddate-1']");
	
	private By txtOriginAirport = By.xpath(".//input[@name='from-airport-code-1']");
	private By txtDestinationAirport = By.xpath(".//input[@name='to-airport-code-1']");
	
	private By checkboxViSa = By.xpath(".//label[@class='vp-checkbox']//input[@data-bind='checked: NeedHelpVisa']");
	private By checkboxAccomodation = By.xpath(".//label[@class='vp-checkbox']//input[@data-bind='checked: Accomodation']");
	private By checkboxTransportation = By.xpath(".//label[@class='vp-checkbox']//input[@data-bind='checked: ArrangeTransportation']");
	
	public By btnsubmit = By.xpath(".//button[@id='request-submit']");
	public By btnaddtrip = By.xpath(".//a[@id='addTrip']");
	public By iconDeleteRoutes = By.xpath("(.//div[@class='close-box']//i)[2]");
	public By btnUpload = By.xpath("//div[@class='upload-box']//button[@data-bind='click: infoUpload']");
	public By fieldChooseFile = By.xpath("//*[@id=\"trip-info\"]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/input[1]");
	
	public By txtPassport = By.xpath(".//input[@id='passportnumber1']");
	public By txtPassport1 = By.xpath(".//input[@id='passportnumberundefined']");
	
	public By txtFirstName = By.xpath(".//input[@id='passportfirstname1']");
	public By txtMiddleName = By.xpath(".//input[@id='passportMiddleName1']");
	public By txtLastName = By.xpath(".//input[@id='passportlastname1']");
	
	public By txtFirstName1 = By.xpath(".//input[@id='passportfirstnameundefined']");
	public By txtMiddleName1 = By.xpath(".//input[@id='passportMiddleNameundefined']");
	public By txtLastName1 = By.xpath(".//input[@id='passportlastnameundefined']");
	
	public By drplist_Title = By.xpath(".//*[normalize-space(text()) and normalize-space(.)='Ms.']");
	
	public By btnFilter = By.xpath(".//button[@data-bind='click: filter']");
	
	public By datePickerPassportExpiredDate = By.xpath(".//input[@id='expireddate1']");
	public By datePickerPassportExpiredDate1 = By.xpath(".//input[@id='expireddateundefined']");
	public By datePickerVisaStartDate = By.xpath(".//input[@id='startDate1']");
	public By datePickerVisaExpiredDate = By.xpath(".//input[@id='expiredDate1']");
	
	private By iconPlusPassport = By.xpath("(.//a[@data-bind='click: add']//i)[1]");
	private By iconMinusPassport = By.xpath("(.//a[@data-bind='click: remove']//i)[1]");
	private By iconPlusVisa = By.xpath("(//a[@data-bind='click: add']//i)[2]");
	private By iconMinusVisa = By.xpath("(//a[@data-bind='click: remove']//i)[2]");

	public TravelRequest(WebDriver driver){
		super(driver);
		this.driver = driver;			
	}
	
	//Select Menu Travel Request
	public void selectMenuTravelRequest(){
		waitForElementClickable(5,menuTravelRequest);
		clickByJavaScript(menuTravelRequest);
		sleep(1);
	}
	
	//Scroll Screen TravelRequest
	public void scrollScreen(int _vertical){
		JavascriptExecutor scroll = (JavascriptExecutor)driver;
		scroll.executeScript("window.scrollBy(0,"+_vertical+")", "");
		sleep(1);
	}
	
	//Option Book for Another
	public void bookOption(int _option){
		clickByJavaScript(By.xpath("//label[@class='vp-radio']//input[@value='"+_option+"']"));
		sleep(1);
	}
	
	//Select user name when book for another 
	public void searchNameBookForOther(String _userName){
		driver.findElement(By.xpath("//*[@id='traveler']//input[@id='other-traveler']")).clear();
		driver.findElement(By.xpath("//*[@id='traveler']//input[@id='other-traveler']")).sendKeys(_userName);
		sleep(3);
	}
	
	//Choose user name when book for another 
	public void chooseNameBookForOther(){
		clickByJavaScript(By.xpath(".//div[@id='other-traveler-list']//li//span"));
		sleep(2);
	}

	//Input Request Name
	public void inputRequestName(String _requestName) {
		waitForElementClickable(5,txtRequestName);
		driver.findElement(txtRequestName).sendKeys(_requestName);
	}
	
	//Get Info Message Request Name
	public String getInfoMessageRequestName() throws InterruptedException {
		String infoMessageRequestName = driver.findElement(By.xpath(".//span[@id='projection-name_validationMessage']")).getText();
		return infoMessageRequestName;
	}
	
	//Input Origin-Destination
	public void inputLocation(String _origin, String _destination, String _index) {
		driver.findElement(By.xpath(".//input[@id='from-city-"+_index+"']")).clear();
		driver.findElement(By.xpath(".//input[@id='from-city-"+_index+"']")).sendKeys(_origin);
		sleep(1);
		driver.findElement(By.xpath(".//input[@id='to-city-"+_index+"']")).clear();;
		driver.findElement(By.xpath(".//input[@id='to-city-"+_index+"']")).sendKeys(_destination);
		sleep(1);
	}
	
	//Get Info Message Origin
	public String getInfoMessageOrigin() throws InterruptedException {
		String infoMessageOrigin = driver.findElement(By.xpath(".//span[@id='from-city-1_validationMessage']")).getText();
		return infoMessageOrigin;
	}
	
	//Get Info Message Destination
	public String getInfoMessageDestination() throws InterruptedException {
		String infoMessageDestination = driver.findElement(By.xpath(".//span[@id='to-city-1_validationMessage']")).getText();
		return infoMessageDestination;
	}
	
	//Select Date On Site = current day + Length of days
	public String dateOnSite(int lengthofday1) {
		clickByJavaScript(datePickerDateOnSite);
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday1).getText().length()>0 && (i+lengthofday1)<list.size())
			{
				list.get(i+lengthofday1).click();
				break;
			}
		}
		
		String valueDateOnSite = driver.findElement(fieldDateOnSite).getAttribute("value");
		return valueDateOnSite;
			
	}
	
	//Get Info Message Date On Site
	public String getInfoMessageDateonSite() throws InterruptedException {
		String infoMessageDateonSite = driver.findElement(By.xpath(".//span[@id='start-date-1_validationMessage']")).getText();
		return infoMessageDateonSite;
	}
	
	//Select End Date = DateonSite + Length of days
	public String endDate(int lengthofday2) {
		clickByJavaScript(datePickerEndDate1);
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));
		
		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday2).getText().length()>0 && (i+lengthofday2)<list.size())
			{
				list.get(i+lengthofday2).click();
				break;
			}
		}
		
		String valueEndDate = driver.findElement(fieldEndDate).getAttribute("value");
		return valueEndDate;
	}
	
	//Select Trip Start Date = current day + Length of days
	public String tripStartDate(int lengthofday3) {
		clickByJavaScript(datePickerTripStartDate1);
		sleep(1);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));
		
		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday3).getText().length()>0 && (i+lengthofday3)<list.size())
			{
				list.get(i+lengthofday3).click();
				break;
			}
		}
		
		String valueTripStartDate = driver.findElement(fieldTripStartDate).getAttribute("value");
		return valueTripStartDate;
		
	}
	
	//Get Info Message Trip Start Date
	public String getInfoMessageTripStartDate() throws InterruptedException {
		String infoMessageTripStartDate = driver.findElement(By.xpath(".//span[@id='actual-startdate-1_validationMessage']")).getText();
		return infoMessageTripStartDate;
	}
	
	//Select Trip End Date = Trip Start Date + Length of days
	public String tripEndDate(int lengthofday4) {		
	clickByJavaScript(datePickerTripEndDate1);
	sleep(2);
	List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));
	
	for (int i =0;i<list.size();i++)
	{
		
		if (list.get(i).getText().length()>0 && list.get(i+lengthofday4).getText().length()>0 && (i+lengthofday4)<list.size())
		{
			list.get(i+lengthofday4).click();
			break;
		}
	}
	
	String valueEndDate = driver.findElement(fieldEndDate).getAttribute("value");
	return valueEndDate;
	
	}
	
	//Get Info Message Trip End Date
	public String getInfoMessageTripEndDate() throws InterruptedException {
		String infoMessageTripEndDate = driver.findElement(By.xpath(".//span[@id='actual-enddate-1_validationMessage']")).getText();
		return infoMessageTripEndDate;
	}
	
	public String endDate2(int lengthofday2) {
		clickByJavaScript(datePickerEndDate2);
		sleep(1);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));
		list.size();
		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday2).getText().length()>0 && (i+lengthofday2)<list.size())
			{
				list.get(i+lengthofday2).click();
				break;
			}
		}
		
		String valueEndDate = driver.findElement(fieldEndDate).getAttribute("value");
		return valueEndDate;
	}
	
	
	
	//Get Info Message End Date
	public String getInfoMessageEndDate() throws InterruptedException {
		String infoMessageEndDate = driver.findElement(By.xpath(".//span[@id='end-date-1_validationMessage']")).getText();
		return infoMessageEndDate;
	}
	
	//Input Airport Code into two fields
	public void inputOriginAirPortCode(String _originAirPort) {
		waitForElementClickable(5,txtOriginAirport);
		driver.findElement(txtOriginAirport).clear();
		driver.findElement(txtOriginAirport).sendKeys(_originAirPort);
		sleep(1);
	}
	
	//Get Info Message Origin AirPort 
	public String getInfoMessageOriginAirport() throws InterruptedException {
		String infoMessageOriginAirport = driver.findElement(By.xpath(".//span[@id='from-airport-code-1_validationMessage']")).getText();
		return infoMessageOriginAirport;
	}
	
	public void inputDestinationAirPort(String _destinationAirPort) {	
		waitForElementClickable(5,txtDestinationAirport);
		driver.findElement(txtDestinationAirport).clear();
		driver.findElement(txtDestinationAirport).sendKeys(_destinationAirPort);
		sleep(1);
	}
	
	//Get Info Message Destination AirPort 
	public String getInfoMessageDestinationAirport() throws InterruptedException {
		String infoMessageDestinationAirport = driver.findElement(By.xpath(".//span[@id='to-airport-code-1_validationMessage']")).getText();
		return infoMessageDestinationAirport;
	}

	//Select Budget Type in Travel Request Screen
	public void selectBudgetType_TravelRequest(String _budgetType){
		budgetType = _budgetType;
		clickOnElement(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select Budget Type...'])[1]/following::span[2]")));
		sleep(1);
		clickOnElement(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_budgetType+"'])[2]")));
		sleep(2);
	}
	
	//Select Budget Owner in Travel Request Screen
	public void selectBudgetOwner_TravelRequest(String _budgetOwner){
		budgetOwner = _budgetOwner;
		clickOnElement(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select Budget Owner...'])[1]/following::span[2]")));
		sleep(1);
		clickOnElement(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_budgetOwner+"'])[2]")));
		sleep(2);
	}
	
	//Select Budget Category in Travel Request Screen
	public void selectBudgetCategory_TravelRequest(String _budgetCategory){
		budgetCategory = _budgetCategory;
		clickOnElement(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select Budget Category...'])[1]/following::span[2]")));
		sleep(1);
		clickOnElement(driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_budgetCategory+"'])[2]")));
		sleep(2);
	}
	
	//Select check box 'I require assistance obtaining a Visa'
	public void checkboxVisa() {
		clickByJavaScript(checkboxViSa);
		sleep(1);
	}
	
	//Select check box 'I require Accomodation'
	public void checkboxAccomodation() {
		clickByJavaScript(checkboxAccomodation);
		sleep(1);
	}
	
	//Select check box 'Transportation Arrangement'
	public void checkboxTransportation(int _index) {
		clickByJavaScript(checkboxTransportation);
		sleep(1);
		clickByJavaScript(By.xpath(".//label[@class='vp-checkbox']//input[@value='"+_index+"']"));
	}
	
	//Input Note in Travel Request
	public void inputRequestNote(String _requestNote) {	
		WebElement iFrame = driver.findElement(By.tagName("iframe"));
		sleep(2);
		driver.switchTo().frame(iFrame);
		driver.findElement(By.tagName("body")).clear();
		driver.findElement(By.tagName("body")).sendKeys(_requestNote);
		driver.switchTo().defaultContent();
		sleep(2);
	}
	
	//Input Note in Travel Request
	public void inputGuestNote(String _guestNote) {	
		driver.findElement(By.xpath(".//textarea[@name='guest-note']")).clear();
		driver.findElement(By.xpath(".//textarea[@name='guest-note']")).sendKeys(_guestNote);
		sleep(1);
	}
	
	//Get Info Message Request Note 
	public String getInfoMessageRequestNote() throws InterruptedException {
		String infoMessageRequestNote = driver.findElement(By.xpath(".//span[@id='trip-info-note_validationMessage']")).getText();
		return infoMessageRequestNote;
	}
	
	//Get Info Message Guest Note 
	public String getInfoMessageGuestNote() throws InterruptedException {
		String infoMessageGuestNote = driver.findElement(By.xpath(".//span[@id='guest-note_validationMessage']")).getText();
		return infoMessageGuestNote;
	}
	
	//Select button Upload Files in Trip Info Attached Files
	public void clickbtnUploadTripInfo() {
		waitForElementClickable(10,btnUpload);
		driver.findElement(btnUpload).click();
		sleep(2);
	}
	
	//Select field Choose File in Trip Info Attached Files
	public void selectFieldChooseFileTripinfo() {
		waitForElementClickable(10,fieldChooseFile);
		driver.findElement(fieldChooseFile).click();
		sleep(2);
	}
	
	//Select button Upload Files in Guest Info Files
	public void clickbtnUploadGuestInfo() {
		clickByJavaScript(By.xpath(".//button[@data-bind='click: travelerUpload']"));
		sleep(2);
	}
	
	//Select field Choose File in Guest Info Files
	public void selectFieldChooseFileGuestinfo() {
		clickByJavaScript(By.xpath("(.//div[@class='upload-box'])[2]//input"));
		sleep(2);
	}
	
	//Select file to Upload
	public void selectFileUpload(String _fileName) throws FindFailed, InterruptedException {
        String filepath = "C:\\Users\\tuan.do-anh\\Desktop\\Upload File\\";
        String inputFilePath = "C:\\Users\\tuan.do-anh\\Desktop\\Upload File\\";
        Screen s = new Screen();
        
        Pattern fileInputTextBox = new Pattern(filepath + "FileTextBox.png");
        Pattern openButton = new Pattern(filepath + "OpenButton.png");
        
        s.wait(fileInputTextBox, 20);
        s.type("a",Key.CTRL);
        s.type(Key.BACKSPACE);
        s.type(fileInputTextBox, inputFilePath + _fileName);
        s.click(openButton);
	}
	
	//Get Info Message Upload
	public String getInfoMessageUpload() throws InterruptedException {
		String infoMessageUpload = driver.findElement(By.xpath("(.//div[@class='upload-box'])[1]//span")).getText();
		return infoMessageUpload;
	}
	
	public boolean selectedFileDisplayed_TravelRequest()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(By.xpath(".//div[@class='upload-box']//li[@class='selected-file']//a[@class='selected-file-name']")).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	//Select Title in Passport
	public void selectTitle(String _titleIndex) {
		clickByJavaScript(By.xpath("(.//td[@class='air-code']//span[@class='k-icon k-i-arrow-s'])["+_titleIndex+"]"));
		sleep(1);
		clickByJavaScript(drplist_Title);
		sleep(2);
	}
	
	//Input Passport Number
	public void inputPassportNumber() {
		waitForElementClickable(5,txtPassport);
		driver.findElement(txtPassport).clear();
		driver.findElement(txtPassport).sendKeys(TienIch.taoRandomSo(10));
	}
	
	//Add more Passport Number
	public void addMorePassportNumber() {
		waitForElementClickable(5,txtPassport1);
		driver.findElement(txtPassport1).clear();
		driver.findElement(txtPassport1).sendKeys(TienIch.taoRandomSo(10));
	}
	
	//Input Name Under Passport
	public void inputNameUnderPassport(String _firstName, String _middleName, String _lastName) {
		firstName = _firstName;
		middleName = _middleName;
		lastName = _lastName;
	
		driver.findElement(txtFirstName).clear();
		driver.findElement(txtFirstName).sendKeys(_firstName);
		sleep(1);
		
		driver.findElement(txtMiddleName).clear();
		driver.findElement(txtMiddleName).sendKeys(_middleName);
		sleep(1);
		
		driver.findElement(txtLastName).clear();
		driver.findElement(txtLastName).sendKeys(_lastName);
		sleep(1);	
	}
	
	//Input Name Under Passport when add more Passport
	public void addMoreNameUnderPassport(String _firstName, String _middleName, String _lastName) {
		firstName = _firstName;
		middleName = _middleName;
		lastName = _lastName;
	
		driver.findElement(txtFirstName1).clear();
		driver.findElement(txtFirstName1).sendKeys(_firstName);
		sleep(1);
		
		driver.findElement(txtMiddleName1).clear();
		driver.findElement(txtMiddleName1).sendKeys(_middleName);
		sleep(1);
		
		driver.findElement(txtLastName1).clear();
		driver.findElement(txtLastName1).sendKeys(_lastName);
		sleep(1);	
	}
	
	//Select Issuing Country
	public void selectIssuingCountry(String _issuingCountry) {
		issuingCountry = _issuingCountry;
		clickByJavaScript(By.xpath(".//td[@class='air-code']//span[@aria-owns='IssuingCountry1_listbox']//span[@class='k-icon k-i-arrow-s']"));
		sleep(1);
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_issuingCountry+"'])[2]"));
		sleep(3);
	}
	
	//Add more Issuing Country
	public void addMoreIssuingCountry(String _issuingCountry) {
		issuingCountry = _issuingCountry;
		clickByJavaScript(By.xpath(".//td[@class='air-code']//span[@aria-owns='IssuingCountryundefined_listbox']//span[@class='k-icon k-i-arrow-s']"));
		sleep(1);
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_issuingCountry+"'])[4]"));
		sleep(3);
	}
	
	//Select Nationality
	public void selectNationality(String _nationality) {
		nationality = _nationality;
		clickByJavaScript(By.xpath(".//td[@class='air-code']//span[@aria-owns='nationalityName1_listbox']//span[@class='k-icon k-i-arrow-s']"));
		sleep(1);
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_nationality+"'])[2]"));
		sleep(3);
	}
	
	//Add more Nationality
	public void addMoreNationality(String _nationality) {
		nationality = _nationality;
		clickByJavaScript(By.xpath(".//td[@class='air-code']//span[@aria-owns='nationalityNameundefined_listbox']//span[@class='k-icon k-i-arrow-s']"));
		sleep(1);
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_nationality+"'])[4]"));
		sleep(3);
	}
	
	//Select Expired Date in pop-up Add Passport & Visa 
	public void inputPassportExpiredDate(String _expiredDate) {
		expiredDate = _expiredDate;
		driver.findElement(datePickerPassportExpiredDate).clear();
		driver.findElement(datePickerPassportExpiredDate).sendKeys(_expiredDate);
		sleep(1);
	}
	
	//Add more Expired Date in pop-up Add Passport & Visa
	public void addMoreExpiredDate(String _expiredDate) {
		expiredDate = _expiredDate;
		driver.findElement(datePickerPassportExpiredDate1).clear();
		driver.findElement(datePickerPassportExpiredDate1).sendKeys(_expiredDate);
		sleep(1);
	}
	
	//Add more Passport - Icon Plus
	public void clickIconPlusPassport() {
		waitForElementClickable(10,iconPlusPassport);
		driver.findElement(iconPlusPassport).click();
		sleep(1);
	}
	
	//Add more Passport - Icon Minus
	public void clickIconMinusPassport() {
		waitForElementClickable(10,iconMinusPassport);
		clickByJavaScript(iconMinusPassport);
		sleep(1);
	}
	
	//Add more Visa - Icon Plus
	public void clickIconPlusVisa() {
		waitForElementClickable(10,iconPlusVisa);
		driver.findElement(iconPlusVisa).click();
		sleep(1);
	}
	
	//Add more Visa - Icon Minus
	public void clickIconMinusVisa() {
		//waitForElementClickable(10,iconMinusVisa);
		//clickByJavaScript(iconMinusVisa);
		driver.findElement(iconMinusVisa).click();
		sleep(1);
	}
	
	//Select Passport Number for Visa
	public void selectPassportNumber(int _indexPassport) {
		clickByJavaScript(By.xpath("(.//td[@class='air-name']//span[@aria-owns='PassportNumber1_listbox']//span[@class='k-icon k-i-arrow-s'])[1]"));
		sleep(1);
		clickByJavaScript(By.xpath(".//*[@id='PassportNumber1_listbox']//li[@data-offset-index='"+_indexPassport+"']"));
		sleep(3);
	}
	
	//Select more Passport Number
	public void selectMorePassportNumber(int _indexPassport) {
		clickByJavaScript(By.xpath("(.//td[@class='air-name']//span[@aria-owns='PassportNumber1_listbox']//span[@class='k-icon k-i-arrow-s'])[1]"));
		sleep(1);
		clickByJavaScript(By.xpath(".//*[@id='PassportNumber2_listbox']//li[@data-offset-index='"+_indexPassport+"']"));
		sleep(3);
	}
	
	//Input Visa Number
	public void inputVisaNumber(int _indexVisaNumber) {
		driver.findElement(By.xpath("(.//input[@data-bind='value: VisaNumber'])["+_indexVisaNumber+"]")).clear();
		driver.findElement(By.xpath("(.//input[@data-bind='value: VisaNumber'])["+_indexVisaNumber+"]")).sendKeys(TienIch.taoRandomSo(10));
		sleep(1);
	}
	
	//Select Visa To
	public void selectVisaTo(String _visaTo) {
		visaTo = _visaTo;
		clickByJavaScript(By.xpath(".//td[@class='air-name']//span[@aria-owns='visaTo1_listbox']//span[@class='k-icon k-i-arrow-s']"));
		sleep(1);
		clickByJavaScript(By.xpath("//*[@id='visaTo1_listbox']//*[normalize-space(text()) and normalize-space(.)='"+_visaTo+"']"));
		sleep(2);
	}
	
	//Add more Visa To
	public void addMoretVisaTo(String _visaTo) {
		visaTo = _visaTo;
		clickByJavaScript(By.xpath(".//td[@class='air-name']//span[@aria-owns='visaTo2_listbox']//span[@class='k-icon k-i-arrow-s']"));
		sleep(1);
		clickByJavaScript(By.xpath("//*[@id='visaTo2_listbox']//*[normalize-space(text()) and normalize-space(.)='"+_visaTo+"']"));
		sleep(2);
	}
	
	//Select Visa Start Date in pop-up Add Passport & Visa 
	public void inputVisaStartDate(String _startDate, int _indexVisaStartDate) {
		startDate = _startDate;
		driver.findElement(By.xpath(".//input[@id='startDate"+_indexVisaStartDate+"']")).clear();
		driver.findElement(By.xpath(".//input[@id='startDate"+_indexVisaStartDate+"']")).sendKeys(_startDate);
		sleep(1);
	}
	
	//Select Visa Expired Date in pop-up Add Passport & Visa
	public void inputVisaExpiredDate(String _expiredDate,int _indexVisaExpiredDate) {
		expiredDate = _expiredDate;
		driver.findElement(By.xpath(".//input[@id='expiredDate"+_indexVisaExpiredDate+"']")).clear();
		driver.findElement(By.xpath(".//input[@id='expiredDate"+_indexVisaExpiredDate+"']")).sendKeys(_expiredDate);
		sleep(1);
	}
	
	// Select Visa Entries
	public void selectVisaEntries(String _entries) {
		entries = _entries;
		clickByJavaScript(By.xpath("(.//td[@class='air-name']//span[@aria-owns='entries1_listbox']//span[@class='k-icon k-i-arrow-s'])[1]"));
		sleep(1);
		clickByJavaScript(By.xpath("//*[@id='entries1_listbox']//*[normalize-space(text()) and normalize-space(.)='"+_entries+"']"));
		sleep(2);
	}
	
	//Add more Visa Entries
	public void addMoretVisaEntries(String _entries) {
		entries = _entries;
		clickByJavaScript(By.xpath("(.//td[@class='air-name']//span[@aria-owns='entries2_listbox']//span[@class='k-icon k-i-arrow-s'])[1]"));
		sleep(1);
		clickByJavaScript(By.xpath("//*[@id='entries2_listbox']//*[normalize-space(text()) and normalize-space(.)='"+_entries+"']"));
		sleep(2);
	}
	
	//Select Visa Type
	public void selectVisaType(String _type) {
		type = _type;
		clickByJavaScript(By.xpath("(.//td[@class='air-name']//span[@aria-owns='entries1_listbox']//span[@class='k-icon k-i-arrow-s'])[2]"));
		sleep(1);
		clickByJavaScript(By.xpath("//*[@id='entries1_listbox']//*[normalize-space(text()) and normalize-space(.)='"+_type+"']"));
		sleep(2);
	}
	
	//Add more  Visa Type
	public void addMoreVisaType(String _type) {
		type = _type;
		clickByJavaScript(By.xpath("(.//td[@class='air-name']//span[@aria-owns='entries2_listbox']//span[@class='k-icon k-i-arrow-s'])[2]"));
		sleep(1);
		clickByJavaScript(By.xpath("//*[@id='entries2_listbox']//*[normalize-space(text()) and normalize-space(.)='"+_type+"']"));
		sleep(2);
	}
	
	//Click on button Submit in Travel Request Screen
	public void clickbtnSubmit() {
		waitForElementClickable(5,btnsubmit);
		clickByJavaScript(btnsubmit);
		sleep(3);
	}
	
	//Click on button Add Additional Route(s) in Travel Request Screen
	public void clickbtnAddRoutes() {
		waitForElementClickable(5,btnaddtrip);
		clickByJavaScript(btnaddtrip);
	}
	
	//Delete Additional Route(s)
	public void deleteRoutes() {
		waitForElementClickable(5,iconDeleteRoutes);
		driver.findElement(iconDeleteRoutes).click();
	}

	public boolean buttonFilterDisplayed()
	{
		try {
			waitForElementVisible(20, btnFilter);
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
	
	public void countIndexAddMoreRoutes(int countIndex) {
		
	}
	
}
