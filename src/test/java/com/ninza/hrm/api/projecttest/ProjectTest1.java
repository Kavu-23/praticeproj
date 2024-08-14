package com.ninza.hrm.api.projecttest;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.ninza.hrm.APIBaseClass.BaseClassApi;
import com.ninza.hrm.api.endPoints.IEndPoint;
import com.ninza.hrm.api.genericUtility.JavaUtility;
import com.ninza.hrm.api.pojoClass.ProjectPojoTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class ProjectTest1 {

	
	 JavaUtility jLib = new JavaUtility();
	 
	@Test
	public void addSingleProjectWithCreatedTest() throws Throwable {
		String ProjectName = "Pretty"+jLib.getRandomNum();
		 //String expMsg = "Successfully Added";
		 
		JSONObject jObj = new JSONObject();
    	jObj.put("createdBy","Peter");
    	jObj.put("projectName",ProjectName);
    	jObj.put("status","Created");
        jObj.put("teamSize", 0);
        
	 Response resp=   given()
	    .contentType(ContentType.JSON)
	    .body(jObj)
	    .when()
	    .post("http://49.249.28.218:8091/addProject");
	    resp.then()
	    .assertThat().statusCode(201)
	    .assertThat().time(Matchers.lessThan(3000L))
	    .assertThat().contentType(ContentType.JSON)
	    .assertThat().body("msg",Matchers.equalTo("Successfully Added"))
	    
	    .log().all();
	    
	    String pId = resp.jsonPath().get("ProjectId");
	    resp.then().assertThat().body("ProjectId",Matchers.equalTo(pId));
	   
	    //Assert.assertEquals(expMsg, actMsg);
	}
	    
	    @Test
		public void addProjectWitONGoingStatusTest() throws Throwable {
			
	    	String ProjectName = "Pretty"+jLib.getRandomNum();
	    	 String expMsg = "Successfully Added";
	    	 
			JSONObject jObj = new JSONObject();
	    	jObj.put("createdBy","Peter");
	    	jObj.put("projectName",ProjectName);
	    	jObj.put("status","On Going");
	        jObj.put("teamSize", 0);
	        
		 Response resp=   given()
		    .contentType(ContentType.JSON)
		    .body(jObj)
		    .when()
		    .post("http://49.249.28.218:8091/addProject");
		    resp.then()
		    .assertThat().statusCode(201)
		    .assertThat().time(Matchers.lessThan(3000L))
		    .assertThat().contentType(ContentType.JSON)
		    .log().all();
		    
		    String actMsg = resp.jsonPath().get("msg");
		    Assert.assertEquals(expMsg, actMsg);
	    }
		    
		    @Test
			public void addProjectWithCompletedStatusTest() throws Throwable {
				
				String expMsg = "Successfully Added";
				String ProjectName = "Pretty"+jLib.getRandomNum();
				
				JSONObject jObj = new JSONObject();
		    	jObj.put("createdBy","Peter");
		    	jObj.put("projectName",ProjectName);
		    	jObj.put("status","Created");
		        jObj.put("teamSize", 0);
			 Response resp=   given()
			    .contentType(ContentType.JSON)
			    .body(jObj)
			    .when()
			    .post("http://49.249.28.218:8091/addProject");
			    resp.then()
			    .assertThat().statusCode(201)
			    .assertThat().time(Matchers.lessThan(3000L))
			    .assertThat().contentType(ContentType.JSON)
			    .log().all();
			    
			    String actMsg = resp.jsonPath().get("msg");
			    Assert.assertEquals(expMsg, actMsg);
	    }
		    
		    @Test(dependsOnMethods = "addSingleProjectWithCreatedTest")
			public void addDuplicateProjectTest() throws Throwable {
		    	String expMsg = "Successfully Added";
				String ProjectName = "Pretty"+jLib.getRandomNum();
				
				JSONObject jObj = new JSONObject();
		    	jObj.put("createdBy","Peter");
		    	jObj.put("projectName",ProjectName);
		    	jObj.put("status","Created");
		        jObj.put("teamSize", 0);
			Response resp=	given()
				.contentType(ContentType.JSON)
			    .body(jObj)
			    .when()
			    .post("http://49.249.28.218:8091/addProject");
			    resp.then()
			    .assertThat().statusCode(409)
			    .assertThat().time(Matchers.lessThan(3000L))
			    .assertThat().contentType(ContentType.JSON)
			    .log().all();
			}
		    
		    @Test
		    public void addProjectWithDuplicateID() {
		    	String expMsg="Pretty1774 Already Exists";
		    	JSONObject jObj = new JSONObject();
		    	jObj.put("createdBy","Peter");
		    	jObj.put("projectName", "Pretty1774");
		    	jObj.put("projectId","NH_PROJ_8078");
		    	jObj.put("status","created");
		        jObj.put("teamSize", 0);
		        
		        Response resp=   given()
		    		    .contentType(ContentType.JSON)
		    		    .body(jObj)
		    		    .when()
		    		    .post("http://49.249.28.218:8091/addProject");
		    		    resp.then()
		    		    .assertThat().statusCode(409)
		    		    .assertThat().time(Matchers.lessThan(3000L))
		    		    .assertThat().contentType(ContentType.JSON)
		    		    .log().all();
		    		    
		    		    String actMsg = resp.jsonPath().get("msg");
		    		    Assert.assertEquals(expMsg, actMsg);
		       }
		    
		    @Test
		    public void addProjectWithOutName() {
		    	JSONObject jObj = new JSONObject();
		    	jObj.put("createdBy","Peter");
		    	jObj.put("projectName", "Pretty"+jLib.getRandomNum());
		    	jObj.put("projectId","NH_PROJ_003");
		    	jObj.put("status","created");
		        jObj.put("teamSize", 10);
		    }
		    
		    
}
