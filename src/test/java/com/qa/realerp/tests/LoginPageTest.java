package com.qa.realerp.tests;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import com.qa.realerp.base.BasePage;
import com.qa.realerp.listeners.ListenersTestNG;
import com.qa.realerp.pages.LoginPage;
import com.qa.realerp.utils.Constants;
import com.qa.realerp.utils.ExcelUtil;

//@Listeners(ListenersTestNG.class)
public class LoginPageTest {
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
	}
	
	@Test(priority=1, enabled=true)
	public void verifyPageTitleTest() {
		String title = loginPage.verifyPageTitle();
		basePage.captureScreenshot();
		//System.out.println(title);
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Test(priority=2, enabled=true, dependsOnMethods = "verifyPageTitleTest")
	public void verifyForgotPasswordLinkTest() {
		boolean b =loginPage.verifyForgotPasswordLink();
		basePage.captureScreenshot();
		System.out.println(b);
		Assert.assertTrue(b);
	}
	
	@DataProvider
	public Object[][] getInvalidLoginTestData() {
		Object [][] data = ExcelUtil.getTestData("login");
		return data;		
	}
	
	
	@Test(enabled=false, priority=3, dataProvider = "getInvalidLoginTestData")
	public void verifyInValidLogin(String uname, String pwd) throws InterruptedException {
		Assert.assertEquals(loginPage.verifyInvalidLogin(uname, pwd), "Wrong User ID/Password");
		Thread.sleep(2000);
		basePage.captureScreenshot();
		Thread.sleep(2000);
	}
	
	
	@Test(priority=4, enabled=true)
	public void verifyValidLoginTest() {
		loginPage.verifyValidLogin(prop.getProperty("username"), prop.getProperty("password"));
		basePage.captureScreenshot();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
	
	
	
	
	
	

}
