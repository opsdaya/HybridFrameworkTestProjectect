package com.tutorialsninja.qa.utils;

import java.io.*;
import java.util.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReport {

	public static ExtentReports generateExtentReport() {

		ExtentReports extentReports = new ExtentReports();
		File extendReportFile = new File(
				System.getProperty("user.dir") + "\\test-output\\ExtentReport\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extendReportFile);
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Daya Test Automation Report");
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");

		extentReports.attachReporter(sparkReporter);
		Properties configPropfile = new Properties();
		try {
			File configPropFile = new File(System.getProperty("user.dir")
					+ "\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\config.properties");
			FileInputStream fisConfigProp = new FileInputStream(configPropFile);
			configPropfile.load(fisConfigProp);
		} catch (IOException e) {
			e.printStackTrace();
		}
		extentReports.setSystemInfo("Application URL", configPropfile.getProperty("url"));
		extentReports.setSystemInfo("Browser Name", configPropfile.getProperty("browserName"));
		extentReports.setSystemInfo("Email", configPropfile.getProperty("validEmail"));
		extentReports.setSystemInfo("Password", configPropfile.getProperty("validPassword"));
//		System.getProperties().list(System.out);
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		
		return extentReports;
		
	}

}
