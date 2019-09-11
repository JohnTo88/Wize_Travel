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
import tektravel.webpages.Management_Trips;
import tektravel.webpages.PopUp;
import tektravel.webpages.TripPreview;
import web.controller.WebDriverController;

public class Test_TripPreview_TravellerRibbon extends TestManager{
	WebDriver driver;
	Login login;
	TripPreview tripPreview;
	HomePage homePage;
	Connection con;
	FeatureTour featureTour;
	PopUp popUp;
	Management_Trips managementTrip;
	
	@BeforeTest
	public void khoiTaoCacTrang(){
		driver = WebDriverController.khoiTaoTrinhDuyetFireFox();
		homePage = new HomePage(driver);
		login = new Login(driver);
		featureTour = new FeatureTour(driver);
		tripPreview = new TripPreview(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
		popUp = new PopUp(driver);
		managementTrip = new Management_Trips(driver);
	}
	
	@Test (priority = 1)
	public void TT_938() throws InterruptedException{
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabOnGoing();
		managementTrip.selectRequestName_tabOnGoing();
		TestLogger.info("*************************");
		
		TestLogger.info("3. Click on Traveller's name");
		tripPreview.clickTravelerRibbonName();
		
		if(tripPreview.travellerRibbonDisplayed() == true) 
		{
			setStatusTest("PASS", "Displays the table of Traveler Info correct");
		}
		else
		{
			setStatusTest("FAIL", "DON'T displays the table of Traveler Info correct");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on the Traveler name again, or click anywhere outside the popover");
		tripPreview.clickTravelerRibbonName();
		
		if(tripPreview.travellerRibbonDisplayed() == false) 
		{
			setStatusTest("PASS", "Close the popover correct");
		}
		else
		{
			setStatusTest("FAIL", "DON'T close the popover");
		}
		
	}
	
	@Test (priority = 2)
	public void TT_1557() throws InterruptedException{
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabOnGoing();
		managementTrip.selectRequestName_tabOnGoing();
		TestLogger.info("*************************");
		
		TestLogger.info("3. Click on Traveller's name");
		tripPreview.clickTravelerRibbonName();
		
		if(tripPreview.travellerRibbonDisplayed() == true) 
		{
			setStatusTest("PASS", "Displays the table of Traveler Info correct");
		}
		else
		{
			setStatusTest("FAIL", "DON'T displays the table of Traveler Info correct");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on button 'Show more'");
		tripPreview.clickbtnShowMore();
		
		if(tripPreview.showLessDisplayed() == true && tripPreview.showMoreDisplayed() == false) 
		{
			setStatusTest("PASS", "- Display full Trip locations & dates correctly\r\n" + 
					"- Display button 'Show less' and vertical scroll.\r\n" + 
					"- The list then displays trip details latest");
		}
		else
		{
			setStatusTest("FAIL", "- DON'T display full Trip locations & dates correctly\r\n" + 
					"- Display button 'Show less' and vertical scroll.\r\n" + 
					"- The list then displays trip details latest");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Show less'");
		tripPreview.clickbtnShowLess();
		
		
		if(tripPreview.showLessDisplayed() == false && tripPreview.showMoreDisplayed() == true) 
		{
			setStatusTest("PASS", "- Hide info trip locations & dates\r\n" + 
					"- Display button 'Show more' and hide vertical scroll");
		}
		else
		{
			setStatusTest("FAIL", "- DON'T hide info trip locations & dates\r\n" + 
					"- Display button 'Show more' and hide vertical scroll");
		}
		
	}
	
	@AfterTest
	public void closeBrowser(){
		driver.close();
	}
	
}
