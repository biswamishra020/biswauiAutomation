package com.ea.framework.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {

	private static ExtentReports extent;
	public static ExtentTest test;
	private ExtentReport()
	{

	}

	public static void initReports()
	{
		if(Objects.isNull(extent)) {
			extent = new ExtentReports();
			ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
			extent.attachReporter(spark);
			spark.config().setTheme(Theme.STANDARD);
			spark.config().setDocumentTitle("GTL Report");
			spark.config().setReportName("GTL UI Automation Report");
		}
	}

	public static void flushReports() throws IOException {
		if(Objects.nonNull(extent)){
        extent.flush();}
		Desktop.getDesktop().browse(new File("index.html").toURI());
	}

	public static void createTest(String testcaseName)
	{
		test=extent.createTest(testcaseName);
	}
}
