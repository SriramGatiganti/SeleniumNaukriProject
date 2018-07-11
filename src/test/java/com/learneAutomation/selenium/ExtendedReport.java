package com.learneAutomation.selenium;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtendedReport {
    @Test
	public static void Report() throws Exception {
		
		
		ExtentHtmlReporter reporter=new ExtentHtmlReporter("E:\\Softwares\\SeleniumProjects\\com.learneAutomation.selenium\\ExtendReport\\NaukriTest.html");
	    
		ExtentReports extent=new ExtentReports();
		
		extent.attachReporter(reporter);
		
		ExtentTest logger=extent.createTest("Close browsers");
		
		logger.log(Status.PASS,"Pass");
		logger.log(Status.FAIL,"Fail");
		logger.fail("Failed because of",MediaEntityBuilder.createScreenCaptureFromPath("E:\\Softwares\\ExntentScreenShot.png").build());
		extent.flush();
		
		

	}

}
