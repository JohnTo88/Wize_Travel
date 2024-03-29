package test.tektravel;

import java.sql.Connection;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataBase.DiscoverData;
import Utils.TestManager;
import tektravel.webpages.FeatureTour;
import tektravel.webpages.HomePage;
import tektravel.webpages.Login;
import tektravel.webpages.Management_Trips;
import tektravel.webpages.PopUp;
import tektravel.webpages.TripPreview;
import web.controller.WebDriverController;

public class Test_EmailContent_TravelArrangements extends TestManager{
	WebDriver driver;
	Login login;
	HomePage homepage;
	Connection con;
	FeatureTour tour;
	Management_Trips managementTrip;
	PopUp popUp;
	TripPreview tripPreview;
	
	@BeforeTest
	public void khoiTaoCacTrang ()
	{
		driver = WebDriverController.khoiTaoTrinhDuyetFireFox();
		homepage = new HomePage(driver);
		login = new Login(driver);
		tour = new FeatureTour(driver);
		managementTrip = new Management_Trips(driver);
		tripPreview = new TripPreview(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
		popUp = new PopUp(driver);
	}
	
	@Test
	public void travelArrangements() throws InterruptedException, SQLException{
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		
		tour.closePopUpFeaturesTourGetStarted();
		
		managementTrip.selectTabNewTrip();
		
		managementTrip.selectRequestName_tabNewTrip();
		
		tripPreview.clickbtnSendArrangements();
		
		popUp.sendArrangements();
		
		System.out.println("SEND ARRANGEMENTS SUCCESSFUL");
		System.out.println("========================================================" );
		System.out.println("Checking Database.Please Wait..." );
		tripPreview.sleep(80);
		
		//Check data with DATABASE
		//Test Subject content from table Send Email
		String dataColumnSubject = DiscoverData.getColumnSubject(con,Login.Email_TravelCoordinator);
		
		System.out.println("Print Subject: " + dataColumnSubject);
		
		if (dataColumnSubject.contains("Travel arrangements"))
		{
			setStatusTest("PASS", "Review Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Review Subject FAIL");
		}
		
		System.out.println("========================================================" );
		
		//Test send email success or fail
		String dataColumnStatus = DiscoverData.getStatus(con,Login.Email_TravelCoordinator);
		
		System.out.println("Print Status: " + dataColumnStatus);
		
		if (dataColumnStatus.contains("1"))
		{
			setStatusTest("PASS", "Review Status OK");
		}
		else
		{
			setStatusTest("FAIL", "Review Status FAIL");
		}
		
		System.out.println("========================================================" );
		
		//Test content email from table Send Email
		String ContentEmail_Traveler = DiscoverData.travelArrangements(con, Login.Email_TravelCoordinator, "1");
		
		System.out.println("========================================================" );
		
		System.out.println("Print Content Trip: " + ContentEmail_Traveler);
		
		if (ContentEmail_Traveler.contains("Please see below travel arrangement:"))
		{
			setStatusTest("PASS", "Review Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Review Content FAIL");
		}
		
	}
	
	@AfterTest
	public void closeBrowser(){
		driver.close();
	}
	
}
