package Utils;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import web.controller.WebActions;

/**
 * This class supplies some static functions to user
 * 
 * @author hanv
 *
 */
public class TienIch {

	public WebDriver driver;

	/**
	 * Capture screenshot and save to Desktop/screenshot
	 * 
	 * @param folderPath Location of folder will be saved screen shot
	 * @param fileName   the file name beside random number
	 */

	public TienIch(WebDriver driver) {
		this.driver = driver;
	}
	
	

	public static void captureSnapshot(String folderPath, String fileName, int level) {
		// Prepare environment
		File screenShot = new File(folderPath);
		String testcaseName = new Throwable().getStackTrace()[level].getMethodName();
		BufferedImage image;
		Date dt = new Date();
		Calendar cal = GregorianCalendar.getInstance();
		cal.setTime(dt);
		String image_Name = (cal.get(Calendar.MONTH) + 1 + "-" + cal.get(Calendar.DAY_OF_MONTH) + "-"
				+ cal.get(Calendar.HOUR_OF_DAY) + "-" + cal.get(Calendar.MINUTE) + "-" + cal.get(Calendar.SECOND));

		String imageLocation = testcaseName + "_" + image_Name + "_" + fileName;
		imageLocation = folderPath + imageLocation + ".png";

		TestLogger.info("Take screen shot and save to : " + imageLocation);
		if (!screenShot.exists()) {
			TestLogger.info("The folder not exist, create new folder...");
			screenShot.mkdirs();
		}
		screenShot = new File(imageLocation);

		// Capture screen shot
		try {
			image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			try {
				ImageIO.write(image, "png", screenShot);
			} catch (IOException e) {
				TestLogger.error("Some error occours: IOException");
			}
		} catch (HeadlessException e) {
			TestLogger.error("Some error occours: HeadlessException");
		} catch (AWTException e) {
			TestLogger.error("Some error occours: AWTException");
		}
	}

	/**
	 * Get Absolute File Path
	 * 
	 * @author hanv
	 * @param file represent a File object
	 * @return a String , represent Absolute File Path of file
	 */
	public static String getAbsoluteFilePath(File file) {
		String path = null;
		if (file.exists()) {
			path = file.getAbsolutePath();
			System.out.println(path.toString());
		} else {
			System.out.println("this file is not avaiable ");
		}

		return path;
	}

	/**
	 * Change HTMLL attribute
	 * 
	 * @author hanv
	 * @param driver        represent Website Manager
	 * @param Id_of_element id of Web element
	 * @param oldAttribute  , old Attribute of Web element
	 * @param newAttribute  . new Attribute of web element
	 * @return void
	 */
	public void changeHTMLattribute(String Id_of_element, String oldAttribute, String newAttribute) {

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("document.getElementById (" + Id_of_element + ").setAttribute(" + oldAttribute + ","
				+ newAttribute + ")");

	}

	/**
	 * change HTML value
	 * 
	 * @author hanv
	 * @param driver        represent Website Manager
	 * @param id_of_element id of Web element
	 * @param newValue      . newValue of web element
	 * @return void
	 */
	public void changeHTMLvalue(String id_of_element, String newValue) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementByID (" + id_of_element + ").value=" + newValue);
	}

	/**
	 * get Current Date in format : yyyy/MM/dd HH:mm:ss
	 * 
	 * @author hanv
	 * @date 23-Oct-2014
	 */
	public static DateFormat getCurrentDateTime() {
		DateFormat datetime = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		System.out.println(datetime.format(cal.getTime()));
		return datetime;
	}

	/**
	 * Get Current Date in format : yyyy/MM/dd
	 * 
	 * @author hanv
	 * @date 23-Oct-2014
	 */
	public static DateFormat getCurrentDate() {
		DateFormat date = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		System.out.println(date.format(cal.getTime()));
		return date;
	}

	/**
	 * Get Current Day
	 * 
	 * @author hanv
	 * @date 23-Oct-2014
	 */
	public static DateFormat getCurrentDay() {
		DateFormat date = new SimpleDateFormat("dd");
		Calendar cal = Calendar.getInstance();
		System.out.println(date.format(cal.getTime()));
		return date;
	}

	/**
	 * Get Curren Month
	 * 
	 * @author hanv
	 * @date 23-Oct-2014
	 */
	public static DateFormat getCurrentMonth() {
		DateFormat date = new SimpleDateFormat("MM");
		Calendar cal = Calendar.getInstance();
		System.out.println(date.format(cal.getTime()));
		return date;
	}

	/**
	 * Get Current Year
	 * 
	 * @author hanv
	 * @date 23-Oct-2014
	 */
	public static DateFormat getCurrentYear() {
		DateFormat year = new SimpleDateFormat("yyyy");
		Calendar cal = Calendar.getInstance();
		System.out.println(year.format(cal.getTime()));
		return year;
	}

	/**
	 * Get Date time in format : day, month, year, hours, second, minutes:
	 * yyyy/MM/dd HH:mm:ss
	 * 
	 * @param format which need to conver
	 * @author hanv
	 * @date 23-Oct-2014
	 */
	public DateFormat getDatetime(String format) {
		try {
			DateFormat date = new SimpleDateFormat(format);
			if (format.equals("")) {
				System.out.println("Please input with this format yyyy/MM/dd HH:mm:ss");
			}
			Calendar cal = Calendar.getInstance();
			System.out.println(date.format(cal.getTime()));
			return date;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Please input with this format yyyy/MM/dd HH:mm:ss");
			return null;
		}

	}

	public static String taoRandomSovaChu(int baoNhieuKiTu) {

		return RandomStringUtils.randomAlphanumeric(baoNhieuKiTu);
	}

	public static String taoRandomSo(int tongSo) {

		return RandomStringUtils.randomNumeric(tongSo);
	}

	public static String taoRandomChu(int tongSoKiTu) {
		return RandomStringUtils.randomAlphabetic(tongSoKiTu);
	}

	public static String getNgayThangNamHienTaicuaMayTinh() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.valueOf(dtf.format(localDate));
	}

	public static String getNgayHienTaicuaMayTinh() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String ngaythangnam = dtf.format(localDate);
		String[] ngay = ngaythangnam.split("\\/");
		return ngay[0];
	}

	public static String getThangHienTaicuaMayTinh() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String ngaythangnam = dtf.format(localDate);
		String[] ngay = ngaythangnam.split("\\/");
		return ngay[1];
	}

	public static String getNamHienTaicuaMayTinh() {
		LocalDate localDate = LocalDate.now();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String ngaythangnam = dtf.format(localDate);
		String[] ngay = ngaythangnam.split("\\/");
		return ngay[2];
	}
	
	/**
	 * To get file from Resources folder
	 * 
	 * @param filename
	 * @return sourcepath folder path of resources needed
	 */
/*	public static String getResources(String filename) {
		URL resource = PageLoginCustomerWize.class.getProtectionDomain().getCodeSource().getLocation();
		String tr = resource.toString().replace("file:/", "").replace("%20", " ").replace("/", "\\\\").replace("target",
				"@");
		int l = tr.indexOf("@");
		String sourcepath = tr.substring(0, l).replace("@", "") + "src\\\\main\\\\resources\\\\" + filename;
		return sourcepath;
	}*/
	
	/**
	 * To get file from Resources folder
	 * 
	 * @param filename
	 * @return sourcepath folder path of resources needed
	 */
/*	public static String getResourcesTest(String filename) {
		URL resource = PageLoginCustomerWize.class.getProtectionDomain().getCodeSource().getLocation();
		String tr = resource.toString().replace("file:/", "").replace("%20", " ").replace("/", "\\\\").replace("target",
				"@");
		int l = tr.indexOf("@");
		String sourcepath = tr.substring(0, l).replace("@", "") + "src\\\\test\\\\resources\\\\" + filename;
		return sourcepath;
	}*/

}
