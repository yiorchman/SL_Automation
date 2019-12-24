package com.sl.helper;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.sl.util.readProperties;

public class Login {
	
	
	//This Function make a simple assertion at the end to check if we got good login
	
	@Test
	public void LoginSL() throws IOException, InterruptedException{
		//got user and password from a property file - not to hardcode credentials and configurations
		//In case password changes, it has to be changed in the property file
		readProperties readuser= new readProperties();
		String user = readuser.getData("usr");
		String pwd =  readuser.getData("pwd");
		String url =  readuser.getData("url");
		String drvrChrm =  readuser.getData("drvrChrm");
		System.setProperty("webdriver.chrome.driver",drvrChrm);
		WebDriver driver = new ChromeDriver();
		
		//Open SwimLane webPage
		driver.get(url);
		
		driver.manage().window().maximize();
		
		//it is handled the "Control+shift+O", to remove the alert of small screen
		String selectAll = Keys.chord(Keys.CONTROL, Keys.SHIFT,"o");
		driver.findElement(By.tagName("html")).sendKeys(selectAll);
					
		//Wait until the controls load
		Thread.sleep(10000);
		driver.findElement(By.id("input-1")).sendKeys(user);
		driver.findElement(By.id("input-2")).sendKeys(pwd);
		
		driver.findElement(By.xpath("//*[@id=\"login\"]/div/button")).click();
		
		Thread.sleep(10000);
		//It is used xpath because we are finding an html control without any significant attribute
		String Compare = driver.findElement(By.xpath("/html/body/div[1]/navigation-bar/ngx-nav-menu/div/div[2]/div/span")).getText();
		
		//Compare the html text with the expected result.
		Assert.assertEquals(Compare, "QAANALYST");
		driver.close();
	}
	
	//This Function make a simple assertion at the end to check if we got bad login
	@Test
	public void LoginSLFail() throws IOException, InterruptedException
	{
		//got bad user and password from a property file - not to hardcode credentials and configurations
		
		readProperties readuser= new readProperties();
		String buser = readuser.getData("busr");
		String bpwd =  readuser.getData("bpdw");
		String url =  readuser.getData("url");
		String drvrChrm =  readuser.getData("drvrChrm");
		System.setProperty("webdriver.chrome.driver",drvrChrm);
		WebDriver driver = new ChromeDriver();
		
		driver.get(url);
		
		driver.manage().window().maximize();
		//it is handled the "Control+shift+O", to remove the alert of small screen	
		String selectAll = Keys.chord(Keys.CONTROL, Keys.SHIFT,"o");
		driver.findElement(By.tagName("html")).sendKeys(selectAll);
		
		
		Thread.sleep(10000);
		driver.findElement(By.id("input-1")).sendKeys(buser);
		driver.findElement(By.id("input-2")).sendKeys(bpwd);
		
		
		driver.findElement(By.xpath("//*[@id=\"login\"]/div/button")).click();
		Thread.sleep(10000);
		
		String message = driver.findElement(By.xpath("/html/body/div[1]/div/ui-view/ui-view-ng-upgrade/ui-view/ng-component/div/div[2]/div/div[2]/div[1]")).getText();
	
		//it is made an assertion to check if we got the error message of Login Failed.
		Assert.assertEquals(message, "Login failed.");
		
		
		driver.close();
		
	}

}
