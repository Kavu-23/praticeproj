package com.ninza.hrm.APIBaseClass;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.ninza.hrm.api.genericUtility.DataBaseUtility;
import com.ninza.hrm.api.genericUtility.JavaUtility;
import com.ninza.hrm.api.genericUtility.PropertyFileUtility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClassApi {

	public JavaUtility jLib = new JavaUtility();
	public PropertyFileUtility fLib = new PropertyFileUtility();
	public DataBaseUtility dbLib = new DataBaseUtility();
	public static RequestSpecification specReqObj;
	public static ResponseSpecification specRespObj;
	
	 @BeforeSuite
	 public void configBS() throws Throwable {
		 dbLib.getDbConnection();
		 System.out.println("=====connect to db====");
		 RequestSpecBuilder builder = new RequestSpecBuilder();
		 builder.setContentType(ContentType.JSON);
		// builder.setAuth(basic("username", "password"));
		// builder.addHeader("", "");
		 builder.setBaseUri(fLib.getDataFromPropertiesFile("BASEUri"));
		 specReqObj = builder.build();
		 
		 ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		 resBuilder.expectContentType(ContentType.JSON);
		 specRespObj=resBuilder.build();
	 } 
	 
	 @AfterSuite
		public void configAS() throws Throwable{
			dbLib.closeDbConnection();
			System.out.println("====disonnect to db=====");
		}	       
}
