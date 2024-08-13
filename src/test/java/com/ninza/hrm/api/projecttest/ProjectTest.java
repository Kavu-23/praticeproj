package com.ninza.hrm.api.projecttest;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.mysql.cj.jdbc.Driver;
import com.ninza.hrm.APIBaseClass.BaseClassApi;
import com.ninza.hrm.api.endPoints.IEndPoint;
import com.ninza.hrm.api.genericUtility.DataBaseUtility;
import com.ninza.hrm.api.genericUtility.JavaUtility;
import com.ninza.hrm.api.genericUtility.PropertyFileUtility;
import com.ninza.hrm.api.pojoClass.ProjectPojoTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;

public class ProjectTest extends BaseClassApi{
	 
	 
	 ProjectPojoTest pObj;
	@Test
	public void createProjectTest() throws Throwable {
   
		//String BaseUri =fLib.getDataFromPropertiesFile("BASEUri");
		
          String expSucMsg = "Successfully Added";
           String ProjectName = "Pretty"+jLib.getRandomNum();
//create Object to POJO class
       pObj= new ProjectPojoTest(ProjectName, "Peter", 20, "On going");
      
      //verify the project Name in API Layer
      Response resp = given()
      .spec(specReqObj)
      .body(pObj)
      .when()
      .post(IEndPoint.ADD_PROJ);
      resp.then()
      .assertThat().statusCode(201)
      .assertThat().time(Matchers.lessThan(3000L))
      .spec(specRespObj)
      .log().all();
      
      //capture the data from response
      String actMsg = resp.jsonPath().get("msg");
      Assert.assertEquals(expSucMsg, actMsg);
      
      
      //verify the project Name in DataBase Layer
     
      boolean flag =dbLib.executeQueryVerifyAndGetData("select * from project", 4, ProjectName);
      Assert.assertTrue(flag,"Project in DB is not verified");
      
	  }

	
	@Test(dependsOnMethods = "createProjectTest")
	public void createDuplicateProjectTest() throws Throwable {
		//String BaseUri =fLib.getDataFromPropertiesFile("BASEUri");  
		
		given()
	      .spec(specReqObj)
	      .body(pObj)
	      .when()
	      .post(IEndPoint.ADD_PROJ)
	      .then()
	      .assertThat().statusCode(409)
	      .assertThat().time(Matchers.lessThan(3000L))
	      .spec(specRespObj)
	      .log().all();
	}
}
