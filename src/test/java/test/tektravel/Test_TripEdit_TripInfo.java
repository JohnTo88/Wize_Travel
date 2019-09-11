package test.tektravel;

import java.awt.AWTException;
import java.awt.Robot;
import java.sql.Connection;

import org.openqa.selenium.WebDriver;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Mouse;
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

public class Test_TripEdit_TripInfo extends TestManager{
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
	public void TT_2506() throws InterruptedException{
		TestLogger.info("**********TT_2506**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select tab 'Processing'");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit' to update");
		managementTrip.clickIconEdit_NewRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Update all field required:\r\n" + 
				"- Origin/Destination\r\n" + 
				"- Date on Site/End Date\r\n" + 
				"- Trip Start Date/ Trip End Date");
		
		editTrip.selectFinancesTeam(0);
		
		String origin1 = "Chinese Garden, Aljunied Road, Singapore";
		String destination1 = "Ta' Xbiex, Malta";
		editTrip.inputLocation(origin1,destination1,"1");
		
		editTrip.updateDateOnSite(3);
		editTrip.updateEndDate(3);
		
		editTrip.updateTripStartDate(3);
		editTrip.updateTripEndDate(10);
		
		editTrip.inputOriginAirPortCode(TienIch.taoRandomSovaChu(3));
		editTrip.inputDestinationAirPort(TienIch.taoRandomSovaChu(3));;
		
		editTrip.checkboxTransportation(2);
		editTrip.checkboxVisa();
		
		editTrip.selectBudgetType_EditTrip("HPE", 1);
		editTrip.selectBudgetOwner_EditTrip("BO 1", 1);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Update'");
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Verify trip updated successful");
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
	public void TT_2508() throws InterruptedException, FindFailed{
		TestLogger.info("**********TT_2508**********");
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
		TestLogger.info("4. Click on button 'Choose file'");
		editTrip.scrollScreen(200);
		editTrip.selectFieldChooseFileTripinfo();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Select file want to upload (> 2MB)");
		String fileMax = "Building+Microservices+-+Sam+Newman.pdf";
		editTrip.selectFileUpload(fileMax);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Upload'");
		editTrip.clickbtnUploadTripInfo();
		if (editTrip.getInfoMessageUpload().equals("File Maximum size is 2MB."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("7. Click on button 'Choose file' and select file is not type of support");
		editTrip.selectFieldChooseFileTripinfo();
		String fileNotSupport = "TienIch.java";
		editTrip.selectFileUpload(fileNotSupport);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Upload'");
		editTrip.clickbtnUploadTripInfo();
		if (editTrip.getInfoMessageUpload().equals("File is not supported."))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("9. Click to button 'Choose file' again");
		editTrip.selectFieldChooseFileTripinfo();
		
		TestLogger.info("*************************");
		TestLogger.info("10. Select file want to upload: (HTML < 2MB)");
		String fileValid = "BCP+Awareness.html";
		editTrip.selectFileUpload(fileValid);
		
		TestLogger.info("*************************");
		TestLogger.info("11. Click on button 'Upload'");
		editTrip.clickbtnUploadTripInfo();
		if (editTrip.selectedFileDisplayed_EditTrip("trip-info")== true)
		{
			setStatusTest("PASS", "Selected file is uploaded and listed below");
		}
		else
		{
			setStatusTest("FAIL", "Selected file is not uploaded and listed below");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("12. Click on button 'Update'");
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("13. Verify trip updated successful");
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
	public void TT_2509() throws InterruptedException{
		TestLogger.info("**********TT_2509**********");
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
		TestLogger.info("4. Input text into 'Note' field have length > 5000");
		editTrip.scrollScreen(300);
		
		String maxNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(5000);
		editTrip.inputRequestNote(maxNote);
		editTrip.inputOriginAirPortCode(TienIch.taoRandomSovaChu(3));
		editTrip.checkboxVisa();
		
		if (travelRequest.getInfoMessageRequestNote().equals("The max length is 5000 characters"))
		{
			setStatusTest("PASS", "Messages error displayed");
		}
		else
		{
			setStatusTest("FAIL", "Messages error is not displayed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("5. Input text into 'Note' field have length <= 5000");
		String requestNote = "Automation_Testing_" + TienIch.taoRandomSovaChu(100);
		editTrip.inputRequestNote(requestNote);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Update'");
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("13. Verify trip updated successful");
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
	public void TT_2504() throws InterruptedException{
		TestLogger.info("**********TT_2504**********");
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
		TestLogger.info("4. Update info Passport");
		editTrip.scrollScreen(1000);
		
		editTrip.selectTitle("1");
		String firstName = Login.Email_TravelCoordinator;
		String middleName = "Testing";
		String lastName = "Fullname";
		editTrip.inputNameUnderPassport(firstName, middleName, lastName);
		editTrip.inputPassportNumber();
		
		String issuingCountry = "Angola";
		editTrip.selectIssuingCountry(issuingCountry);
		
		String nationality = "American";
		editTrip.selectNationality(nationality);
		
		String passportExpiredDate = "01 Feb 2025";
		editTrip.inputPassportExpiredDate(passportExpiredDate);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Update more Passport");
		editTrip.clickIconPlusPassport();
		editTrip.selectTitle("1");
		String _firstName = Login.Email_Traveler;
		String _middleName = "Add more Testing";
		String _lastName = "Add more Fullname";
		editTrip.addMoreNameUnderPassport(_firstName, _middleName, _lastName);
		editTrip.addMorePassportNumber();
		String _nationality = "Afghan";
		editTrip.addMoreNationality(_nationality);
		String _issuingCountry = "Albania";
		editTrip.addMoreIssuingCountry(_issuingCountry);
		String _expiredDate = "01 Feb 2035";
		editTrip.addMoreExpiredDate(_expiredDate);
		
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
	
	@Test (priority = 5)
	public void TT_2505() throws InterruptedException{
		TestLogger.info("**********TT_2505**********");
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
		TestLogger.info("4. Update info Visa");
		editTrip.selectPassportNumber(1);
		editTrip.inputVisaNumber(1);
		String visaTo = "Afghanistan";
		editTrip.selectVisaTo(visaTo);
		
		String visaStartDate = "01 Mar 2018";
		String visaExpiredDate = "01 Mar 2030";
		editTrip.inputVisaStartDate(visaStartDate,1);
		editTrip.inputVisaExpiredDate(visaExpiredDate,1);
	
		String entries = "Single";
		editTrip.selectVisaEntries(entries);
		
		String type = "Visitor";
		editTrip.selectVisaType(type);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Update more Visa");
		editTrip.clickIconPlusVisa();
		editTrip.selectMorePassportNumber(0);
		editTrip.inputVisaNumber(2);
		editTrip.addMoretVisaTo(visaTo);
		editTrip.inputVisaStartDate(visaStartDate,2);
		editTrip.inputVisaExpiredDate(visaStartDate,2);
		editTrip.addMoretVisaEntries(entries);
		editTrip.addMoreVisaType(type);
		
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
	
	@Test (priority = 6)
	public void TT_1979() throws InterruptedException{
		TestLogger.info("**********TT_1979**********");
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
		TestLogger.info("4. Click on button 'Add Additional Route(s)'");
		editTrip.clickbtnAddRoutes();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Update all fields required");
		editTrip.scrollScreen(200);
		String origin2 = "Hyderabad, Telangana, India";
		String destination2 = "Jaipur, Rajasthan, India";
		editTrip.inputLocation(origin2,destination2,"2");
		editTrip.updateAddMoreEndDate(4);
		
		editTrip.updateAddMoreTripStartDate(3);
		editTrip.updateAddMoreTripEndDate(4);
		
		editTrip.addMoreBudgetOwner_EditTrip("BO 1", 2);
		editTrip.addMoreBudgetCategory_EditTrip("eeeeee", 2);
		
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
	
	@Test (priority = 7)
	public void TT_1977() throws InterruptedException{
		TestLogger.info("**********TT_1977**********");
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
		TestLogger.info("4. Click on icon 'X' to delete routes");
		editTrip.deleteRoutes();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Update'");	
		editTrip.clickbtnUpdate();
		popUp.tripConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Verify trip updated successful");
		if (managementTrip.tabProcessingDisplayed() == true)
		{
			setStatusTest("PASS", "Update Trip Info SUCCESSFUL!");
		}
		else
		{
			setStatusTest("FAIL", "Update Trip Info UNSUCCESSFUL!");
		}
		
	}
	
	@Test (priority = 7)
	public void TT_2427() throws InterruptedException, AWTException{
		TestLogger.info("**********TT_1977**********");
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
		TestLogger.info("4. Click on button 'Cancel'");
		editTrip.clickbtnCancel();
		
		popUp.popUpBrowser();
	
		TestLogger.info("*************************");
		TestLogger.info("5. Verify trip updated successful");
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
				editTrip.sleep(5);
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
