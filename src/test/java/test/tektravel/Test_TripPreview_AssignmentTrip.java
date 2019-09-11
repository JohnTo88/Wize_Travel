package test.tektravel;

import java.sql.Connection;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataBase.DiscoverData;
import Utils.TestLogger;
import Utils.TestManager;
import tektravel.webpages.FeatureTour;
import tektravel.webpages.HomePage;
import tektravel.webpages.Login;
import tektravel.webpages.Management_MyTrips;
import tektravel.webpages.Management_Trips;
import tektravel.webpages.PopUp;
import tektravel.webpages.TripPreview;
import web.controller.WebDriverController;

public class Test_TripPreview_AssignmentTrip extends TestManager{
	WebDriver driver;
	Login login;
	TripPreview tripPreview;
	HomePage homePage;
	Connection con;
	FeatureTour featureTour;
	PopUp popUp;
	Management_Trips managementTrip;
	Management_MyTrips myTrips;
	
	@BeforeTest
	public void khoiTaoCacTrang(){
		driver = WebDriverController.khoiTaoTrinhDuyetFireFox();
		homePage = new HomePage(driver);
		login = new Login(driver);
		featureTour = new FeatureTour(driver);
		tripPreview = new TripPreview(driver);
		popUp = new PopUp(driver);
		managementTrip = new Management_Trips(driver);
		myTrips = new Management_MyTrips(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
	}
	
	@Test (priority = 1)
	public void TT_2869() throws InterruptedException{
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		
		if (tripPreview.iconEditassignmentTripDisplayed() == true && tripPreview.assignmentTripFieldDisplayed() == true)
		{
			setStatusTest("PASS", "- User can view assignment field\r\n" + 
					"- User can edit field 'Assigned TC'");
		}
		else
		{
			setStatusTest("FAIL", "- User CAN'T view assignment field\r\n" + 
					"- User CAN'T edit field 'Assigned TC'");
		}
		
		login.clickbtnSignout();
		popUp.signOutConfirmationYes();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Login with account Admin");
		login.signIn(Login.Email_Admin, Login.Password);
		
		TestLogger.info("*************************");
		TestLogger.info("4. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		
		if (tripPreview.iconEditassignmentTripDisplayed() == false && tripPreview.assignmentTripFieldDisplayed() == true)
		{
			setStatusTest("PASS", "Just read only field 'Assigned TC'");
		}
		else
		{
			setStatusTest("FAIL", "DON'T read only field 'Assigned TC''");
		}
		
		login.clickbtnSignout();
		popUp.signOutConfirmationYes();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Login with account Budget Owner");
		login.signIn(Login.Email_BudgetOwner, Login.Password);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Go to Trip Preview of any request");
		myTrips.selectTabMyTrips();
		myTrips.selectRequestName_tabMyTrips();
		
		if (tripPreview.iconEditassignmentTripDisplayed() == false && tripPreview.assignmentTripFieldDisplayed() == true)
		{
			setStatusTest("PASS", "Just read only field 'Assigned TC'");
		}
		else
		{
			setStatusTest("FAIL", "DON'T read only field 'Assigned TC''");
		}
		
		login.clickbtnSignout();
		popUp.signOutConfirmationYes();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Login with account Finances");
		login.signIn(Login.Email_Finances, Login.Password);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabProcessing();
		managementTrip.selectRequestName_tabProcessing();
		
		if (tripPreview.iconEditassignmentTripDisplayed() == false && tripPreview.assignmentTripFieldDisplayed() == true)
		{
			setStatusTest("PASS", "Just read only field 'Assigned TC'");
		}
		else
		{
			setStatusTest("FAIL", "DON'T read only field 'Assigned TC''");
		}
		
	}
	
	@Test (priority = 2)
	public void TT_2796() throws InterruptedException{
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit'");
		if(tripPreview.getInfoAssignmentTrip().equals("Unassigned")){
			tripPreview.clickIconEditAssignmentTrip();
		}
		else {
			setStatusTest("FAIL", "No Qualified");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select TC in drop-down list");
		String assignedTC = "TC 1";
		tripPreview.selectAssignedTC(assignedTC);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Save'");
		popUp.saveAssignment();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Verify assignment TC field");
		
		if(tripPreview.getInfoAssignmentTrip().equals(assignedTC)){
			setStatusTest("PASS", "Assigned TC display user name selected");
		}
		else {
			setStatusTest("FAIL", "NO display user name selected");
		}
		
	}
	
	@Test (priority = 3)
	public void TT_2797() throws InterruptedException{
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit'");
		tripPreview.clickIconEditAssignmentTrip();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select TC in drop-down list");
		String assignedTC1 = "TC 1";
		tripPreview.selectAssignedTC(assignedTC1);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Cancel'");
		popUp.cancelAssignment();
		
		if(tripPreview.getInfoAssignmentTrip().equals(assignedTC1)){
			setStatusTest("FAIL", "Assigned TC is changed");
		}
		else {
			setStatusTest("PASS", "Assigned TC is not changed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on icon 'Edit' again");
		tripPreview.clickIconEditAssignmentTrip();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Select TC in drop-down list");
		String assignedTC2 = "Huy Pham";
		tripPreview.selectAssignedTC(assignedTC2);
		
		TestLogger.info("*************************");
		TestLogger.info("8. Click on button 'Save'");
		popUp.saveAssignment();
		
		if(tripPreview.getInfoAssignmentTrip().equals(assignedTC2)){
			setStatusTest("PASS", "Assigned TC is changed");
		}
		else {
			setStatusTest("FAIL", "Assigned TC is not changed");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("9. Click on icon 'Edit' again");
		tripPreview.clickIconEditAssignmentTrip();
		
		TestLogger.info("*************************");
		TestLogger.info("10. Remove TC in drop-down list");
		tripPreview.removeAssignedTC();
		
		TestLogger.info("*************************");
		TestLogger.info("11. Click on button 'Save'");
		popUp.saveAssignment();
		
		if(tripPreview.getInfoAssignmentTrip().equals("Unassigned")){
			setStatusTest("PASS", "Remove assignment trip successful");
		}
		else {
			setStatusTest("FAIL", "Remove unsuccessful");
		}
		
	}
	
	@Test (priority = 4)
	public void TT_2799() throws InterruptedException{
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit'");
		tripPreview.clickIconEditAssignmentTrip();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select TC in drop-down list");
		String assignedTC1 = "TC 1";
		tripPreview.selectAssignedTC(assignedTC1);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Save'");
		popUp.saveAssignment();
		
		TestLogger.info("*************************");
		TestLogger.info("6. View Trip History Log");
		tripPreview.selectTabHistoryLog();
		
		if(tripPreview.viewHistoryLog().equals(assignedTC1)){
			setStatusTest("PASS", "History Log OK");
		}
		else {
			setStatusTest("FAIL", "History Log not OK");
		}
		
	}
	
	@Test (priority = 5)
	public void TT_2801() throws InterruptedException, SQLException{
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Click on icon 'Edit'");
		tripPreview.clickIconEditAssignmentTrip();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select TC in drop-down list");
		String assignedTC = "TC 1";
		String emailAssignedTC = "TC1@tek-experts.com";
		tripPreview.selectAssignedTC(assignedTC);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'Save'");
		popUp.saveAssignment();
		
		TestLogger.info("*************************");
		TestLogger.info("CHECKING DATABASE.PLEASE WAIT..." );
		tripPreview.sleep(80);
		
		String dataColumnSubject = DiscoverData.getColumnSubject(con,Login.Email_TravelCoordinator);
		
		TestLogger.info("Print Subject: " + dataColumnSubject);
		
		if (dataColumnSubject.contains("TRIP ASSIGNMENT CHANGE"))
		{
			setStatusTest("PASS", "Review Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Review Subject FAIL");
		}
		
		TestLogger.info("*************************");
		String ContentEmail = DiscoverData.tripAssignment(con,Login.Email_TravelCoordinator,emailAssignedTC);
		
		TestLogger.info("6. View Content Email when sent to New Assigned TC");
		TestLogger.info(ContentEmail);
		
		if (ContentEmail.contains("This Travel Request has been assigned to you."))
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
