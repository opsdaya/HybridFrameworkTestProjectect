package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReport;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener {

	ExtentReports extentreports;
	ExtentTest extendTest;

	@Override
	public void onStart(ITestContext context) {

		extentreports = ExtentReport.generateExtentReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		extendTest = extentreports.createTest(result.getName());
		extendTest.log(Status.INFO, result.getName() + "Started Executing");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extendTest.log(Status.PASS, result.getName() + "got successfully executed");
		System.out.println(result.getName() + "got successfully executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		extendTest.addScreenCaptureFromPath(destinationScreenshotPath);
		extendTest.log(Status.INFO, result.getThrowable());
		extendTest.log(Status.FAIL, result.getName() + "got Failed");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		extendTest.log(Status.INFO, result.getThrowable());
		extendTest.log(Status.SKIP, result.getName() + "got Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extentreports.flush();
		
		//Open reports automatically
		String pathOfExtentReport = System.getProperty("user.dir") + "\\test-output\\ExtentReport\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
