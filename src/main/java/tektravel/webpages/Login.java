package tektravel.webpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utils.TestLogger;
import web.controller.WebActions;

public class Login extends WebActions{
	WebDriver driver;

	private By txtEmail = By.xpath(".//div[@class='form-group ']//input[@name='Email']");
	private By txtPassword = By.xpath(".//div[@class='form-group ']//input[@name='Password']");
	
	private By txtMessageEmail = By.xpath(".//span[@data-valmsg-for='Email']");
	private By txtMessagePass = By.xpath(".//span[@data-valmsg-for='Password']");
	private By txtOtherMessage = By.xpath(".//div[@class='validation-summary-errors']//span");
	
	private By btnSignin = By.xpath(".//button[@type='submit' and text()='Sign In']");
	private By btnSignout = By.xpath(".//a[@id='btn-signout']");
	
	public static String Email_TravelCoordinator = "TC2@tek-experts.com";
	public static String Email_Traveler = "Traveler2@tek-experts.com";
	public static String Email_BudgetOwner = "BO1@tek-experts.com";
	public static String Email_Admin = "admin1@Tek-experts.com";
	public static String Email_Finances = "Finance2@tek-experts.com";

	public static String Password = "Travel@123";

	public Login(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	public boolean btnSigninDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(btnSignin).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	public boolean btnSignoutDisplayed()
	{
		boolean flag = false;
		
		try {
			if (driver.findElement(btnSignout).isDisplayed() == true)
				flag = true;
		} catch (Exception e) {
			flag = false;
		}
		
		return flag;
		
	}
	
	//Function Login
	public void signIn(String email, String password) {
		driver.findElement(txtEmail).clear();
		driver.findElement(txtEmail).sendKeys(email);
		TestLogger.info(driver.findElement(txtEmail).getAttribute("value"));
		
		driver.findElement(txtPassword).clear();
		driver.findElement(txtPassword).sendKeys(password);
		TestLogger.info(driver.findElement(txtPassword).getAttribute("value"));
		
		driver.findElement(btnSignin).click();
		sleep(2);
	}
	
	//Input Email
	public void inputEmail(String email) throws InterruptedException{
		waitForElementVisible(5, txtEmail);
		driver.findElement(txtEmail).clear();
		driver.findElement(txtEmail).sendKeys(email);
		sleep(1);
	}
	
	//Input Password
	public void inputPassword(String password) throws InterruptedException{
		waitForElementVisible(5, txtPassword);
		driver.findElement(txtPassword).clear();
		driver.findElement(txtPassword).sendKeys(password);
		sleep(1);
	}
	
	//Click on button Sign In
	public void clickbtnSignIn() throws InterruptedException {
		waitForElementVisible(5, btnSignin);
		driver.findElement(btnSignin).click();
		sleep(2);
	}
	
	//Click on button Sign Out
	public void clickbtnSignout() throws InterruptedException {
		waitForElementPresent(10, btnSignout);
		clickByJavaScript(btnSignout);
		sleep(2);
	}
	
	//Get Info Message Email
	public String getInfoMesageEmail() throws InterruptedException {
		waitForElementVisible(10, txtMessageEmail);
		String infoAMessageEmail = driver.findElement(txtMessageEmail).getText();
		return infoAMessageEmail;
	}
	
	//Get Info Message Password
	public String getInfoMesagePass() throws InterruptedException {
		waitForElementVisible(10, txtMessagePass);
		String infoMessagePass = driver.findElement(txtMessagePass).getText();
		return infoMessagePass;
	}
	
	//Get Info Other Message
	public String getInfoOtherMesage() throws InterruptedException {
		waitForElementVisible(10, txtOtherMessage);
		String infoOtherMessage = driver.findElement(txtOtherMessage).getText();
		return infoOtherMessage;
	}
	
}
