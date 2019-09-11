package test.tektravel;

import java.sql.Connection;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataBase.DiscoverData;
import Utils.TestLogger;
import Utils.TestManager;
import tektravel.webpages.FeatureTour;
import tektravel.webpages.HomePage;
import tektravel.webpages.Login;
import tektravel.webpages.Management_MyRequests;
import tektravel.webpages.PopUp;
import tektravel.webpages.TravelRequest;
import web.controller.WebDriverController;

public class Test_MyRequest extends TestManager{
	WebDriver driver;
	Login login;
	TravelRequest travelRequest;
	HomePage homepage;
	Connection con;
	FeatureTour featureTour;
	PopUp popUp;
	Management_MyRequests myRequest;
	
	@BeforeTest
	public void khoiTaoCacTrang(){
		driver = WebDriverController.openChrome();
		homepage = new HomePage(driver);
		login = new Login(driver);
		featureTour = new FeatureTour(driver);
		travelRequest = new TravelRequest(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
		popUp = new PopUp(driver);
		myRequest = new Management_MyRequests(driver);
	}
	
	@Test (priority = 1)
	public void TT_3329() throws InterruptedException{
		TestLogger.info("**********TT_3329**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'My Request'");
		myRequest.selectTabMyRequests();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Select Request to Edit");
		myRequest.clickIconEditRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Update Trip Start Date/ Trip End Date");
		travelRequest.tripStartDate(0);
		travelRequest.tripEndDate(2);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();

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
	public void TT_2289() throws InterruptedException{
		TestLogger.info("**********TT_2289**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'My Request'");
		myRequest.selectTabMyRequests();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Select Request which have more routes and click on icon 'Edit'");
		myRequest.clickIconEditRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Delete Additional Route(s)");
		travelRequest.deleteRoutes();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Verify updated request");
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
