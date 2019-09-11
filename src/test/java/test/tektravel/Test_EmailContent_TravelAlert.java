package test.tektravel;

import java.sql.Connection;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataBase.DiscoverData;
import Utils.TestManager;
import web.controller.WebDriverController;

public class Test_EmailContent_TravelAlert extends TestManager{
	WebDriver driver;
	Connection con;
	
	@BeforeTest
	public void khoiTaoCacTrang ()
	{
		driver = WebDriverController.khoiTaoTrinhDuyetFireFox();
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
	}

	@Test
	public void travelAlert() throws SQLException {
		//Check DataBase
		String ContentEmailTraveler = DiscoverData.travelAlert(con, "System", "1");
		
		System.out.println("========================================================" );
		System.out.println("Checking Database.Please Wait..." );
		System.out.println("========================================================" );
		
		System.out.println("Print Content: " + ContentEmailTraveler);
		
		if (ContentEmailTraveler.contains("This is a reminder of your upcoming trip.") && ContentEmailTraveler.contains("Below is your final itinerary "))
		{
			setStatusTest("PASS", "Review Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Review Subject FAIL");
		}
	}
	
}
