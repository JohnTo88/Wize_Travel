package test.tektravel;

import java.sql.Connection;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataBase.DiscoverData;
import Utils.TestLogger;
import Utils.TestManager;
import tektravel.webpages.FeatureTour;
import tektravel.webpages.HomePage;
import tektravel.webpages.Login;
import tektravel.webpages.Management_MyTrips;
import tektravel.webpages.Management_Trips;
import tektravel.webpages.PopUp;
import tektravel.webpages.TripPreview;
import web.controller.WebDriverController;

public class Test_TripPreview_ConfidentialNote extends TestManager{
	WebDriver driver;
	Login login;
	TripPreview tripPreview;
	HomePage homePage;
	Connection con;
	FeatureTour featureTour;
	PopUp popUp;
	Management_Trips managementTrip;
	Management_MyTrips myTrips;
	
	@BeforeTest
	public void khoiTaoCacTrang(){
		driver = WebDriverController.khoiTaoTrinhDuyetFireFox();
		homePage = new HomePage(driver);
		login = new Login(driver);
		featureTour = new FeatureTour(driver);
		tripPreview = new TripPreview(driver);
		popUp = new PopUp(driver);
		managementTrip = new Management_Trips(driver);
		myTrips = new Management_MyTrips(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
	}
	
	@Test (priority = 1)
	public void TT_609() throws InterruptedException{
		TestLogger.info("1. Login with account Finances");
		login.signIn(Login.Email_Finances, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		managementTrip.selectRequestName_tabProcessing();
		TestLogger.info("*************************");
		
		//Check icon Edit Confidential Note available or not
		//TestLogger.info("Expected Result: User can view content Confidential fields without action edit");
		
		if (tripPreview.confidentialsNoteDisplayed() == true && tripPreview.iconEditConfidentialsNoteDisplayed() == false)
		{
			setStatusTest("PASS", "User can view content Confidential fields without action edit");
		}
		else
		{
			setStatusTest("FAIL", "User can NOT view content Confidential fields without action edit");
		}		
			
	}
	
	@Test (priority = 2)
	public void TT_610() throws InterruptedException{
		TestLogger.info("1. Login with account Admin");
		login.signIn(Login.Email_Admin, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		managementTrip.selectRequestName_tabProcessing();
		
		if (tripPreview.confidentialsNoteDisplayed() == false && tripPreview.iconEditConfidentialsNoteDisplayed() == false)
		{
			setStatusTest("PASS", "User can't view Confidential Note");
		}
		else
		{
			setStatusTest("FAIL", "User CAN view Confidential Note");
		}
		
		login.clickbtnSignout();
		popUp.signOutConfirmationYes();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Login with account Budget Owner");
		login.signIn(Login.Email_BudgetOwner, Login.Password);
		TestLogger.info("*************************");
		
		TestLogger.info("4. Go to Trip Preview of any request");
		myTrips.selectTabMyTrips();
		myTrips.selectRequestName_tabMyTrips();
		TestLogger.info("*************************");
		
		//Check icon Edit Confidential Note available or not
		//TestLogger.info("Expected Result: User can't view Confidential Note");
		
		if (tripPreview.confidentialsNoteDisplayed() == false && tripPreview.iconEditConfidentialsNoteDisplayed() == false)
		{
			setStatusTest("PASS", "User can't view Confidential Note");
		}
		else
		{
			setStatusTest("FAIL", "User CAN view Confidential Note");
		}
		
		login.clickbtnSignout();
		popUp.signOutConfirmationYes();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		TestLogger.info("*************************");
		
		TestLogger.info("6. Go to Trip Preview of any request");
		myTrips.selectTabMyTrips();
		myTrips.selectRequestName_tabMyTrips();
		TestLogger.info("*************************");
		
		//Check icon Edit Confidential Note available or not
		//TestLogger.info("Expected Result: User can't view Confidential Note");
		
		if (tripPreview.confidentialsNoteDisplayed() == false && tripPreview.iconEditConfidentialsNoteDisplayed() == false)
		{
			setStatusTest("PASS", "User can't view Confidential Note");
		}
		else
		{
			setStatusTest("FAIL", "User CAN view Confidential Note");
		}
	}
	
	@Test (priority = 3)
	public void TT_611() throws InterruptedException{
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		managementTrip.selectRequestName_tabProcessing();
		TestLogger.info("*************************");
		
		TestLogger.info("Expected Result: User can view Confidential Note with action Edit");
		
		if (tripPreview.confidentialsNoteDisplayed() == true && tripPreview.iconEditConfidentialsNoteDisplayed() == true)
		{
			setStatusTest("PASS", "User can view Confidential Note with action Edit");
		}
		else
		{
			setStatusTest("FAIL", "User can NOT view Confidential Note with action Edit");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit' Confidential fields");
		tripPreview.clickIconEditConfidentials();
		
		TestLogger.info("3. Expected Result: User can input text into field");
		String inputConfidentialsNote = "Automation Testing Confidentials Note write sucesssful";
		tripPreview.inputConfidentialsNote(inputConfidentialsNote);
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on button 'Save' confidential fields");
		tripPreview.clickbtnSaveConfidentials();
		
		if(tripPreview.getInfoConfidentialNote().equals(inputConfidentialsNote)) {
			setStatusTest("PASS", "Saved successful");
		}
		else
		{
			setStatusTest("FAIL", "Saved unsuccessful");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Edit' again");
		tripPreview.clickIconEditConfidentials();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Cancel'");
		tripPreview.clickbtnCancelConfidentials();
		
		if(tripPreview.getInfoConfidentialNote().equals(inputConfidentialsNote)) {
			setStatusTest("PASS", "No change value");
		}
		else
		{
			setStatusTest("FAIL", "Change value");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("7. Click on button 'Edit' again");
		tripPreview.clickIconEditConfidentials();
		tripPreview.inputConfidentialsNote("Test Change Value Confidential Note");
		
		TestLogger.info("*************************");
		TestLogger.info("8. Navigate other pages while typing note");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		managementTrip.selectRequestName_tabProcessing();
		
		if(tripPreview.getInfoConfidentialNote().equals(inputConfidentialsNote)) {
			setStatusTest("PASS", "No change value");
		}
		else
		{
			setStatusTest("FAIL", "Change value");
		}
		
	}
		
	@Test (priority = 4)
	public void TT_612() throws InterruptedException{
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of trip which have existed Confidential Note");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		managementTrip.selectRequestName_tabProcessing();
		TestLogger.info("*************************");
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit' Confidential fields");
		tripPreview.clickIconEditConfidentials();
		
		TestLogger.info("3. Expected Result: User can input text into field");
		String updateConfidentialsNote = "Update Automation Testing Confidentials Note write sucesssful";
		tripPreview.inputConfidentialsNote(updateConfidentialsNote);
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on button 'Save' confidential fields");
		tripPreview.clickbtnSaveConfidentials();
		
		if(tripPreview.getInfoConfidentialNote().equals(updateConfidentialsNote)) {
			setStatusTest("PASS", "Saved successful");
		}
		else
		{
			setStatusTest("FAIL", "Saved unsuccessful");
		}
		
	}
		
	@AfterTest
	public void closeBrowser(){
		driver.close();
	}
	
}
