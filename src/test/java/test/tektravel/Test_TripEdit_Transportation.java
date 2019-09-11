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

public class Test_TripEdit_Transportation extends TestManager{
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
	public void TT_2480() throws InterruptedException{
		TestLogger.info("**********TT_2480**********");
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
		TestLogger.info("4. Select tab 'Transportation'");
		editTrip.selectTabTransportation();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Select Trip in drop-down list");
		
		TestLogger.info("*************************");
		TestLogger.info("6. Input Transportation Carrier");
		String _carrierTransportation =  TienIch.taoRandomChu(5);
		editTrip.inputCarrierTransportation(_carrierTransportation, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Select Transportation Type");
		String _typeTransportation = "Train";
		editTrip.selectTransportationType(_typeTransportation, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Select Date from Calendar");
		editTrip.updateDateTransportation(3);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Choose Pick Up Time");
		String _pickUpTimeTransportation = "1:00 AM";
		editTrip.selectPickUpTimeTransportation(_pickUpTimeTransportation, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("10. Input location From/To");
		String _from = "Tucson, AZ, USA";
		String _to = "Charleston, SC, USA";
		editTrip.inputFromTransportation(_from, 1);
		editTrip.inputToTransportation(_to, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("11. Input Contact Number");
		String _contactNumber = TienIch.taoRandomSo(10);
		editTrip.inputContactNumberTransportation(_contactNumber, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("12. Input Cost");
		String _cost = TienIch.taoRandomSo(4);
		editTrip.inputCostTransportation(_cost, 1);
		
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
	
	@Test (priority = 2)
	public void TT_2296() throws InterruptedException{
		TestLogger.info("**********TT_2296**********");
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
		TestLogger.info("4. Select tab 'Transportation'");
		editTrip.selectTabTransportation();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on icon 'Plus'");
		editTrip.clickPlusTransportation();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Add all fields required");
		String _carrierTransportation =  TienIch.taoRandomChu(5);
		editTrip.inputCarrierTransportation(_carrierTransportation, 2);
		
		String _typeTransportation = "Other";
		editTrip.selectTransportationType(_typeTransportation, 2);
		
		editTrip.addMoreDateTransportation(3);
		
		String _pickUpTimeTransportation = "1:00 AM";
		editTrip.selectPickUpTimeTransportation(_pickUpTimeTransportation, 2);
		
		String _from = "Tucson, AZ, USA";
		String _to = "Charleston, SC, USA";
		editTrip.inputFromTransportation(_from, 2);
		editTrip.inputToTransportation(_to, 2);
		
		String _contactNumber = TienIch.taoRandomSo(10);
		editTrip.inputContactNumberTransportation(_contactNumber, 2);
		
		String _cost = TienIch.taoRandomSo(4);
		editTrip.inputCostTransportation(_cost, 2);
		
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
	
	@Test (priority = 3)
	public void TT_2465() throws InterruptedException{
		TestLogger.info("**********TT_2465**********");
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
		TestLogger.info("4. Select tab 'Transportation'");
		editTrip.selectTabTransportation();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on icon 'Minus'");
		editTrip.clickMinusTransportation();
		
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
	
	@Test (priority = 4)
	public void TT_2463() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2463**********");
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
		TestLogger.info("4. Select tab 'Transportation'");
		editTrip.selectTabTransportation();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Choose file'");
		editTrip.selectFieldChooseFileTransportation();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Select file want to upload (> 2MB)");
		String fileMax = "Building+Microservices+-+Sam+Newman.pdf";
		editTrip.selectFileUpload(fileMax);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Click on button 'Upload'");
		editTrip.clickbtnUploadTransportation();
		
		if (editTrip.getInfoMessageUploadTransportation().equals("File Maximum size is 2MB."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Choose file' and select file is not type of support");
		editTrip.selectFieldChooseFileTransportation();
		String fileNotSupport = "TienIch.java";
		travelRequest.selectFileUpload(fileNotSupport);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Click on button 'Upload'");
		editTrip.clickbtnUploadTransportation();
		if (editTrip.getInfoMessageUploadTransportation().equals("File is not supported."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("10. Click to button 'Choose file' again");
		editTrip.selectFieldChooseFileTransportation();
		
		TestLogger.info("*************************");
		TestLogger.info("11. Select file want to upload: (HTML < 2MB)");
		String fileValid = "BCP+Awareness.html";
		editTrip.selectFileUpload(fileValid);
		
		TestLogger.info("*************************");
		TestLogger.info("12. Click on button 'Upload'");
		editTrip.clickbtnUploadTransportation();
		if (editTrip.selectedFileDisplayed_EditTrip("transportation")== true)
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
	public void TT_2464() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2464**********");
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
		TestLogger.info("4. Select tab 'Transportation'");
		editTrip.selectTabTransportation();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Update text into 'Note' field have length > 5000");
		String maxNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(5000);
		editTrip.inputNoteTransportation(maxNote);
		
		String _carrierTransportation =  TienIch.taoRandomChu(5);
		editTrip.inputCarrierTransportation(_carrierTransportation, 1);
		
		if (editTrip.getInfoMessageNoteTransportation().equals("The max length is 5000 characters"))
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
		editTrip.inputNoteTransportation(defaultNote);
		
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
