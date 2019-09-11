package Utils;

import org.apache.log4j.Logger;
import org.apache.log4j.Level;

public class TestLogger {
	/**
	 * This class user to log everything which happens when run test cases .
	 * 
	 * @author hanv
	 * @param message which will be displayed to user when run test case
	 * @param level   which can be like Trace , debug , warn, error
	 * @return void
	 */

	public static void log(String message, Level level) {

		Throwable t = new Throwable();
		String logMessage = message;
		StackTraceElement[] elements = t.getStackTrace();
		//Logger logger = Logger.getLogger(PageLoginCustomerWize.class);
		String Filename = elements[2].getFileName();
		String sClassName = Filename.substring(0, Filename.length() - 5); // remove .java
		String sMethodName = elements[2].getMethodName();
		logMessage = String.format("[%-10s][%s] %s", sClassName, sMethodName, message);
		//logger.log(level, logMessage);

	}

	/**
	 * log everything when run testcase in TRACE level
	 * 
	 * @author hanv
	 * @param message which will be displayed to user when run test case
	 * @return void
	 */
	public static void trace(String message) {
		log(message, Level.TRACE);
	}

	/**
	 * log everything when run testcase in DEBUG level
	 * 
	 * @author hanv
	 * @param message which will be displayed to user when run test case
	 * @return void
	 */
	public static void debug(String message) {
		log(message, Level.DEBUG);
	}

	/**
	 * log everything when run testcase in INFO level
	 * 
	 * @author hanv
	 * @param message which will be displayed to user when run test case
	 * @return void
	 */
	public static void info(String message) {
//			log(message, Level.INFO);
		System.out.println(message);
	}

	/**
	 * log everything when run testcase in WARN level
	 * 
	 * @author hanv
	 * @param message which will be displayed to user when run test case
	 * @return void
	 */
	public static void warn(String message) {
		log(message, Level.WARN);
	}

	/**
	 * log everything when run testcase in ERROR level
	 * 
	 * @author hanv
	 * @param message which will be displayed to user when run test case
	 * @return void
	 */
	public static void error(String message) {
		log(message, Level.ERROR);
	}
}
