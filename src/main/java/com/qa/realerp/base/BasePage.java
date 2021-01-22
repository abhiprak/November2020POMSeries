package com.qa.realerp.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.realerp.utils.ElementUtil;
import com.qa.realerp.utils.JavaScriptUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	
	WebDriver driver;
	Properties prop;
	public ElementUtil elementUtil;
	public JavaScriptUtil javascriptUtil;
	
	/**
	 * This method is used to initialize the WebDriver, as per the choice of browser provided.
	 * @param browserName
	 * @return
	 */
	
	public WebDriver init_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		} else if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		} else {
			System.out.println("Please provide a valid browser name.");
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		return driver;
	}
	
	
	/**
	 * This method is used to initialize the properties from the config.properties file.
	 * @return
	 */
	
	public Properties init_properties() {
		prop = new Properties();
		String path = null;
		String env = null;
		
		
		try {
			env=System.getProperty("env");
			if(env.equalsIgnoreCase("qa")){
				path = "./src/main/java/com/qa/realerp/config/qa_config.properties";
			} else if(env.equalsIgnoreCase("stg")){
				path = "./src/main/java/com/qa/realerp/config/stg_config.properties";
			}
		} catch (Exception e1) {
				path = "./src/main/java/com/qa/realerp/config/config.properties";
		} 
		
		
		try {
			FileInputStream ip = new FileInputStream(path);
	//		FileInputStream ip = new FileInputStream("./src/main/java/com/qa/realerp/config/config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
		
	}
	
	
	public void captureScreenshot() {
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        //Copy the file to a location and use try catch block to handle exception
        try {
            FileUtils.copyFile(screenshot, new File("C:\\Users\\dell\\eclipse-workspace\\November2020POMSeries\\Screenshots\\screenshot" +System.currentTimeMillis()+ ".png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
	}
	

}
