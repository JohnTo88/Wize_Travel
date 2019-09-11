package test.tektravel;

import java.sql.Connection;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataBase.DiscoverData;
import Utils.TestManager;

public class Test_EmailContent_UrgentRequestAlert extends TestManager{
	WebDriver driver;
	Connection con;
	
	@BeforeTest
	public void khoiTaoCacTrang ()
	{
		//driver = WebDriverController.khoiTaoTrinhDuyetFireFox();
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
	}
	
	@Test
	public void urgentRequestAlert() throws InterruptedException, SQLException {
		String ContentEmailBO = DiscoverData.urgentRequestAlert(con, "System", "3");
		String ContentEmailTC = DiscoverData.urgentRequestAlert(con, "System", "4");
		
		System.out.println("========================================================" );
		System.out.println("Checking Database.Please Wait..." );
		System.out.println("========================================================" );
		
		System.out.println(("Print Content: " + ContentEmailBO));
		
		if (ContentEmailBO.contains("This message is sent as a reminder for you to approve or reject the cost projection"))
		{
			setStatusTest("PASS", "Review Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Review Subject FAIL");
		}
		
		System.out.println("========================================================" );
		System.out.println(("Print Content: " + ContentEmailTC));
		
		if (ContentEmailTC.contains("This message is sent as a reminder for you to create a Cost Projection"))
		{
			setStatusTest("PASS", "Review Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Review Subject FAIL");
		}
			
	}
	
}
