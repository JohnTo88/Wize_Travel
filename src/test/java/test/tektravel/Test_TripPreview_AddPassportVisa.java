package test.tektravel;

import java.sql.Connection;

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
import tektravel.webpages.Management_Trips;
import tektravel.webpages.PopUp;
import tektravel.webpages.TripPreview;
import web.controller.WebDriverController;

public class Test_TripPreview_AddPassportVisa extends TestManager{
	WebDriver driver;
	Login login;
	TripPreview tripPreview;
	HomePage homePage;
	Connection con;
	FeatureTour featureTour;
	PopUp popUp;
	Management_Trips managementTrip;
	
	@BeforeTest
	public void khoiTaoCacTrang(){
		driver = WebDriverController.khoiTaoTrinhDuyetFireFox();
		homePage = new HomePage(driver);
		login = new Login(driver);
		featureTour = new FeatureTour(driver);
		tripPreview = new TripPreview(driver);
		popUp = new PopUp(driver);
		managementTrip = new Management_Trips(driver);
		con = DiscoverData.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
	}
	
	@Test (priority = 1)
	public void TT_2171() throws InterruptedException{
		TestLogger.info("**********TT_2171**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		TestLogger.info("*************************");
		TestLogger.info("3. Click on button 'Add Visa'");
		
		tripPreview.clickbtnAddVisa();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select an item from drop-down list 'Title'");
		tripPreview.selectTitle("1");
		
		TestLogger.info("*************************");
		TestLogger.info("5. Input Name Under Passport (First name/Middle Name/Last Name) have length < 50");
		String firstName = "Automation";
		String middleName = "Testing";
		String lastName = "Fullname";
		tripPreview.inputNameUnderPassport(firstName, middleName, lastName);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Input Passport Number have length < 50");
		tripPreview.inputPassportNumber();
		
		TestLogger.info("*************************");
		String issuingCountry = "Angola";
		TestLogger.info("7. Select Issuing Country from the drop-down list");
		tripPreview.selectIssuingCountry(issuingCountry);
		
		TestLogger.info("*************************");
		String nationality = "American";
		TestLogger.info("8. Select Nationality from the drop-down list");
		tripPreview.selectNationality(nationality);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Input valid expired date");
		
		String expiredDate = "01 Feb 2025";
		tripPreview.inputPassportExpiredDate(expiredDate);
		
		TestLogger.info("*************************");
		TestLogger.info("10. Click on button 'Submit'");
		popUp.addPassportVisaSubmit();
		
		TestLogger.info("*************************");
		TestLogger.info("11. Verify Passport added on table 'Passport & Visa'");
		if(tripPreview.getInfoPassportExpiryDate("1").equals(expiredDate) && tripPreview.getInfoPassportIssuingCountry("1").equals(issuingCountry)) {
			setStatusTest("PASS", "CAN view new passport");
		}
		else
		{
			setStatusTest("FAIL", "CAN NOT view new passport");
		}	
	}
	
	@Test (priority = 2)
	public void TT_2792() throws InterruptedException{
		TestLogger.info("**********TT_2792**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		TestLogger.info("*************************");
		
		TestLogger.info("3. Click on button 'Add Visa'");
		tripPreview.clickbtnAddVisa();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Update Name Under Passport (First name/Middle Name/Last Name)");
		String firstName = "Update Automation";
		String middleName = "Update Testing";
		String lastName = "Update Fullname";
		tripPreview.inputNameUnderPassport(firstName, middleName, lastName);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Select Issuing Country from the drop-down list");
		String issuingCountry = "Angola";
		tripPreview.selectIssuingCountry(issuingCountry);
		
		TestLogger.info("*************************");
		String nationality = "Albanian";
		TestLogger.info("6. Select Nationality from the drop-down list");
		tripPreview.selectNationality(nationality);
		
		TestLogger.info("*************************");
		TestLogger.info("7. Update Passport Number");
		tripPreview.inputPassportNumber();
		
		TestLogger.info("*************************");
		TestLogger.info("8. Update valid expired date");
		
		String expiredDate = "01 Feb 2030";
		tripPreview.inputPassportExpiredDate(expiredDate);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Click on button 'Submit'");
		popUp.addPassportVisaSubmit();
		
		TestLogger.info("*************************");
		TestLogger.info("10. Verify Passport added on table 'Passport & Visa'");
		if(tripPreview.getInfoPassportExpiryDate("1").equals(expiredDate) && tripPreview.getInfoPassportIssuingCountry("1").equals(issuingCountry)) {
			setStatusTest("PASS", "CAN view passport updated");
		}
		else
		{
			setStatusTest("FAIL", "CAN NOT view passport updated");
		}	
	}
	
	@Test (priority = 3)
	public void TT_2162() throws InterruptedException{
		TestLogger.info("**********TT_2162**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		TestLogger.info("*************************");
		
		TestLogger.info("3. Click on button 'Add Visa'");
		
		tripPreview.clickbtnAddVisa();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on icon Plus '+'");
		tripPreview.clickIconPlusPassport();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Input Name Under Passport (First name/Middle Name/Last Name)");
		String firstName = "Add more Automation";
		String middleName = "Add more Testing";
		String lastName = "Add more Fullname";
		tripPreview.addMoreNameUnderPassport(firstName, middleName, lastName);
		
		TestLogger.info("*************************");
		TestLogger.info("6. Input Passport Number");
		tripPreview.addMorePassportNumber();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Select Issuing Country from the drop-down list");
		String issuingCountry = "Algeria";
		tripPreview.addMoreIssuingCountry(issuingCountry);
		
		TestLogger.info("*************************");
		String nationality = "Afghan";
		TestLogger.info("8. Select Nationality from the drop-down list");
		tripPreview.addMoreNationality(nationality);
		
		TestLogger.info("*************************");
		TestLogger.info("9. Input valid expired date");
		
		String expiredDate = "01 Feb 2035";
		tripPreview.addMoreExpiredDate(expiredDate);
		
		TestLogger.info("*************************");
		TestLogger.info("10. Click on button 'Submit'");
		popUp.addPassportVisaSubmit();
		
		TestLogger.info("*************************");
		TestLogger.info("11. Verify Passport added on table 'Passport & Visa'");
		if(tripPreview.getInfoPassportExpiryDate("2").equals(expiredDate) && tripPreview.getInfoPassportIssuingCountry("2").equals(issuingCountry)) {
			setStatusTest("PASS", "CAN view passprt which ADD more");
		}
		else
		{
			setStatusTest("FAIL", "CAN NOT view passprt which ADD more");
		}
			
	}
	
	@Test (priority = 4)
	public void TT_2159() throws InterruptedException{
		TestLogger.info("**********TT_2159**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		TestLogger.info("*************************");
		
		TestLogger.info("3. Click on button 'Add Visa'");
		tripPreview.clickbtnAddVisa();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Input Visa Number");
		tripPreview.inputVisaNumber(1);
		
		TestLogger.info("*************************");
		String visaTo = "Angola";
		TestLogger.info("5. Select Visa To from the drop-down list");
		tripPreview.selectVisaTo(visaTo);
		
		TestLogger.info("*************************");
		String startDate = "01 Mar 2018";
		TestLogger.info("6. Input valid Start Date");
		tripPreview.inputVisaStartDate(startDate,1);
		
		TestLogger.info("*************************");
		String expiredDate = "01 Mar 2030";
		TestLogger.info("7. Input valid Expired Date");
		tripPreview.inputVisaExpiredDate(expiredDate,1);
		
		TestLogger.info("*************************");
		String entries = "Single";
		TestLogger.info("8. Select Entries from the drop-down list");
		tripPreview.selectVisaEntries(entries);
		
		TestLogger.info("*************************");
		String type = "Visitor";
		TestLogger.info("9. Select Type from the drop-down list");
		tripPreview.selectVisaType(type);
		
		TestLogger.info("*************************");
		TestLogger.info("10. Click on button 'Submit'");
		popUp.addPassportVisaSubmit();
		
		TestLogger.info("*************************");
		TestLogger.info("11. Verify Visa added on table 'Passport & Visa'");
		if(tripPreview.getInfoVisaExpiryDate("1").equals(expiredDate) && tripPreview.getInfoVisaEntries("1").equals(entries) && tripPreview.getInfoVisaType("1").equals(type)) {
			setStatusTest("PASS", "CAN view new visa");
		}
		else
		{
			setStatusTest("FAIL", "CAN NOT view new visa");
		}	
		
	}
	
	@Test (priority = 5)
	public void TT_2793() throws InterruptedException{
		TestLogger.info("**********TT_2793**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		TestLogger.info("*************************");
		
		TestLogger.info("3. Click on button 'Add Visa'");
		tripPreview.clickbtnAddVisa();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Select Passport Number from the drop-down list");
		tripPreview.selectPassportNumber(0);
		
		TestLogger.info("*************************");
		TestLogger.info("5. Update Visa Number");
		tripPreview.inputVisaNumber(1);
		
		TestLogger.info("*************************");
		String visaTo = "Albania";
		TestLogger.info("6. Select Visa To from the drop-down list");
		tripPreview.selectVisaTo(visaTo);
		
		TestLogger.info("*************************");
		String startDate = "01 Mar 2020";
		TestLogger.info("7. Update valid Start Date");
		tripPreview.inputVisaStartDate(startDate,1);
		
		TestLogger.info("*************************");
		String expiredDate = "01 Mar 2035";
		TestLogger.info("8. Update valid Expired Date");
		tripPreview.inputVisaExpiredDate(expiredDate,1);
		
		TestLogger.info("*************************");
		String entries = "Multiple";
		TestLogger.info("9. Select Entries from the drop-down list");
		tripPreview.selectVisaEntries(entries);
		
		TestLogger.info("*************************");
		String type = "Business";
		TestLogger.info("10. Select Type from the drop-down list");
		tripPreview.selectVisaType(type);
		
		TestLogger.info("*************************");
		TestLogger.info("11. Click on button 'Submit'");
		popUp.addPassportVisaSubmit();
		
		TestLogger.info("*************************");
		TestLogger.info("12. Verify Visa updated on table 'Passport & Visa'");
		if(tripPreview.getInfoVisaExpiryDate("1").equals(expiredDate) && tripPreview.getInfoVisaEntries("1").equals(entries) && tripPreview.getInfoVisaType("1").equals(type)) {
			setStatusTest("PASS", "CAN view visa updated");
		}
		else
		{
			setStatusTest("FAIL", "CAN NOT view visa updated");
		}		
	}
	
	@Test (priority = 6)
	public void TT_2148() throws InterruptedException{
		TestLogger.info("**********TT_2148**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		TestLogger.info("*************************");
		
		TestLogger.info("3. Click on button 'Add Visa'");
		tripPreview.clickbtnAddVisa();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on icon Plus '+'");
		tripPreview.clickIconPlusVisa();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Add more Visa Number");
		tripPreview.inputVisaNumber(2);
		
		TestLogger.info("*************************");
		String visaTo = "Albania";
		TestLogger.info("6. Select Visa To from the drop-down list");
		tripPreview.addMoretVisaTo(visaTo);
		
		TestLogger.info("*************************");
		String startDate = "01 Mar 2030";
		TestLogger.info("7. Input valid Start Date");
		tripPreview.inputVisaStartDate(startDate,2);
		
		TestLogger.info("*************************");
		String expiredDate = "01 Mar 2040";
		TestLogger.info("8. Input valid Expired Date");
		tripPreview.inputVisaExpiredDate(expiredDate,2);
		
		TestLogger.info("*************************");
		String entries = "Single";
		TestLogger.info("9. Select Entries from the drop-down list");
		tripPreview.addMoretVisaEntries(entries);
		
		TestLogger.info("*************************");
		String type = "Business";
		TestLogger.info("10. Select Type from the drop-down list");
		tripPreview.addMoreVisaType(type);
		
		TestLogger.info("*************************");
		TestLogger.info("11. Click on button 'Submit'");
		popUp.addPassportVisaSubmit();
		
		TestLogger.info("*************************");
		TestLogger.info("12. Verify Visa added on table 'Passport & Visa'");
		if(tripPreview.getInfoVisaExpiryDate("2").equals(expiredDate) && tripPreview.getInfoVisaEntries("2").equals(entries) && tripPreview.getInfoVisaType("2").equals(type)) {
			setStatusTest("PASS", "CAN view Visa add more");
		}
		else
		{
			setStatusTest("FAIL", "CAN NOT view Visa add more");
		}	
	}
	
	@Test (priority = 7)
	public void TT_2147() throws InterruptedException{
		TestLogger.info("**********TT_2147**********");
		TestLogger.info("1. Login with account Travel Coordinator");
		login.signIn(Login.Email_TravelCoordinator, Login.Password);
		featureTour.closePopUpFeaturesTourGetStarted();
		TestLogger.info("*************************");
		
		TestLogger.info("2. Go to Trip Preview of any request");
		managementTrip.selectMenuTrips();
		managementTrip.selectTabNewRequest();
		managementTrip.selectRequestName_tabNewRequest();
		TestLogger.info("*************************");
		
		TestLogger.info("3. Click on button 'Add Visa'");
		tripPreview.clickbtnAddVisa();
		
		TestLogger.info("*************************");
		TestLogger.info("4. Click on icon Minus '-' Visa");
		tripPreview.clickIconMinusVisa();
		
		TestLogger.info("*************************");
		TestLogger.info("5. Click on icon Minus '-' Passport");
		tripPreview.clickIconMinusPassport();
		
		TestLogger.info("*************************");
		TestLogger.info("6. Click on button 'Submit'");
		popUp.addPassportVisaSubmit();
		
		TestLogger.info("*************************");
		TestLogger.info("7. Verify Passport/Visa deleted on table 'Passport & Visa'");
		
		if(tripPreview.morePassportDisplayed() == false && tripPreview.moreVisaDisplayed() == false) {
			setStatusTest("PASS", "Passport/Visa is deleted");
		}
		else
		{
			setStatusTest("FAIL", "Passport/Visa is not deleted");
		}	
		
	}
		
	@AfterTest
	public void closeBrowser(){
		driver.close();
	}
	
}
