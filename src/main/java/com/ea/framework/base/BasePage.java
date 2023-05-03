package com.ea.framework.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.ea.framework.config.ConfigReader;
import com.ea.framework.config.Settings;
import com.ea.framework.report.ExtentReport;
import com.ea.framework.utilities.DatabaseUtil;
import com.ea.framework.utilities.ExcelUtil;
import com.ea.framework.utilities.LogUtil;
import com.ea.framework.utilities.ReportingUtil;
import cucumber.api.java.Before;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.IOException;

import static com.ea.framework.base.FrameworkInitialize.InitializeBrowser;

/**
 * Created by Karthik-PC on 11/23/2018.
 */
public abstract class BasePage extends Base {

    public BasePage(RemoteWebDriver driver) {
        super(driver);
    }

    public BasePage() {

    }

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite()
    {
        ExtentReport.initReports();
    }
    @BeforeClass(alwaysRun = true)
    public void initializedData() throws IOException {
        String currentDir=System.getProperty("user.dir");
        ProcessBuilder processBuilder=new ProcessBuilder(currentDir+"/start_dockergrid.sh");
       // Process p=processBuilder.start();
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //Initialize Config
        ConfigReader.PopulateSettings();

        //Logging
        Settings.Logs = new LogUtil();
        Settings.Logs.CreateLogFile();
        Settings.Logs.Write("Framework Initialize");

        //Create Test Cycle for Reporting
        Settings.ReportingConnection = DatabaseUtil.Open(Settings.ReportingConnectionString);
        ReportingUtil.CreateTestCycle(Settings.ReportingConnection);

        Settings.Logs.Write("Test Cycle Created");
        //InitializeBrowser(Settings.BrowserType);
        Settings.Logs.Write("Browser Initialized");
        //DriverContext.GoToUrl(Settings.AUT);
        Settings.Logs.Write("Navigated to URL " + Settings.AUT);

        try {
            ExcelUtil util = new ExcelUtil(Settings.ExcelSheetPath);
        } catch (Exception e) {
        }

    }


    public <TPage extends BasePage> TPage As(Class<TPage> pageInstance)
    {
        try
        {
            return (TPage)this;
        }
        catch (Exception e)
        {
            e.getStackTrace();
        }

        return null;
    }
@AfterMethod(alwaysRun = true)
    public void tearDown()
{
    if(LocalDriverContext.getRemoteWebDriver()!=null)
    {
        LocalDriverContext.getRemoteWebDriver().quit();
    }
    else {
        LocalDriverContext.getRemoteWebDriver().quit();
    }
}

//@AfterClass(alwaysRun = true)
    public void stopDocker() throws IOException {


    String currentDir=System.getProperty("user.dir");
    ProcessBuilder processBuilder=new ProcessBuilder(currentDir+"/stop_dockergrid.sh");
    Process p=processBuilder.start();
    try {
        Thread.sleep(8000);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
}

@AfterSuite(alwaysRun = true)
    public void afterSuite() throws IOException {
    ExtentReport.flushReports();
}



}