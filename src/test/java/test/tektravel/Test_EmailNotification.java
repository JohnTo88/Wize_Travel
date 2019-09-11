package test.tektravel;

import java.sql.Connection;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
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
import tektravel.webpages.Management_CostProjection;
import tektravel.webpages.Management_Trips;
import tektravel.webpages.PopUp;
import tektravel.webpages.TravelRequest;
import tektravel.webpages.TripPreview;
import web.controller.WebDriverController;

public class Test_EmailNotification extends TestManager{
	WebDriver driver;
	Login login;
	TravelRequest travelRequest;
	HomePage homepage;
	Connection con;
	FeatureTour featureTour;
	PopUp popUp;
	Management_Trips managementTrip;
	EditTrip editTrip;
	Management_CostProjection costProjection;
	TripPreview tripPreview;

	@BeforeTest
	public void khoiTaoCacTrang ()
	{
		driver = WebDriverController.openChrome();
		homepage = new HomePage(driver);
		login = new Login(driver);
		featureTour = new FeatureTour(driver);
		travelRequest = new TravelRequest(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
		popUp = new PopUp(driver);
		managementTrip = new Management_Trips(driver);
		editTrip = new EditTrip(driver);
		costProjection = new Management_CostProjection(driver);
		tripPreview = new TripPreview(driver);
	}
	
	//View Email Content when Create a New Request
	@Test (priority = 1)
	public void TT_1999() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_1999**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Create a new request");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		String origin = "Chinese Garden, Aljunied Road, Singapore";
		String destination = "Amsterdam, Netherlands";
		travelRequest.inputLocation(origin,destination,"1");
		/*String valueDateonSite = */ travelRequest.dateOnSite(3);
		/*String valueEndDate = */ travelRequest.endDate(3);
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("Checking Database.Please Wait..." );
		travelRequest.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("4. View subject email sent to traveler");
		String dataSubjectEmail = DiscoverData.getColumnSubject(con,Login.Email_Traveler);
		
		TestLogger.info("Print Subject: " + dataSubjectEmail);
		
		if (dataSubjectEmail.contains("NEW TRAVEL REQUEST") && dataSubjectEmail.contains(destination))
		{
			setStatusTest("PASS", "Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Subject FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("5. View content email sent to traveler/requestor");
		String emailTraveler = DiscoverData.contentEmail(con,Login.Email_Traveler,"1");
		TestLogger.info("*************************");
		
		TestLogger.info("Email Content Traveler: " + emailTraveler);
		 
		if (emailTraveler.contains("Your new travel request has been submitted."))
		{
			setStatusTest("PASS", "Content OK");
		
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
	}
	
	@Test (priority = 2)
	public void TT_1996() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_1996**********");
		TestLogger.info("1. Login with account Traveler");
		login.signIn(Login.Email_Traveler, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select menu 'Travel Request'");
		travelRequest.selectMenuTravelRequest();
		featureTour.closePopUpFeaturesTourTravelRequest();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Create a new request");
		String requestName = "Automation_Testing_" + TienIch.taoRandomSo(10);
		travelRequest.inputRequestName(requestName);
		String origin = "Uitgeest, Netherlands";
		String destination = "Amsterdam, Netherlands";
		travelRequest.inputLocation(origin,destination,"1");
		/*String valueDateonSite = */ travelRequest.dateOnSite(3);
		/*String valueEndDate = */ travelRequest.endDate(3);
		travelRequest.clickbtnSubmit();
		popUp.travelRequestConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("Checking Database.Please Wait..." );
		travelRequest.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("4. View content email sent to Travel Coordinator/CEO");
		String emailTC = DiscoverData.contentEmail(con,Login.Email_Traveler,"4");
		TestLogger.info("Email Content Travel Coordinator/CEO: " + emailTC);
		
		travelRequest.sleep(10);
		
		if (emailTC.contains("A new travel request has been submitted."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
	}
	
	//*******************************************************************************//
	//View Email Content when Send Approve Request
	
	@Test (priority = 3)
	public void TT_2512() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_2512**********");
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
		TestLogger.info("4. Add info in tab 'Flight'");
		editTrip.selectTabFlight();
		
		String airlineFlight = "Aigle Azur";
		editTrip.selectAirlineFlight(airlineFlight);
		
		String agentFlight = TienIch.taoRandomSovaChu(5);
		editTrip.inputAgentFlight(agentFlight, 1);
		
		String _codeFlight = TienIch.taoRandomSovaChu(3);
		editTrip.inputCodeFlight(_codeFlight, 1);
		
		String noteFlight = TienIch.taoRandomSovaChu(10);
		editTrip.inputReservationCodeFlight(noteFlight, 1);
		
		editTrip.updateDepartureFlight(1);
		editTrip.updateReturnFlight(5);
		
		String costFlight = TienIch.taoRandomSo(4);
		editTrip.inputCostFlight(costFlight, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'SEND APPROVAL'");
		editTrip.clickbtnSendApprove();
		popUp.sendApprovalConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("Checking Database.Please Wait..." );
		travelRequest.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("6. View subject email sent to Travel Coordinator");
		String dataSubjectEmail = DiscoverData.getColumnSubject(con,Login.Email_TravelCoordinator);
		
		TestLogger.info("Print Subject: " + dataSubjectEmail);
		
		if (dataSubjectEmail.contains("APPROVAL REQUEST"))
		{
			setStatusTest("PASS", "Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Subject FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("7. View content email sent to Travel Coordinator");
		
		String ContentEmailTC = DiscoverData.contentEmail(con,Login.Email_TravelCoordinator,"4");
		
		TestLogger.info("*************************");
		TestLogger.info("Email Content Travel Coordinator: " + ContentEmailTC);
		
		if (ContentEmailTC.contains("A Cost Projection has been submitted against an existing Travel Request."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
	}
	
	@Test (priority = 4)
	public void TT_2507() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_2507**********");
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
		TestLogger.info("4. Add info in tab 'Flight'");
		editTrip.selectTabFlight();
		
		String airlineFlight = "Aigle Azur";
		editTrip.selectAirlineFlight(airlineFlight);
		
		String agentFlight = TienIch.taoRandomSovaChu(5);
		editTrip.inputAgentFlight(agentFlight, 1);
		
		String _codeFlight = TienIch.taoRandomSovaChu(3);
		editTrip.inputCodeFlight(_codeFlight, 1);
		
		String noteFlight = TienIch.taoRandomSovaChu(10);
		editTrip.inputReservationCodeFlight(noteFlight, 1);
		
		editTrip.updateDepartureFlight(1);
		editTrip.updateReturnFlight(5);
		
		String costFlight = TienIch.taoRandomSo(4);
		editTrip.inputCostFlight(costFlight, 1);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on button 'SEND APPROVAL'");
		editTrip.clickbtnSendApprove();
		popUp.sendApprovalConfirmation();
		
		TestLogger.info("*************************");
		TestLogger.info("Checking Database.Please Wait..." );
		travelRequest.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("6. View subject email sent to Travel Coordinator");
		String dataSubjectEmail = DiscoverData.getColumnSubject(con,Login.Email_TravelCoordinator);
		
		TestLogger.info("Print Subject: " + dataSubjectEmail);
		
		if (dataSubjectEmail.contains("APPROVAL REQUEST"))
		{
			setStatusTest("PASS", "Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Subject FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("7. View content email sent to BO");
		
		String ContentEmailBO = DiscoverData.contentEmail(con,Login.Email_TravelCoordinator,"3");
		
		TestLogger.info("*************************");
		TestLogger.info("Email Content Budget Owner: " + ContentEmailBO);
		
		if (ContentEmailBO.contains("A new Cost Projection has been submitted against your Budget."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("8. View content email sent to Finance");
		
		String ContentEmailFinance = DiscoverData.contentEmail(con,Login.Email_TravelCoordinator,"5");
		
		TestLogger.info("*************************");
		TestLogger.info("Email Content Finance Team: " + ContentEmailFinance);
		
		if (ContentEmailFinance.contains("A new Cost Projection has been submitted against your Budget."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
	}
	
	//*******************************************************************************//
	//View Email Content when Approve or Reject Request
	@Test (priority = 5)
	public void TT_2502() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_2502**********");
		TestLogger.info("1. Login with account Budget Owner");
		login.signIn(Login.Email_BudgetOwner, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select a request with status 'Pending Approve'");
		costProjection.selectTabCostProjection();
		costProjection.filterRequestStatus("5");
		costProjection.clickbtnFilter();
		costProjection.selectProjectionName();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Approve this request");
		if(costProjection.btnApproveDisplayed() == true) {
			costProjection.clickbtnApprove();
		}
		else {
			TestLogger.info("Button 'Approve' fail to display");
		}
		
		popUp.costProjectionConfirmationApprove();
		
		TestLogger.info("========================================================" );
		TestLogger.info("Checking Database.Please Wait..." );
		costProjection.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("4. View subject email sent to Budget Owner");
		String dataSubjectEmail = DiscoverData.getColumnSubject(con,Login.Email_BudgetOwner);
		
		TestLogger.info("Print Subject: " + dataSubjectEmail);
		
		if (dataSubjectEmail.contains("REQUEST APPROVED"))
		{
			setStatusTest("PASS", "Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Subject FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("5. View content email sent to Budget Owner");
		String ContentEmailBO = DiscoverData.contentEmail(con,Login.Email_BudgetOwner,"3");
		
		TestLogger.info("Email Content Budget Owner: " + ContentEmailBO);
		
		if (ContentEmailBO.contains("A Cost Projection against your budget for business ") && ContentEmailBO.contains("has been approved."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
	}
	
	@Test (priority = 6)
	public void TT_2497() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_2497**********");
		TestLogger.info("1. Login with account Budget Owner");
		login.signIn(Login.Email_BudgetOwner, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select a request with status 'Pending Approve'");
		costProjection.selectTabCostProjection();
		costProjection.filterRequestStatus("5");
		costProjection.clickbtnFilter();
		costProjection.selectProjectionName();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Approve this request");
		if(costProjection.btnApproveDisplayed() == true) {
			costProjection.clickbtnApprove();
		}
		else {
			TestLogger.info("Button 'Approve' fail to display");
		}
		
		popUp.costProjectionConfirmationApprove();
		
		TestLogger.info("========================================================" );
		TestLogger.info("Checking Database.Please Wait..." );
		costProjection.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("4. View subject email sent to Budget Owner");
		String dataSubjectEmail = DiscoverData.getColumnSubject(con,Login.Email_BudgetOwner);
		
		TestLogger.info("Print Subject: " + dataSubjectEmail);
		
		if (dataSubjectEmail.contains("REQUEST APPROVED"))
		{
			setStatusTest("PASS", "Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Subject FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("5. View content email sent to Budget Owner");
		String ContentEmailBO = DiscoverData.contentEmail(con,Login.Email_BudgetOwner,"3");
		
		TestLogger.info("Email Content Budget Owner: " + ContentEmailBO);
		
		if (ContentEmailBO.contains("A Cost Projection against your budget for business ") && ContentEmailBO.contains("has been approved."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
	}
	
	@Test (priority = 7)
	public void TT_2493() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_2493**********");
		TestLogger.info("1. Login with account Budget Owner");
		login.signIn(Login.Email_BudgetOwner, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select a request with status 'Pending Approve'");
		costProjection.selectTabCostProjection();
		costProjection.filterRequestStatus("5");
		costProjection.clickbtnFilter();
		costProjection.selectProjectionName();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Reject this request");
		if(costProjection.btnRejectDisplayed() == true) {
			costProjection.clickbtnReject();
		}
		else {
			TestLogger.info("Button 'Reject' fail to display");
		}
		
		popUp.costProjectionConfirmationReject();
		
		TestLogger.info("========================================================" );
		TestLogger.info("Checking Database.Please Wait..." );
		costProjection.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("4. View subject email sent to Budget Owner");
		String dataColumnSubject = DiscoverData.getColumnSubject(con,Login.Email_BudgetOwner);
		
		TestLogger.info("Print Subject Email: " + dataColumnSubject);
		
		if (dataColumnSubject.contains("REQUEST REJECTED"))
		{
			setStatusTest("PASS", "Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Subject FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("5. View content email sent to Budget Owner");
		String ContentEmailBO = DiscoverData.contentEmail(con,Login.Email_BudgetOwner,"3");
		
		TestLogger.info("Email Content Budget Owner: " + ContentEmailBO);
		
		if (ContentEmailBO.contains("A Cost Projection against your budget for ") && ContentEmailBO.contains("has been rejected."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
	
	}
	
	@Test (priority = 8)
	public void TT_2491() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_2491**********");
		TestLogger.info("1. Login with account Budget Owner");
		login.signIn(Login.Email_BudgetOwner, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select a request with status 'Pending Approve'");
		costProjection.selectTabCostProjection();
		costProjection.filterRequestStatus("5");
		costProjection.clickbtnFilter();
		costProjection.selectProjectionName();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Reject this request");
		if(costProjection.btnRejectDisplayed() == true) {
			costProjection.clickbtnReject();
		}
		else {
			TestLogger.info("Button 'Reject' fail to display");
		}
		
		popUp.costProjectionConfirmationReject();
		
		TestLogger.info("========================================================" );
		TestLogger.info("Checking Database.Please Wait..." );
		costProjection.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("4. View subject email sent to Budget Owner");
		String dataColumnSubject = DiscoverData.getColumnSubject(con,Login.Email_BudgetOwner);
		
		TestLogger.info("Print Subject Email: " + dataColumnSubject);
		
		if (dataColumnSubject.contains("REQUEST REJECTED"))
		{
			setStatusTest("PASS", "Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Subject FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("5. View content email sent to Budget Owner");
		String ContentEmailTC = DiscoverData.contentEmail(con,Login.Email_BudgetOwner,"4");
		String ContentEmailFinance = DiscoverData.contentEmail(con,Login.Email_BudgetOwner,"5");
		
		TestLogger.info("Email Content Travel Coordinator: " + ContentEmailTC);
		
		if (ContentEmailTC.contains("A Cost Projection for ") && ContentEmailTC.contains("has been rejected."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
		TestLogger.info("Email Content Finance Team: " + ContentEmailFinance);
		
		if (ContentEmailFinance.contains("A Cost Projection against your budget for ") && ContentEmailFinance.contains("has been rejected."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
	
	}
	
	//*******************************************************************************//
	//View Email Content when Cancel Trip
	@Test (priority = 9)
	public void TT_2470() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_2470**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select tab 'New Trip'");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewTrip();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Select a trip to view Trip Preview");
		managementTrip.selectRequestName_tabNewTrip();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on button 'Cancel Trip'");
		tripPreview.clickbtnCancelTrip();
		popUp.cancelTripComfirmationYes();
		
		TestLogger.info("========================================================" );
		TestLogger.info("Checking Database.Please Wait..." );
		costProjection.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("5. View subject email sent to Travel Coordinator");
		String dataColumnSubject = DiscoverData.getColumnSubject(con,Login.Email_TravelCoordinator);
		
		TestLogger.info("Print Subject Email: " + dataColumnSubject);
		
		if (dataColumnSubject.contains("TRIP CANCELLED"))
		{
			setStatusTest("PASS", "Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Subject FAIL");
		}
		
		TestLogger.info("*************************");
		String ContentEmail_Traveler = DiscoverData.cancelTrip(con, Login.Email_TravelCoordinator, "1");
		String ContentEmail_BO = DiscoverData.cancelTrip(con, Login.Email_TravelCoordinator, "3");
		String ContentEmail_TC = DiscoverData.cancelTrip(con, Login.Email_TravelCoordinator, "4");
		String ContentEmail_Finances = DiscoverData.cancelTrip(con, Login.Email_TravelCoordinator, "5");
		
		TestLogger.info("Print Content Email Traveler: " + ContentEmail_Traveler);
		
		if (ContentEmail_Traveler.contains("Your trip has been cancelled."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
		TestLogger.info("Print Content Email Budget Owner: " + ContentEmail_BO);
		
		if (ContentEmail_BO.contains("A trip against your budget has been cancelled."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
		TestLogger.info("Print Content Email Finances: " + ContentEmail_Finances);
		
		if (ContentEmail_Finances.contains("A trip against your budget has been cancelled."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
		TestLogger.info("Print Content Email TC: " + ContentEmail_TC);
		
		if (ContentEmail_TC.contains("A trip has been cancelled."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
	}
	
	@Test (priority = 10)
	public void TT_2467() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_2467**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select tab 'New Trip'");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewTrip();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Select a trip to view Trip Preview");
		managementTrip.selectRequestName_tabNewTrip();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on button 'Cancel Trip'");
		tripPreview.clickbtnCancelTrip();
		popUp.cancelTripComfirmationYes();
		
		TestLogger.info("========================================================" );
		TestLogger.info("Checking Database.Please Wait..." );
		costProjection.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("5. View content email sent to Budget owner/Finance");
		String ContentEmail_BO = DiscoverData.cancelTrip(con, Login.Email_TravelCoordinator, "3");
		String ContentEmail_Finances = DiscoverData.cancelTrip(con, Login.Email_TravelCoordinator, "5");
		
		TestLogger.info("Print Content Email Budget Owner: " + ContentEmail_BO);
		
		if (ContentEmail_BO.contains("A trip against your budget has been cancelled."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
		TestLogger.info("Print Content Email Finances: " + ContentEmail_Finances);
		
		if (ContentEmail_Finances.contains("A trip against your budget has been cancelled."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("6. View content email sent to Traveler");
		String ContentEmail_Traveler = DiscoverData.cancelTrip(con, Login.Email_TravelCoordinator, "1");

		TestLogger.info("Print Content Email Traveler: " + ContentEmail_Traveler);
		
		if (ContentEmail_Traveler.contains("Your trip has been cancelled."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
	}
	
	//*******************************************************************************//
	//View Email Content when Complete Trip
	@Test (priority = 11)
	public void TT_2474() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_2474**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select tab 'Finished'");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabFinished();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Select a trip to view Trip Preview");
		managementTrip.selectRequestName_tabFinished();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on button 'Complete Trip'");
		tripPreview.clickbtnCompleteTrip();
		popUp.completeTripComfirmationYes();
		
		TestLogger.info("========================================================" );
		TestLogger.info("Checking Database.Please Wait..." );
		costProjection.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("5. View subject email sent to Travel Coordinator");
		String dataColumnSubject = DiscoverData.getColumnSubject(con,Login.Email_TravelCoordinator);
		
		System.out.println("Print Subject Email: " + dataColumnSubject);
		
		if (dataColumnSubject.contains("TRIP COMPLETE"))
		{
			setStatusTest("PASS", "Subject OK");
		}
		else
		{
			setStatusTest("FAIL", "Subject FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("6. View content email sent to Travel Coordinator");
		String ContentEmail_TC = DiscoverData.tripComplete(con, Login.Email_TravelCoordinator, "4");
		
		TestLogger.info("Print Content Trip: " + ContentEmail_TC);
		
		if (ContentEmail_TC.contains("The flowing trip has been marked as complete."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
	}
	
	@Test (priority = 12)
	public void TT_2472() throws InterruptedException, SQLException{
		TestLogger.info("**********TT_2472**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Select tab 'Finished'");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabFinished();
		
		TestLogger.info("*************************");
		TestLogger.info("3. Select a trip to view Trip Preview");
		managementTrip.selectRequestName_tabFinished();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on button 'Complete Trip'");
		tripPreview.clickbtnCompleteTrip();
		popUp.completeTripComfirmationYes();
		
		TestLogger.info("========================================================" );
		TestLogger.info("Checking Database.Please Wait..." );
		costProjection.sleep(80);
		
		TestLogger.info("*************************");
		TestLogger.info("5. View content email sent to Budget Owner");
		String ContentEmail_BO = DiscoverData.tripComplete(con, Login.Email_TravelCoordinator, "3");
		
		TestLogger.info("Print Content Trip: " + ContentEmail_BO);
		
		if (ContentEmail_BO.contains("The flowing trip has been marked as complete against your budget."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
		TestLogger.info("*************************");
		TestLogger.info("6. View content email sent to Finance");
		String ContentEmail_Finances = DiscoverData.tripComplete(con, Login.Email_TravelCoordinator, "5");
		
		TestLogger.info("Print Content Trip: " + ContentEmail_Finances);
		
		if (ContentEmail_Finances.contains("The flowing trip has been marked as complete against your budget."))
		{
			setStatusTest("PASS", "Content OK");
		}
		else
		{
			setStatusTest("FAIL", "Content FAIL");
		}
		
	}
	
	//*******************************************************************************//
	
	
	
	
	
/*	@AfterMethod (alwaysRun = true)
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
	}*/
}
