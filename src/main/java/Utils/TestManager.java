package Utils;

import org.testng.Assert;

public class TestManager {
	public static void setStatusTest(String PASSorFAIL, String ThongTinKetquaMongDoi) {
		if (PASSorFAIL.equalsIgnoreCase("PASS")) {
			TestLogger.info("Result : " + ThongTinKetquaMongDoi);
			Assert.assertTrue(true, ThongTinKetquaMongDoi);
			
		} else if (PASSorFAIL.equalsIgnoreCase("FAIL")) {
			TestLogger.info("Result :  " + ThongTinKetquaMongDoi);
			String homePaths = System.getProperty("user.home") + "/Desktop/screenShot/";
			TienIch.captureSnapshot(homePaths, "ScreenShotTestCaseFailed", 2);
			Assert.assertTrue(false, ThongTinKetquaMongDoi);
		}
		else
		{
			TestLogger.info("Please only use two Options : PASS or FAIL !");
		}
	}
}
