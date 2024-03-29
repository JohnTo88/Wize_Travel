package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Utils.TestLogger;

public class DiscoverData {
	public static Connection con;
	public static String URL = "jdbc:sqlserver://10.17.14.206;databaseName=TravelTool_v2_Testing";
	public static String USERNAME = "traveltool";
	public static String PASSWORD = "Abc@12345";
	Statement smt;
	
	//Connect DataBase Testing
	public static Connection connectDataBase(String URL, String user, String password) {
	    try {
	        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    } catch (ClassNotFoundException e) {
	        e.printStackTrace();
	    }
	    System.out.println("Driver Loaded");
	    try {
	        con = DriverManager.getConnection(URL, user, password);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    TestLogger.info("Connection created");

	    return con;	    
	}
	
	//Get DATA Content Email from table Send Email
	public static String getColumnContentEmail(Connection con,String valueEmail) throws SQLException
	{
		Statement smt = con.createStatement();
		System.out.println("Content Email Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT TOP 10 * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail
				+ "'ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Content Email Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("ContentEmail");
		
		return data;
	}

	// Get DATA Subject from table Send Email
	public static String getColumnSubject(Connection con,String valueEmail) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Subject Email Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT TOP 10 * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail
				+ "'ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Subject Email Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("Subject");
		return data;
	}
	
	
	//Get DATA RoleID from table Send Email
	public static String getColumnRoleId(Connection con,String valueEmail) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Role ID Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT TOP 10 * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail
				+ "'ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Role ID founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("RoleId");
		return data;
	}
	
	//Get DATA Status from table Send Email
	public static String getStatus(Connection con, String valueEmail) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Status Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT TOP 10 * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail
				+ "'ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Status Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("Status");
		return data;
	}
	
	//Get DATA Email from table Send Email
	public static String getColumnEmail(Connection con, String valueEmail) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Status Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT TOP 10 * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail
				+ "'ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Email Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("Email");
		return data;
	}
	
	//Content Email from table Send Email
	public static String contentEmail(Connection con, String valueEmail, String RoleId) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Statement Created.");

		 ResultSet rs = smt.executeQuery("SELECT * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail + "' AND RoleId ='" + RoleId + "' ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Content Email Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("ContentEmail");
		return data;
	}

	//Get DATA Email Travel Alert
	public static String travelAlert(Connection con, String valueEmail, String RoleId) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Travel Alert Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail
				+ "' and ContentEmail like '%TRAVEL ALERT%' ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Travel Alert Email Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("ContentEmail");
		return data;
	}
	
	//Get DATA Email Urgent Request Alert
	public static String urgentRequestAlert(Connection con, String valueEmail, String RoleId) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Urgent Request Alert Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail
				+ "' and Subject like '%URGENT REQUEST ALERT%' and RoleId = '"+ RoleId +"'ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Urgent Request Alert Email Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("ContentEmail");
		return data;
	}
	
	//Get DATA Email Trip Complete
	public static String tripComplete(Connection con, String valueEmail, String RoleId) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Urgent Request Alert Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail
				+ "' and Subject like '%COMPLETE%' and RoleId = '"+ RoleId +"'ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Trip Complete Email Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("ContentEmail");
		return data;
	}
	
	//Get DATA Email Trip Canceled
	public static String cancelTrip(Connection con, String valueEmail, String RoleId) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Cancel Trip Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail
				+ "' and Subject like '%CANCEL%' and RoleId = '"+ RoleId +"' ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Trip Canceled Email Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("ContentEmail");
		return data;
	}
	
	//Get DATA Email Travel Arrangements
	public static String travelArrangements(Connection con, String valueEmail, String RoleId) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Cancel Trip Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator='" + valueEmail
				+ "' and Subject like '%arrangements%' and RoleId = '"+ RoleId +"' ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Travel Arrangements Email Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("ContentEmail");
		return data;
	}
	
	//Get DATA email Trip Assign
	public static String tripAssignment(Connection con, String valueEmail1, String valueEmail2) throws SQLException
	{
		Statement smt = con.createStatement();
		TestLogger.info("Trip Assign Statement Created.");

		ResultSet rs = smt.executeQuery("SELECT * FROM TravelTool_v2_Testing.dbo.SendEmail Where Creator= '"+valueEmail1+"' and Email= '"+valueEmail2+"' ORDER BY CreatedDate desc;");
		if (rs.next()) {
			TestLogger.info("Trip Assignment Email Founded !");
		} else {
			TestLogger.info("NO DATA !");
			return "";
		}
		//System.out.println("Query Executed");
		String data = rs.getString("ContentEmail");
		return data;
	}
	
	public static String getContentOnJsonElement(String dataFromColumnSubject, String nameOfWantedJsonElement)
	{
		TestLogger.info(dataFromColumnSubject);
		JsonParser k = new JsonParser();
	    JsonObject element =k.parse(dataFromColumnSubject).getAsJsonObject();
	    if (!element.isJsonNull())
	    {
	       System.out.println(element.get(nameOfWantedJsonElement)); 
	       return element.get(nameOfWantedJsonElement).toString();
	    }
	    else
	    {
	    	TestLogger.info("NO DATA !");
	    	return "";
	    }
			
	}
			
}