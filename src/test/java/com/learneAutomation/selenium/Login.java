package com.learneAutomation.selenium;

import java.util.ArrayList;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Login extends BaseClass {

	//Closing unwanted popups and windows


		public void CloseWindowsAndPoups() throws Exception {
			System.out.println("CloseWindowsAndPoups Started");
			String parentwindow=driver.getWindowHandle();
			System.out.println("Parentwindow is"+parentwindow);
	       	Set<String> Allwindows=driver.getWindowHandles();
			System.out.println("child windows ids are"+Allwindows);
			ArrayList <String> tabs=new ArrayList<String>(Allwindows);
			int size=tabs.size();
			System.out.println("Total Tabs are "+size);
			for (String child:tabs)
			{
				if (!parentwindow.equalsIgnoreCase(child))
				{
					driver.switchTo().window(child).close();;
				}
			}
	        driver.switchTo().window(parentwindow);
			Thread.sleep(9000);
			String popupwindow=driver.getWindowHandle();
			System.out.println("New popup window id is "+popupwindow);
			driver.switchTo().window(popupwindow);
			driver.findElement(By.id("block")).click();
			driver.switchTo().window(parentwindow);
		}
		
		//Login application
		@Test (priority=2)
		public void LogInApplication() throws Exception {
			System.out.println("LogInApplication Started");
			
			ExcelFunctions config= new ExcelFunctions("E:\\Softwares\\SeleniumProjects\\FirstSeleniumProject\\TestData\\TestData.xlsx");
			
			System.out.println("Excel Path"+config);
			
			String strUserName=config.getdata(0,1,0);
			String strPassWord=config.getdata(0,1,1);

			System.out.println("UserName and Password"+strUserName+" and "+strPassWord);
			
			
			driver.findElement(By.xpath("//*[@id=\"login_Layer\"]/div")).click();
			String LoginWindow=driver.getWindowHandle();
			System.out.println("LoginWindow id is "+LoginWindow);
			
//			HashMap<String,String> data= new HashMap<String,String>();
//			data.put("100","sriram.gatiganti@gmail.com");
//			data.put("200","Gsunny@1");
			
//			String strUserName=data.get("100");
//			String strPassWord=data.get("200");
			driver.findElement(By.xpath("//*[contains(@name,'email')]")).sendKeys(strUserName);
			driver.findElement(By.xpath("//*[contains(@name,'PASSWORD')]")).sendKeys(strPassWord);
			
			driver.findElement(By.xpath("//*[contains(@name,\"PASSWORD\")]//following::div[2]")).click();
			Thread.sleep(2000);
			String SearchText=driver.findElement(By.xpath("//*[contains(@id,'search-jobs')]/div[1]")).getText();
	        System.out.println(SearchText);
	        
	        config.putdata(0,1,2,SearchText);
	        
			Assert.assertEquals(SearchText, "Search Jobs");
			
			}
	   
		//Update profile
		@Test (priority=3)
		public void updateProfile() throws Exception {
			driver.findElement(By.xpath("//*[@class=\"btn btn-block btn-large white-text\"]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//*[@id=\"lazyResumeHead\"]/div/div/div/div[1]/span[2]")).click();
			String parentwindow1=driver.getWindowHandle();
			System.out.println("Parentwindow is"+parentwindow1);
			driver.switchTo().window(parentwindow1);
			driver.findElement(By.xpath("//*[@id=\"resumeHeadlineTxt\"]")).clear();
			driver.findElement(By.xpath("//*[@id=\"resumeHeadlineTxt\"]")).sendKeys("Working in Infosys as Technical Test Lead having 7 Years of Experience as a Software Tester");
			driver.findElement(By.xpath("/html/body/div[5]/div[5]/div[2]/form/div[3]/div/button")).click();						
			String SavedText=driver.findElement(By.xpath("//*[@id=\"lazyResumeHead\"]/div/div/div/div[2]/div/div")).getText();											  
			Assert.assertEquals(SavedText, "Working in Infosys as Technical Test Lead having 7 Years of Experience as a Software Tester"); 
		}
		@Test (priority=4)
		public void LogOutApplication() throws Exception {
		      System.out.println("Logout is started executing");
			  //Create obeject on Action Class
			  Actions builder=new Actions(driver);
			  WebElement MyNouk= driver.findElement(By.xpath("/html/body/div[1]/div/div/ul[2]/li[2]/a/div[2]"));
			  builder.moveToElement(MyNouk).build().perform();
			  WebElement Logout=driver.findElement(By.xpath("/html/body/div[1]/div/div/ul[2]/li[2]/div/ul[2]/li[5]/a"));
			  Thread.sleep(2000);
			  Logout.click();
			  System.out.println("Logout button is sucessfully clicked");

		 
		  
		  
		}
		
		ExtentReports extent;
		ExtentTest logger;

		
		@SuppressWarnings("unused")
		@BeforeMethod
		public static void Report() throws Exception 
		{
								
				ExtentHtmlReporter reporter=new ExtentHtmlReporter("E:\\Softwares\\SeleniumProjects\\com.learneAutomation.selenium\\ExtendReport\\NaukriTest.html");
			    
				ExtentReports extent=new ExtentReports();
				
				extent.attachReporter(reporter);
				
				ExtentTest logger=extent.createTest("CloseWindowsAndPoups");
				
				extent.flush();
				
				
			}
		

		@AfterMethod
		public void ScreenshotForFailure(ITestResult result) throws Exception 
	    {
		
			if(ITestResult.FAILURE==result.getStatus())	
	    	{
	     		String temp=Utility.capturescreenshot(driver,"NaukriTest");
				
	     		logger.fail(result.getThrowable().getMessage(),MediaEntityBuilder.createScreenCaptureFromPath(temp+"screenshot.png").build());
				extent.flush();
	        }
	    	
	    }

}
