package web.controller;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverController {

	public static WebDriver driver;
	public WebElement element;

	public static WebDriver openChrome() {
		System.out.println("Opening Chrome Browser...");

		System.setProperty("webdriver.chrome.driver", ".\\src\\main\\resources\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (driver != null) {
			System.out.println("Start Chrome Browser SUCCESSFUL...!");
			return driver;
		} else {
			System.out.println("Start Chrome Browser UNSUCCESSFUL...!");
			return driver;
		}
	}

	public static WebDriver khoiTaoTrinhDuyetIE() {
		System.out.println("Opening Internet Explorer Browser.... ");

		System.setProperty("webdriver.ie.driver", ".\\src\\main\\resources\\IEDriverServer.exe");
		driver = new InternetExplorerDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		if (driver != null) {
			System.out.println("Start Internet Explorer SUCCESSFUL...!");
			return driver;
		} else {
			System.out.println("Start Internet Explorer UNSUCCESSFUL...!");
			return driver;
		}

	}

	private static String pathToFirefoxBinary ="C:\\Program Files\\Mozilla Firefox\\firefox.exe";

	public static WebDriver khoiTaoTrinhDuyetFireFox() {
		System.out.println("Opening FireFox Browser.... ");

		System.setProperty("webdriver.gecko.driver", ".\\src\\main\\resources\\geckodriver.exe");

		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_BINARY, pathToFirefoxBinary);
		System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
		System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "log.txt");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		if (driver != null) {
			System.out.println("Start FireFox SUCCESSFUL...!");
			return driver;
		} else {
			System.out.println("Start FireFox UNSUCCESSFUL...!");
			return driver;
		}

	}

	public static void waitforElementVisibility(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(element));

	}

	public static void waitForElementClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public static void hoverOnElement(WebElement element) {
		waitforElementVisibility(element);
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public static void clickOnElement(WebElement element) {
		waitForElementClickable(element);
		Actions action = new Actions(driver);
		action.moveToElement(element).click().build().perform();
	}

}
