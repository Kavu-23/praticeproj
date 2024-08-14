package com.ninza.hrm.api.employeetest;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import com.ninza.hrm.APIBaseClass.BaseClassApi;
import com.ninza.hrm.api.endPoints.IEndPoint;
import com.ninza.hrm.api.pojoClass.EmployeePojo;
import com.ninza.hrm.api.pojoClass.ProjectPojoTest;

public class EmployeeTest extends BaseClassApi{
	
	@Test
	public void addEmployeeTest() throws Throwable {
		
		//String BaseUri =fLib.getDataFromPropertiesFile("BASEUri");
		
				String projectName =  "Airtel"+jLib.getRandomNum();
				String userName = "Jack_"+jLib.getRandomNum();
				ProjectPojoTest pObj= new ProjectPojoTest("Airtel"+jLib.getRandomNum(), "David", 5, "created");
				 
				 given()
			      .spec(specReqObj)
			      .body(pObj)
			     .when()
			       .post(IEndPoint.ADD_PROJ)
			      .then()
			        .log().all();
			    
			      System.out.println("hi");
			      EmployeePojo empObj = new EmployeePojo("Software engineer", "23/11/1995", "abc@gmail.com", userName, 4, "9739891892", projectName, "ROLE_EMPLOYEE", userName);
			       given()
			    	      .spec(specReqObj)
			    	      .body(empObj)
			    	     .when()
			    	       .post(IEndPoint.ADD_EMP)
			               .then()
			               .spec(specRespObj)
			               .assertThat().statusCode(201)
			               .and()
			               .time(Matchers.lessThan(3000L))
			               .log().all();
			       
			       //verify employee name in database
			       
			       boolean flag =dbLib.executeQueryVerifyAndGetData("select * from employee", 5, userName);
			       Assert.assertTrue(flag,"Employee in DB is not verified");
			       Assert.assertTrue(flag,"Project in DB is not verified");
			       
			 	  }
	
	@Test
	public void addEmployeeWithoutEmailTest() throws Throwable {
		
		//String BaseUri =fLib.getDataFromPropertiesFile("BASEUri");
		        String projectName =  "Airtel"+jLib.getRandomNum();
				String userName = "Jack_"+jLib.getRandomNum();
			      
			      EmployeePojo empObj = new EmployeePojo("Software engineer", "23/11/1995", "", userName, 4, "9739891892", projectName, "ROLE_EMPLOYEE", userName);
			       given()
			    	      .spec(specReqObj)
			    	      .body(empObj)
			    	     .when()
			    	       .post(IEndPoint.ADD_EMP)
			               .then()
			               .spec(specRespObj)
			               .assertThat().statusCode(500)
			               .log().all();
			               //.log().all();
	}

}
