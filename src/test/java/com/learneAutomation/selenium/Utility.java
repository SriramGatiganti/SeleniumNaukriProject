package com.learneAutomation.selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utility extends BaseClass {
	
	public static String capturescreenshot(WebDriver driver,String ScreenshotName) throws Exception {
		
		TakesScreenshot ts= (TakesScreenshot)driver;
		
		File Srcfl=ts.getScreenshotAs(OutputType.FILE);
		
		String Path=System.getProperty("user.dir")+"/ScreenShotsForFailure/"+System.currentTimeMillis();
		File dest=new File(Path);
		try {
			 FileUtils.copyFile(Srcfl, dest);
		}catch (IOException e)
		{
			System.out.println("Capture Failure"+e.getMessage());
		}
       
       return Path;
		
	  }


}
