package com.qa.realerp.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.qa.realerp.utils.JavaScriptUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptUtilsTest {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://realerp.puremindz.info/");
		
		WebElement loginBtn = driver.findElement(By.xpath("//input[@value='LOGIN']"));
		
		JavaScriptUtil ju = new JavaScriptUtil(driver);
		String title = ju.getTitleByJS();
		System.out.println(title);
		
		Thread.sleep(2000);
		
//		ju.clickElementByJS(loginBtn);
//		
//		Thread.sleep(2000);

//		ju.drawBorderByJS(loginBtn);
		
		ju.flash(loginBtn);
		
		ju.clickElementByJS(loginBtn);
		

	}

}
