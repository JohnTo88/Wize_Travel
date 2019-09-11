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

public class Test_TripEdit_Hotel extends TestManager{
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
	public void TT_2462() throws InterruptedException{
		TestLogger.info("**********TT_2462**********");
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
		TestLogger.info("4. Select tab 'Hotel'");
		editTrip.selectTabHotel();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Select Trip in drop-down list");
		
		TestLogger.info("*************************");
		TestLogger.info("6. Input City/Location");
		String _locationHotel = "Awan Town, Lahore, Pakistan";
		editTrip.inputCityLocationHotel(_locationHotel, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Select Hotel Name in  drop-down list");
		String _hotelName = "BGH";
		editTrip.selectHotelName(_hotelName, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Input Address");
		String _addressHotel =  TienIch.taoRandomSovaChu(50);
		editTrip.inputAddressHotel(_addressHotel, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Select Check-in/Check-out from Calendar");
		editTrip.checkinHotel(2);
		editTrip.checkoutHotel(4);
		
		TestLogger.info("*************************");
		TestLogger.info("10. Input Cost");
		String _costHotel = TienIch.taoRandomSo(4);
		editTrip.inputCostHotel(_costHotel, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("11. Click on button 'Update'");	
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("12. Verify trip updated successful");
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
	public void TT_2452() throws InterruptedException{
		TestLogger.info("**********TT_2452**********");
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
		TestLogger.info("4. Select tab 'Hotel'");
		editTrip.selectTabHotel();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on icon 'Plus'");
		editTrip.clickPlusHotel();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Add all fields required");
		editTrip.selectTripHotel(2);
		
		String _locationHotel = "Iuri Gagarini Street, Tbilisi, Georgia";
		editTrip.inputCityLocationHotel(_locationHotel, 2);
		
		String _hotelName = "CRH";
		editTrip.selectHotelName(_hotelName, 2);
		
		String _addressHotel =  TienIch.taoRandomSovaChu(50);
		editTrip.inputAddressHotel(_addressHotel, 2);
		
		editTrip.addMoreCheckInHotel(2);
		editTrip.addMoreCheckOutHotel(4);
		
		String _costHotel = TienIch.taoRandomSo(4);
		editTrip.inputCostHotel(_costHotel, 2);
		
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
	public void TT_2451() throws InterruptedException{
		TestLogger.info("**********TT_2451**********");
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
		TestLogger.info("4. Select tab 'Hotel'");
		editTrip.selectTabHotel();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on icon 'Minus'");
		editTrip.clickMinusHotel();
		
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
	public void TT_2449() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2449**********");
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
		TestLogger.info("4. Select tab 'Hotel'");
		editTrip.selectTabHotel();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Choose file'");
		editTrip.selectFieldChooseFileHotel();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Select file want to upload (> 2MB)");
		String fileMax = "Building+Microservices+-+Sam+Newman.pdf";
		editTrip.selectFileUpload(fileMax);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Click on button 'Upload'");
		editTrip.clickbtnUploadHotel();
		
		if (editTrip.getInfoMessageUploadHotel().equals("File Maximum size is 2MB."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Choose file' and select file is not type of support");
		editTrip.selectFieldChooseFileHotel();
		String fileNotSupport = "TienIch.java";
		travelRequest.selectFileUpload(fileNotSupport);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Click on button 'Upload'");
		editTrip.clickbtnUploadHotel();
		if (editTrip.getInfoMessageUploadHotel().equals("File is not supported."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("10. Click to button 'Choose file' again");
		editTrip.selectFieldChooseFileHotel();
		
		TestLogger.info("*************************");
		TestLogger.info("11. Select file want to upload: (HTML < 2MB)");
		String fileValid = "BCP+Awareness.html";
		editTrip.selectFileUpload(fileValid);
		
		TestLogger.info("*************************");
		TestLogger.info("12. Click on button 'Upload'");
		editTrip.clickbtnUploadHotel();
		if (editTrip.selectedFileDisplayed_EditTrip("hotel-info") == true)
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
	public void TT_2450() throws InterruptedException{
		TestLogger.info("**********TT_2451**********");
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
		TestLogger.info("4. Select tab 'Hotel'");
		editTrip.selectTabHotel();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Update text into 'Note' field have length > 5000");
		String maxNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(5000);
		editTrip.inputNoteHotel(maxNote);
		
		String _locationHotel = "Iuri Gagarini Street, Tbilisi, Georgia";
		editTrip.inputCityLocationHotel(_locationHotel, 1);
		
		if (editTrip.getInfoMessageNoteHotel().equals("The max length is 5000 characters"))
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
		editTrip.inputNoteHotel(defaultNote);
		
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
