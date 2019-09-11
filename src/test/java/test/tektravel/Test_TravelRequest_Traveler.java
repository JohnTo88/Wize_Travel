package test.tektravel;

import java.sql.Connection;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataBase.DiscoverData;
import Utils.TestLogger;
import Utils.TestManager;
import Utils.TienIch;
import tektravel.webpages.FeatureTour;
import tektravel.webpages.HomePage;
import tektravel.webpages.Login;
import tektravel.webpages.PopUp;
import tektravel.webpages.TravelRequest;
import web.controller.WebDriverController;

public class Test_TravelRequest_Traveler extends TestManager{
	WebDriver driver;
	Login login;
	TravelRequest travelRequest;
	HomePage homepage;
	Connection con;
	FeatureTour featureTour;
	PopUp popUp;
	
	@BeforeTest
	public void khoiTaoCacTrang(){
		driver = WebDriverController.openChrome();
		homepage = new HomePage(driver);
		login = new Login(driver);
		featureTour = new FeatureTour(driver);
		travelRequest = new TravelRequest(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
		popUp = new PopUp(driver);
	}
	
	@Test (priority = 1)
	public void TT_2272() throws InterruptedException{
		TestLogger.info("**********TT_2272**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Input all fields required");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		String origin = "Chinese Garden, Aljunied Road, Singapore";
		String destination = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin,destination,"1");
		travelRequest.dateOnSite(2);
		travelRequest.endDate(3);
		
		TestLogger.info("*************************");
		TestLogger.info("4. Add info Passport");
		
		JavascriptExecutor scroll = (JavascriptExecutor)driver;
		scroll.executeScript("window.scrollBy(0,1000)", "");
		
		travelRequest.selectTitle("1");
		String firstName = Login.Email_Traveler;
		String middleName = "Testing";
		String lastName = "Fullname";
		travelRequest.inputNameUnderPassport(firstName, middleName, lastName);
		travelRequest.inputPassportNumber();
		
		String issuingCountry = "Angola";
		travelRequest.selectIssuingCountry(issuingCountry);
		
		String nationality = "American";
		travelRequest.selectNationality(nationality);
		
		String passportExpiredDate = "01 Feb 2025";
		travelRequest.inputPassportExpiredDate(passportExpiredDate);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Add more Passport");
		travelRequest.clickIconPlusPassport();
		travelRequest.selectTitle("1");
		String _firstName = Login.Email_Traveler;
		String _middleName = "Add more Testing";
		String _lastName = "Add more Fullname";
		travelRequest.addMoreNameUnderPassport(_firstName, _middleName, _lastName);
		travelRequest.addMorePassportNumber();
		String _nationality = "Afghan";
		travelRequest.addMoreNationality(_nationality);
		String _issuingCountry = "Albania";
		travelRequest.addMoreIssuingCountry(_issuingCountry);
		String _expiredDate = "01 Feb 2035";
		travelRequest.addMoreExpiredDate(_expiredDate);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Add info Visa");
		travelRequest.selectPassportNumber(1);
		travelRequest.inputVisaNumber(1);
		String visaTo = "Afghanistan";
		travelRequest.selectVisaTo(visaTo);
		
		String visaStartDate = "01 Mar 2018";
		String visaExpiredDate = "01 Mar 2030";
		travelRequest.inputVisaStartDate(visaStartDate,1);
		travelRequest.inputVisaExpiredDate(visaExpiredDate,1);
	
		String entries = "Single";
		travelRequest.selectVisaEntries(entries);
		
		String type = "Visitor";
		travelRequest.selectVisaType(type);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Add more Visa");
		travelRequest.clickIconPlusVisa();
		travelRequest.selectMorePassportNumber(0);
		travelRequest.inputVisaNumber(2);
		travelRequest.addMoretVisaTo(visaTo);
		travelRequest.inputVisaStartDate(visaStartDate,2);
		travelRequest.inputVisaExpiredDate(visaStartDate,2);
		travelRequest.addMoretVisaEntries(entries);
		travelRequest.addMoreVisaType(type);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("9. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}
		
	}
	
	@Test (priority = 2)
	public void TT_2035() throws InterruptedException{
		TestLogger.info("**********TT_2035**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on radio box 'Book for another'");
		JavascriptExecutor scroll = (JavascriptExecutor)driver;
		scroll.executeScript("window.scrollBy(0,1000)", "");
		travelRequest.bookOption(2);
		
		TestLogger.info("*************************");
		TestLogger.info("4. Search user name want to select");
		String userName1 = "huy.pham-quang";
		String userName2 = "tuan.do-anh";
		travelRequest.searchNameBookForOther(userName1);
		travelRequest.searchNameBookForOther(userName2);
		travelRequest.chooseNameBookForOther();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Input all fields required");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		String origin = "Chinese Garden, Aljunied Road, Singapore";
		String destination = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin,destination,"1");
		travelRequest.dateOnSite(2);
		travelRequest.endDate(3);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}
		
	}
	
	@Test (priority = 3)
	public void TT_2034() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2034**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on radio box 'Book for a guest'");
		JavascriptExecutor scroll = (JavascriptExecutor)driver;
		scroll.executeScript("window.scrollBy(0,1000)", "");
		travelRequest.bookOption(3);
		
		TestLogger.info("*************************");
		TestLogger.info("4. Input text into 'Note' field have length > 5000");
		String guestNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(500);
		travelRequest.inputGuestNote(guestNote);
				
		TestLogger.info("*************************");
		TestLogger.info("5. Select file want to upload: (HTML < 2MB)");
		travelRequest.selectFieldChooseFileGuestinfo();
		String fileValid = "BCP+Awareness.html";
		travelRequest.selectFileUpload(fileValid);
		
		if (travelRequest.getInfoMessageGuestNote().equals("The max length is 500 characters"))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Upload'");
		travelRequest.clickbtnUploadGuestInfo();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Input text into 'Note' field have length < 5000");
		String defaultNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(100);
		travelRequest.inputGuestNote(defaultNote);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Input all fields required");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		String origin = "Chinese Garden, Aljunied Road, Singapore";
		String destination = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin,destination,"1");
		travelRequest.dateOnSite(2);
		travelRequest.endDate(3);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("10. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}
		
	}
	
	@AfterMethod (alwaysRun = true)
	public void signOut() throws InterruptedException{
		if(login.btnSignoutDisplayed() == true) {
				login.clickbtnSignout();
				popUp.signOutConfirmationYes();
		}
		else {
			TestLogger.info("No need '@AfterMethod'");
		}
	}
		
	@AfterTest (alwaysRun = true)
	public void closeBrowser(){
		driver.close();
	}
	
}
