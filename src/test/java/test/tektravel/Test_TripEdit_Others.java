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

public class Test_TripEdit_Others extends TestManager{
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
	public void TT_2440() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2440**********");
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
		TestLogger.info("4. Select tab 'Others'");
		editTrip.selectTabOthers();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Select Trip in drop-down list");
		
		TestLogger.info("*************************");
		TestLogger.info("6. Select 'Type' in drop-down list");
		String _type = "Miscellaneous";
		editTrip.selectTypeOthers(_type, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Input Description");
		String _descriptionOthers = TienIch.taoRandomChu(50);
		editTrip.inputDescriptionOthers(_descriptionOthers, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Input Cost");
		String _costOthers = TienIch.taoRandomSo(4);
		editTrip.inputCostOthers(_costOthers, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Click on button 'Update'");	
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("10. Verify trip updated successful");
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
	public void TT_2434() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2434**********");
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
		TestLogger.info("4. Select tab 'Others'");
		editTrip.selectTabOthers();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on icon 'Plus'");
		editTrip.clickPlusOthers();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Add all fields required");
		String _type = "Miscellaneous";
		editTrip.selectTypeOthers(_type, 2);
		
		String _descriptionOthers = TienIch.taoRandomChu(50);
		editTrip.inputDescriptionOthers(_descriptionOthers, 2);
		
		String _costOthers = TienIch.taoRandomSo(4);
		editTrip.inputCostOthers(_costOthers, 2);
		
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
	public void TT_2433() throws InterruptedException{
		TestLogger.info("**********TT_2433**********");
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
		TestLogger.info("4. Select tab 'Others'");
		editTrip.selectTabOthers();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on icon 'Minus'");
		editTrip.clickMinusOthers();
		
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
	public void TT_2431() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2431**********");
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
		TestLogger.info("4. Select tab 'Others'");
		editTrip.selectTabOthers();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Choose file'");
		editTrip.selectFieldChooseFileOthers();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Select file want to upload (> 2MB)");
		String fileMax = "Building+Microservices+-+Sam+Newman.pdf";
		editTrip.selectFileUpload(fileMax);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Click on button 'Upload'");
		editTrip.clickbtnUploadOthers();
		
		if (editTrip.getInfoMessageUploadOthers().equals("File Maximum size is 2MB."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Choose file' and select file is not type of support");
		editTrip.selectFieldChooseFileOthers();
		String fileNotSupport = "TienIch.java";
		travelRequest.selectFileUpload(fileNotSupport);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Click on button 'Upload'");
		editTrip.clickbtnUploadOthers();
		if (editTrip.getInfoMessageUploadOthers().equals("File is not supported."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("10. Click to button 'Choose file' again");
		editTrip.selectFieldChooseFileOthers();
		
		TestLogger.info("*************************");
		TestLogger.info("11. Select file want to upload: (HTML < 2MB)");
		String fileValid = "BCP+Awareness.html";
		editTrip.selectFileUpload(fileValid);
		
		TestLogger.info("*************************");
		TestLogger.info("12. Click on button 'Upload'");
		editTrip.clickbtnUploadOthers();
		if (editTrip.selectedFileDisplayed_EditTrip("other-info") == true)
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
	public void TT_2432() throws InterruptedException{
		TestLogger.info("**********TT_2432**********");
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
		TestLogger.info("4. Select tab 'Others'");
		editTrip.selectTabOthers();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Update text into 'Note' field have length > 5000");
		String maxNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(5000);
		editTrip.inputNoteOthers(maxNote);
		
		String _descriptionOthers = TienIch.taoRandomChu(50);
		editTrip.inputDescriptionOthers(_descriptionOthers, 1);
		
		if (editTrip.getInfoMessageNoteOthers().equals("The max length is 5000 characters"))
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
		editTrip.inputNoteOthers(defaultNote);
		
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
