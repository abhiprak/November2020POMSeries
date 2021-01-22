package com.qa.realerp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HRMPage {
	
private WebDriver driver;
	
	//By Locators:
	
	By username = By.id("userid");
	By password = By.id("pwd");
	By loginBtn = By.xpath("//input[@value='LOGIN']");
	By forgotPasswordLink = By.xpath("//a[text()='FORGOT YOUR PASSWORD']");
	By alreadyLoggedIn = By.xpath("//a[text()='Click']");
	
	
	//Login Page Class Constructor
	
	public HRMPage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Page Actions:

}
