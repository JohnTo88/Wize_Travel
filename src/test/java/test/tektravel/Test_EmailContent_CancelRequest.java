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
import tektravel.webpages.Management_MyRequests;
import tektravel.webpages.PopUp;
import web.controller.WebDriverController;

public class Test_EmailContent_CancelRequest extends TestManager{
	WebDriver driver;
	Login login;
	Management_MyRequests myrequest;
	HomePage homepage;
	Connection con;
	FeatureTour tour;
	PopUp popUp;
		
	@BeforeTest
	public void khoiTaoCacTrang ()
	{
		driver = WebDriverController.khoiTaoTrinhDuyetFireFox();
		homepage = new HomePage(driver);
		login = new Login(driver);
		tour = new FeatureTour(driver);
		myrequest = new Management_MyRequests(driver);
		popUp = new PopUp(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
	}
	
	@Test
	public void cancelRequest() throws InterruptedException, SQLException{
		login.signIn(Login.Email_Traveler, Login.Password);
		
		tour.closePopUpFeaturesTourGetStarted();
		
		myrequest.selectTabMyRequests();
		
		//Select status: Pending Approve = 5 , Rejected = 6, Requested = 7
		myrequest.selectStatus("7");
		
		myrequest.clickButtonFilter();
		
		myrequest.sleep(2);
		
		myrequest.clickIconCancelRequest();
		
		popUp.confirmCancellationYes();
		
		System.out.println("CANCEL REQUEST SUCCESSFUL");
		System.out.println("========================================================" );
		System.out.println("Checking Database.Please Wait..." );
		myrequest.sleep(80);
		
		// Check data with DATABASE
		
		//Test Subject content from table Send Email
		String dataColumnSubject = DiscoverData.getColumnSubject(con,Login.Email_Traveler);
		
		System.out.println("Print Subject: " + dataColumnSubject);
		
		if (dataColumnSubject.contains("TRIP CANCELLED"))
		{
			setStatusTest("PASS", "Review Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Review Subject FAIL");
		}
		
		System.out.println("========================================================" );
		
		//Test send email success or fail
		String dataColumnStatus = DiscoverData.getStatus(con,Login.Email_Traveler);
		
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
		//String RoleId = DiscoverData.getColumnRoleId(con,Login.Email_Traveler);
		//String ContentEmail = DiscoverData.getColumnContentEmail(con,Login.Email_Traveler);
		String ContentEmail = DiscoverData.contentEmail(con,Login.Email_Traveler,"1");
		
/*		System.out.println("========================================================" );
		
		System.out.println("Print Content Email Traveler: " + ContentEmail);
		 
		if (ContentEmail.contains("Your trip has been cancelled.") && RoleId.equals("1"))
		{
			setStatusTest("PASS", "Review Content OK");
		
		}
		else
		{
			setStatusTest("FAIL", "Review Content FAIL");
		}*/
		
		System.out.println("========================================================" );
		
		System.out.println("Print Content Trip: " + ContentEmail);
		
		if (ContentEmail.contains("Your trip has been cancelled."))
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
