package demotest;

import com.ea.framework.base.*;
import com.ea.framework.config.ConfigReader;
import com.ea.framework.config.Settings;
import com.ea.framework.report.ExtentReport;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;

import java.io.IOException;


public class DemoTest extends BasePage {

	@BeforeMethod(alwaysRun = true )
	public void setUp() throws IOException {
		System.out.println("hiiiiii");
		FrameworkInitialize.InitializeBrowser(Settings.BrowserType);
		ConfigReader.PopulateSettings();
		ConfigReader configReader=new ConfigReader();
		DriverContext.GoToUrl(Settings.AUT);
		DriverContext.maximizeBrowser();
//        Settings.Logs.Write("Navigated to URL " + Settings.AUT);


	}
	@Test
	public void test1() throws InterruptedException {
        ExtentReport.createTest("login link clicl success");
		HomePage hp=new HomePage(LocalDriverContext.getRemoteWebDriver());
		hp.ClickLogin();

		Thread.sleep(12000);

	}
	@Test
	public void test2()
	{
		ExtentReport.createTest("login link clicl success for test2");
		HomePage hp=new HomePage(LocalDriverContext.getRemoteWebDriver());
		hp.ClickLogin();

	}



}
