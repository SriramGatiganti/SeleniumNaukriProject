package com.learneAutomation.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {

	public WebDriver driver;
	
	//intilaising chrome driver
	@BeforeClass
	public  void StartBrowser () 
	{
		System.out.println("Driver Started");
		System.setProperty("webdriver.chrome.driver","E:\\Softwares\\chromedriver_win32\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.naukri.com/");
		driver.manage().window().maximize();
		
		
	}

	//Quite webdriver
	@AfterClass
	public void CloseDriver() 
	{
		System.out.println("Driver Clsed Started");
		driver.close();
		driver.quit();
	}
}
