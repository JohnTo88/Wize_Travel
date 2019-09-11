package test.tektravel;

import java.sql.Connection;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataBase.DiscoverData;
import tektravel.webpages.FeatureTour;
import tektravel.webpages.HomePage;
import tektravel.webpages.Login;
import tektravel.webpages.Management_MyRequests;
import tektravel.webpages.PopUp;
import web.controller.WebDriverController;

public class Test_EmailContent_UpdatedRequest {
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
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
		popUp = new PopUp(driver);
	}
	
	@Test
	public void updatedRequest() {
		login.signIn(Login.Email_Traveler, Login.Password);
		
		tour.closePopUpFeaturesTourGetStarted();
		
		myrequest.selectStatus("7");
		
		myrequest.clickButtonFilter();
		
		myrequest.sleep(2);
		
		myrequest.clickIconEditRequest();
		
		 
		
		
	}
	
/*	@AfterTest
	public void closeBrowser() {
		driver.close();
	}*/
	
}
