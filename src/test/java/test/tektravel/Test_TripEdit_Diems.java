package test.tektravel;

import java.sql.Connection;

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
import tektravel.webpages.EditTrip;
import tektravel.webpages.FeatureTour;
import tektravel.webpages.HomePage;
import tektravel.webpages.Login;
import tektravel.webpages.Management_Trips;
import tektravel.webpages.PopUp;
import tektravel.webpages.TravelRequest;
import tektravel.webpages.TripPreview;
import web.controller.WebDriverController;

public class Test_TripEdit_Diems extends TestManager{
	WebDriver driver;
	Login login;
	TripPreview tripPreview;
	HomePage homePage;
	Connection con;
	FeatureTour featureTour;
	PopUp popUp;
	Management_Trips managementTrip;
	TravelRequest travelRequest;
	EditTrip editTrip;
	
	@BeforeTest
	public void khoiTaoCacTrang(){
		driver = WebDriverController.openChrome();
		homePage = new HomePage(driver);
		login = new Login(driver);
		featureTour = new FeatureTour(driver);
		tripPreview = new TripPreview(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
		popUp = new PopUp(driver);
		managementTrip = new Management_Trips(driver);
		travelRequest = new TravelRequest(driver);
		editTrip = new EditTrip(driver);
	}
	
	@Test (priority = 1)
	public void TT_2446() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2446**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select tab 'Processing'");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit' to update");
		managementTrip.clickIconEdit_Processing();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select tab 'Diems'");
		editTrip.selectTabDiems();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Tick on check box 'Manual Overwrite'");
		editTrip.tickManualOverwrite(1);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Input Number Of Days");
		String numberOfDay = TienIch.taoRandomSo(2);
		editTrip.inputNumberOfDays(numberOfDay, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Input Per Diem Rate");
		String _perDiemRate = TienIch.taoRandomSo(1);
		editTrip.inputPerDiemRate(_perDiemRate, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Update'");	
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("9. Verify trip updated successful");
		if (managementTrip.tabProcessingDisplayed() == true)
		{
			setStatusTest("PASS", "Update Trip Info SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Update Trip Info UNSUCCESSFUL!");
		}
		
	}
	
	@Test (priority = 2)
	public void TT_2444() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2444**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select tab 'Processing'");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit' to update");
		managementTrip.clickIconEdit_Processing();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select tab 'Diems'");
		editTrip.selectTabDiems();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on icon 'Plus'");
		editTrip.clickPlusDiems();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Add all fields required");
		editTrip.tickManualOverwrite(2);
		
		String numberOfDay = TienIch.taoRandomSo(2);
		editTrip.inputNumberOfDays(numberOfDay, 2);
		
		String _perDiemRate = TienIch.taoRandomSo(1);
		editTrip.inputPerDiemRate(_perDiemRate, 2);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Update'");	
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("9. Verify trip updated successful");
		if (managementTrip.tabProcessingDisplayed() == true)
		{
			setStatusTest("PASS", "Update Trip Info SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Update Trip Info UNSUCCESSFUL!");
		}
		
	}
	
	@Test (priority = 3)
	public void TT_2443() throws InterruptedException{
		TestLogger.info("**********TT_2443**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select tab 'Processing'");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit' to update");
		managementTrip.clickIconEdit_Processing();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select tab 'Diems'");
		editTrip.selectTabDiems();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on icon 'Minus'");
		editTrip.clickMinusDiems();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Update'");	
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Verify trip updated successful");
		if (managementTrip.tabProcessingDisplayed() == true)
		{
			setStatusTest("PASS", "Update Trip Info SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Update Trip Info UNSUCCESSFUL!");
		}
		
	}
	
	@Test (priority = 4)
	public void TT_2441() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2441**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select tab 'Processing'");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit' to update");
		managementTrip.clickIconEdit_Processing();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select tab 'Diems'");
		editTrip.selectTabDiems();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Choose file'");
		editTrip.selectFieldChooseFileDiems();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Select file want to upload (> 2MB)");
		String fileMax = "Building+Microservices+-+Sam+Newman.pdf";
		editTrip.selectFileUpload(fileMax);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Click on button 'Upload'");
		editTrip.clickbtnUploadDiems();
		
		if (editTrip.getInfoMessageUploadDiems().equals("File Maximum size is 2MB."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Choose file' and select file is not type of support");
		editTrip.selectFieldChooseFileDiems();
		String fileNotSupport = "TienIch.java";
		travelRequest.selectFileUpload(fileNotSupport);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Click on button 'Upload'");
		editTrip.clickbtnUploadDiems();
		if (editTrip.getInfoMessageUploadDiems().equals("File is not supported."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("10. Click to button 'Choose file' again");
		editTrip.selectFieldChooseFileDiems();
		
		TestLogger.info("*************************");
		TestLogger.info("11. Select file want to upload: (HTML < 2MB)");
		String fileValid = "BCP+Awareness.html";
		editTrip.selectFileUpload(fileValid);
		
		TestLogger.info("*************************");
		TestLogger.info("12. Click on button 'Upload'");
		editTrip.clickbtnUploadDiems();
		if (editTrip.selectedFileDisplayed_EditTrip("diems") == true)
		{
			setStatusTest("PASS", "Selected file is uploaded and listed below");
		}
		else
		{
			setStatusTest("FAIL", "Selected file is not uploaded and listed below");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("13. Click on button 'Update'");	
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("14. Verify trip updated successful");
		if (managementTrip.tabProcessingDisplayed() == true)
		{
			setStatusTest("PASS", "Update Trip Info SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Update Trip Info UNSUCCESSFUL!");
		}
		
	}
	
	@Test (priority = 5)
	public void TT_2442() throws InterruptedException{
		TestLogger.info("**********TT_2442**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select tab 'Processing'");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit' to update");
		managementTrip.clickIconEdit_Processing();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select tab 'Diems'");
		editTrip.selectTabDiems();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Update text into 'Note' field have length > 5000");
		String maxNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(5000);
		editTrip.inputNoteDiems(maxNote);
		
		String _perDiemRate = TienIch.taoRandomSo(2);
		editTrip.inputPerDiemRate(_perDiemRate, 1);
		
		if (editTrip.getInfoMessageNoteDiems().equals("The max length is 5000 characters"))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("6. Update text into 'Note' field have length <= 5000");
		
		String defaultNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(500);
		editTrip.inputNoteDiems(defaultNote);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Click on button 'Update'");	
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("8. Verify trip updated successful");
		if (managementTrip.tabProcessingDisplayed() == true)
		{
			setStatusTest("PASS", "Update Trip Info SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Update Trip Info UNSUCCESSFUL!");
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
