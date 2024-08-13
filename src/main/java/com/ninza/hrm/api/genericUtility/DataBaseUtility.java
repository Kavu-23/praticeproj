package com.ninza.hrm.api.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {

	static Connection con=null;
	static ResultSet resultset =null;
	static PropertyFileUtility fLib = new PropertyFileUtility();
	
	public void getDbConnection() throws Throwable {
		
		try {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		
		con=DriverManager.getConnection(fLib.getDataFromPropertiesFile("DBUrl"), fLib.getDataFromPropertiesFile("DB_Username"), fLib.getDataFromPropertiesFile("DB_Password"));
		}catch(Exception e) {
			
		}
	}
	
	
	public void closeDbConnection() throws Throwable {
		try {
		con.close();
		}catch(Exception e) {
			
		}
	}
	
	public ResultSet executeSelectQuery(String query) throws Throwable {
		ResultSet result =null;
		try {
		Statement stat= con.createStatement();
		 result= stat.executeQuery(query);
		}catch(Exception e) {
			
		}
		return result;/// return in the form of table
	}
	
	public int executeNonSelectQuery(String query) {
		int result =0;
		try {
		Statement stat= con.createStatement();
		 result= stat.executeUpdate(query);
		}catch(Exception e) {
			
		}
		return result;///return in the form +-int
	}
	
	public  boolean  executeQueryVerifyAndGetData(String query, int coloumnIndex, String expectedData) throws Throwable {
    boolean flag = false;
             resultset = con.createStatement().executeQuery(query);
	  while(resultset.next()) {
		  if(resultset.getString(coloumnIndex).equals(expectedData)) {
			  flag = true;
			  break;
		  }
	  }
	  if(flag) {
		  System.out.println(expectedData +"====>data verified in data base table");
		  return true;
	  }else {
		  System.out.println(expectedData +"====>data not verified in data base table");
		  return false;
	  }
}
}
