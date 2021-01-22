package com.qa.realerp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	
private WebDriver driver;
	
	//By Locators:

	By homePageHeader = By.xpath("//span[text()='Dashboard']");
	By homePageText = By.xpath("//h3[text()='WELCOME TO PURE REAL ERP']");
	By hrmPageLink = By.xpath("(//a[text()='HRM'])[1]");

	//Home Page CLas Constructor

	public HomePage(WebDriver driver) {
		this.driver=driver;
	}
	
	//Page Actions:
	
	public String verifyPageTitle() {
		String title = driver.getTitle();
		System.out.println(title);
		return title;
	}
	
	public boolean verifyHRMLinkVisibility() {
		return driver.findElement(hrmPageLink).isDisplayed();
	}
	
	public void navigateToHRMPage() {
		driver.findElement(hrmPageLink).click();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
