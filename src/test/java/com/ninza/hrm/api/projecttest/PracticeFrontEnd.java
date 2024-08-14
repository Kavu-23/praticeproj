package com.ninza.hrm.api.projecttest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class PracticeFrontEnd {

	@Test
	public void createProject() throws Throwable {
		
		WebDriver driver= new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		driver.get("http://49.249.28.218:8091");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys("pretty4843");
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("peter");
		
		WebElement ele= driver.findElement(By.xpath("//input[@name='createdBy']/parent::div[@class='form-group']/following-sibling::div[@class='form-group']/child::select"));
		Select sel= new Select(ele);
		sel.selectByVisibleText("1");
		
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();
		
	}
}
