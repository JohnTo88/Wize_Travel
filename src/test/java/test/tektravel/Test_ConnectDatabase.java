package test.tektravel;

import java.sql.Connection;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import DataBase.DiscoverData;
import Utils.TestLogger;

public class Test_ConnectDatabase {
	DiscoverData dataBase;

	@BeforeTest
	public void khoitao() {
		dataBase = new DiscoverData();
	}

	@Test
	public void testconnect() {
		Connection con = dataBase.connectDataBase(DiscoverData.URL, DiscoverData.USERNAME, DiscoverData.PASSWORD);
		TestLogger.info("Connect Successful");
	}
	
}
