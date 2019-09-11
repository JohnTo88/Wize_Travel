package tektravel.webpages;

import org.openqa.selenium.WebDriver;

public class HomePage {
	private String URL_HOMEPAGE_TekTravel = "http://10.17.14.207:8282/";
	WebDriver driver; 
	
	public HomePage(WebDriver driver){
		this.driver = driver;
		driver.get(URL_HOMEPAGE_TekTravel);
	}
	
}
