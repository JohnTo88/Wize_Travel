package web.controller;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.base.Function;

import Utils.TestLogger;
import Utils.TienIch;

public class WebActions {

	public static WebDriver driver;

	
	public WebActions(WebDriver _driver)
	{
		driver = _driver;
	}
	
	/**
	 * Shut down the web driver instance or destroy the web driver instance (Close
	 * all the windows).
	 * 
	 * @author hanv
	 * @param driver which is a object of WebDriver
	 * @return : void
	 */
	public void closeAllBrowserWindows() {
		driver.quit();
		TestLogger.info("Closed browser by shutting down the web driver instance ");
	}

	/**
	 * close the browser or page currently which is having the focus.
	 * 
	 * @author hanv
	 * @param driver which is a object of WebDriver
	 * @return : void
	 */
	public void closeCurrentBrowserWindow() {
		driver.close();
	}

	/**
	 * Delete Cookies on Browser
	 * 
	 * @author hanv
	 * @param driver which is a object of WebDriver which will perform delete
	 *               cookies
	 * @return void
	 */
	public void deleteCookies() {
		driver.manage().deleteAllCookies();
		TestLogger.info("Deleted coookies susscessfully ! ");
	}

	/**
	 * Open known link on Browser with know URL (TargetURL)
	 * 
	 * @author hanv
	 * @param driver    which will open link on it .
	 * @param TargetURL link need to open
	 * @return a WebDriver object
	 */
	public void moLinkWeb(String TargetURL) {
		driver.navigate().to(TargetURL);
		TestLogger.info("Opened link : " + TargetURL);
	}

	/**
	 * Navigate to previous website in History
	 * 
	 * @author hanv
	 * @param driver which will navigate to Previous Website in History .
	 * @return a WebDriver object
	 */
	public void navigateBackward() {
		driver.navigate().back();
	}

	/**
	 * Forward to website in History
	 * 
	 * @author hanv
	 * @param driver which will forward to website in History .
	 * @return a WebDriver object
	 */
	public void navigateForward() {
		driver.navigate().forward();
	}

	/**
	 * Clink on Web Element , verifying element by By class
	 * 
	 * @author hanv
	 * @param driver     which contain web elenments.
	 * @param webLocator which belong to By class . User can call static functions
	 *                   on By to find element based on criteria like name,
	 *                   id,class,...ect
	 * @return a WebDriver object
	 */
	public void click(By webLocator) {
		driver.findElement(webLocator).click();
	}

	/**
	 * Get a Web Element , verifying element By class
	 * 
	 * @author hanv
	 * @param driver which contain web elenments.
	 * @param        webLocator, which belong to By class . User can call static
	 *               functions on By to find element based on criteria like name,
	 *               id,class,...ect
	 * @return a WebElement
	 */
	public WebElement getWebElement(By webLocator) {
		WebElement element = driver.findElement(webLocator);
		return element;
	}

	/**
	 * Insert value into TextField or TextArea , verifying TextField By class
	 * 
	 * @author hanv
	 * @param driver, which contain web elements.
	 * @param webLocator, which belong to By class . User can call static functions
	 *        on By to find element based on criteria like name, id,class,...etc ..
	 * @param Value, which will be inserted into Textfield or TextArea .
	 * @return void
	 */
	public void typeText(By webLocator, String Value) {
		WebElement textfield = driver.findElement(webLocator);
		if (textfield == null) {
			TestLogger.error(" textfield is NULL , thus, can't perform insert action on it  ");
		} else {
			textfield.clear();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			textfield.sendKeys(Value);
			// textfield.sendKeys(Keys.ENTER);
			TestLogger.info("Insert successfully value " + Value);
		}

	}

	public void goTextOn(By by, String value) {
		String val = value;
		WebElement element = driver.findElement(by);
		element.clear();

		for (int i = 0; i < val.length(); i++) {
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			sleep(0.2);
			element.sendKeys(s);
			
		}
		element.sendKeys(Keys.ENTER);
	}
	
	public void goTextOnNoEnter(By by, String value) {
		String val = value;
		WebElement element = driver.findElement(by);
		element.clear();

		for (int i = 0; i < val.length(); i++) {
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			sleep(0.3);
			element.sendKeys(s);
			
		}
		
	}
	
	public void goTextOnNoClear(By by, String value) {
		String val = value;
		WebElement element = driver.findElement(by);
	
		for (int i = 0; i < val.length(); i++) {
			char c = val.charAt(i);
			String s = new StringBuilder().append(c).toString();
			sleep(0.2);
			element.sendKeys(s);
			TestLogger.info(s);
			
		}
		
	}

	public void sleep(double timeInSecond) {
		try {
			Thread.sleep((long) (timeInSecond * 1000));
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}

	public void typeTextOn(WebElement webElement, String Value) {
		WebElement textfield = webElement;
		if (textfield == null) {
			TestLogger.error(" textfield is NULL , thus, can't perform insert action on it  ");
		} else {
			textfield.clear();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			textfield.sendKeys(Value);
			// textfield.sendKeys(Keys.ENTER);
			TestLogger.info("Insert successfully value " + Value);
		}

	}

	/**
	 * Open new Tab on same window of browser
	 * 
	 * @author hanv
	 * @param driver, which belong to WebDriver class
	 * @return a WebDriver object
	 */
	public void openNewTab() {

		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "t");
	}

	/**
	 * Open link text on a New Tab
	 * 
	 * @author hanv
	 * @param driver, which belong to WebDriver class
	 * @param Textlink, which when user click on it , will open URL on new tab
	 * @return a WebDriver object
	 */
	public void openTexLinkOnNewTab(String Textlink) {
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		driver.findElement(By.linkText(Textlink)).sendKeys(selectLinkOpeninNewTab);
	}

	/**
	 * Close Current NewTab
	 * 
	 * @author hanv
	 * @param driver, which belong to WebDriver class and manage actions like switch
	 *        Tabs ,close tab
	 * @return a WebDriver object
	 */
	public void closeCurentNewTab() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs.get(0));
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
	}

	/**
	 * Drag and Drop from Source Element to Target Element
	 * 
	 * @author hanv
	 * @param               driver, which belong to WebDriver class ,which will
	 *                      perform find Elenments on Web
	 * @param sourceElement which need to drag and drop to target element
	 * @param               targetElement, which will is target of source element
	 * @return a WebDriver object
	 */
	public void dragAndDrop(By sourceElement, By targetElement) {
		WebElement source = driver.findElement(sourceElement);
		WebElement target = driver.findElement(targetElement);
		(new Actions(driver)).dragAndDrop(source, target).perform();
	}

	/**
	 * Refresh current Page
	 * 
	 * @author hanv
	 * @param driver, which belong to WebDriver class and perform Refesh action
	 * @return void
	 */

	public void refreshPage() {
		driver.navigate().refresh();
	}

	/**
	 * Check an elenment is Present or Not
	 * 
	 * @author hanv
	 * @param driver     ,represent like website mamager
	 * @param locatorKey belong to By class and reponsible for locating element basd
	 *                   on criteria like name , id, class of element
	 * @return void
	 */
	public boolean isElementPresent2(By locatorKey) {
		boolean available = false;
		WebElement element = driver.findElement(locatorKey);
		if (element != null) {
			available = true;
		}
		return available;
	}

	public WebElement choPhanTuHienThi(final By locator) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(30, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});

		return foo;
	};

	/**
	 * Clear Cache
	 * 
	 * @author hanv
	 * @param driver represent like website mamager
	 * @return void
	 */
	public void clearCache() {
		Actions actionObject = new Actions(driver);
		try {
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		} catch (WebDriverException e) {
			System.out.print("retrying refesh page");
			actionObject.sendKeys(Keys.CONTROL).sendKeys(Keys.F5).build().perform();
		}

	}

	/**
	 * Mouse hover on a Web Element
	 * 
	 * @author hanv
	 * @date 023-Octorber-2014
	 * @param element Web Element that will be moused over
	 * @param driver  that containt web Element
	 */
	public void mouseOver(WebElement element, long waitTime) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build();
		pause(waitTime);
	}

	/**
	 * Mouse hover by Javascript
	 * 
	 * @author hanv
	 * @date 06-Nov-2013
	 * @param targetElement Web Element that will be moused over
	 * @param driver        play a role like website manager
	 */
	public void mouseHoverByJavascript(WebElement targetElement) {
		String argu1 = "var evObj = document.createEvent('MouseEvents');";
		String argu2 = "evObj.initMouseEvent('mouseover',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);";
		String argu3 = "arguments[0].dispatchEvent(evObj);";
		String javascript = argu1 + argu2 + argu3;
		((JavascriptExecutor) driver).executeScript(javascript, targetElement);
	}

	/**
	 * Double -click on a Web Element
	 * 
	 * @author hanv
	 * @date 023-Octorber-2014
	 * @param element Web Element that will be moused over
	 * @param driver  that containt web Element
	 */
	public void doubleClick(WebElement element, long waitTime) {
		Actions action = new Actions(driver);
		action.doubleClick(element).build().perform();
		pause(waitTime);

	}

	/**
	 * Right -click on a Web Element
	 * 
	 * @author hanv
	 * @date 023-Octorber-2014
	 * @param element Web Element that will be righ- click on on
	 * @param driver  that containt web Element
	 */
	public void rightClick(WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element);

	}

	/**
	 * Get message from pop-up alert
	 * 
	 * @author hanv
	 * @date 23-Octorber-2014
	 * @param driver that contains pop-up alert
	 * @return a String about message from pop-up
	 */
	public String getPopupMessage() {
		String message = null;
		try {
			Alert alert = driver.switchTo().alert();
			message = alert.getText();

		} catch (Exception e) {
			message = null;
		}
		System.out.println("message" + message);
		return message;
	}

	/**
	 * Press Cancel button from pop-up alert
	 * 
	 * @author hanv
	 * @date 23-Octorber-2014
	 * @param driver that contains pop-up alert
	 * @return void
	 */
	public void cancelPopupMessage() {

		Alert alert = driver.switchTo().alert();

		alert.dismiss();

	}

	/**
	 * Press OK button from pop-up alert
	 * 
	 * @author hanv
	 * @date 023-Octorber-2014
	 * @param driver that contains pop-up alert
	 * @return void
	 */
	public void acceptPopupMessage() {

		Alert alert = driver.switchTo().alert();

		alert.accept();

	}

	/**
	 * Select Radio button or select CheckBox
	 * 
	 * @author hanv
	 * @date 023-Octorber-2014
	 * @param driver               that contains radio buttons
	 * @param radioCheckboxLocator which locates radio button or a CheckBox
	 * @param value                of radio button or CheckBox
	 * @return void
	 */
	public void selectRadio(By radioCheckboxLocator, String value) {
		List<WebElement> list = (List<WebElement>) driver.findElements(radioCheckboxLocator);
		for (WebElement element : list) {
			if (element.getAttribute("value").equals(value)) {
				element.click();

			}
		}
	}

	/**
	 * Selecting Dropdown
	 * 
	 * @author hanv
	 * @date 023-Octorber-2014
	 * @param driver          that contains radio buttons
	 * @param dropdownLocator locates a DropDown
	 * @param value           of a Dropdown
	 * @return void
	 */
	public void selectDropdown(By dropdownLocator, String value) {
		Select select = new Select(driver.findElement(dropdownLocator));
		select.selectByVisibleText(value);
	}

	/**
	 * Get Text from a Web Element
	 * 
	 * @author hanv
	 * @date 023-Octorber-2014
	 * @param driver         that contains Web Element
	 * @param elementLocator locates a Web Element
	 * @return a Text of Web Element
	 */
	public String getText(By elementLocator) {
		String value = null;
		WebElement element = driver.findElement(elementLocator);

		if (element != null) {
			value = element.getText();
		} else {
			value = "this element is NULL !";
		}
		return value;
	}

	/**
	 * zoom in page
	 * 
	 * @param solanZoom ,solanZoom in integer
	 * @author hanv
	 * 
	 * @return void
	 */

	public void zoomIn(int solanZoom) {
		WebElement html = driver.findElement(By.tagName("html"));
		for (int i = 0; i <= solanZoom; i++) {
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.ADD));
		}

		/*
		 * new Actions(driver) .sendKeys(html, Keys.CONTROL, Keys.ADD, Keys.NULL)
		 * .perform();
		 */

	}

	/**
	 * zoom out page
	 * 
	 * @param solanZoom ,solanZoom in integer
	 * @author hanv
	 * 
	 * @return void
	 */

	public void zoomOut(int solanZoom) {
		WebElement html = driver.findElement(By.tagName("html"));
		for (int i = 0; i <= solanZoom; i++) {
			html.sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
		}
	}

	/**
	 * maximize Window
	 * 
	 * @author hanv
	 * 
	 * @return void
	 */

	public static void phongToCuaSoTrinhDuyet() {
		driver.manage().window().maximize();
	}

	/**
	 * Wait for Element or Website is displayed or loaded on Website
	 * 
	 * @author lanht
	 * @param i  ,time in second
	 * @param by web element located by By class
	 * @return void
	 */

	public void waitForElementPresent(int i, By by) throws InterruptedException {
		(new WebDriverWait(driver, i)).until(ExpectedConditions.presenceOfElementLocated(by));
	}

	/**
	 * To wait for smth loading
	 * 
	 * @author hanv
	 * @param time
	 */
	public void pause(long time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	/**
	 * Wait for Element to be clickable
	 * 
	 * @author hanv
	 * @param i  time
	 * @param by web element located by By class
	 * @return void
	 */
	public void waitForElementClickable(long i, By by) {
		WebDriverWait wait = new WebDriverWait(driver, i);
		wait.until(ExpectedConditions.elementToBeClickable(by));
	}

	/**
	 * Get link from a Web Element
	 * 
	 * @author hanv
	 * @date 2015-May-27
	 * @param elementLocator locates a Web Element
	 * @return href attribute of Web Element
	 */
	public String getlink(By elementLocator) {
		String value = null;
		WebElement element = driver.findElement(elementLocator);

		if (element != null) {
			value = element.getAttribute("href");
		} else {
			value = "this element don't have link!";
		}
		return value;
	}

	/**
	 * Check a link exist or NOT
	 * 
	 * @author hanv
	 * @date 2015-May-27
	 * @param urlname
	 * @return boolean
	 */

	public static boolean linkExists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con = (HttpURLConnection) new URL(URLName).openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Check a link exist or NOT
	 * 
	 * @author hanv
	 * @date 2016-Jan-26
	 * @param URL
	 * 
	 */

	public boolean checkLinkDie(String URL) {

		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(URL);
		try {
			HttpResponse response = client.execute(request);
			// verifying response code and The HttpStatus should be 200 if not,
			// increment invalid link count
			//// We can also check for 404 status code like
			// response.getStatusLine().getStatusCode() == 404
			if (response.getStatusLine().getStatusCode() != 200)
				return false;
			else {
				return true;
			}
		}

		catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * wait For ElementVisible on page
	 * 
	 * @param i  , time in second
	 * @param by , by of By class
	 * @author hanv
	 * 
	 */

	public void waitForElementVisible(int i, By by) throws InterruptedException {
		(new WebDriverWait(driver, i)).until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	/**
	 * wait for Page finishing loading
	 * 
	 * @author hanv
	 * 
	 */
	public void waitForPageLoaded() {

		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};

		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			wait.until(expectation);
		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}
	}
	
	/**
	 * Click on element by javascript
	 * 
	 * @author hanv
	 * @param by , by of By Class
	 * 
	 */
	public void clickByJavaScript(By by) {
		String script = "arguments[0].click();";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement someElem = driver.findElement(by);
		js.executeScript(script, someElem);
	}

	/**
	 * Click on element by javascript
	 * 
	 * @author hanv
	 * @param xpath    , xpath of Add (Th�m) menu
	 * 
	 * @param attName  , attribute of Menu element
	 * 
	 * @param attValue , value of attribute of Menu element
	 */
	public void clickByJS(By xpath, String attName, String attValue) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// WebElement element = driver.findElement(By.xpath(xpath));
		WebElement element = driver.findElement(xpath);
		js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2]);", element, attName, attValue);
	}

	/**
	 * Get on element by javascript ,
	 * 
	 * @author hanv
	 * @param locator , locator of By class
	 * 
	 * @param attName , attribute of Menu element
	 * 
	 */

	public static WebElement getElementByLocator(By locator) {
		TestLogger.info("Get element by locator: " + locator.toString());
		long startTime = System.currentTimeMillis();
		driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
		WebElement we = null;
		boolean unfound = true;
		int tries = 0;
		while (unfound && tries < 20) {
			tries += 1;
			TestLogger.info("Locating remaining time: " + (180 - (9 * (tries - 1))) + " seconds.");
			try {
				we = driver.findElement(locator);
				unfound = false; // FOUND IT
			} catch (StaleElementReferenceException ser) {
				TestLogger.info("ERROR: Stale element. " + locator.toString());
				unfound = true;
			} catch (NoSuchElementException nse) {
				TestLogger.info("ERROR: No such element. " + locator.toString());
				unfound = true;
			} catch (Exception e) {
				TestLogger.info(e.getMessage());
			}
		}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		TestLogger.info("Finished click after waiting for " + totalTime + " milliseconds.");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		return we;
	}

	/**
	 * Check an element visible or NOT
	 * 
	 * @author hanv
	 * @date 2015-Jun-6
	 * @param by web element located by By class
	 * @return boolean
	 */
	
	public boolean isElementPresent(By locatorKey) {
		try {
			driver.findElement(locatorKey);
			return true;
		} catch (org.openqa.selenium.NoSuchElementException e) {
			TestLogger.info("Element is not displayed !");
			TestLogger.info("*************************");
			return false;
		}
	}

	public boolean isElementVisible(final By by) throws InterruptedException {
		boolean value = false;

		if (driver.findElements(by).size() > 0) {
			value = true;
		}
		return value;
	}

	/**
	 * Share On FaceBook
	 * 
	 * @author hanv
	 * @param driver , is a browser
	 * @param user   , represents username of facebook account
	 * @param pass   , represents password of facebook account
	 */
	public void shareFacebook(String user, String pass) {

		clickByJavaScript(By.xpath("//a[contains(text(),'Facebook')]"));
		String parentWindow = driver.getWindowHandle();
		Set<String> handles = driver.getWindowHandles();
		for (String windowHandle : handles) {
			if (!windowHandle.equals(parentWindow)) {
				driver.switchTo().window(windowHandle);
				WebElement username = driver.findElement(By.xpath(".//*[@id='email']"));
				username.sendKeys(user);
				WebElement password = driver.findElement(By.xpath(".//*[@id='pass']"));
				password.sendKeys(pass);
				WebElement btnLogin = driver.findElement(By.xpath(".//*[@id='u_0_2']"));
				btnLogin.click();
			}
		}
	}

	/**
	 * Log Out Facebook
	 * 
	 * @author hanv
	 * @param none
	 * @return void
	 * @date 20-June-2015
	 * 
	 */

	public void LogOutFB() {
		TestLogger.info("Click drop-down menu");
		waitForPageLoaded();
		if (driver instanceof InternetExplorerDriver) {
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		clickByJavaScript(By.xpath(".//*[@id='pageLoginAnchor']"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestLogger.info("Click Log out");
		waitForPageLoaded();
		clickByJavaScript(By.xpath("//span[text()='Log Out']"));
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TestLogger.info("Confirm log out successfully");
		// Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='email']")).isDisplayed());

	}

	/**
	 * Log In Twiter page
	 * 
	 * @author hanv, update by hanv (date Jun-20-2015)
	 * 
	 * @param driver , is a browser
	 * @param user   , represents username of Twiter account
	 * @param pass   , represents password of Twiter account
	 */
	public void LogInTwitter(String user, String pass) {
		WebElement username = driver.findElement(By.xpath("//*[@id='username_or_email']"));
		username.sendKeys(user);
		WebElement password = driver.findElement(By.xpath("//*[@id='password']"));
		password.sendKeys(pass);

		clickByJavaScript(By.xpath("//input[@value='Log in and Tweet']"));
	}

	/**
	 * Log Out Twitter
	 * 
	 * @author hanv
	 * @param none
	 * @return void
	 * @date 20-June-2015
	 * 
	 */

	public void LogOutTw() {
		driver.navigate().refresh();
		TestLogger.info("Click drop-down menu");
		waitForElementClickable(300, By.xpath(".//*[@id='session']/h2/a/img"));
		driver.findElement(By.xpath(".//*[@id='session']/h2/a/img")).click();
		TestLogger.info("Click Log out");
		waitForElementClickable(300, By.xpath("//input[@value='Sign out']"));
		driver.findElement(By.xpath("//input[@value='Sign out']")).click();
		TestLogger.info("Confirm log out successfully");
		driver.findElement(By.xpath("//*[@id='username_or_email']"));
	}

	/**
	 * Share On FaceBook
	 * 
	 * @author hanv
	 * @param driver , is a browser
	 * @param user   , represents username of facebook account
	 * @param pass   , represents password of facebook account
	 */

	public void shareFacebook2(String user, String pass) {

		WebElement username = driver.findElement(By.xpath(".//*[@id='email']"));
		username.sendKeys(user);
		WebElement password = driver.findElement(By.xpath(".//*[@id='pass']"));
		password.sendKeys(pass);
		WebElement btnLogin = driver.findElement(By.xpath(".//*[@id='u_0_2']"));
		btnLogin.click();

	}

	/**
	 * click on Element
	 * 
	 * @author hanv
	 * @param element , a object belong to By class
	 * @return void
	 * @date 30-July-2015
	 */
	public void ClickElement(By by) {

		WebElement element = driver.findElement(by);
		Actions builder = new Actions(driver);
		builder.moveToElement(element).click().perform();

	}

	/**
	 * click on Element
	 * 
	 * @author hanv
	 * @param element , a object belong to WebElement class
	 * @return void
	 * @date 30-July-2015
	 */

	public void clickOnElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).click().perform();
	}

	/**
	 * Mouve Over on Element
	 * 
	 * @author hanv
	 * @param element
	 * @return void
	 * @date 30-July-2015
	 */

	public void mouveOverElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	public void waitForElementClickable(String xpath) {

		WebElement element = (new WebDriverWait(driver, 10))
				.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

	}

	public void setStatusTest(String PASSorFAIL, String ThongTinKetquaMongDoi) {
		if (PASSorFAIL.equalsIgnoreCase("PASS")) {
			TestLogger.info("PASSED : " + ThongTinKetquaMongDoi);
			Assert.assertTrue(true, ThongTinKetquaMongDoi);

		} else if (PASSorFAIL.equalsIgnoreCase("FAIL")) {
			TestLogger.info("FAILED :  " + ThongTinKetquaMongDoi);
			String homePaths = System.getProperty("user.home") + "/Desktop/screenShot/";
			TienIch.captureSnapshot(homePaths, "ScreenShotTestCaseFailed", 2);
			Assert.assertTrue(false, ThongTinKetquaMongDoi);
		}
		else
		{
			TestLogger.info("Please only use two Options : PASS or FAIL !");
		}
	}

	public boolean checkForcus(By by) {

		if (driver.findElement(by).equals(driver.switchTo().activeElement())) {
			return true;
		} else {

			return false;
		}
	}

	public void doiTrangTaiVe() {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Initially bellow given if condition will check ready state of page.
		if (js.executeScript("return document.readyState").toString().equals("complete")) {
			System.out.println("Page Is loaded.");
			return;
		}

		// This loop will rotate for 25 times to check If page Is ready after
		// every 1 second.
		// You can replace your value with 25 If you wants to Increase or
		// decrease wait time.
		for (int i = 0; i < 25; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			// To check page ready state.
			if (js.executeScript("return document.readyState").toString().equals("complete")) {
				break;
			}
		}
	}

	public WebElement FluentWaitClick(By by, int time, int loopTime) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(time, TimeUnit.SECONDS)
				.pollingEvery(loopTime, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return driver.findElement(by);
			}
		});
		return element;
	}// end

}
