package com.qa.realerp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.realerp.base.BasePage;
import com.qa.realerp.utils.ElementUtil;

public class LoginPage extends BasePage {
	
	private WebDriver driver;
	//private ElementUtil elementUtil;
	
	//By Locators:
	
	By username = By.id("userid");
	By password = By.id("pwd");
	By loginBtn = By.xpath("//input[@value='LOGIN']");
	By forgotPasswordLink = By.xpath("//a[text()='FORGOT YOUR PASSWORD']");
	By alreadyLoggedIn = By.xpath("//a[text()='Click']");
	By invalidLoginText = By.xpath("//div[text()='Wrong User ID/Password']");
	
	
	//Login Page Class Constructor
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		elementUtil=new ElementUtil(this.driver);
	}
	
	//Page Actions:
	
	public String verifyPageTitle() {
//		String title = driver.getTitle();
//		System.out.println(title);
//		return title;
		String title = elementUtil.doGetTitle();
		System.out.println(title);
		return title;
	}
	
	public boolean verifyForgotPasswordLink() {
		//return driver.findElement(forgotPasswordLink).isDisplayed();
		
		return elementUtil.doIsDisplayed(forgotPasswordLink);
	}
	
	public String verifyInvalidLogin(String uname, String pwd){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(username).clear();
		elementUtil.doSendKeys(username, uname);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.findElement(password).clear();
		elementUtil.doSendKeys(password, pwd);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//JavascriptExecutor js = ((JavascriptExecutor) driver);
		//js.executeScript("arguments[0].click();", loginBtn);
		driver.findElement(loginBtn).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String invalidLoginHeaderText= driver.findElement(invalidLoginText).getText();
		return invalidLoginHeaderText;
	}
	
	public HomePage verifyValidLogin(String username, String password) {
	//	driver.findElement(this.username).sendKeys(username);
	//	driver.findElement(this.password).sendKeys(password);
	//	driver.findElement(loginBtn).click();
		
		elementUtil.doSendKeys(this.username, username);
		elementUtil.doSendKeys(this.password, password);
		elementUtil.doClick(loginBtn);
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String text = driver.findElement(alreadyLoggedIn).getText();
		if(text.equals("Click")) {
			driver.findElement(alreadyLoggedIn).click();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//driver.findElement(this.username).sendKeys(username);
			//driver.findElement(this.password).sendKeys(password);
			//driver.findElement(loginBtn).click();
			
			elementUtil.doSendKeys(this.username, username);
			elementUtil.doSendKeys(this.password, password);
			elementUtil.doClick(loginBtn);
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return new HomePage(driver);
		} else {
			return new HomePage(driver);
		}
	}

}
