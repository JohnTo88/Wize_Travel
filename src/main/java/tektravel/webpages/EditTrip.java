package tektravel.webpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Utils.TestLogger;

public class EditTrip extends TravelRequest{
	WebDriver driver;
	
	private By tabTripInfo = By.xpath(".//a[@href='#trip-info']");
	private By tabFlight = By.xpath(".//li[@data-bind='click: loadFlight']//a");
	private By tabTransportation = By.xpath(".//li[@data-bind='click: loadTransportation']//a");
	private By tabHotel = By.xpath(".//li[@data-bind='click: loadHotel']//a");
	private By tabDiems = By.xpath(".//li[@data-bind='click: loadDiems']//a");
	private By tabOthers = By.xpath(".//li[@data-bind='click: loadOther']//a");
	private By tabSummary = By.xpath(".//li[@data-bind='click: loadSummary']//a");

	private By datePickerUpdateDateOnSite = By.xpath(".//span[@aria-controls='start-date-1_dateview']");
	private By datePickerUpdateEndDate = By.xpath(".//*[@id=\"trip-update-form\"]/div[1]/div[2]/div[1]/div[2]/div[4]/span[1]/span[1]/span[1]/span[1]/span[1]");
	private By datePickerUpdateEndDate2 = By.xpath("//*[@id=\"trip-update-form\"]/div[1]/div[2]/div[2]/div[2]/div[4]/span[1]/span[1]/span[1]/span[1]/span[1]");

	private By datePickerUpdateTripStartDate = By.xpath("//*[@id='trip-update-form']/div[1]/div[2]/div[1]/div[3]/div[3]/span[1]/span[1]/span[1]/span[1]");
	private By datePickerUpdateTripEndDate = By.xpath(".//*[@id=\"trip-update-form\"]/div[1]/div[2]/div[1]/div[3]/div[4]/span[1]/span[1]/span[1]/span[1]");
	private By datePickerUpdateDepartureFlight = By.xpath("//*[@id=\"flight-info\"]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[9]/span[1]/span[1]/span[1]/span[1]/span[1]");
	private By datePickerUpdateReturnFlight = By.xpath("//*[@id=\"flight-info\"]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[10]/span[1]/span[1]/span[1]/span[1]/span[1]");
	
	private By iconPlusFlight = By.xpath(".//div[@id='flight-info']//i[@class='icon icon-plus-circle']");
	private By iconMinusFlight = By.xpath("(.//div[@id='flight-info']//i[@class='icon icon-minus-circle'])[1]");
	private By txtItineraryFlight = By.xpath(".//div[@id='flight-info']//textarea[@name='itinerary']");
	
	private By iconPlusTransportation = By.xpath(".//div[@id='transportation']//i[@class='icon icon-plus-circle']");
	private By iconMinusTransportation = By.xpath("(.//div[@id='transportation']//i[@class='icon icon-minus-circle'])[1]");
	
	private By iconPlusHotel = By.xpath(".//div[@id='hotel-info']//i[@class='icon icon-plus-circle']");
	private By iconMinusHotel = By.xpath("(.//div[@id='hotel-info']//i[@class='icon icon-minus-circle'])[1]");
	
	private By iconPlusDiems = By.xpath(".//div[@id='diems']//i[@class='icon icon-plus-circle']");
	private By iconMinusDiems = By.xpath("(.//div[@id='diems']//i[@class='icon icon-minus-circle'])[1]");
	
	private By iconPlusOthers = By.xpath(".//div[@id='other-info']//i[@class='icon icon-plus-circle']");
	private By iconMinusOthers = By.xpath("(.//div[@id='other-info']//i[@class='icon icon-minus-circle'])[1]");
	
	private By fieldChooseFile = By.xpath(".//*[@id=\"trip-update-form\"]/div[2]/div[2]/div[1]/div[1]/div[1]/input[1]");
	private By btnSendApprove = By.xpath(".//button[@id='send-approval-submit']");
	private By btnUpdate = By.xpath(".//button[@id='trip-update-submit']");
	private By btnCancel = By.xpath(".//a[@class='btn btn-default']");
	
	public EditTrip(WebDriver driver){
		super(driver);
		this.driver = driver;			
	}
	
	//Select tab Trip Info
	public void selectTabTripInfo() {
		driver.findElement(tabTripInfo).click();
	}
	
	//Select tab Flight
	public void selectTabFlight() {
		driver.findElement(tabFlight).click();
		sleep(1);
	}
	
	//Select tab Transportation
	public void selectTabTransportation() {
		driver.findElement(tabTransportation).click();
		sleep(1);
	}
	
	//Select tab Hotel
	public void selectTabHotel() {
		driver.findElement(tabHotel).click();
		sleep(1);
	}
	
	//Select tab Diems
	public void selectTabDiems() {
		driver.findElement(tabDiems).click();
		sleep(1);
	}
	
	//Select tab Others
	public void selectTabOthers() {
		driver.findElement(tabOthers).click();
		sleep(1);
	}
	
	//Select tab Summary
	public void selectTabSummary() {
		driver.findElement(tabSummary).click();
		sleep(1);
	}
	
	public boolean selectedFileDisplayed_EditTrip(String _tab)
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(By.xpath(".//div[@id='"+_tab+"']//li[@class='selected-file']//a")).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean btnCancelDisplayed(String _tab)
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(By.xpath(".//div[@id='"+_tab+"']//li[@class='selected-file']//a")).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	//Update Date on Site - End Date
	public void updateDateOnSite(int lengthofday1) {
		clickByJavaScript(datePickerUpdateDateOnSite);
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
		//String valueDateOnSite = driver.findElement(fieldDateOnSite).getAttribute("value");
		//return valueDateOnSite;
			
	}
	
	public void updateEndDate(int lengthofday2) {
		clickByJavaScript(datePickerUpdateEndDate);
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
		//String valueEndDate = driver.findElement(fieldEndDate).getAttribute("value");
		//return valueEndDate;
	}
	
	public void updateAddMoreEndDate(int lengthofday5) {
		clickByJavaScript(datePickerUpdateEndDate2);
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));
		
		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday5).getText().length()>0 && (i+lengthofday5)<list.size())
			{
				list.get(i+lengthofday5).click();
				break;
			}
		}
		//String valueEndDate = driver.findElement(fieldEndDate).getAttribute("value");
		//return valueEndDate;
	}
	
	//Update Trip Start Date - Trip End Date
	public void updateTripStartDate(int lengthofday3) {
		clickByJavaScript(datePickerUpdateTripStartDate);
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday3).getText().length()>0 && (i+lengthofday3)<list.size())
			{
				list.get(i+lengthofday3).click();
				break;
			}
		}
		//String valueTripStartDate = driver.findElement(fieldTripStartDate).getAttribute("value");
		//return valueTripStartDate;
			
	}
	
	public void updateAddMoreTripStartDate(int lengthofday6) {
		clickByJavaScript(By.xpath(".//*[@id=\"trip-update-form\"]/div[1]/div[2]/div[2]/div[3]/div[3]/span[1]/span[1]/span[1]/span[1]"));
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday6).getText().length()>0 && (i+lengthofday6)<list.size())
			{
				list.get(i+lengthofday6).click();
				break;
			}
		}
		//String valueTripStartDate = driver.findElement(fieldTripStartDate).getAttribute("value");
		//return valueTripStartDate;
			
	}
	
	public void updateTripEndDate(int lengthofday4) {
		clickByJavaScript(datePickerUpdateTripEndDate);
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
		//String valueTripEndDate = driver.findElement(fieldTripEndDate).getAttribute("value");
		//return valueTripEndDate;
			
	}
	
	public void updateAddMoreTripEndDate(int lengthofday7) {
		clickByJavaScript(By.xpath(".//*[@id=\"trip-update-form\"]/div[1]/div[2]/div[2]/div[3]/div[4]/span[1]/span[1]/span[1]/span[1]"));
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday7).getText().length()>0 && (i+lengthofday7)<list.size())
			{
				list.get(i+lengthofday7).click();
				break;
			}
		}
		//String valueTripEndDate = driver.findElement(fieldTripEndDate).getAttribute("value");
		//return valueTripEndDate;
			
	}
	
	//Select field Choose File in tab Trip Info
	public void selectFieldChooseFileTripinfo() {
		waitForElementClickable(10,fieldChooseFile);
		driver.findElement(fieldChooseFile).click();
		sleep(2);
	}
	
	//Select Trip in tab Flight
	public void selectTripFlight(String _tripFlight) {
		clickOnElement(driver.findElement(By.xpath(".//*[@id=\"flight-info\"]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[2]/span[1]/span[1]/span[2]/span[1]")));
		sleep(1);
		clickOnElement(driver.findElement(By.xpath("")));
		sleep(2);
	}
	
	//Select Airline in tab Flight
	public void selectAirlineFlight(String _airlineFlight) {
		clickByJavaScript((By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select Airline'])[1]")));
		sleep(1);
		clickByJavaScript((By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_airlineFlight+"'])[2]")));
		sleep(2);
	}
	
	//Add more Airline in tab Flight
	public void addMoreAirlineFlight(String _airlineFlight) {
		clickByJavaScript((By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select Airline'])[2]")));
		sleep(1);
		clickByJavaScript((By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_airlineFlight+"'])[4]")));
		sleep(2);
	}
	
	
	//Input Agent in tab Flight
	public void inputAgentFlight(String _agentFlight, int _index) {
		driver.findElement(By.xpath("(.//input[@data-bind='value: Agent'])["+_index+"]")).clear();
		driver.findElement(By.xpath("(.//input[@data-bind='value: Agent'])["+_index+"]")).sendKeys(_agentFlight);
		sleep(1);	
	}
	
	//Input Flight Code in tab Flight
	public void inputCodeFlight(String _codeFlight, int _index) {
		driver.findElement(By.xpath("(.//input[@data-bind='value: FlightCode'])["+_index+"]")).clear();
		driver.findElement(By.xpath("(.//input[@data-bind='value: FlightCode'])["+_index+"]")).sendKeys(_codeFlight);
		sleep(1);	
	}
	
	//Input Reservation Code in tab Flight
	public void  inputReservationCodeFlight(String _noteFlight, int _index) {
		driver.findElement(By.xpath("(.//input[@data-bind='value: ReservationCode'])["+_index+"]")).clear();
		driver.findElement(By.xpath("(.//input[@data-bind='value: ReservationCode'])["+_index+"]")).sendKeys(_noteFlight);
		sleep(1);	
	}
	
	//Select Departure - Return in tab Flight
	public void updateDepartureFlight(int lengthofday5) {
		clickByJavaScript(datePickerUpdateDepartureFlight);
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday5).getText().length()>0 && (i+lengthofday5)<list.size())
			{
				list.get(i+lengthofday5).click();
				break;
			}
		}
		//String valueTripStartDate = driver.findElement(fieldTripStartDate).getAttribute("value");
		//return valueTripStartDate;
		
	}
	
	public void updateReturnFlight(int lengthofday6) {
		clickByJavaScript(datePickerUpdateReturnFlight);
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday6).getText().length()>0 && (i+lengthofday6)<list.size())
			{
				list.get(i+lengthofday6).click();
				break;
			}
		}
		//String valueTripStartDate = driver.findElement(fieldTripStartDate).getAttribute("value");
		//return valueTripStartDate;
		
	}
	
	//Input Cost in tab Flight
	public void inputCostFlight(String _costFlight, int _index) {
		driver.findElement(By.xpath(".//input[@id='flight-cost-"+_index+"']")).clear();
		driver.findElement(By.xpath(".//input[@id='flight-cost-"+_index+"']")).sendKeys(_costFlight);
		sleep(1);	
	}
	
	//Click on icon Plus in tab Flight
	public void clickPlusFlight() throws InterruptedException {
		waitForElementPresent(10, iconPlusFlight);
		driver.findElement(iconPlusFlight).click();
		sleep(1);
	}
	
	//Click on icon Minus in tab Flight
	public void clickMinusFlight() throws InterruptedException {
		waitForElementPresent(10, iconMinusFlight);
		driver.findElement(iconMinusFlight).click();
		sleep(1);
	}
	
	//Click on icon Plus in tab Transportation
	public void clickPlusTransportation() throws InterruptedException {
		waitForElementPresent(10, iconPlusTransportation);
		driver.findElement(iconPlusTransportation).click();
		sleep(1);
	}
	
	//Click on icon Minus in tab Transportation
	public void clickMinusTransportation() throws InterruptedException {
		waitForElementPresent(10, iconMinusTransportation);
		driver.findElement(iconMinusTransportation).click();
		sleep(1);
	}
	
	//Select field Choose File in tab Flight
	public void selectFieldChooseFileFlight() {
		clickByJavaScript(By.xpath(".//div[@id='flight-info']//div[@class='upload-box']//input"));
		sleep(2);
	}
	
	//Select button Upload Files in tab Flight
	public void clickbtnUploadFlight() {
		sleep(2);
		driver.findElement(By.xpath(".//div[@id='flight-info']//button")).click();
	}
	
	//Get Info Message Upload in tab Flight
	public String getInfoMessageUploadFlight() throws InterruptedException {
		String infoMessageUploadFlight = driver.findElement(By.xpath(".//div[@id='flight-info']//div[@class='upload-box']//span")).getText();
		return infoMessageUploadFlight;
	}
	
	//Input Itinerary in tab Flight
	public void inputItineraryFlight(String _itineraryFlight) {
		driver.findElement(txtItineraryFlight).clear();
		driver.findElement(txtItineraryFlight).sendKeys(_itineraryFlight);
		sleep(2);	
	}
	
	//Input Note in tab Flight
	public void inputNoteFlight(String _noteFlight) {	
		WebElement iFrame = driver.findElement(By.xpath(".//div[@id='flight-info']//iframe"));
		sleep(2);
		driver.switchTo().frame(iFrame);
		driver.findElement(By.tagName("body")).clear();
		driver.findElement(By.tagName("body")).sendKeys(_noteFlight);
		driver.switchTo().defaultContent();
		sleep(2);
	}
	
	//Get Info Message Note in tab Flight
	public String getInfoMessageNoteFlight() throws InterruptedException {
		String infoMessageNoteFlight = driver.findElement(By.xpath(".//div[@id='flight-info']//span[@id='flight-info-note_validationMessage']")).getText();
		return infoMessageNoteFlight;
	}
	
	//Input Carrier in tab Transportation
	public void inputCarrierTransportation(String _carrierTransportation, int _index) {
		driver.findElement(By.xpath("(.//div[@id='transportation']//input[@data-bind='value: Carrier'])["+_index+"]")).clear();
		driver.findElement(By.xpath("(.//div[@id='transportation']//input[@data-bind='value: Carrier'])["+_index+"]")).sendKeys(_carrierTransportation);
		sleep(2);	
	}
	
	//Select Transportation Type in tab Transportation
	public void selectTransportationType(String _typeTransportation, int _index) {
		clickByJavaScript((By.xpath("//*[@id=\"transportation\"]/form[1]/div[1]/table[1]/tbody[1]/tr["+_index+"]/td[4]/span[1]/span[1]/span[2]/span[1]")));
		sleep(1);
		clickByJavaScript((By.xpath("//div[@id='entries"+_index+"-list']//*[normalize-space(text()) and normalize-space(.)='"+_typeTransportation+"']")));
		sleep(2);
	}
	
	//Add More Transportation Type in tab Transportation
	public void addMoreTransportationType(String _typeTransportation) {
		clickByJavaScript((By.xpath("//*[@id=\"transportation\"]/form[1]/div[1]/table[1]/tbody[1]/tr[2]/td[4]/span[1]/span[1]/span[2]/span[1]")));
		sleep(1);
		clickByJavaScript((By.xpath("//div[@id='entries2-list']//*[normalize-space(text()) and normalize-space(.)='"+_typeTransportation+"']")));
		sleep(2);
	}
	
	//Select Date in tab Transportation
	public void updateDateTransportation(int lengthofday) {
		clickByJavaScript(By.xpath(".//*[@id=\"transportation\"]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[5]/span[1]/span[1]/span[1]/span[1]"));
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday).getText().length()>0 && (i+lengthofday)<list.size())
			{
				list.get(i+lengthofday).click();
				break;
			}
		}	
	}
	
	//Add more Date in tab Transportation
	public void addMoreDateTransportation(int lengthofday) {
		clickByJavaScript(By.xpath(".//*[@id=\"transportation\"]/form[1]/div[1]/table[1]/tbody[1]/tr[2]/td[5]/span[1]/span[1]/span[1]/span[1]"));
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday).getText().length()>0 && (i+lengthofday)<list.size())
			{
				list.get(i+lengthofday).click();
				break;
			}
		}	
	}
	
	//Select Pick Up time in tab Transportation
	public void selectPickUpTimeTransportation(String _pickUpTimeTransportation, int _index) {
		driver.findElement(By.xpath("//input[@id='PickUpTime"+_index+"']")).clear();
		driver.findElement(By.xpath("//input[@id='PickUpTime"+_index+"']")).sendKeys(_pickUpTimeTransportation);
		sleep(1);	
	}
	
	//Input From in tab Transportation
	public void inputFromTransportation(String _fromTransportation, int _index) {
		driver.findElement(By.xpath("(//input[@data-bind='value: From'])["+_index+"]")).clear();
		driver.findElement(By.xpath("(//input[@data-bind='value: From'])["+_index+"]")).sendKeys(_fromTransportation);
		sleep(1);	
	}
	
	//Input To in tab Transportation
	public void inputToTransportation(String _toTransportation, int _index) {
		driver.findElement(By.xpath("(//input[@data-bind='value: To'])["+_index+"]")).clear();
		driver.findElement(By.xpath("(//input[@data-bind='value: To'])["+_index+"]")).sendKeys(_toTransportation);
		sleep(1);	
	}
	
	//Input Contact Number in tab Transportation
	public void inputContactNumberTransportation(String _contactNumberTransportation, int _index) {
		driver.findElement(By.xpath("(//input[@data-bind='value: ContractNumber'])["+_index+"]")).clear();
		driver.findElement(By.xpath("(//input[@data-bind='value: ContractNumber'])["+_index+"]")).sendKeys(_contactNumberTransportation);
		sleep(1);	
	}
	
	//Input Cost in tab Transportation
	public void inputCostTransportation(String _costTransportation, int _index) {
		driver.findElement(By.xpath("//input[@id='tran-cost-"+_index+"']")).clear();
		driver.findElement(By.xpath("//input[@id='tran-cost-"+_index+"']")).sendKeys(_costTransportation);
		sleep(1);	
	}
	
	//Select field Choose File in tab Transportation
	public void selectFieldChooseFileTransportation() {
		clickByJavaScript(By.xpath(".//div[@id='transportation']//div[@class='upload-box']//input"));
		sleep(2);
	}
	
	//Select button Upload Files in tab Transportation
	public void clickbtnUploadTransportation() {
		sleep(2);
		driver.findElement(By.xpath(".//div[@id='transportation']//button")).click();
	}
	
	//Get Info Message Upload in tab Transportation
	public String getInfoMessageUploadTransportation() throws InterruptedException {
		String infoMessageUploadTransportation = driver.findElement(By.xpath(".//div[@id='transportation']//div[@class='upload-box']//span")).getText();
		return infoMessageUploadTransportation;
	}
	
	//Input Note in tab Transportation
	public void inputNoteTransportation(String _noteTransportation) {	
		WebElement iFrame = driver.findElement(By.xpath(".//div[@id='transportation']//iframe"));
		sleep(2);
		driver.switchTo().frame(iFrame);
		driver.findElement(By.tagName("body")).clear();
		driver.findElement(By.tagName("body")).sendKeys(_noteTransportation);
		driver.switchTo().defaultContent();
		sleep(2);
	}
	
	//Get Info Message Note in tab Transportation
	public String getInfoMessageNoteTransportation() throws InterruptedException {
		String infoMessageNoteTransportation = driver.findElement(By.xpath(".//div[@id='transportation']//span[@id='comment#: Order #_validationMessage']")).getText();
		return infoMessageNoteTransportation;
	}
	
	//Select Trip in tab Hotel
	public void selectTripHotel(int _index) {
		clickByJavaScript((By.xpath("//*[@id=\"hotel-info\"]/form[1]/div[1]/table[1]/tbody[1]/tr["+_index+"]/td[2]/span[1]/span[1]/span[2]/span[1]")));
		sleep(2);
		clickByJavaScript((By.xpath("(//div[@class='k-list-scroller']//ul[@class='k-list k-reset'])[40]//li[@data-offset-index=0]")));
		sleep(2);
	}
	
	//Input City/Location in tab Hotel
	public void inputCityLocationHotel(String _locationHotel, int _index) {
		driver.findElement(By.xpath("//div[@id='hotel-info']//input[@name='location-"+_index+"']")).clear();
		driver.findElement(By.xpath("//div[@id='hotel-info']//input[@name='location-"+_index+"']")).sendKeys(_locationHotel);
		sleep(1);	
	}
	
	//Select Hotel Name in tab Hotel
	public void selectHotelName(String _hotelName, int _index) {
		clickByJavaScript((By.xpath("//*[@id=\"hotel-info\"]/form[1]/div[1]/table[1]/tbody[1]/tr["+_index+"]/td[4]/span[1]/span[1]/span[2]/span[1]")));
		sleep(1);
		clickByJavaScript((By.xpath("//*[@id='hotel-name-id"+_index+"_listbox']//*[normalize-space(text()) and normalize-space(.)='"+_hotelName+"']")));
		sleep(2);
	}
	
	//Input Address in tab Hotel
	public void inputAddressHotel(String _addressHotel, int _index) {
		driver.findElement(By.xpath("//div[@id='hotel-info']//input[@name='adderss-"+_index+"']")).clear();
		driver.findElement(By.xpath("//div[@id='hotel-info']//input[@name='adderss-"+_index+"']")).sendKeys(_addressHotel);
		sleep(1);	
	}
	
	//Select Date Check-in in tab Hotel
	public void checkinHotel(int lengthofday) {
		clickByJavaScript(By.xpath(".//*[@id=\"hotel-info\"]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[6]/span[1]/span[1]/span[1]/span[1]"));
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday).getText().length()>0 && (i+lengthofday)<list.size())
			{
				list.get(i+lengthofday).click();
				break;
			}
		}	
	}
	
	//Add more Date Check-in in tab Hotel
	public void addMoreCheckInHotel(int lengthofday) {
		clickByJavaScript(By.xpath("//*[@id=\"hotel-info\"]/form[1]/div[1]/table[1]/tbody[1]/tr[2]/td[6]/span[1]/span[1]/span[1]/span[1]"));
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday).getText().length()>0 && (i+lengthofday)<list.size())
			{
				list.get(i+lengthofday).click();
				break;
			}
		}	
	}
	
	//Select Date Check-out in tab Hotel
	public void checkoutHotel(int lengthofday) {
		clickByJavaScript(By.xpath(".//*[@id=\"hotel-info\"]/form[1]/div[1]/table[1]/tbody[1]/tr[1]/td[7]/span[1]/span[1]/span[1]/span[1]"));
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday).getText().length()>0 && (i+lengthofday)<list.size())
			{
				list.get(i+lengthofday).click();
				break;
			}
		}	
	}
	
	//Add more Date Check-out in tab Hotel
	public void addMoreCheckOutHotel(int lengthofday) {
		clickByJavaScript(By.xpath(".//*[@id=\"hotel-info\"]/form[1]/div[1]/table[1]/tbody[1]/tr[2]/td[7]/span[1]/span[1]/span[1]/span[1]"));
		sleep(2);
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='k-content']/tbody/tr[@role='row']/td/a[@class='k-link']"));

		for (int i =0;i<list.size();i++)
		{
			
			if (list.get(i).getText().length()>0 && list.get(i+lengthofday).getText().length()>0 && (i+lengthofday)<list.size())
			{
				list.get(i+lengthofday).click();
				break;
			}
		}	
	}
	
	//Input Cost in tab Hotel
	public void inputCostHotel(String _costHotel, int _index) {
		driver.findElement(By.xpath("//div[@id='hotel-info']//input[@id='hotel-cost-"+_index+"']")).clear();
		driver.findElement(By.xpath("//div[@id='hotel-info']//input[@id='hotel-cost-"+_index+"']")).sendKeys(_costHotel);
		sleep(1);	
	}
	
	//Select field Choose File in tab Hotel
	public void selectFieldChooseFileHotel() {
		clickByJavaScript(By.xpath(".//div[@id='hotel-info']//div[@class='upload-box']//input"));
		sleep(2);
	}
	
	//Select button Upload Files in tab Hotel
	public void clickbtnUploadHotel() {
		sleep(2);
		driver.findElement(By.xpath(".//div[@id='hotel-info']//button")).click();
	}
	
	//Get Info Message Upload in tab Hotel
	public String getInfoMessageUploadHotel() throws InterruptedException {
		String infoMessageUploadHotel = driver.findElement(By.xpath(".//div[@id='hotel-info']//div[@class='upload-box']//span")).getText();
		return infoMessageUploadHotel;
	}
	
	//Input Note in tab Hotel
	public void inputNoteHotel(String _noteHotel) {	
		WebElement iFrame = driver.findElement(By.xpath(".//div[@id='hotel-info']//iframe"));
		sleep(2);
		driver.switchTo().frame(iFrame);
		driver.findElement(By.tagName("body")).clear();
		driver.findElement(By.tagName("body")).sendKeys(_noteHotel);
		driver.switchTo().defaultContent();
		sleep(2);
	}
	
	//Get Info Message Note in tab Hotel
	public String getInfoMessageNoteHotel() throws InterruptedException {
		String infoMessageNoteHotel = driver.findElement(By.xpath(".//div[@id='hotel-info']//span[@id='hotel-info-note_validationMessage']")).getText();
		return infoMessageNoteHotel;
	}
	
	//Click on icon Plus in tab Hotel
	public void clickPlusHotel() throws InterruptedException {
		waitForElementPresent(10, iconPlusHotel);
		driver.findElement(iconPlusHotel).click();
		sleep(1);
	}
	
	//Click on icon Minus in tab Hotel
	public void clickMinusHotel() throws InterruptedException {
		waitForElementPresent(10, iconMinusHotel);
		driver.findElement(iconMinusHotel).click();
		sleep(1);
	}
	
	//Tick check box 'Manual Overwrite' in tab Diems
	public void tickManualOverwrite(int _index) throws InterruptedException {
		WebElement checkbox = driver.findElement(By.xpath("(.//div[@id='diems']//label[@class='vp-checkbox']//input)["+_index+"]"));
		if(checkbox.isSelected() == true) {
			TestLogger.info("Checkbox is selected. No need action.");
		}
		else {
			driver.findElement(By.xpath("(.//div[@id='diems']//label[@class='vp-checkbox']//input)["+_index+"]")).click();
			sleep(1);
		}
	}
	
	//Input Number Of Days in tab Diems
	public void inputNumberOfDays(String _numberOfDays, int _index) throws InterruptedException {
		driver.findElement(By.xpath("//div[@id='diems']//input[@id='cost-diem-number-of-days-"+_index+"']")).clear();
		driver.findElement(By.xpath("//div[@id='diems']//input[@id='cost-diem-number-of-days-"+_index+"']")).sendKeys(_numberOfDays);
		sleep(1);
	}
	
	//Input Per Diem Rate in tab Diems
	public void inputPerDiemRate(String _perDiemRate, int _index) throws InterruptedException {
		driver.findElement(By.xpath("//div[@id='diems']//input[@id='cost-diem-cost-"+_index+"']")).clear();
		driver.findElement(By.xpath("//div[@id='diems']//input[@id='cost-diem-cost-"+_index+"']")).sendKeys(_perDiemRate);
		sleep(1);
	}
	
	//Click on icon Plus in tab Diems
	public void clickPlusDiems() throws InterruptedException {
		waitForElementPresent(10, iconPlusDiems);
		driver.findElement(iconPlusDiems).click();
		sleep(1);
	}
	
	//Click on icon Minus in tab Diems
	public void clickMinusDiems() throws InterruptedException {
		waitForElementPresent(10, iconMinusDiems);
		driver.findElement(iconMinusDiems).click();
		sleep(1);
	}
	
	//Select field Choose File in tab Diems
	public void selectFieldChooseFileDiems() {
		clickByJavaScript(By.xpath(".//div[@id='diems']//div[@class='upload-box']//input"));
		sleep(2);
	}
	
	//Select button Upload Files in tab Diems
	public void clickbtnUploadDiems() {
		sleep(2);
		driver.findElement(By.xpath(".//div[@id='diems']//button")).click();
	}
	
	//Get Info Message Upload in tab Diems
	public String getInfoMessageUploadDiems() throws InterruptedException {
		String infoMessageUploadDiems = driver.findElement(By.xpath(".//div[@id='diems']//div[@class='upload-box']//span")).getText();
		return infoMessageUploadDiems;
	}
	
	//Input Note in tab Diems
	public void inputNoteDiems(String _noteDiems) {	
		WebElement iFrame = driver.findElement(By.xpath(".//div[@id='diems']//iframe"));
		sleep(2);
		driver.switchTo().frame(iFrame);
		driver.findElement(By.tagName("body")).clear();
		driver.findElement(By.tagName("body")).sendKeys(_noteDiems);
		driver.switchTo().defaultContent();
		sleep(2);
	}
	
	//Get Info Message Note in tab Diems
	public String getInfoMessageNoteDiems() throws InterruptedException {
		String infoMessageNoteDiems = driver.findElement(By.xpath(".//div[@id='diems']//span[@id='diems-info-note_validationMessage']")).getText();
		return infoMessageNoteDiems;
	}
	
	//Select Type in tab Others
	public void selectTypeOthers(String _typeOthers, int _index){
		clickByJavaScript((By.xpath("//*[@id=\"other-info\"]/form[1]/div[1]/table[1]/tbody[1]/tr["+_index+"]/td[3]/span[1]/span[1]/span[2]/span[1]")));
		sleep(2);
		clickByJavaScript((By.xpath("(//div[@class='k-list-scroller']//ul[@class='k-list k-reset']//*[normalize-space(text()) and normalize-space(.)='"+_typeOthers+"'])["+_index+"]")));
		sleep(2);
	}
	
	//Input Description in tab Others
	public void inputDescriptionOthers(String _descriptionOthers, int _index) throws InterruptedException {
		driver.findElement(By.xpath("(.//div[@id='other-info']//input[@data-bind='value: Description'])["+_index+"]")).clear();
		driver.findElement(By.xpath("(.//div[@id='other-info']//input[@data-bind='value: Description'])["+_index+"]")).sendKeys(_descriptionOthers);
		sleep(1);
	}
	
	//Input Cost in tab Others
	public void inputCostOthers(String _costOthers, int _index) throws InterruptedException {
		driver.findElement(By.xpath(".//div[@id='other-info']//input[@id='other-cost-"+_index+"']")).clear();
		driver.findElement(By.xpath(".//div[@id='other-info']//input[@id='other-cost-"+_index+"']")).sendKeys(_costOthers);
		sleep(1);
	}
	
	//Click on icon Plus in tab Others
	public void clickPlusOthers() throws InterruptedException {
		waitForElementPresent(10, iconPlusOthers);
		driver.findElement(iconPlusOthers).click();
		sleep(1);
	}
	
	//Click on icon Minus in tab Others
	public void clickMinusOthers() throws InterruptedException {
		waitForElementPresent(10, iconMinusOthers);
		driver.findElement(iconMinusOthers).click();
		sleep(1);
	}
	
	//Select field Choose File in tab Others
	public void selectFieldChooseFileOthers() {
		clickByJavaScript(By.xpath(".//div[@id='other-info']//div[@class='upload-box']//input"));
		sleep(2);
	}
	
	//Select button Upload Files in tab Others
	public void clickbtnUploadOthers() {
		sleep(2);
		driver.findElement(By.xpath(".//div[@id='other-info']//button")).click();
	}
	
	//Get Info Message Upload in tab Others
	public String getInfoMessageUploadOthers() throws InterruptedException {
		String infoMessageUploadOthers = driver.findElement(By.xpath(".//div[@id='other-info']//div[@class='upload-box']//span")).getText();
		return infoMessageUploadOthers;
	}
	
	//Input Note in tab Others
	public void inputNoteOthers(String _noteOthers) {	
		WebElement iFrame = driver.findElement(By.xpath(".//div[@id='other-info']//iframe"));
		sleep(2);
		driver.switchTo().frame(iFrame);
		driver.findElement(By.tagName("body")).clear();
		driver.findElement(By.tagName("body")).sendKeys(_noteOthers);
		driver.switchTo().defaultContent();
		sleep(2);
	}
	
	//Get Info Message Note in tab Others
	public String getInfoMessageNoteOthers() throws InterruptedException {
		String infoMessageNoteOthers = driver.findElement(By.xpath(".//div[@id='other-info']//span[@id='other-info-note_validationMessage']")).getText();
		return infoMessageNoteOthers;
	}
	
	//Select field Choose File in tab Summary
	public void selectFieldChooseFileSummary() {
		clickByJavaScript(By.xpath(".//div[@id='trip-summary']//div[@class='upload-box']//input"));
		sleep(2);
	}
	
	//Select button Upload Files in tab Summary
	public void clickbtnUploadSummary() {
		sleep(2);
		driver.findElement(By.xpath(".//div[@id='trip-summary']//button")).click();
	}
	
	//Get Info Message Upload in tab Summary
	public String getInfoMessageUploadSummary() throws InterruptedException {
		String infoMessageUploadSummary = driver.findElement(By.xpath(".//div[@id='trip-summary']//div[@class='upload-box']//span")).getText();
		return infoMessageUploadSummary;
	}
	
	//Input Note in tab Summary
	public void inputNoteSummary(String _noteSummary) {	
		WebElement iFrame = driver.findElement(By.xpath(".//div[@id='trip-summary']//iframe"));
		sleep(2);
		driver.switchTo().frame(iFrame);
		driver.findElement(By.tagName("body")).clear();
		driver.findElement(By.tagName("body")).sendKeys(_noteSummary);
		driver.switchTo().defaultContent();
		sleep(2);
	}
	
/*	//Get Info Message Note in tab Summary
	public String getInfoMessageNoteSummary() throws InterruptedException {
		String infoMessageNoteSummary = driver.findElement(By.xpath(".//div[@id='other-info']//span[@id='other-info-note_validationMessage']")).getText();
		return infoMessageNoteSummary;
	}*/
	
	//Click on button Send Approval
	public void clickbtnSendApprove() {
		waitForElementClickable(10,btnSendApprove);
		clickByJavaScript(btnSendApprove);
		sleep(1);
	}
	
	
	//Click on button Update
	public void clickbtnUpdate() {
		waitForElementClickable(10,btnUpdate);
		driver.findElement(btnUpdate).click();
		sleep(1);
	}
	
	//Click on button Update
	public void clickbtnCancel() {
		waitForElementClickable(10,btnCancel);
		driver.findElement(btnCancel).click();
		sleep(1);
	}
	
	// Select Team Finances: Global Finance Team
	public void selectFinancesTeam(int _team) {
		clickOnElement(driver.findElement(By.xpath(".//*[@id=\"trip-update-form\"]/div[1]/div[1]/div[2]/span[1]/span[1]")));
		sleep(1);
		clickOnElement(driver.findElement(By.xpath("(.//li[@data-offset-index='"+_team+"'])[1]")));
		sleep(2);
	}
	
	//Select Budget Type in Edit Trip Screen
	public void selectBudgetType_EditTrip(String _budgetType, int _index){
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select Budget Type...'])["+_index+"]"));
		sleep(1);
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_budgetType+"'])[3]"));
		sleep(2);
	}
	
	//Select Budget Owner in Edit Trip Screen
	public void selectBudgetOwner_EditTrip(String _BO, int _index) {
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select Budget Owner...'])["+_index+"]"));
		sleep(1);
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_BO+"'])[2]"));
		sleep(2);
	}
	
	//Add More Budget Owner in Edit Trip Screen
	public void addMoreBudgetOwner_EditTrip(String _BO, int _index) {
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select Budget Owner...'])["+_index+"]"));
		sleep(1);
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_BO+"'])[4]"));
		sleep(2);
	}
	
	//Select Budget Category in Edit Trip Screen
	public void addMoreBudgetCategory_EditTrip(String _BudgetCategory, int _index) {
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Select Budget Category...'])["+_index+"]"));
		sleep(1);
		clickByJavaScript(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='"+_BudgetCategory+"'])[5]"));
		sleep(2);
	}

}
