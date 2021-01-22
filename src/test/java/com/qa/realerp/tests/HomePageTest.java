package com.qa.realerp.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.realerp.base.BasePage;
import com.qa.realerp.pages.HomePage;
import com.qa.realerp.pages.LoginPage;
import com.qa.realerp.utils.Constants;

public class HomePageTest {
	
	WebDriver driver;
	Properties prop;
	BasePage basePage;
	LoginPage loginPage;
	HomePage homePage;
	
	@BeforeTest
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.init_properties();
		driver = basePage.init_driver(prop);
		loginPage = new LoginPage(driver);
		homePage = loginPage.verifyValidLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void verifyPageTitleTest() {
		String title = homePage.verifyPageTitle();
		//System.out.println(title);
		Assert.assertEquals(title, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority=2)
	public void verifyHRMLinkVisibilityTest() {
		boolean b =homePage.verifyHRMLinkVisibility();
		System.out.println(b);
		Assert.assertTrue(b);
	}
	
	@Test(priority=3)
	public void verifyNavigationToHRMPageTest() {
		homePage.navigateToHRMPage();
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
