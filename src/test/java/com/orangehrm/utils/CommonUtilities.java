package com.orangehrm.utils;

import org.openqa.selenium.By;

import com.orangehrm.base.Base;

public class CommonUtilities extends Base {
	
	
	public static boolean validLogin() throws Exception {
		
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Uname_Box"))).sendKeys(getTestDataFromMap("login", "username"));
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Pwd_Box"))).sendKeys(getTestDataFromMap("login", "password"));
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Ok_Button"))).click();
		
		boolean logoutlink=true;
		
		try 
		{
			 logoutlink = driver.findElement(By.xpath(getLocatorDataFromMap("Home", "Logout_Button"))).isDisplayed();
		}
		catch(Exception e) {
			logoutlink=false;
			e.printStackTrace();
			writeResultsToFile("validLogin", "fail");
			
		}
		return logoutlink;
	}

	public static boolean inValidLogin() throws Exception {
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Uname_Box"))).sendKeys(getTestDataFromMap("login", "Username1"));
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Pwd_Box"))).sendKeys(getTestDataFromMap("login", "Password1"));
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Ok_Button"))).click();
		
		boolean errormsg=false;
		
		try 
		{
			  errormsg = driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Er_Msg"))).isDisplayed();
		}
		catch(Exception e) {
			errormsg=false;
			e.printStackTrace();
			writeResultsToFile("invalidLogin", "fail");
			
		}
		return errormsg;
	}
	
	public static boolean inValidLoginWithParameter(String username, String password) throws Exception {
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Uname_Box"))).sendKeys(username);
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Pwd_Box"))).sendKeys(password);
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Ok_Button"))).click();
		
		boolean errormsg=false;
		
		try 
		{
			  errormsg = driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Er_Msg"))).isDisplayed();
		}
		catch(Exception e) {
			errormsg=false;
			e.printStackTrace();
			writeResultsToFile("invalidLogin", "fail");
			
		}
		return errormsg;
	}

	public static boolean loginWithDataProvider(String username, String password) throws Exception {
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Uname_Box"))).sendKeys(username);
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Pwd_Box"))).sendKeys(password);
		driver.findElement(By.xpath(getLocatorDataFromMap("Login", "Ok_Button"))).click();
		
		boolean logoutlink=true;
		
		try 
		{		
			 logoutlink = driver.findElement(By.xpath(getLocatorDataFromMap("Home", "Logout_Button"))).isDisplayed();
		}
		catch(Exception e) {
			logoutlink=false;
			e.printStackTrace();
			writeResultsToFile("validLogin", "fail");
			
		}
		return logoutlink;
		
	}
}
