package test.tektravel;

import java.sql.Connection;

import org.openqa.selenium.JavascriptExecutor;
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
import tektravel.webpages.FeatureTour;
import tektravel.webpages.HomePage;
import tektravel.webpages.Login;
import tektravel.webpages.PopUp;
import tektravel.webpages.TravelRequest;
import web.controller.WebDriverController;

public class Test_TravelRequest_TravelRequest extends TestManager{
	WebDriver driver;
	Login login;
	TravelRequest travelRequest;
	HomePage homepage;
	Connection con;
	FeatureTour featureTour;
	PopUp popUp;
	
	@BeforeTest
	public void khoiTaoCacTrang(){
		driver = WebDriverController.openChrome();
		homepage = new HomePage(driver);
		login = new Login(driver);
		featureTour = new FeatureTour(driver);
		travelRequest = new TravelRequest(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
		popUp = new PopUp(driver);
	}
	
	@Test (priority = 1)
	public void TT_2291() throws InterruptedException{
		TestLogger.info("**********TT_2291**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		
		if(travelRequest.getInfoMessageRequestName().equals("* Required") 
				&& travelRequest.getInfoMessageOrigin().equals("* Required") 
				&& travelRequest.getInfoMessageDestination().equals("* Required") 
				&& travelRequest.getInfoMessageDateonSite().equals("* Required") 
				&& travelRequest.getInfoMessageEndDate().equals("* Required")) 
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("4. Input Request Name");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Input Origin/Destination");
		String origin = "Chinese Garden, Aljunied Road, Singapore";
		String destination = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin,destination,"1");
		
		TestLogger.info("*************************");
		TestLogger.info("6. Select Date on Site/End Date");
		travelRequest.dateOnSite(4);
		travelRequest.endDate(4);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("8. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}
		
	}
	
	@Test (priority = 2)
	public void TT_2278() throws InterruptedException, FindFailed {
		TestLogger.info("**********TT_2278**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on button 'Choose file'");
		travelRequest.selectFieldChooseFileTripinfo();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select file want to upload (> 2MB)");
		String fileMax = "Building+Microservices+-+Sam+Newman.pdf";
		travelRequest.selectFileUpload(fileMax);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Upload'");
		travelRequest.clickbtnUploadTripInfo();
		if (travelRequest.getInfoMessageUpload().equals("File Maximum size is 2MB."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("6. Hover mouse to the info icon besides the upload button");
		
		TestLogger.info("*************************");
		TestLogger.info("7. Click on button 'Choose file' and select file is not type of support");
		travelRequest.selectFieldChooseFileTripinfo();
		String fileNotSupport = "TienIch.java";
		travelRequest.selectFileUpload(fileNotSupport);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Upload'");
		travelRequest.clickbtnUploadTripInfo();
		if (travelRequest.getInfoMessageUpload().equals("File is not supported."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("9. Click to button 'Choose file' again");
		travelRequest.selectFieldChooseFileTripinfo();
		
		TestLogger.info("*************************");
		TestLogger.info("10. Select file want to upload: (HTML < 2MB)");
		String fileValid = "BCP+Awareness.html";
		travelRequest.selectFileUpload(fileValid);
		
		TestLogger.info("*************************");
		TestLogger.info("11. Click on button 'Upload'");
		travelRequest.clickbtnUploadTripInfo();
		if (travelRequest.selectedFileDisplayed_TravelRequest() == true)
		{
			setStatusTest("PASS", "Selected file is uploaded and listed below");
		}
		else
		{
			setStatusTest("FAIL", "Selected file is not uploaded and listed below");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("12. Input all fields required");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		String origin = "Chinese Garden, Aljunied Road, Singapore";
		String destination = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin,destination,"1");
		travelRequest.dateOnSite(4);
		travelRequest.endDate(4);
		
		TestLogger.info("*************************");
		TestLogger.info("13. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("14. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}
		
	}
	
	@Test (priority = 3)
	public void TT_2294() throws InterruptedException{
		TestLogger.info("**********TT_2294**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Input text into fields 'Request name' have length >= 100");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(100);
		travelRequest.inputRequestName(requestName);
		
		TestLogger.info("*************************");
		TestLogger.info("4. Input other fields");
		String origin = "Chinese Garden, Aljunied Road, Singapore";
		String destination = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin,destination,"1");
		travelRequest.dateOnSite(2);
		travelRequest.endDate(4);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}
		
	}
	
	@Test (priority = 4)
	public void TT_2276() throws InterruptedException{
		TestLogger.info("**********TT_2276**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Trip 1: Input all fields required");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		String origin1 = "Chinese Garden, Aljunied Road, Singapore";
		String destination1 = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin1,destination1,"1");
		travelRequest.dateOnSite(4);
		travelRequest.endDate(4);
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on button 'Add Additional Route(s)'");
		travelRequest.clickbtnAddRoutes();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Trip 2: Input all fields required");
		String origin2 = "Hyderabad, Telangana, India";
		String destination2 = "Jaipur, Rajasthan, India";
		travelRequest.inputLocation(origin2,destination2,"2");
		travelRequest.endDate2(4);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}	
		
	}
	
	@Test (priority = 5)
	public void TT_2282() throws InterruptedException{
		TestLogger.info("**********TT_2282**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Input Airport Code - Origin with length > 3");
		travelRequest.inputOriginAirPortCode(TienIch.taoRandomSovaChu(5));
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		
		if (travelRequest.getInfoMessageOriginAirport().equals("The max length is 3 characters"))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("4. Input Airport Code - Origin with length <= 3");
		travelRequest.inputOriginAirPortCode(TienIch.taoRandomSovaChu(3));
		
		TestLogger.info("*************************");
		TestLogger.info("5. Input Airport Code - Destination with length > 3");
		travelRequest.inputDestinationAirPort(TienIch.taoRandomSovaChu(5));
		String origin1 = "Chinese Garden, Aljunied Road, Singapore";
		String destination1 = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin1,destination1,"1");

		if (travelRequest.getInfoMessageDestinationAirport().equals("The max length is 3 characters"))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("6. Input Airport Code - Destination with length <= 3");
		travelRequest.inputDestinationAirPort(TienIch.taoRandomSovaChu(3));
		
		TestLogger.info("*************************");
		TestLogger.info("7. Input all fields required");
		travelRequest.dateOnSite(4);
		travelRequest.endDate(4);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("9. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}	
		
	}
	
	@Test (priority = 6)
	public void TT_2286() throws InterruptedException{
		TestLogger.info("**********TT_2286**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Select Budget Type from Drop-down list");
		travelRequest.selectBudgetType_TravelRequest("HPE");
		
		TestLogger.info("*************************");
		TestLogger.info("4. Check all check-boxes");
		//travelRequest.checkboxAccomodation();
		travelRequest.checkboxTransportation(1);
		travelRequest.checkboxVisa();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Input all fields required");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		
		String origin1 = "Chinese Garden, Aljunied Road, Singapore";
		String destination1 = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin1,destination1,"1");
		
		travelRequest.dateOnSite(4);
		travelRequest.endDate(4);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}	
		
	}
	
	@Test (priority = 7)
	public void TT_2280() throws InterruptedException{
		TestLogger.info("**********TT_2280**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Input text into 'Note' field have length > 5000");
		JavascriptExecutor scroll = (JavascriptExecutor)driver;
		scroll.executeScript("window.scrollBy(0,500)", "");
		
		String maxNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(5000);
		travelRequest.inputRequestNote(maxNote);
		
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		
		if (travelRequest.getInfoMessageRequestNote().equals("The max length is 5000 characters"))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("4. Input text into 'Note' field have length <= 5000");
		String requestNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(100);
		travelRequest.inputRequestNote(requestNote);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Input all fields required");
		String origin = "Chinese Garden, Aljunied Road, Singapore";
		String destination = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin,destination,"1");
		
		travelRequest.dateOnSite(4);
		travelRequest.endDate(4);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}			
		
	}
	
	@Test (priority = 8)
	public void TT_2041() throws InterruptedException{
		TestLogger.info("**********TT_2041**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Select Budget Type from Drop-down list");
		travelRequest.selectBudgetType_TravelRequest("HPE");
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select Budget Owner from Drop-down list");
		travelRequest.selectBudgetOwner_TravelRequest("BO 1");
		
		TestLogger.info("*************************");
		TestLogger.info("5. Select Budget Category from Drop-down list");
		travelRequest.selectBudgetCategory_TravelRequest("eeeeee");
		
		TestLogger.info("*************************");
		TestLogger.info("6. Input all fields required");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		String origin = "Chinese Garden, Aljunied Road, Singapore";
		String destination = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin,destination,"1");
		travelRequest.dateOnSite(4);
		travelRequest.endDate(4);
		travelRequest.tripStartDate(3);
		travelRequest.tripEndDate(3);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("8. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
		}	
		
	}
	
	@Test (priority = 9)
	public void TT_3303() throws InterruptedException{
		TestLogger.info("**********TT_3303**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Set date on site & end date at the same day");
		travelRequest.dateOnSite(0);
		travelRequest.endDate(0);
		
		TestLogger.info("*************************");
		TestLogger.info("4. Input all fields required");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		String origin = "Chinese Garden, Aljunied Road, Singapore";
		String destination = "Ta' Xbiex, Malta";
		travelRequest.inputLocation(origin,destination,"1");
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Submit'");
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Verify created request");
		if (travelRequest.buttonFilterDisplayed())
		{
			setStatusTest("PASS", "Create Travel Request SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Create Travel Request UNSUCCESSFUL!");
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
