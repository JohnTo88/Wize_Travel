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
import Utils.TienIch;
import tektravel.webpages.FeatureTour;
import tektravel.webpages.HomePage;
import tektravel.webpages.Login;
import tektravel.webpages.Management_CostProjection;
import tektravel.webpages.Management_MyRequests;
import tektravel.webpages.Management_MyTrips;
import tektravel.webpages.Management_TravelBudget;
import tektravel.webpages.Management_TravelData;
import tektravel.webpages.Management_Trips;
import tektravel.webpages.PopUp;
import web.controller.WebDriverController;

public class Test_Login extends TestManager{
	WebDriver driver;
	Login login;
	HomePage homepage;
	Connection con;
	FeatureTour featureTour;
	PopUp popUp;
	Management_MyRequests myRequests;
	Management_MyTrips myTrips;
	Management_Trips trips;
	Management_TravelData travelData;
	Management_CostProjection costProjection;
	Management_TravelBudget travelBudget;
	
	@BeforeTest
	public void khoiTaoCacTrang(){
		driver = WebDriverController.khoiTaoTrinhDuyetFireFox();
		homepage = new HomePage(driver);
		login = new Login(driver);
		myRequests = new Management_MyRequests(driver);
		myTrips = new Management_MyTrips(driver);
		trips = new Management_Trips(driver);
		travelData = new Management_TravelData(driver);
		travelBudget = new Management_TravelBudget(driver);
		costProjection = new Management_CostProjection(driver);
		featureTour = new FeatureTour(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
		popUp = new PopUp(driver);
	}
	
	@Test (priority = 1)
	public void TT_2236() throws InterruptedException{
		TestLogger.info("**********TT_2236**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Verify Modules available");
		
		if (myRequests.tabMyRequestsDisplayed() == true && myTrips.tabMyTripsDisplayed() == true)
		{
			setStatusTest("PASS", "- My Request available\r\n" + 
					"- My Trips available");
		}
		else
		{
			setStatusTest("FAIL", "- My Request is not available\r\n" + 
					"- My Trips is not available");
		}
		
		login.clickbtnSignout();
		popUp.signOutConfirmationYes();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);

		TestLogger.info("*************************");
		TestLogger.info("4. Verify Modules available");
		
		if (trips.tabTripsDisplayed() == true && travelData.tabTravelDataDisplayed() == true)
		{
			setStatusTest("PASS", "- Trips available\r\n" + 
					"- Travel Data available");
		}
		else
		{
			setStatusTest("FAIL", "- Trips is not available\r\n" + 
					"- Travel Data is not available");
		}
		
		login.clickbtnSignout();
		popUp.signOutConfirmationYes();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Login with account Admin");
		login.signIn(Login.Email_Admin, Login.Password);

		TestLogger.info("*************************");
		TestLogger.info("6. Verify Modules available");
		
		if (trips.tabTripsDisplayed() == true && travelData.tabTravelDataDisplayed() == true && travelBudget.tabTravelBudgetDisplayed() == true)
		{
			setStatusTest("PASS", "- Trips available\r\n" + 
					"- Travel Data available\r\n" + 
					"- Travel Budget available");
		}
		else
		{
			setStatusTest("FAIL", "- Trips is not available\r\n" + 
					"- Travel Data is not available\r\n" + 
					"- Travel Budget is not available");
		}
		
		login.clickbtnSignout();
		popUp.signOutConfirmationYes();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Login with account Budget Owner");
		login.signIn(Login.Email_BudgetOwner, Login.Password);

		TestLogger.info("*************************");
		TestLogger.info("8. Verify Modules available");
		
		if (costProjection.tabCostProjectionDisplayed() == true && travelBudget.tabTravelBudgetDisplayed() == true)
		{
			setStatusTest("PASS", "- Cost Projection available\r\n" + 
					"- Travel Budget available");
		}
		else
		{
			setStatusTest("FAIL", "- Cost Projection is not available\r\n" + 
					"- Travel Budget is not available");
		}
		
		login.clickbtnSignout();
		popUp.signOutConfirmationYes();
		
		TestLogger.info("*************************");
		TestLogger.info("9. Login with account Finance");
		login.signIn(Login.Email_Finances, Login.Password);

		TestLogger.info("*************************");
		TestLogger.info("10. Verify Modules available");
		
		if (costProjection.tabCostProjectionDisplayed() == true && trips.tabTripsDisplayed() == true && travelData.tabTravelDataDisplayed() == true)
		{
			setStatusTest("PASS", "- Cost Projection available\r\n" + 
					"- Travel Data available\r\n" + 
					"- Trips available");
		}
		else
		{
			setStatusTest("FAIL", "- Cost Projection is not available\r\n" + 
					"- Travel Data is not available\r\n" + 
					"- Trips is not available");
		}
		
	}
	
	@Test (priority = 2)
	public void TT_2061() throws InterruptedException{
		TestLogger.info("**********TT_2061**********");
		TestLogger.info("1. Open HomePage");
		
		TestLogger.info("*************************");
		TestLogger.info("2. Click on button 'Sign In'");
		login.clickbtnSignIn();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Verify error messages");
		if (login.getInfoMesageEmail().equals("Enter your Talentwize email.") && login.getInfoMesagePass().equals("Enter your Talentwize password."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
	}
	
	@Test (priority = 3)
	public void TT_2060() throws InterruptedException{
		TestLogger.info("**********TT_2060**********");
		TestLogger.info("1. Open HomePage");
		
		TestLogger.info("*************************");
		TestLogger.info("2. Input invalid email");
		String invalidEmail = "ABCDEFGH";
		login.inputEmail(invalidEmail);
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on button 'Sign In'");
		login.clickbtnSignIn();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Verify Error Message");
		if (login.getInfoMesageEmail().equals("The Email field is not a valid e-mail address.")){
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("5. Input email not existed in system");
		String unexistedEmail = "BO10@tek-experts.com";
		login.inputEmail(unexistedEmail);
		login.inputPassword(TienIch.taoRandomChu(6));
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Sign In'");
		login.clickbtnSignIn();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Verify Error Message");
		if (login.getInfoOtherMesage().equals("The username or password is incorrect")){
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("8. Input valid email");
		String validEmail = "tc2@tek-experts.com";
		login.inputEmail(validEmail);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Input invalid password");
		login.inputPassword(TienIch.taoRandomChu(6));
		
		TestLogger.info("*************************");
		TestLogger.info("10. Click on button 'Sign In'");
		login.clickbtnSignIn();
		
		TestLogger.info("*************************");
		TestLogger.info("11. Verify Error Message");
		if (login.getInfoOtherMesage().equals("The username or password is incorrect")){
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
	}
	
	@Test (priority = 4)
	public void TT_2059() throws InterruptedException{
		TestLogger.info("**********TT_2059**********");
		TestLogger.info("1. Open HomePage");
		
		TestLogger.info("*************************");
		TestLogger.info("2. Input valid email/password ");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Verify Login successful");
		
		if (myRequests.tabMyRequestsDisplayed() == true && myTrips.tabMyTripsDisplayed() == true)
		{
			setStatusTest("PASS", "Login SUCCESSFUL");
		}
		else
		{
			setStatusTest("FAIL", "Login UNSUCCESSFUL");
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
